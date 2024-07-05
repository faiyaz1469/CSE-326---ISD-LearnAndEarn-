package com.nabil.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nabil.models.Blog;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    
}
