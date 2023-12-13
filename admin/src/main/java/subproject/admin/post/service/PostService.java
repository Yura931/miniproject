package subproject.admin.post.service;

import subproject.admin.post.dto.record.PostPageDto;
import subproject.admin.post.dto.record.RegisterPostDto;
import subproject.admin.post.dto.record.SearchPostDto;
import subproject.admin.post.dto.response.PostPageResponse;
import subproject.admin.post.dto.response.RegisterPostResponse;
import subproject.admin.post.dto.response.SearchPostResponse;

public interface PostService {
    RegisterPostResponse save(RegisterPostDto dto);
    SearchPostResponse findById(SearchPostDto dto);
    PostPageResponse findAllByBoard(PostPageDto dto);
}
