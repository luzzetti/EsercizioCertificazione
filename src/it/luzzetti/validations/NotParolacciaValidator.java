package it.luzzetti.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NotParolacciaValidator implements ConstraintValidator<NotParolaccia, String> {

    private Lingua lingua;
    List<String> paroleProibite = Collections.emptyList();
    private Path dizionarioPath;
    // TODO: Trovare una posizione migliore di:
    //  Desktop\CertJavaEE7\wildfly-20.0.1.Final\wildfly-20.0.1.Final\bin
    //  Possibilmente dentro al war?

    @Override
    public void initialize(NotParolaccia notParolaccia) {
        this.lingua = notParolaccia.lingua();
        dizionarioPath = FileSystems.getDefault().getPath(lingua.toString());
        paroleProibite = caricaListaDaFile(lingua);
        System.out.println("[VALIDAZIONE] - Caricate " + paroleProibite.size() + " parole in " + lingua.name());
    }

    private List<String> caricaListaDaFile(Lingua lingua) {
        try (BufferedReader dizionario = Files.newBufferedReader(dizionarioPath)) {
            return dizionario
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // The Bean Validation specification recommends as good practice to consider null as valid
        if (value == null || value.length() == 0) {
            return true;
        }
        return !paroleProibite.contains(value.toLowerCase());
    }
}
