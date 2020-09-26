package it.luzzetti.repository;

import it.luzzetti.models.Post;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@TipoDatabase(tipoDB = TipoDB.IN_MEMORY)
@ApplicationScoped
@Deprecated
public class PostRepositoryVolatile implements FalsoDatabase {

    List<Post> posts;

    @PostConstruct
    public void init() {
        posts = new LinkedList<>();
    }

    @Override
    public void persist(Post post) {
        posts.add(post);
    }

    @Override
    public List<Post> findAll() {
        return new LinkedList<>(posts);
    }

}
