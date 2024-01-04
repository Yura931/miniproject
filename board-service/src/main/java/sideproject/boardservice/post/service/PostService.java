package sideproject.boardservice.post.service;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.post.dto.*;
import sideproject.boardservice.post.dto.response.RegisterPostResponse;
import sideproject.boardservice.post.dto.response.SearchPostResponse;
import sideproject.boardservice.post.dto.response.SelectPostResponse;
import sideproject.boardservice.post.dto.response.UpdatePostResponse;


public interface PostService {
    RegisterPostResponse save(RegisterPostDto dto, MultipartHttpServletRequest request);
    @EntityGraph("boardCategory")
    SelectPostResponse selectPost(SelectPostDto dto);
    SearchPostResponse findAll(SearchPostDto dto);
    UpdatePostResponse updatePost(UpdatePostDto dto, MultipartHttpServletRequest request, UserDetails userDetails);
    void deletePost(DeletePostDto dto, UserDetails userDetails);
}
