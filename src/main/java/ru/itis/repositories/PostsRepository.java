package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostsRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    Optional<Post> findByUser_IdAndId(UUID userId, Long id);

    Optional<List<Post>> findByUser_Id(UUID userId);

    @Query(nativeQuery = true, value = "" +
            "WITH cte_account_subs AS " +
            "    (SELECT subscription_id, count(subscriber_id) AS count " +
            "          FROM account acc INNER JOIN subscriber_subscription sub " +
            "              ON acc.id = sub.subscription_id " +
            "          GROUP BY subscription_id " +
            "          ORDER BY count DESC LIMIT :limit " +
            "    )" +
            "SELECT * " +
            "FROM post " +
            "WHERE user_id IN " +
            "      (SELECT subscription_id " +
            "          FROM cte_account_subs " +
            "      );"
    )
    Optional<List<Post>> findByUserRating(@Param("limit") int limit);

    @Query("" +
            "SELECT post FROM Post post " +
            "WHERE post.user IN " +
            "(SELECT resume.user FROM Resume resume " +
            "    WHERE SIZE(resume.skills) = " +
            "   (SELECT MAX(SIZE(r.skills)) " +
            "   FROM Resume r) " +
            ")"
    )
    Optional<List<Post>> findByUserSkills();

    @Query(nativeQuery = true, value = "" +
            "WITH cte_user_posts AS" +
            "    (SELECT user_id, count(user_id) AS count" +
            "     FROM post INNER JOIN account acc ON post.user_id = acc.id" +
            "     GROUP BY user_id" +
            "     ORDER BY count DESC LIMIT :limit" +
            "     )," +
            "     cte_user_comment AS" +
            "     (SELECT user_id, count(user_id) AS count" +
            "      FROM comment INNER JOIN account acc ON comment.user_id = acc.id" +
            "      GROUP BY user_id" +
            "      ORDER BY count DESC LIMIT :limit" +
            "      )" +
            "SELECT * " +
            "FROM post " +
            "WHERE user_id IN " +
            "      (SELECT user_id FROM cte_user_posts)" +
            "   OR user_id IN" +
            "      (SELECT user_id FROM cte_user_comment);"
    )
    Optional<List<Post>> findByUserActivities(@Param("limit") int limit);
}
