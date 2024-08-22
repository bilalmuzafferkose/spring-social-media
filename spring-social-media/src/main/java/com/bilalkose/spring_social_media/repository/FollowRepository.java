package com.bilalkose.spring_social_media.repository;

import com.bilalkose.spring_social_media.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByUserIdAndFollowingId(Long userId, Long followingId);
}
