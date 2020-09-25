package it.luzzetti.repository;

import it.luzzetti.models.Post;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@AList
@ApplicationScoped
public class PostRepositoryArrayList implements FalsoDatabase {

    List<Post> posts;

    @PostConstruct
    public void init() {
        System.out.println("[Start] - PostRepositoryArrayList.init");
        posts = new ArrayList<>();
        System.out.println("[ End ] - PostRepositoryArrayList.init");
    }

    @Override
    public void persist(Post post) {
        System.out.println("[Start] - PostRepositoryArrayList.persist");
        posts.add(post);
        System.out.println("[ End ] - PostService.persist");
    }

    @Override
    public List<Post> findAll() {
        System.out.println("[Start] - PostRepositoryArrayList.findAll");
        System.out.println("[ End ] - PostRepositoryArrayList.findAll");
        return new ArrayList<>(posts);
    }

}
