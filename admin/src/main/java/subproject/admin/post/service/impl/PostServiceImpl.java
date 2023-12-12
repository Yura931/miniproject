package subproject.admin.post.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import subproject.admin.post.dto.PostPageDto;
import subproject.admin.post.dto.RegisterPostDto;
import subproject.admin.post.dto.SearchPostDto;
import subproject.admin.post.dto.item.PostItem;
import subproject.admin.post.dto.response.PostPageResponse;
import subproject.admin.post.dto.response.RegisterPostResponse;
import subproject.admin.post.dto.response.SearchPostResponse;
import subproject.admin.post.entity.Post;
import subproject.admin.post.repository.PostRepository;
import subproject.admin.post.service.PostService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    @Override
    public RegisterPostResponse save(RegisterPostDto dto) {
        Post post = Post.createPost(dto, new ArrayList<>());
        Post savePost = postRepository.save(post);
        PostItem postItem = PostItem.PostEntityToDto(savePost);
        return null;
    }

    @Override
    public SearchPostResponse findById(SearchPostDto dto) {
        return null;
    }

    @Override
    public PostPageResponse findAllByBoard(PostPageDto dto) {
        return null;
    }
}
