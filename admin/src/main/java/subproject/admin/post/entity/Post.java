package subproject.admin.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import subproject.admin.board.entity.Board;
import subproject.admin.board.entity.BoardCategory;
import subproject.admin.common.entity.BaseEntity;
import subproject.admin.file.dto.FileDto;
import subproject.admin.file.entity.File;
import subproject.admin.post.dto.record.RegisterPostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "postTitle", "postContent"})
public class Post extends BaseEntity {

    @Id
    @Column(name = "post_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_category_id")
    private BoardCategory boardCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<File> files = new ArrayList<>();

    private String postTitle;

    private String postContent;

    private Post(UUID id, Board board, BoardCategory boardCategory, String postTitle, String postContent, List<FileDto> fileDtos) {
        this.id = id;
        this.board = board;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.boardCategory = boardCategory;
    }

    public static Post createPost(RegisterPostDto dto, List<FileDto> fileDtos) {
        UUID id = UUID.randomUUID();
        return new Post(
                id,
                dto.board(),
                dto.boardCategory(),
                dto.postTitle(),
                dto.postContent(),
                fileDtos
        );
    }


}
