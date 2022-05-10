package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.models.FileInfo;

import javax.servlet.http.HttpServletResponse;


public interface FilesService {

    FileInfo upload(MultipartFile multipart);

    void addFileToResponse(String fileName, HttpServletResponse response);
}
