package ing.ilyankin.rawspring;

import ing.ilyankin.rawspring.model.Comment;
import ing.ilyankin.rawspring.model.Post;
import ing.ilyankin.rawspring.model.Profile;
import ing.ilyankin.rawspring.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RawSpringApplicationTests {
    @PersistenceContext
    private EntityManager em;

    @Test
    void checkPersistedUsers() {
        var users = em.createQuery("select u from User u", User.class).getResultList();
        Assertions.assertEquals(5, users.size());
    }

    @Test
    void checkPersistedProfile() {
        var profiles = em.createQuery("select p from Profile p", Profile.class).getResultList();
        Assertions.assertEquals(5, profiles.size());
    }

    @Test
    void checkPersistedPosts() {
        var posts = em.createQuery("select p from Post p", Post.class).getResultList();
        Assertions.assertEquals(10, posts.size());
    }

    @Test
    void checkPersistedComments() {
        var comments = em.createQuery("select c from Comment c", Comment.class).getResultList();
        Assertions.assertEquals(27, comments.size());
    }
}
