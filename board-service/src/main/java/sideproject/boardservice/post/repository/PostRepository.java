package sideproject.boardservice.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import sideproject.boardservice.post.entity.Post;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID>, QuerydslPredicateExecutor {

}
