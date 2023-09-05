package com.giyong.community.service;

import com.giyong.community.StorageProperties;
import com.giyong.community.dto.BoardImagesDto;
import com.giyong.community.entity.BoardImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class BoardImagesServiceImpl implements BoardImagesService {
    private final Path rootLocation;
    @Autowired
    public BoardImagesServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public BoardImages upload(BoardImagesDto boardImagesDto) {
        return null;
    }

    @Override
    public void store(MultipartFile file) {
        System.out.println("BoardImagesServiceImpl.store");
        System.out.println("file = " + file);
        Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename()))
                .normalize().toAbsolutePath();
        System.out.println("destinationFile = " + destinationFile);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
