package sideproject.fileservice.post.service;

import org.springframework.web.multipart.MultipartFile;
import sideproject.fileservice.post.dto.PostFileDto;
import sideproject.fileservice.post.dto.response.RegisterPostFileResponse;

import java.util.List;
import java.util.UUID;

public interface PostFileService {
    RegisterPostFileResponse save(List<MultipartFile> files, UUID postId);

}
