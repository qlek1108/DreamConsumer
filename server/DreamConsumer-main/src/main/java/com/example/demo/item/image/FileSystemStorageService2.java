package com.example.item.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

@Slf4j
@Service
public class FileSystemStorageService2 implements StoreService2 {
//    private final Path rootLocation = Paths.get("/Users/jangtaehwan/file");
    private final Path rootLocation = Paths.get(System.getProperty("user.home"), "file");
    @Override
    public String store(MultipartFile file,String name) {
        String storedFilePath = null;
        try {
            if (file.isEmpty()) {
                throw new FileSystemNotFoundException("Failed to store empty file.");
            }
            String newDirectoryName = String.valueOf(name);
            Path newDirectoryPath = this.rootLocation.resolve(newDirectoryName);
            if (!Files.exists(newDirectoryPath)) {
                Files.createDirectories(newDirectoryPath);
            }
            Path destinationFile = newDirectoryPath.resolve(
                    Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(newDirectoryPath.toAbsolutePath())) {
                // This is a security check
                throw new FileSystemNotFoundException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                log.info("#stroed");
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                storedFilePath = destinationFile.toString();
            }
        } catch (IOException e) {
            throw new FileSystemNotFoundException("Failed to store file.");
        }
        return storedFilePath;
    }

}

