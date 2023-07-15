package com.ootdgram.ootdgram.service;

import com.ootdgram.ootdgram.domain.dto.CommentRequestDto;
import com.ootdgram.ootdgram.domain.dto.CommentResponseDto;
import com.ootdgram.ootdgram.domain.dto.ResponseDto;
import com.ootdgram.ootdgram.domain.entity.Comment;
import com.ootdgram.ootdgram.domain.entity.Post;
import com.ootdgram.ootdgram.domain.entity.User;
import com.ootdgram.ootdgram.repository.CommentRepository;
import com.ootdgram.ootdgram.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, User user) {
        Post findPost = findPost(postId);
        Comment comment = new Comment(commentRequestDto, user, findPost);

        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    public List<CommentResponseDto> getComments(Long postId) {
        Post findPost = findPost(postId);

        List<CommentResponseDto> findCommentListByPost = findPost.getCommentList().stream().map(CommentResponseDto::new).toList();

        return findCommentListByPost;
    }

    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = findComment(commentId);
        isWriterValidation(comment, user);

        comment.update(commentRequestDto);

        return new CommentResponseDto(comment);
    }


    public ResponseDto deleteComment(Long commentId, User user) {
        Comment findComment = findComment(commentId);
        isWriterValidation(findComment, user);

        commentRepository.delete(findComment);

        return new ResponseDto("삭제완료");
    }

    private Comment findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
        );
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 포스트입니다.")
        );
    }

    private void isWriterValidation(Comment comment, User user) {
        if(!comment.getUser().getEmail().equals(user.getEmail()))
            throw new IllegalArgumentException("작성자가 아닙니다.");
    }
}