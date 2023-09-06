package com.giyong.community.service;

import com.giyong.community.StorageProperties;
import com.giyong.community.dto.BoardImagesDto;
import com.giyong.community.entity.BoardImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    public String store(MultipartFile file) {
        long nowTime = System.currentTimeMillis();
        String originFileName = String.valueOf(Paths.get(file.getOriginalFilename()));
        String ext = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        String storedFileName = String.valueOf(nowTime) + "." + ext;

        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
        String year = now.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = now.format(DateTimeFormatter.ofPattern("MM"));
        String day = now.format(DateTimeFormatter.ofPattern("dd"));
        String storedPath = year + "/" + month + "/" + day + "/";

        File filePath = new File(storedPath);

        if (!filePath.exists()) filePath.mkdirs();

        Path destinationFile = this.rootLocation.resolve(storedPath + storedFileName)
                .normalize().toAbsolutePath();
        System.out.println("destinationFile = " + destinationFile);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.valueOf(destinationFile);
    }
}
