package com.shareduck.shareduck.domain.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shareduck.shareduck.common.exception.BusinessLogicException;
import com.shareduck.shareduck.common.security.exception.AuthExceptionCode;
import com.shareduck.shareduck.domain.board.entity.Category;
import com.shareduck.shareduck.domain.board.entity.Hashtag;
import com.shareduck.shareduck.domain.board.entity.Post;
import com.shareduck.shareduck.domain.board.repository.PostRepository;
import com.shareduck.shareduck.domain.board.request.PostCreateReq;
import com.shareduck.shareduck.domain.board.request.PostUpdateReq;
import com.shareduck.shareduck.domain.board.response.PostRes;
import com.shareduck.shareduck.domain.board.response.PostSimpleRes;
import com.shareduck.shareduck.domain.user.entity.UserEntity;
import com.shareduck.shareduck.domain.user.repository.UserRepository;

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

	public Post findById(Long postId) {
		return postRepository.findById(postId)
			.orElseThrow(RuntimeException::new);
	}

	public void checkAccess(Long userId, Post post) {
		if (!post.getUser().getId().equals(userId)) {
			throw new RuntimeException(String.format("user %d cannot access post %d", userId, post.getId()));
		}
	}

	public PostRes createPost(Long userId, PostCreateReq postCreateReq) {
		UserEntity user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessLogicException(AuthExceptionCode.USER_NOT_FOUND));

		Category category = categoryService.findById(postCreateReq.getCategoryId());
		categoryService.checkAccess(category, userId);

		Post newPost = Post.builder()
			.user(user)
			.category(category)
			.title(postCreateReq.getTitle())
			.content(postCreateReq.getContent())
			.properties(postCreateReq.getProperties())
			.thumbnailPath(postCreateReq.getThumbnailPath())
			.uuid(postCreateReq.getUuid())
			.build();

		for (var tags : postCreateReq.getHashtags()) {
			newPost.addTag(Hashtag.create(tags));
		}

		postRepository.save(newPost);
		return PostRes.from(newPost);

	}

	public PostRes getPostDetail(Long userId, Long postId) {
		Post post = findById(postId);
		checkAccess(userId, post);
		return PostRes.from(post);
	}

	@Transactional(readOnly = true)
	public PostRes updatePost(Long userId, Long postId, PostUpdateReq postUpdateReq) {
		Post post = findById(postId);
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
	public Page<PostSimpleRes> getSimplepostPage(Long userId, Long categoryId, Pageable pageable) {
		Category category = categoryService.findById(categoryId);
		categoryService.checkAccess(category, userId);

		Page<Post> posts = postRepository.findPostByCategoryIdOrderByIdDesc(categoryId,
			pageable);
		Page<PostSimpleRes> postSimpleResPage = posts.map(PostSimpleRes::from);
		return postSimpleResPage;
	}

	public void deletePost(Long userId, Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(RuntimeException::new);
		checkAccess(userId, post);
		postRepository.delete(post);
	}
}
