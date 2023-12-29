package sideproject.fileservice.file.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.fileservice.file.dto.FileDto;


import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Util {

    private final AmazonS3 amazonS3Client;
    private final Map<String, String> buketInfo;

    public List<FileDto> uploadFileDto(MultipartHttpServletRequest request) {

        final Map<String, MultipartFile> files = request.getFileMap();

        if (Boolean.FALSE.equals(files.isEmpty())) {

            String uploadFilePath = "bbs" +  "/" + getFolderName();
            return files.entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .filter(file -> Boolean.FALSE.equals(StringUtils.isEmpty(file.getOriginalFilename())))
                    .map((file) -> {
                        String originalFileName = file.getOriginalFilename();
                        int index = originalFileName.lastIndexOf(".");
                        String fileExt = originalFileName.substring(index + 1);

                        long size = file.getSize();

                        ObjectMetadata objectMetadata = new ObjectMetadata();
                        objectMetadata.setContentLength(file.getSize());
                        objectMetadata.setContentType(file.getContentType());

                        String keyName = uploadFilePath + "/" + getUuidFileName(originalFileName);

                        try {
/*                            amazonS3Client.putObject(
                                    new PutObjectRequest(buketInfo.get("bucket"), getFolderName(),file.getInputStream(), objectMetadata)
                            );*/

                            // 외부에 공개하는 파일인 경우 Public Read 권한을 추가, ACL 확인
                            /*amazonS3Client.putObject(
                                    new PutObjectRequest(buketInfo.get("bucket"), getFolderName(), file.getInputStream(), objectMetadata)
                                            .withCannedAcl(CannedAccessControlList.PublicRead)
                            );*/
                            String uploadFileUrl  = amazonS3Client.getUrl(buketInfo.get("bucket"), keyName).toString();

                            return FileDto.of(
                                    originalFileName,
                                    keyName,
                                    uploadFileUrl,
                                    Long.toString(size),
                                    file.getContentType(),
                                    fileExt, 0
                            );

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
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

    private String getUuidFileName(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    private String getFolderName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str;
    }
}
