package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.exceptions.EntityNotFoundException;
import ru.itis.exceptions.FileStorageException;
import ru.itis.services.FilesService;
import ru.itis.models.FileInfo;
import ru.itis.repositories.FilesInfoRepository;


import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {

    @Value("${files.storage.path}")
    private String storagePath;

    private final FilesInfoRepository filesInfoRepository;

    @Transactional
    @Override
    public FileInfo upload(MultipartFile multipart) {
        try {
            String extension = FilenameUtils.getExtension(multipart.getOriginalFilename());

            String storageFileName = UUID.randomUUID() + "." + extension;

            FileInfo file = FileInfo.builder()
                    .type(multipart.getContentType())
                    .originalFileName(multipart.getOriginalFilename())
                    .storageFileName(storageFileName)
                    .size(multipart.getSize())
                    .build();

            Files.copy(multipart.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));

            return filesInfoRepository.save(file);
        } catch (IOException ex) {
            throw new FileStorageException("Cant save file", ex);
        }
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = filesInfoRepository.findByStorageFileName(fileName)
                .orElseThrow((Supplier<RuntimeException>) () ->
                        new EntityNotFoundException("File",
                                Collections.singletonMap("file's name", fileName))
                );

        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalFileName() + "\"");
        try {
            String path = storagePath + "\\" + file.getStorageFileName();
            IOUtils.copy(new FileInputStream(path), response.getOutputStream());
            response.flushBuffer();

        } catch (IOException ex) {
            throw new FileStorageException("Cant read file", ex);
        }
    }

}
