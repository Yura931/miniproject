package sideproject.fileservice.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.common.dto.FileDto;
import sideproject.fileservice.post.entity.PostFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileUploadUtil {
    @Value("${file.uploadPath}")
    private String storedPathString;
    public List<FileDto> uploadFileDto (List<MultipartFile> files) {
        if (Boolean.FALSE.equals(files.isEmpty())) {
            File saveFolder = new File(filePathBlackList(storedPathString));

            return files
                    .stream()
                    .filter(file -> Boolean.FALSE.equals(StringUtils.isEmpty(file.getOriginalFilename())))
                    .map((file) -> {
                        String originalFileName = file.getOriginalFilename();
                        String fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                        String newName = UUID.randomUUID().toString();
                        long size = file.getSize();

                        String filePath = storedPathString + File.separator + newName + "." + fileExt;
                        try {
                            if(!saveFolder.exists()) {
                                saveFolder.mkdirs();
                            }
                            File storedFile = new File(filePathBlackList(filePath));
                            file.transferTo(storedFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }

                        return FileDto.of(
                            originalFileName,
                            newName,
                            filePath,
                            Long.toString(size),
                            file.getContentType(),
                            fileExt, 0
                        );

                    })
                    .toList();

        }

        return new ArrayList<>();
    }

    public void deleteFiles(List<PostFile> files) {
        files.stream()
                .forEach(file -> {
                    String filePath = file.getFilePath();
                    File deleteFile = new File(filePath);
                    deleteFile.delete();
                });
    }

    private String filePathBlackList(String value) {
        if(Objects.isNull(value) || StringUtils.trim(value).isEmpty()) {
            return "";
        }

        return value.replaceAll("\\.\\.", "");
    }
}
