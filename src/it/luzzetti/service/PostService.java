package it.luzzetti.service;

import it.luzzetti.events.Aggiunto;
import it.luzzetti.events.Rimosso;
import it.luzzetti.interceptors.Loggable;
import it.luzzetti.interceptors.Profilable;
import it.luzzetti.models.Post;
import it.luzzetti.repository.FalsoDatabase;
import it.luzzetti.repository.TipoDB;
import it.luzzetti.repository.TipoDatabase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
@Loggable
@Profilable
public class PostService {

    @Inject
    @TipoDatabase(tipoDB = TipoDB.FILE)
    FalsoDatabase falsoDatabase;

    @Inject
    @Aggiunto
    private Event<Post> eventoPostCreato;

    @Inject
    @Rimosso
    private Event<Post> eventoPostRimosso;

    @PostConstruct
    private void init() {
    }

    public Post creaPost(String titolo, String testo) {
        OffsetDateTime istanteCreazione = OffsetDateTime.now();
        Post nuovoPost = new Post();
        nuovoPost.setTitolo(titolo);
        nuovoPost.setTesto(testo);
        nuovoPost.setIstanteCreazione(istanteCreazione);
        falsoDatabase.persist(nuovoPost);
        eventoPostCreato.fire(nuovoPost);       // Gli eventi in CDI non vengono trattati in maniera ASINCRONA.
        return nuovoPost;
    }

    public Post rimuoviPost(Post post) {
        eventoPostRimosso.fire(post);
        throw new UnsupportedOperationException("Metodo non implementato");
    }

    public List<Post> leggiListaPost() {
        return falsoDatabase.findAll();
    }

//    @AroundInvoke
//    private Object loggaMetodiInvocati(InvocationContext ic) throws Exception {
//        // TODO: Chiedere a renzo come impostare lo spool dei LOG a FINE
//        logger.entering(ic.getTarget().toString(), ic.getMethod().getName());
//        logger.warning("[Start] " + ic.getTarget().toString() + " " + ic.getMethod().getName());
//        try {
//            return ic.proceed();
//        } finally {
//            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
//            logger.warning("[ End ] " + ic.getTarget().toString() + " " + ic.getMethod().getName());
//        }
//    }

}
