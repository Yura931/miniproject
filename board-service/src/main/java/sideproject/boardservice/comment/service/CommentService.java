package sideproject.boardservice.comment.service;

import sideproject.boardservice.comment.dto.DeleteCommentDto;
import sideproject.boardservice.comment.dto.RegisterCommentDto;
import sideproject.boardservice.comment.dto.UpdateCommentDto;
import sideproject.boardservice.comment.dto.response.RegisterCommentResponse;
import sideproject.boardservice.comment.dto.response.UpdateCommentResponse;

public interface CommentService {
    RegisterCommentResponse save(RegisterCommentDto dto);
    UpdateCommentResponse updateById(UpdateCommentDto dto);
    void deleteById(DeleteCommentDto dto);
}
