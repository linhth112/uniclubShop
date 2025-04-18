package com.example.demouniclubBE.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileNotFoundException extends RuntimeException {
    private String message;
}
