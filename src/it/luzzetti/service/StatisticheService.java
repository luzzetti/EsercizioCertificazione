package it.luzzetti.service;

import it.luzzetti.events.Aggiunto;
import it.luzzetti.events.Rimosso;
import it.luzzetti.models.Post;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@ApplicationScoped
public class StatisticheService {

    Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    Map<String, Integer> postGiornalieri = new ConcurrentHashMap<>();

    enum TipoOperazione {
        INCREMENTA, DECREMENTA;
    }

    public void onPostCreato(@Observes @Aggiunto Post post) {
        aggiornaContatore(TipoOperazione.INCREMENTA, post);
    }

    public void onPostRimosso(@Observes @Rimosso Post post) {
        aggiornaContatore(TipoOperazione.DECREMENTA, post);
    }

    private void aggiornaContatore(TipoOperazione tipoOperazione, Post post) {
        String dataDiCreazioneDelPost = post.getIstanteCreazione().toLocalDate().toString();
        if (postGiornalieri.containsKey(dataDiCreazioneDelPost)) {
            Integer contatore = postGiornalieri.get(dataDiCreazioneDelPost);
            contatore = tipoOperazione.equals(TipoOperazione.INCREMENTA) ? ++contatore : --contatore;
            postGiornalieri.put(dataDiCreazioneDelPost, contatore);
        } else {
            postGiornalieri.put(dataDiCreazioneDelPost, 1);
        }
    }


    public int contaPostOdierni() {
        OffsetDateTime istanteCreazione = OffsetDateTime.now();
        String oggi = istanteCreazione.toLocalDate().toString();
        return postGiornalieri.get(oggi);
    }

}


