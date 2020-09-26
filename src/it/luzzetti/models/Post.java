package it.luzzetti.models;

import it.luzzetti.interceptors.LoggingInterceptor;
import it.luzzetti.validations.Lingua;
import it.luzzetti.validations.NotParolaccia;

import javax.interceptor.Interceptors;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class Post {

    @NotNull @NotParolaccia(lingua = Lingua.ITALIANO)
    private String titolo;
    @NotNull @NotParolaccia(lingua = Lingua.ITALIANO)
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
        return titolo + ";" +
                testo + ";" +
                istanteCreazione + ";";
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
