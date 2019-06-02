package com.opteamix.training.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opteamix.training.domain.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
    
    public List<Review> findByProductId(@Param("pid") int pid);

}
