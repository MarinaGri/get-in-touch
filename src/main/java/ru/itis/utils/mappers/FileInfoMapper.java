package ru.itis.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.models.FileInfo;
import ru.itis.dto.response.FileLink;

@Mapper(componentModel = "spring")
public interface FileInfoMapper {

    @Mapping(target = "link", source = "storageFileName")
    FileLink toResponse(FileInfo fileInfo);

}
