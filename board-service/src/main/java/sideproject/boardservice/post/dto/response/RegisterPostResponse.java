package sideproject.boardservice.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sideproject.boardservice.post.dto.item.RegisterPostItem;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPostResponse {
    private RegisterPostItem items;
}
