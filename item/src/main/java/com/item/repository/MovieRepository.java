package com.item.repository;

import com.common.domain.Item.Book;
import com.common.domain.Item.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
