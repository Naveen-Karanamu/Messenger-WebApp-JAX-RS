package rest_jax.messenger.validators;

import rest_jax.messenger.model.Comment;

public class CommentValidator {
    
    public static void validateComment(Comment comment) throws IllegalArgumentException {
        if (comment == null) {
            throw new IllegalArgumentException("Comment cannot be null");
        }
        if (comment.getMessage() == null || comment.getMessage().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be null or empty");
        }
        if (comment.getAuthor() == null || comment.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("Comment author cannot be null or empty");
        }
    }
}
