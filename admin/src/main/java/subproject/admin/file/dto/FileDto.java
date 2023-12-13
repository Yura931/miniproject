package subproject.admin.file.dto;


public record FileDto (
        // 원본 명
        String originalFilename,

        // 저장 명
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
