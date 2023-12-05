package subproject.admin.post.service;

import subproject.admin.board.entity.Board;
import subproject.admin.post.dto.PostPageDto;
import subproject.admin.post.dto.RegisterPostDto;
import subproject.admin.post.dto.SearchPostDto;
import subproject.admin.post.dto.response.PostPageResponse;
import subproject.admin.post.dto.response.RegisterPostResponse;
import subproject.admin.post.dto.response.SearchPostResponse;

public interface PostService {
    RegisterPostResponse save(RegisterPostDto dto);
    SearchPostResponse findById(SearchPostDto dto);
    PostPageResponse findAllByBoard(PostPageDto dto);
}
