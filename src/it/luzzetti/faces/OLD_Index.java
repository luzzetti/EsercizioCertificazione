package it.luzzetti.faces;

import it.luzzetti.models.Utente;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Named
@RequestScoped
@Deprecated
public class OLD_Index implements Serializable {

    public enum Navigazione {
        UTENTE_GIA_PRESENTE("utenteGiaPresente"),
        RANDOM_PAGE("randomPage");

        String nome;

        Navigazione(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    private String titolo = "Default";

    private String nomeUtente;
    private String cognomeUtente;

    private String accettaTermini;
    private boolean terminiAccettati;
    private boolean armiComprate;
    private boolean puttaneComprate;

    private List<Utente> utenti;

    @PostConstruct
    public void init() {
        utenti = new ArrayList<>(Arrays.asList(
                new Utente("Christian", "Luzzetti"),
                new Utente("Simone", "Fiani"))
        );
        nomeUtente = "";
        cognomeUtente = "";
        accettaTermini = "accetti?";
    }

    public boolean isArmiComprate() {
        return armiComprate;
    }

    public void setArmiComprate(boolean armiComprate) {
        this.armiComprate = armiComprate;
    }

    public boolean isPuttaneComprate() {
        return puttaneComprate;
    }

    public void setPuttaneComprate(boolean puttaneComprate) {
        this.puttaneComprate = puttaneComprate;
    }

    public String getTitolo() {
        return titolo;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getCognomeUtente() {
        return cognomeUtente;
    }

    public void setCognomeUtente(String cognomeUtente) {
        this.cognomeUtente = cognomeUtente;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAccettaTermini() {
        return accettaTermini;
    }

    public void setAccettaTermini(String accettaTermini) {
        this.accettaTermini = accettaTermini;
    }

    public boolean isTerminiAccettati() {
        return terminiAccettati;
    }

    public void setTerminiAccettati(boolean terminiAccettati) {
        this.terminiAccettati = terminiAccettati;
    }

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
    }

    public String aggiungiUtente() {
        System.out.println("Cerco di inserire: " + nomeUtente + " - " + cognomeUtente);
        Predicate<Utente> stessoNome = utente -> utente.getNome().equalsIgnoreCase(nomeUtente);
        long utentiCount = utenti.stream()
                .filter(stessoNome)
                .count();
        if (utentiCount > 1) {
            return Navigazione.UTENTE_GIA_PRESENTE.name();
        } else if (utentiCount == 1) {
            return Navigazione.RANDOM_PAGE.getNome();
        } else {
            utenti.add(new Utente(nomeUtente, cognomeUtente));
            return "utenteInserito";
        }
    }

    public void somethingHasChanged(ValueChangeEvent e) {
        System.out.println("Cognome has changed!");
        System.out.println(e.getSource());
        System.out.println(e.getNewValue());
    }

    public void selezionaTutto(ValueChangeEvent e) {
        System.out.println(e.getNewValue());
        if (e.getNewValue().equals(Boolean.TRUE)) {
            System.out.println("SELEZIONA TUTTO");
            armiComprate = true;
            puttaneComprate = true;
        } else {
            armiComprate = false;
            puttaneComprate = false;
            System.out.println("DESELEZIONA TUTTO");
        }

    }

}
