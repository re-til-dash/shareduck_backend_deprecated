package com.shareduck.shareduck.domain.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.common.request.dto.CurrentUser;
import com.shareduck.shareduck.common.security.exception.AuthExceptionCode;
import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.entity.Hashtag;
import com.shareduck.shareduck.domain.board.entity.Post;
import com.shareduck.shareduck.domain.board.repository.PostRepository;
import com.shareduck.shareduck.domain.board.request.PostCreateReq;
import com.shareduck.shareduck.domain.board.request.PostUpdateReq;
import com.shareduck.shareduck.domain.board.response.PostRes;
import com.shareduck.shareduck.domain.board.response.PostSimpleRes;
import com.shareduck.shareduck.domain.user.persistence.entity.UserEntity;
import com.shareduck.shareduck.domain.user.persistence.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {

	private final PostRepository postRepository;

	private final UserRepository userRepository;

	private final CategoryService categoryService;

	@Transactional(readOnly = true)
	public Post findPostById(Long postId) {
		return postRepository.findById(postId)
			.orElseThrow(() -> new RuntimeException("%d번 글은 존재하지 않음".formatted(postId)));
	}

	public Post findPostByIdAndCheckAccess(Long postId, CurrentUser currentUser) {
		Post post = findPostById(postId);
		if (!post.getUser().getId().equals(currentUser.getUserId())) {
			throw new RuntimeException(
				String.format("user %d cannot access post %d", currentUser.getUserId(), post.getId()));
		}
		return post;
	}

	public PostRes createPost(CurrentUser currentUser, PostCreateReq postCreateReq) {
		UserEntity user = userRepository.findById(currentUser.getUserId())
			.orElseThrow(() -> new BusinessLogicException(AuthExceptionCode.USER_NOT_FOUND));

		Category category = categoryService.findCategoryByIdAndCheckAccess(postCreateReq.getCategoryId(), currentUser);

		Post newPost = Post.builder()
			.user(user)
			.category(category)
			.title(postCreateReq.getTitle())
			.content(postCreateReq.getContent())
			.properties(postCreateReq.getProperties())
			.thumbnailPath(postCreateReq.getThumbnailPath())
			.build();

		for (var tags : postCreateReq.getHashtags()) {
			newPost.addTag(Hashtag.create(tags));
		}

		postRepository.save(newPost);
		return PostRes.from(newPost);

	}

	@Transactional(readOnly = true)
	public PostRes getPostDetail(CurrentUser currentUser, Long postId) {
		Post post = findPostByIdAndCheckAccess(postId, currentUser);
		return PostRes.from(post);
	}

	public PostRes updatePost(CurrentUser currentUser, Long postId, PostUpdateReq postUpdateReq) {
		Post post = findPostByIdAndCheckAccess(postId, currentUser);
		updatePostHashtag(post, postUpdateReq.getHashTags());

		post.update(
			postUpdateReq.getTitle(),
			postUpdateReq.getContent(),
			postUpdateReq.getProperties(),
			postUpdateReq.getThumbnailPath()
		);

		return PostRes.from(post);
	}

	private void updatePostHashtag(Post post, List<String> hashTags) {
		// 있던거 제거
		List<Hashtag> deletedTags = post.getHashtags().stream()
			.filter(hashtag -> !hashTags.contains(hashtag.getTag()))
			.toList();
		deletedTags.forEach(post::removeTag);

		// set에 추가
		hashTags
			.stream()
			.map(Hashtag::create)
			.forEach(post::addTag);
	}

	@Transactional(readOnly = true)
	public Page<PostSimpleRes> getSimplepostPage(CurrentUser currentUser, Long categoryId, Pageable pageable) {
		categoryService.findCategoryByIdAndCheckAccess(categoryId, currentUser);

		Page<Post> posts = postRepository.findActivePostByCategoryId(categoryId,
			pageable);
		return posts.map(PostSimpleRes::from);

	}

	public void deletePost(CurrentUser currentUser, Long postId) {
		Post post = findPostByIdAndCheckAccess(postId, currentUser);
		postRepository.delete(post);
	}
}
