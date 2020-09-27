package it.luzzetti.service;

import it.luzzetti.events.Aggiunto;
import it.luzzetti.events.Rimosso;
import it.luzzetti.interceptors.Loggable;
import it.luzzetti.interceptors.Profilable;
import it.luzzetti.models.Post;
import it.luzzetti.repository.FalsoDatabase;
import it.luzzetti.repository.TipoDB;
import it.luzzetti.repository.TipoDatabase;
import it.luzzetti.validations.NotParolaccia;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
@Loggable
@Profilable
public class PostService {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Inject
    @TipoDatabase(tipoDB = TipoDB.FILE)
    FalsoDatabase falsoDatabase;

    @Inject
    Validator validator;

    @Inject
    @Aggiunto
    private Event<Post> eventoPostCreato;

    @Inject
    @Rimosso
    private Event<Post> eventoPostRimosso;

    public Post creaPost(@Size(min = 3, max = 200) @NotParolaccia String titolo,
                         @Size(min = 3, max = 200) String testo) {
        Post nuovoPost = new Post();
        nuovoPost.setTesto(testo);
        nuovoPost.setTitolo(titolo);
        OffsetDateTime istanteCreazione = OffsetDateTime.now();
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
        return falsoDatabase.findAll()
                .stream()
                .sorted((p1, p2) -> p2.getIstanteCreazione().compareTo(p1.getIstanteCreazione()))
                .collect(Collectors.toList());
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
