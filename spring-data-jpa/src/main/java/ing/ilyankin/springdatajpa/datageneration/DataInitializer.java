package ing.ilyankin.springdatajpa.datageneration;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private DataGenerator dataGenerator;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var users = dataGenerator.persistDefaultUsers(5);
        dataGenerator.persistDefaultUserProfiles(users);
        var posts = dataGenerator.persistDefaultComments(users, 2);
        var comments = dataGenerator.persistDefaultComments(Map.of(
                users.get(0), List.of(posts.get(1), posts.get(3), posts.get(7)),
                users.get(2), List.of(posts.get(0), posts.get(3), posts.get(8), posts.get(9)),
                users.get(3), List.of(posts.get(5), posts.get(8))
        ), 3);
    }
}
