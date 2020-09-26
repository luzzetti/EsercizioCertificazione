package it.luzzetti.models;

import it.luzzetti.interceptors.LoggingInterceptor;

import javax.interceptor.Interceptors;
import java.time.OffsetDateTime;

public class Post {

    private String titolo;
    private String testo;
    private OffsetDateTime istanteCreazione;

    @Interceptors(LoggingInterceptor.class)
    public static Post parse(String s) {
        Post parsedPost = new Post();
        String[] tokens = s.split(";");
        parsedPost.setTitolo(tokens[0]);
        parsedPost.setTesto(tokens[1]);
        parsedPost.setIstanteCreazione(OffsetDateTime.parse(tokens[2]));
        return parsedPost;
    }

    @Interceptors(LoggingInterceptor.class)
    public String serializza() {
        String sb = titolo + ";" +
                testo + ";" +
                istanteCreazione + ";";
        return sb;
    }

    public OffsetDateTime getIstanteCreazione() {
        return istanteCreazione;
    }

    public void setIstanteCreazione(OffsetDateTime istanteCreazione) {
        this.istanteCreazione = istanteCreazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    @Override
    public String toString() {
        return "Post{" +
                "titolo='" + titolo + '\'' +
                ", testo='" + testo + '\'' +
                ", istanteCreazione=" + istanteCreazione +
                '}';
    }
}
