package com.example.demouniclubBE.service;

import com.example.demouniclubBE.dto.*;
import com.example.demouniclubBE.entity.*;
import com.example.demouniclubBE.entity.key.ProductDetailID;
import com.example.demouniclubBE.exception.FileNotFoundException;
import com.example.demouniclubBE.exception.InsertProductException;
import com.example.demouniclubBE.payload.request.InsertProductDetailRequest;
import com.example.demouniclubBE.payload.request.InsertProductRequest;
import com.example.demouniclubBE.repository.*;
import com.example.demouniclubBE.service.Imp.FileServiceImp;
import com.example.demouniclubBE.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    @Override
    public boolean insertProduct(InsertProductRequest request) {
        try {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setName(request.getName());
                productEntity.setPrice(request.getPrice());
                productEntity.setDescription(request.getDescription());

                CategoryEntity category = categoryRepository.findByName(request.getCategory().toLowerCase().trim());
                if (category != null) {
                    productEntity.setCategory(category);
                } else {
                    CategoryEntity categoryEntity = new CategoryEntity();
                    categoryEntity.setName(request.getCategory());
                    categoryRepository.save(categoryEntity);
                    productEntity.setCategory(categoryEntity);
                }

                productRepository.save(productEntity);

                return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> list = productRepository.findAll();
        List<ProductDTO> listDTO = new ArrayList<>();

        list.forEach(item -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(item.getName());
            productDTO.setPrice(item.getPrice());
            productDTO.setIdProduct(item.getId());
            productDTO.setSku(item.getSku());
            productDTO.setDescription(item.getDescription());
            productDTO.setCategory(item.getCategory().getName());
            productDTO.setIdCategory(item.getCategory().getId());

            List<ProductDetailEntity> productDetailEntityList = item.getProductDetails();
            int totalQuantity = productDetailEntityList == null
                    ? 0
                    : productDetailEntityList.stream().mapToInt(ProductDetailEntity::getQuantity).sum();
            productDTO.setQuantity(totalQuantity);

            List<ProductImageEntity> listImage = item.getProductImages();
            List<String> listImageDTO = new ArrayList<>();
            listImage.forEach(itemImg -> {
                String image = "http://localhost:8080/file/" + itemImg.getName();
                listImageDTO.add(image);
            });

            productDTO.setImages(listImageDTO);
            listDTO.add(productDTO);
        });

        return listDTO;
    }


    @Override
    public SingleProductDTO getSingleProduct(int id) {
        try {
            ProductEntity productEntity = productRepository.findById(id);
            SingleProductDTO singleProductDTO = new SingleProductDTO();

            singleProductDTO.setName(productEntity.getName());
            singleProductDTO.setPrice(productEntity.getPrice());
            singleProductDTO.setDescription(productEntity.getDescription());

            List<ProductImageEntity> imageEntityList = productEntity.getProductImages();
            List<ImagesDTO> listImageDTO = new ArrayList<>();
            imageEntityList.forEach(item -> {
                ImagesDTO imagesDTO = new ImagesDTO();
                String image = "http://localhost:8080/file/" + item.getName();
                imagesDTO.setImageName(image);
                imagesDTO.setIdColor(item.getColor().getId());
                listImageDTO.add(imagesDTO);
            });
            singleProductDTO.setImages(listImageDTO);

            List<ProductDetailEntity> productDetailEntityList = productEntity.getProductDetails();
            Set<ColorList> setColorDTO = new HashSet<>();
            Set<SizeList> setSizeDTO = new HashSet<>();

            List<ProductDetailDTO> productDetailDTOList = new ArrayList<>();

            productDetailEntityList.forEach(item -> {
                ProductDetailDTO productDetailDTO = new ProductDetailDTO();
                ColorList colorList = new ColorList();
                SizeList sizeList = new SizeList();

                String colorName = item.getColor().getName().toUpperCase();
                int idColor = item.getColor().getId();
                colorList.setColorName(colorName);
                colorList.setIdColor(idColor);
                setColorDTO.add(colorList);

                String sizeName = item.getSize().getName().toUpperCase();
                int idSize = item.getSize().getId();
                sizeList.setSizeName(sizeName);
                sizeList.setIdSize(idSize);
                setSizeDTO.add(sizeList);

                productDetailDTO.setIdProduct(item.getProduct().getId());
                productDetailDTO.setIdColor(item.getColor().getId());
                productDetailDTO.setIdSize(item.getSize().getId());
                productDetailDTO.setQuantity(item.getQuantity());
                productDetailDTO.setPrice(item.getPrice());

                productDetailDTOList.add(productDetailDTO);
            });

            List<ColorList> listColorDTO = new ArrayList<>(setColorDTO);
            List<SizeList> listSizeDTO = new ArrayList<>(setSizeDTO);

            singleProductDTO.setSizeList(listSizeDTO);
            singleProductDTO.setColorList(listColorDTO);
            singleProductDTO.setProductDetailS(productDetailDTOList);

            return singleProductDTO;
        } catch (Exception e)  {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductDTO> getProductDetail(int id) {
        try {
            List<ProductDTO> productDTOList = new ArrayList<>();
            productDetailRepository.findByProductId(id).forEach(item -> {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setColorName(item.getColor().getName());
                productDTO.setSizeName(item.getSize().getName());
                productDTO.setQuantity(item.getQuantity());
                productDTO.setPrice(item.getPrice());
                productDTO.setIdSize(item.getSize().getId());
                productDTO.setIdColor(item.getColor().getId());
                productDTO.setName(item.getProduct().getName());

                productDTOList.add(productDTO);
            });

            return productDTOList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public boolean insertProductDetail(InsertProductDetailRequest request) {
        try {
            boolean isSuccess = true;
            for (MultipartFile file : request.getFile()) {

                if (!fileServiceImp.saveFile(file)) {
                    System.out.println("lưu file thất bại");
                    isSuccess = false;
                    break;
                }
            }

            if (isSuccess) {
                int idProduct = request.getIdProduct();
                String size = request.getSize().toUpperCase();
                String color = request.getColor().toUpperCase();
                int quantity = request.getQuantity();
                double price = request.getPrice();
                int idColor;
                int idSize;

                ColorEntity getColor = colorRepository.findByName(color);
                if (getColor != null) {
                    idColor = getColor.getId();
                    System.out.println(idColor);
                } else {
                    ColorEntity colorEntity = new ColorEntity();
                    colorEntity.setName(color);
                    ColorEntity newColorEntity = colorRepository.save(colorEntity);
                    idColor = newColorEntity.getId();
                }

                SizeEntity getSize = sizeRepository.findByName(size);
                if (getSize != null) {
                    idSize = getSize.getId();
                    System.out.println(idColor);
                } else {
                    SizeEntity sizeEntity = new SizeEntity();
                    sizeEntity.setName(size);
                    SizeEntity newSizeEntity = sizeRepository.save(sizeEntity);
                    idSize = newSizeEntity.getId();
                }

                ProductDetailEntity productDetailEntity = productDetailRepository.findByProductIdAndColorIdAndSizeId(idProduct,idColor,idSize);
//                System.out.println("kiểm tra" + productDetailEntity);
                if (productDetailEntity == null) {
                    ProductDetailEntity productDetail = new ProductDetailEntity();

                    ProductDetailID productDetailID = new ProductDetailID();
                    productDetailID.setIdProduct(idProduct);
                    productDetailID.setIdColor(idColor);
                    productDetailID.setIdSize(idSize);

                    productDetail.setId(productDetailID);
                    productDetail.setQuantity(quantity);
                    productDetail.setPrice(price);

                    productDetailRepository.save(productDetail);
//                    System.out.println("lưu xong rồi");

                    for (MultipartFile multipartFile : request.getFile()) {
                        ProductImageEntity productImageEntities = productImageRepository.findByProductIdAndNameAndColorId(idProduct,multipartFile.getOriginalFilename(),idColor);
                        System.out.println("kiem tra image " + multipartFile.getOriginalFilename());
                        if (productImageEntities == null) {
                            ProductImageEntity productImageEntity = new ProductImageEntity();
                            productImageEntity.setName(multipartFile.getOriginalFilename());
                            productImageEntity.setProduct(productRepository.findById(idProduct));
                            ColorEntity colorEntity = new ColorEntity();
                            colorEntity.setId(idColor);
                            productImageEntity.setColor(colorEntity);
                            productImageRepository.save(productImageEntity);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateProduct(InsertProductRequest request) {
        try {
            int idProduct = request.getIdProduct();
            String name = request.getName();
            double price = request.getPrice();
            String description = request.getDescription();
            String category = request.getCategory().toLowerCase().trim();

            ProductEntity productEntity = productRepository.findById(idProduct);
            if (productEntity == null) {
                System.out.println("Product item not found for the given parameters");
                return false;
            }

            productEntity.setName(name);
            productEntity.setPrice(price);
            productEntity.setDescription(description);

            CategoryEntity categoryEntity = categoryRepository.findByName(category);
            if (categoryEntity != null) {
                productEntity.setCategory(categoryEntity);
            } else {
                CategoryEntity categoryEntityNew = new CategoryEntity();
                categoryEntityNew.setName(category);
                categoryRepository.save(categoryEntityNew);
                productEntity.setCategory(categoryEntityNew);
            }

            productRepository.save(productEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
