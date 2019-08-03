package com.jamsearch.auth.repository;

import com.jamsearch.auth.model.Post;
import com.jamsearch.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	Post findByOwner(User user);
}
