package subproject.admin.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import subproject.admin.board.dto.record.BoardCategoryDto;
import subproject.admin.board.dto.record.RegisterBoardDto;
import subproject.admin.board.dto.request.RegisterBoardRequest;
import subproject.admin.board.entity.enums.BoardType;
import subproject.admin.board.entity.enums.Enabled;
import subproject.admin.common.entity.BaseEntity;
import subproject.admin.common.enums.EnumDto;
import subproject.admin.post.entity.Post;

import java.beans.IntrospectionException;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "id", "boardEnabled", "boardVisible",
                "boardType", "boardTitle", "boardDescription", "boardCategoryEnabled",
                "boardFileEnabled", "boardCommentEnabled", "boardReplyCommentEnabled"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private Enabled boardEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardVisible;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    private String boardTitle;

    private String boardDescription;

    @Enumerated(EnumType.STRING)
    private Enabled boardCategoryEnabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardCategory> categories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Enabled boardFileEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardCommentEnabled;

    @Enumerated(EnumType.STRING)
    private Enabled boardReplyCommentEnabled;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

    private Board(Enabled boardEnabled, Enabled boardVisible, BoardType boardType, String boardTitle,
                  String boardDescription, Enabled boardCategoryEnabled, Enabled boardFileEnabled, Enabled boardCommentEnabled,
                  Enabled boardReplyCommentEnabled, List<BoardCategoryDto> categories) {
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        this.categories = BoardCategory.createCategories(this, categories);
    }

    public static Board createBoard(RegisterBoardDto bm) {
        return new Board(
                bm.boardEnabled(),
                bm.boardVisible(),
                bm.boardType(),
                bm.boardTitle(),
                bm.boardDescription(),
                bm.boardCategoryEnabled(),
                bm.boardFileEnabled(),
                bm.boardCommentEnabled(),
                bm.boardReplyCommentEnabled(),
                bm.categories()
        );
    }

    public Board updateBoard(Enabled boardEnabled, Enabled boardVisible, BoardType boardType,
                                  String boardTitle, String boardDescription, Enabled boardCategoryEnabled,
                                  Enabled boardFileEnabled, Enabled boardCommentEnabled, Enabled boardReplyCommentEnabled) {
        this.boardEnabled = boardEnabled;
        this.boardVisible = boardVisible;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardDescription = boardDescription;
        this.boardCategoryEnabled = boardCategoryEnabled;
        this.boardFileEnabled = boardFileEnabled;
        this.boardCommentEnabled = boardCommentEnabled;
        this.boardReplyCommentEnabled = boardReplyCommentEnabled;
        return this;
    }

    public Board addBoardCategory(BoardCategoryDto dto) {
        this.categories.add(BoardCategory.createCategory(this, dto));
        return this;
    }

    public Board deleteBoardCategory(UUID categoryId) {
        this.categories.removeIf(category -> category.getId().equals(categoryId));
        return this;
    }
    public static List<Map<String, List<EnumDto>>> getEnabled() throws IntrospectionException {
        Board board = new Board();
        Class<?> clazz = board.getClass();

        java.beans.BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(clazz);
        java.beans.PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        List<Map<String, List<EnumDto>>> maps = Arrays.stream(propertyDescriptors)
                .filter((property) -> Enabled.class.isAssignableFrom(property.getPropertyType()))
                .map((property) -> {
                    Map<String, List<EnumDto>> map = new HashMap<>();
                    map.put(property.getName(), Enabled.getEnabledList());
                    return map;
                })
                .toList();
        return maps;
    }
}
