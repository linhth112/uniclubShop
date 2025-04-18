package com.example.demouniclubBE.service.Imp;

import com.example.demouniclubBE.payload.request.InsertProductRequest;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    boolean saveFile(MultipartFile file);
    Resource loadFile(String fileName);
}
