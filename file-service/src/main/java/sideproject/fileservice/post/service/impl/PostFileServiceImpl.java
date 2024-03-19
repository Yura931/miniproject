package sideproject.fileservice.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.common.dto.FileDto;
import sideproject.fileservice.post.dto.PostFileDto;
import sideproject.fileservice.post.dto.item.RegisterPostFileItem;
import sideproject.fileservice.post.dto.response.RegisterPostFileResponse;
import sideproject.fileservice.post.entity.PostFile;
import sideproject.fileservice.post.repository.PostFileRepository;
import sideproject.fileservice.post.service.PostFileService;
import sideproject.fileservice.common.util.FileUploadUtil;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostFileServiceImpl implements PostFileService {

    private final PostFileRepository postFileRepository;
    private final FileUploadUtil fileUtil;

    @Transactional
    public RegisterPostFileResponse save(List<MultipartFile> files, UUID postId) {
        List<FileDto> fileListDto = fileUtil.uploadFileDto(files);
        List<PostFileDto> postFileListDto = fileListDto.stream()
                .map(dto -> PostFileDto.of(dto, postId))
                .toList();
        List<PostFile> postFiles = PostFile.createFiles(postFileListDto);
        List<PostFile> saveFiles = postFileRepository.saveAll(postFiles);
        return new RegisterPostFileResponse(RegisterPostFileItem.fileEntityToDto(saveFiles));
    }

}
