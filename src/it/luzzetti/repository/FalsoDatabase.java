package it.luzzetti.repository;

import it.luzzetti.models.Post;

import java.util.List;

public interface FalsoDatabase {

    public void persist(Post post);

    public List<Post> findAll();

}
