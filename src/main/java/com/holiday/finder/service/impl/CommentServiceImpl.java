package com.holiday.finder.service.impl;

import com.holiday.finder.model.Comment;
import com.holiday.finder.repository.CommentRepository;
import com.holiday.finder.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
}
