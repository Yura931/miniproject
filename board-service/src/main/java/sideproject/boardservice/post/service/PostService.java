package sideproject.boardservice.post.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sideproject.boardservice.post.dto.RegisterPostDto;
import sideproject.boardservice.post.dto.SearchPostDto;
import sideproject.boardservice.post.dto.SelectPostDto;
import sideproject.boardservice.post.dto.response.RegisterPostResponse;
import sideproject.boardservice.post.dto.response.SearchPostResponse;
import sideproject.boardservice.post.dto.response.SelectPostResponse;


public interface PostService {
    RegisterPostResponse save(RegisterPostDto dto, MultipartHttpServletRequest request) throws JsonProcessingException;
    @EntityGraph("boardCategory")
    SelectPostResponse findById(SelectPostDto dto);
    SearchPostResponse findAll(SearchPostDto dto);
}
