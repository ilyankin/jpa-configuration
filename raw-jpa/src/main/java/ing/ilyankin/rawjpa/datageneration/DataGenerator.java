package ing.ilyankin.rawjpa.datageneration;

import ing.ilyankin.rawjpa.model.Profile;
import ing.ilyankin.rawjpa.model.Comment;
import ing.ilyankin.rawjpa.model.Post;
import ing.ilyankin.rawjpa.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class DataGenerator {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> persistDefaultUsers(int userNumber) {
        return IntStream.rangeClosed(1, userNumber)
                .mapToObj(i -> User.builder()
                        .username("User" + i)
                        .email("user%d@mail.com".formatted(i))
                        .build())
                .peek(entityManager::persist)
                .toList();
    }

    @Transactional
    public List<Profile> persistDefaultUserProfiles(List<User> users) {
        var profiles = new ArrayList<Profile>(users.size());
        for (int i = 1; i <= users.size(); i++) {
            var profile = Profile.builder()
                    .firstName("UserFirstName" + i)
                    .lastName("UserLastName" + i)
                    .user(users.get(i - 1))
                    .build();
            entityManager.persist(profile);
            profiles.add(profile);
        }
        return profiles;
    }

    @Transactional
    public List<Post> persistDefaultComments(List<User> users, int postsNumber) {
        var posts = new ArrayList<Post>(users.size());
        for (int i = 1; i <= users.size(); i++) {
            for (int j = 0; j < postsNumber; j++) {
                var post = Post.builder()
                        .title("UserPostTitle " + i)
                        .content("UserPostTitle " + i)
                        .user(users.get(i - 1))
                        .build();
                entityManager.persist(post);
                posts.add(post);
            }
        }
        return posts;
    }

    @Transactional
    public List<Comment> persistDefaultComments(Map<User, List<Post>> postsByUser, int commentNumber) {
        var comments = new ArrayList<Comment>();
        postsByUser.forEach((user, posts) -> posts.forEach(post -> IntStream.range(0, commentNumber)
                        .forEach(i -> {
                            var comment = Comment.builder()
                                    .post(post)
                                    .content(user.getUsername() + " comment " + i + " to " + post.getTitle())
                                    .build();
                            entityManager.persist(comment);
                            comments.add(comment);
                        })));

        return comments;
    }
}
