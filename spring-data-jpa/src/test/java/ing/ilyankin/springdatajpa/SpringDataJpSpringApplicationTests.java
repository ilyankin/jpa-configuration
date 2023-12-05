package ing.ilyankin.springdatajpa;

import ing.ilyankin.springdatajpa.model.Comment;
import ing.ilyankin.springdatajpa.model.Post;
import ing.ilyankin.springdatajpa.model.Profile;
import ing.ilyankin.springdatajpa.model.User;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringDataJpSpringApplicationTests {
    @PersistenceContext
    private Session session;

    @Test
    void checkPersistedUsers() {
        var users = session.createQuery("select u from User u", User.class).getResultList();
        Assertions.assertEquals(5, users.size());
    }

    @Test
    void checkPersistedProfile() {
        var profiles = session.createQuery("select p from Profile p", Profile.class).getResultList();
        Assertions.assertEquals(5, profiles.size());
    }

    @Test
    void checkPersistedPosts() {
        var posts = session.createQuery("select p from Post p", Post.class).getResultList();
        Assertions.assertEquals(10, posts.size());
    }

    @Test
    void checkPersistedComments() {
        var comments = session.createQuery("select c from Comment c", Comment.class).getResultList();
        Assertions.assertEquals(27, comments.size());
    }
}
