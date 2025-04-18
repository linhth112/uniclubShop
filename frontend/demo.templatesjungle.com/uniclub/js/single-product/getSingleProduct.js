$(document).ready(function () {
  var url = window.location.href;

  // Tìm kiếm giá trị của tham số 'id' trong URL
  var urlParams = new URLSearchParams(window.location.search);
  var idUrl = urlParams.get("id");

  // console.log(id);
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/product/get-single-product",
    data: { id: idUrl },
  }).done(function (result) {
    if (result.data) {
      var data = result.data;
      console.log(data);

      var htmlImageThumbnailProduct = "";
      for (i = 0; i < data.images.length; i++) {
        htmlImageThumbnailProduct += `<div class="swiper-slide">
                    <img src="${data.images[i].imageName}" alt="" class="thumb-image img-fluid">
                  </div>`;
      }
      // console.log(htmlImageThumbnailProduct);

      var htmlImageLargeProduct = "";
      for (i = 0; i < data.images.length; i++) {
        htmlImageLargeProduct += `<div class="swiper-slide">
                    <img src="${data.images[i].imageName}" alt="product-large" class="w-100 h-100 object-fit-cover">
                  </div>`;
      }

      var htmlColor = "";
      for (let i = 0; i < data.colorList.length; i++) {
        htmlColor += `<li class="select-item pe-3" data-val="${data.colorList[i].idColor}" title="${data.colorList[i].colorName}">
                    <button type="button" class="btn btn-light btn-color fs-6 border border-dark" >${data.colorList[i].colorName}</button>
                    </li>`;
        
      }

      const sizeOrder = ["S", "M", "L", "XL", "XXL", "XXXL", "XXXXL", "XXXXXL"];

      data.sizeList.sort((a, b) => {
        return sizeOrder.indexOf(a.sizeName) - sizeOrder.indexOf(b.sizeName);
      });

      var htmlSize = "";
      for (let i = 0; i < data.sizeList.length; i++) {
        htmlSize += `<li data-val="${data.sizeList[i].idSize}" class="select-item pe-3">
                    <button type="button" class="btn btn-light fs-6 border border-dark">${data.sizeList[i].sizeName}</button>
                  </li>`;
        
      }

      var html = `<div class="container my-md-5 py-5">
                    <div class="row g-md-5">
                      <div class="col-lg-6">
                        <div class="row g-1">
                          <div class="col-md-3 d-none d-md-flex">
                            <!-- product-thumbnail-slider -->
                            <div class="swiper product-thumbnail-slider" id="product-thumbnail-slider">
                              <div class="swiper-wrapper" id="thumbnail-product">
                              ${htmlImageThumbnailProduct}
                              </div>
                            </div>
                            <!-- / product-thumbnail-slider -->
                          </div>
                          <div class="col-md-9">
                            <!-- product-large-slider -->
                            <div class="swiper product-large-slider ratio ratio-1x1">
                              <div class="swiper-wrapper" id="large-product">
                                ${htmlImageLargeProduct}
                              </div>
                            </div>
                            <!-- / product-large-slider -->
                          </div>
                        </div>

                      </div>
                      <div class="col-lg-6 mt-5 ">
                        <div class="product-info">
                          <div class="element-header">
                            <h2 itemprop="name" class="display-6 fw-bold">${data.name}</h2>
                            <div class="rating-container d-flex gap-0 align-items-center">
                              <span class="rating secondary-font">
                                <iconify-icon icon="clarity:star-solid" class="text-primary"></iconify-icon>
                                <iconify-icon icon="clarity:star-solid" class="text-primary"></iconify-icon>
                                <iconify-icon icon="clarity:star-solid" class="text-primary"></iconify-icon>
                                <iconify-icon icon="clarity:star-solid" class="text-primary"></iconify-icon>
                                <iconify-icon icon="clarity:star-solid" class="text-primary"></iconify-icon>
                                5.0</span>
                            </div>
                          </div>
                          <div class="product-price pt-3 pb-3">
                            <strong id="price-html" class="text-primary display-6 ">$ ${data.price}</strong>
                          </div>
                          <div> 
                          <p style="display: inline;">Status: </p> <p status="" style="display: inline;" id="status-product"></p>
                          </div>
                          <p>${data.description}</p>
                          <div class="cart-wrap">
                            <div class="color-options product-select">
                              <div class="color-toggle pt-2" data-option-index="0">
                                <h6 class="item-title fw-bold">Color:</h6>
                                <ul id="list-color" class="select-list list-unstyled d-flex">
                                  ${htmlColor}
                                </ul>
                              </div>
                            </div>
                            <div class="swatch product-select pt-3" data-option-index="1">
                              <h6 class="item-title fw-bold">Size:</h6>
                              <ul id="list-size" class="select-list list-unstyled d-flex">
                                ${htmlSize}
                              </ul>
                            </div>
                            <div class="product-quantity pt-2">
                              <div class="stock-number text-dark"><em>2 in stock</em></div>
                              <div class="stock-button-wrap">
                                <div class="d-flex flex-wrap">
                                  <div class="input-group product-qty align-items-center w-25 me-3">
                                    <span class="input-group-btn">
                                      <button type="button" class="quantity-left-minus btn btn-light btn-number" data-type="minus">
                                        <svg width="16" height="16">
                                          <use xlink:href="#minus"></use>
                                        </svg>
                                      </button>
                                    </span>
                                    <input type="text" id="quantity" name="quantity"
                                      class="form-control input-number text-center p-2 mx-1" value="1">
                                    <span class="input-group-btn">
                                      <button type="button" class="quantity-right-plus btn btn-light btn-number" data-type="plus"
                                        data-field="">
                                        <svg width="16" height="16">
                                          <use xlink:href="#plus"></use>
                                        </svg>
                                      </button>
                                    </span>
                                  </div>
                                  <button type="button" id="btn-add-to-cart" class="btn btn-outline-dark me-3 px-4 pt-3 pb-3" fdprocessedid="0aw8a">Add to Car</button>
                                  <a href="#" class="btn-wishlist px-4 pt-3 ">
                                    <iconify-icon icon="fluent:heart-28-filled" class="fs-5"></iconify-icon>
                                  </a>
                                </div>

                              </div>
                            </div>
                          </div>
                          <div class="meta-product pt-4">
                            <div class="meta-item d-flex align-items-baseline">
                              <h6 class="item-title fw-bold no-margin pe-2">SKU:</h6>
                              <ul class="select-list list-unstyled d-flex">
                                <li data-value="S" class="select-item">1223</li>
                              </ul>
                            </div>
                            <div class="meta-item d-flex align-items-baseline">
                              <h6 class="item-title fw-bold no-margin pe-2">Category:</h6>
                              <ul class="select-list list-unstyled d-flex">
                                <li data-value="S" class="select-item">
                                  <a href="#">T-shirt</a>,
                                </li>
                                <li data-value="S" class="select-item">
                                  <a href="#">Hoodies</a>
                                </li>
                              </ul>
                            </div>
                            <div class="meta-item d-flex align-items-baseline">
                              <h6 class="item-title fw-bold no-margin pe-2">Tags:</h6>
                              <ul class="select-list list-unstyled d-flex">
                                <li data-value="S" class="select-item">
                                  <a href="#">Clothes</a>,
                                </li>
                                <li data-value="S" class="select-item">
                                  <a href="#">Cotton</a>
                                </li>
                              </ul>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>`;

      var htmlscript = `<script src="js/jquery-1.11.0.min.js"></script>
                        <script src="../../jsdelivr/npm/swiper%409/swiper-bundle.min.js"></script>
                        
                        <script src="js/plugins.js"></script>
                        <script src="js/script.js"></script>
                        <script src="../../iconify/iconify-icon/1.0.7/iconify-icon.min.js"></script>`;

      $("#selling-product").append(html);
      $("body").append(htmlscript);

      let colorVal;
      let sizeVal;
      // Hiển thị image large-product theo click colorcolor
      $("#selling-product #list-color .select-item button").click(function() {
        colorVal = $(this).closest("li").data("val");
        // console.log(colorVal);
        function getImageByIdColor(idColor) {
          const result = data.images.find(item => item.idColor === idColor);
          return result.imageName;
        }

        const imageName = getImageByIdColor(colorVal);
        // console.log(imageName);
        
        const newImageHtml = `
                              <div class="swiper-slide">
                                <img src="${imageName}" alt="Product Image" class="img-fluid">
                              </div>`;
        $("#large-product").html(newImageHtml); 

        if (colorVal && sizeVal) {
          const result = checkExistence(sizeVal, colorVal);
          if (result != null) {
            $("#status-product").html("In stock");
            $("#status-product").attr("status", "1")
            $("#price-html").html(`$ ${result}`);
          } else {
            $("#status-product").html("Out of stock");
            $("#status-product").attr("status", "0")
            $("#price-html").html(`$ ${data.price}`);
          }
        }
      })

      $("#selling-product #list-size .select-item button").click(function() {
        sizeVal = $(this).closest("li").data("val");
        // console.log(sizeVal);
        if (colorVal && sizeVal) {
          const result = checkExistence(sizeVal, colorVal);
          if (result != null) {
            $("#status-product").html("In stock");
            $("#status-product").attr("status", "1")
            $("#price-html").html(`$ ${result}`);
          } else {
            $("#status-product").html("Out of stock");
            $("#status-product").attr("status", "0")
            $("#price-html").html(`$ ${data.price}`);
          }
        }
      })

        function checkExistence(idSize, idColor) {
          const product = data.productDetailS.find(product => product.idSize === idSize && product.idColor === idColor);
          if (product) {
            return product.price; // Trả về giá của sản phẩm
          }
          
          // Nếu không tìm thấy sản phẩm, trả về null hoặc giá mặc định
          return null; 
        }
      
    }
  });
});
