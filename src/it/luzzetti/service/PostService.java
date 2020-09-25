package it.luzzetti.service;

import it.luzzetti.models.Post;
import it.luzzetti.repository.AList;
import it.luzzetti.repository.FalsoDatabase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@RequestScoped
public class PostService {

    @Inject @AList
    FalsoDatabase falsoDatabase;

    private OffsetDateTime istanteCreazione;

    @PostConstruct
    private void init() {
        System.out.println("[Start] - PostService.init");
        istanteCreazione = OffsetDateTime.now();
        System.out.println("[ End ] - PostService.init");
    }

    public Post creaPost(String titolo, String testo) {
        System.out.println("[Start] - PostService.creaPost");
        Post nuovoPost = new Post();
        nuovoPost.setTitolo(titolo);
        nuovoPost.setTesto(testo);
        nuovoPost.setIstanteCreazione(istanteCreazione);
        falsoDatabase.persist(nuovoPost);
        System.out.println("[ End ] - PostService.creaPost");
        return nuovoPost;
    }

    public List<Post> leggiListaPost() {
        System.out.println("[Start] - PostService.leggiListaPost");
        List<Post> listaPost = falsoDatabase.findAll();
        System.out.println("[ End ] - PostService.leggiListaPost");
        return listaPost;
    }
}
