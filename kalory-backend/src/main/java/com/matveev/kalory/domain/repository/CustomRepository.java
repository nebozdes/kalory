package com.matveev.kalory.domain.repository;

import com.matveev.kalory.error.EntityNotFoundException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CustomRepository<T> extends PagingAndSortingRepository<T, Long> {

    default T getById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }
}
