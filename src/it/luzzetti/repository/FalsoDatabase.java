package it.luzzetti.repository;

import it.luzzetti.models.Post;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

public interface FalsoDatabase {

    public void persist(@Valid Post post) throws ValidationException;

    public List<Post> findAll();

}
