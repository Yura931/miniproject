package sideproject.boardservice.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sideproject.boardservice.comment.entity.Comment;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}
