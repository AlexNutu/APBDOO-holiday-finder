package com.holiday.finder.repository;

import com.holiday.finder.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment getById(Long id);

    Comment getByDescription(String description);
}
