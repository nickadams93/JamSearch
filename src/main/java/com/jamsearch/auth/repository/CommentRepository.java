package com.jamsearch.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamsearch.auth.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
