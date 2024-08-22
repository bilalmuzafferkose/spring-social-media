package com.bilalkose.spring_social_media.repository;

import com.bilalkose.spring_social_media.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserIdOrderByIdDesc(Long userId);
    @Query("SELECT p FROM Post p WHERE p.id IN :postIds")
    List<Post> findAllByPostIds(@Param("postIds") List<Long> postIds);

    @Query("SELECT COUNT(l) > 0 FROM Like l WHERE l.user.id = :userId AND l.post.id = :postId")
    boolean existsLikeByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("SELECT COUNT(f) > 0 FROM Follow f WHERE f.user.id = :userId AND f.following.id = :ownerId")
    boolean existsFollowByUserIdAndOwnerId(@Param("userId") Long userId, @Param("ownerId") Long ownerId);
}
