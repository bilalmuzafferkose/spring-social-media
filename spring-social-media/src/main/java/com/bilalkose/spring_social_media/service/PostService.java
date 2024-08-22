package com.bilalkose.spring_social_media.service;

import com.bilalkose.spring_social_media.dto.UserDto;
import com.bilalkose.spring_social_media.dto.request.CreatePostRequest;
import com.bilalkose.spring_social_media.dto.PostDto;
import com.bilalkose.spring_social_media.exception.PostNotFoundException;
import com.bilalkose.spring_social_media.model.Post;
import com.bilalkose.spring_social_media.model.User;
import com.bilalkose.spring_social_media.model.mapper.PostMapper;
import com.bilalkose.spring_social_media.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserService userService;

    public PostService(PostRepository postRepository, PostMapper postMapper, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.postMapper = postMapper;
    }

    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return postList.stream()
                .map(postMapper::toDtoWithUser)
                .collect(Collectors.toList());
    }

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(
                        () -> new PostNotFoundException("Post could not find by id: " + id)
                );
        return this.postMapper.toDtoWithUser(post);
    }

    public List<PostDto> getAllByUser(Long userId) {
        List<Post> postList = postRepository.findAllByUserIdOrderByIdDesc(userId);
        return postMapper.toDtoList(postList);
    }

    public PostDto save(CreatePostRequest createPostRequest) {
        User user = this.userService.getUserEntityById(createPostRequest.getUserId());
        Post post = postMapper.toModel(createPostRequest);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        return this.postMapper.toDtoWithUser(savedPost);
    }

    public List<PostDto> getPosts(Long userId, List<Long> postIds) {
        List<Post> posts = postRepository.findAllByPostIds(postIds);
        List<PostDto> postDtoList = postMapper.toDtoList(posts);
        for (PostDto postDto : postDtoList) {

            if (postDto != null) {
                boolean isLiked = postRepository.existsLikeByUserIdAndPostId(userId, postDto.getId());
                postDto.setLikedByUser(isLiked);

                UserDto ownerDto = postDto.getUserDto(); //owner -> post'un sahibi
                if (ownerDto != null) {
                    boolean isFollowed = postRepository.existsFollowByUserIdAndOwnerId(userId, ownerDto.getId());
                    ownerDto.setFollowedByUser(isFollowed);
                }
            }
        }

        /*for (Long postId : postIds) {
            if (postDtoList.stream().noneMatch(postDto -> postDto.getId().equals(postId))) {
                postDtoList.add(null);
            }
        }*/

        return postDtoList;
    }

    public List<PostDto> mixByOwners(List<PostDto> postDtoList) {
        Map<Long, List<PostDto>> ownerToPosts = new HashMap<>();

        // gruplama
        for (PostDto postDto : postDtoList) {
            if (!ownerToPosts.containsKey(postDto.getUserDto().getId())) {
                ownerToPosts.put(postDto.getUserDto().getId(), new ArrayList<>());
            }
            ownerToPosts.get(postDto.getUserDto().getId()).add(postDto);
        }

        List<PostDto> postDtoListResult = new ArrayList<>();
        boolean isMore = true;
        int index = 0;
        List<List<PostDto>> listOfPostDtoList = new ArrayList<>(ownerToPosts.values());
        while (isMore) {
            isMore = false;
            for (List<PostDto> dtoList : listOfPostDtoList) {
                if (index < dtoList.size()) {
                    postDtoListResult.add(dtoList.get(index));
                    isMore = true;
                }
            }
            index++;
        }
        return postDtoListResult;
    }

    public List<PostDto> mergePosts(List<List<PostDto>> listOfPostDtoList) {
        PriorityQueue<PostDto> minHeap = new PriorityQueue<>(
                (postDto1, postDto2) -> {
                    if (postDto1.getCreatedAt() != postDto2.getCreatedAt()) {
                        return postDto1.getCreatedAt().compareTo(postDto2.getCreatedAt());
                    } else {
                        return postDto1.getId().compareTo(postDto2.getId());
                    }
                }
        );
        List<PostDto> mergedList = new ArrayList<>();

        for (List<PostDto> postDtoList : listOfPostDtoList) {
            if (!postDtoList.isEmpty()) {
                minHeap.add(postDtoList.get(0));
            }
        }

        while (!minHeap.isEmpty()) {
            PostDto current = minHeap.poll(); //en kucuk ögeyi (yani min-heap'teki en üst ögeyi) al
            mergedList.add(current);

            for (List<PostDto> posts : listOfPostDtoList) {
                if (!posts.isEmpty() && posts.get(0).equals(current)) {
                    posts.remove(0);
                    if (!posts.isEmpty()) {
                        minHeap.add(posts.get(0));
                    }
                    break;
                }
            }
        }

        Collections.reverse(mergedList);
        return mergedList;
    }

}
