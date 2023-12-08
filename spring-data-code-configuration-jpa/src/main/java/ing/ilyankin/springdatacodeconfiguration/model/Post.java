package ing.ilyankin.springdatacodeconfiguration.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @ToString.Include
    @Column(name = "title", nullable = false)
    private String title;

    @ToString.Include
    @Column(name = "content", nullable = false, length = 4096)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();
}