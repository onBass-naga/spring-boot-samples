package com.example.app.comment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by naga on 2015/01/29.
 */
interface CommentRepository extends JpaRepository<Comment, Long> {
}