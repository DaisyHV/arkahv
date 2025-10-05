package com.arka.arkahv.domain.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface UploadProductImageUseCase {

    String uploadProductImage(MultipartFile image);
}
