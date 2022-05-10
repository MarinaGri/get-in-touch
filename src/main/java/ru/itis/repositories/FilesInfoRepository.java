package ru.itis.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.models.FileInfo;

import java.util.Optional;

public interface FilesInfoRepository extends CrudRepository<FileInfo, Long> {

    Optional<FileInfo> findByStorageFileName(String name);
}
