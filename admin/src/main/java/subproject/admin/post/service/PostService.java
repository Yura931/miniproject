package subproject.admin.post.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.dto.record.SearchPostDto;
import subproject.admin.post.dto.record.SelectPostDto;
import subproject.admin.post.dto.response.PostPageResponse;
import subproject.admin.post.dto.response.RegisterPostResponse;
import subproject.admin.post.dto.response.SearchPostResponse;
import subproject.admin.post.dto.response.SelectPostResponse;
import subproject.admin.post.projection.SearchPostPageDto;

public interface PostService {
    RegisterPostResponse save(RegisterPostDto dto);
    @EntityGraph("boardCategory")
    SelectPostResponse findById(SelectPostDto dto);
    SearchPostResponse findAll(SearchPostDto dto);
}
