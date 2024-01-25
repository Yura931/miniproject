package sideproject.boardservice.post.dto.kafka;

import lombok.Getter;

import java.time.ZoneOffset;

@Getter
public class Payload {

    private String post_id;
    private Long board_id;
    private String post_title;
    private String post_content;
    private Long view_count;
    private Long create_date;
    private Long modified_date;
    private String created_by;
    private String last_modified_by;

    private Payload(String postId, Long boardId,
                    String postTitle, String postContent, Long viewCount,
                    Long createDate, Long modifiedDate, String createdBy, String lastModifiedBy
    ) {
        this.post_id = postId;
        this.board_id = boardId;
        this.post_title = postTitle;
        this.post_content = postContent;
        this.view_count = viewCount;
        this.create_date = createDate;
        this.modified_date = modifiedDate;
        this.created_by = createdBy;
        this.last_modified_by = lastModifiedBy;
    }

    public static Payload createPayload(KafkaRegisterPostDto dto) {
        return new Payload(
                dto.postId().toString(),
                dto.boardId(),
                dto.postTitle(),
                dto.postContent(),
                dto.viewCount(),
                dto.createDate().toInstant(ZoneOffset.UTC).toEpochMilli(),
                dto.modifiedDate().toInstant(ZoneOffset.UTC).toEpochMilli(),
                dto.createdBy(),
                dto.lastModifiedBy()
        );
    }
}
