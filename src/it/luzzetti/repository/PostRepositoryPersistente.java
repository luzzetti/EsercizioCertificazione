package it.luzzetti.repository;

import it.luzzetti.interceptors.Loggable;
import it.luzzetti.interceptors.LoggingInterceptor;
import it.luzzetti.models.Post;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.interceptor.Interceptors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@TipoDatabase(tipoDB = TipoDB.FILE)
@Loggable
@ApplicationScoped
public class PostRepositoryPersistente implements FalsoDatabase {

    private static final Path savePath = FileSystems.getDefault().getPath("falsoDatabase.txt");

    List<Post> posts;

    @PostConstruct
    public void init() {
        posts = new ArrayList<>();
        tentaCaricamentoDaDisco();
    }

    @Override
    public void persist(Post post) {
        posts.add(post);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    @PreDestroy
    public void shutDown() {
        tentaSalvataggioSuDisco();
    }

    private void tentaSalvataggioSuDisco() {
        System.out.println("[INFO ] - " + savePath.toString());
        try (BufferedWriter saveFile = Files.newBufferedWriter(savePath)) {
            for (Post post : posts) {
                saveFile.write(post.serializza() + "\n");
                System.out.println("\t[Saved] - " + post.toString());
            }
        } catch (IOException e) {
            System.out.println("[ERROR] - PostRepositoryPersistente.shutDown");
            System.out.println("\t[ERROR] - Impossibile salvare il post");
        } catch (Exception ex) {
            System.out.println("[ERROR] - PostRepositoryPersistente.shutDown");
            System.out.println("\t[ERROR] - Exception inaspettata");
            System.out.println("\t" + ex.getMessage());
        }
    }

    private void tentaCaricamentoDaDisco() {
        try (BufferedReader loadFile = Files.newBufferedReader(savePath)) {
            loadFile.lines().forEach(this::aggiungiAiPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void aggiungiAiPost(String s) {
        try {
            posts.add(Post.parse(s));
        } catch (Exception exception) {
            System.out.println("\t[Info ] - Skipping: " + s);
        }
    }

}
