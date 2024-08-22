package com.bilalkose.spring_social_media.repository;

import com.bilalkose.spring_social_media.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByPostId(Long postId);
}
