package it.luzzetti.faces;

import it.luzzetti.interceptors.Loggable;
import it.luzzetti.models.Post;
import it.luzzetti.service.PostService;
import it.luzzetti.service.StatisticheService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PostJSF {

    @Inject
    private PostService postService;

    @Inject
    private StatisticheService statisticheService;

    private String titoloPost;
    private String testoPost;
    private List<Post> listaPost;

    @PostConstruct
    public void init() {
        titoloPost = "";
        testoPost = "";
        listaPost = postService.leggiListaPost();
    }

    @Loggable
    public String aggiungiPost() {
        final Post nuovoPost = postService.creaPost(this.titoloPost, this.testoPost);
        return "post_inserito";
    }

    /* GETTERS - SETTERS */

    public int contaPostOdierni() {
        return statisticheService.contaPostOdierni();
    }

    public List<Post> getListaPost() {
        return listaPost;
    }

    public void setListaPost(List<Post> listaPost) {
        this.listaPost = listaPost;
    }

    public String getTitoloPost() {
        return titoloPost;
    }

    public void setTitoloPost(String titoloPost) {
        this.titoloPost = titoloPost;
    }

    public String getTestoPost() {
        return testoPost;
    }

    public void setTestoPost(String testoPost) {
        this.testoPost = testoPost;
    }
}
