package it.luzzetti.models;

import java.time.OffsetDateTime;

public class Post {

    private String titolo;
    private String testo;
    private OffsetDateTime istanteCreazione;

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
