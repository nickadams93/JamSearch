package com.jamsearch.auth.repository;

import com.jamsearch.auth.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	Post findByOwner();
}
