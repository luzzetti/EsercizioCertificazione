package it.luzzetti.faces;

import it.luzzetti.models.Post;
import it.luzzetti.service.PostService;

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

    private String titoloPost;
    private String testoPost;
    private List<Post> listaPost;

    @PostConstruct
    public void init() {
        System.out.println("[Start] - PostJSF.init");
        titoloPost = "";
        testoPost = "";
        listaPost = postService.leggiListaPost();
        System.out.println("[ End ] - PostJSF.init");
    }

    public String aggiungiPost() {
        System.out.println("[Start] - PostJSF.aggiungiPost");
        final Post nuovoPost = postService.creaPost(this.titoloPost, this.testoPost);
        System.out.println("[ End ] - PostJSF.aggiungiPost");
        return "post_inserito";
    }

    /* GETTERS - SETTERS */

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
