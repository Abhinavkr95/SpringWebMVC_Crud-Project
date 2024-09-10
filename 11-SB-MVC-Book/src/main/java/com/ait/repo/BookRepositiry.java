package com.ait.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.entitiy.Book;

//Creating JPA Repository  to Map into Database and perform crud Operations
public interface BookRepositiry extends JpaRepository<Book, Integer> {

	public List<Book> findByActiveSW(String status);
}
