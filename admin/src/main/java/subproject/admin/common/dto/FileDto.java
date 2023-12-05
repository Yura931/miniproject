package subproject.admin.common.dto;


public record FileDto (
        String originalFilename,
        String storedFilename,
        String filePath,
        String fileSize,

        String fileContentType,
        String fileExt,
        Integer downloadCount
) {

    public static FileDto of(String originalFilename, String storedFilename,
                             String filePath, String fileSize, String fileContentType,
                             String fileExt, Integer downloadCount) {
        return new FileDto(
                originalFilename,
                storedFilename,
                filePath,
                fileSize,
                fileContentType,
                fileExt,
                downloadCount
        );
    }
}
