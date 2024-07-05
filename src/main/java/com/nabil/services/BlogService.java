package com.nabil.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nabil.models.Blog;
import com.nabil.repositories.BlogRepository;

@Service
public class BlogService {
    
    @Autowired
    private BlogRepository blogRepository;   

    public List<Blog> getAllBlogs() {
        List<Blog> blogList = new ArrayList<>();
        blogRepository.findAll().forEach(blogList::add);
        return blogList;
    }

	public Blog getBlog(Long id) {
		return blogRepository.findById(id).get();
	}

    public void addBlog(Blog blog) {
        blogRepository.save(blog);
    }

	public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
	}

    public void updateBlog(Blog updateBlog, Long id) {
        Blog blog = blogRepository.findById(id).get();
        blog = updateBlog; 
        blogRepository.save(blog);
    }

	public void deleteAllBlogs() {
        blogRepository.deleteAll();
	}

}
