package com.example.demouniclubBE.service;

import com.example.demouniclubBE.exception.FileNotFoundException;
import com.example.demouniclubBE.exception.SaveFileException;
import com.example.demouniclubBE.payload.request.InsertProductRequest;
import com.example.demouniclubBE.service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;

@Service
public class FileService implements FileServiceImp {

    @Value("${path.save-file}")
    private String pathFile;

    @Override
    public boolean saveFile(MultipartFile file) {
        Path root = Paths.get(pathFile);
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            Path destination = root.resolve(file.getOriginalFilename());

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            }

//            System.out.println("Lưu file thành công: " + file.getOriginalFilename());
            return true;
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file: " + e.getMessage());
            throw new SaveFileException(e.getMessage());
        } finally {
            System.gc();  // Giải phóng bộ nhớ
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            Path root = Paths.get(pathFile);
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("File không tìm thấy hoặc không đọc được.");
            }
        } catch (Exception e) {
            throw new FileNotFoundException(e.getMessage());
        } finally {
            System.gc(); // Giải phóng bộ nhớ và tài nguyên
        }
    }

}
