package ru.itis.repositories.filters;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.models.Post;


public class PostSpecification {

    public static Specification<Post> byFieldLike(String field, String value) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(field)),
                    "%" + value.toLowerCase() + "%"
                );
    }

    public static Specification<Post> byUserFieldEqual(String field, String value) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(
                    criteriaBuilder.lower(root.join("user").get(field)),
                    value.toLowerCase()
                );
    }
}
