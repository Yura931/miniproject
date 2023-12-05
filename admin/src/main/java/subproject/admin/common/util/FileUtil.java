package subproject.admin.common.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import subproject.admin.common.dto.FileDto;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileUtil {

    public List<FileDto> uploadFileDto (MultipartHttpServletRequest request, String uploadPath) throws Exception {

        final Map<String, MultipartFile> files = request.getFileMap();
        final String storedPathString = "/" + uploadPath;

        if (Boolean.FALSE.equals(files.isEmpty())) {
            File saveFolder = new File(filePathBlackList(storedPathString));

            return files.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .filter(file -> Boolean.FALSE.equals(StringUtils.isEmpty(file.getOriginalFilename())))
                    .map((file) -> {
                        String originalFileName = file.getOriginalFilename();
                        int index = originalFileName.lastIndexOf(".");
                        String fileExt = originalFileName.substring(index + 1);

                        String newName = UUID.randomUUID().toString();
                        long size = file.getSize();

                        String filePath = storedPathString + File.pathSeparator + newName;
                        try {
                            file.transferTo(new File(filePathBlackList(filePath)));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        return FileDto.of(
                           originalFileName,
                           newName,
                           storedPathString,
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
