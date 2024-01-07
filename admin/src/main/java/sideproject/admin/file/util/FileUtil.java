package sideproject.admin.file.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.admin.file.dto.FileDto;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class FileUtil {
    @Value("${file.uploadPath}")
    String storedPathString;

    public List<FileDto> uploadFileDto (MultipartHttpServletRequest request) throws Exception {
        final Map<String, MultipartFile> files = request.getFileMap();

        if (Boolean.FALSE.equals(files.isEmpty())) {
            File saveFolder = new File(filePathBlackList(storedPathString));

            return files.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
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

    private String filePathBlackList(String value) {
        if(Objects.isNull(value) || StringUtils.trim(value).isEmpty()) {
            return "";
        }

        return value.replaceAll("\\.\\.", "");
    }
}
