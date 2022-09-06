package com.bobo.dao;

import com.bobo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByForeignId(Integer foreignId);

}
