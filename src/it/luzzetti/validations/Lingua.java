package it.luzzetti.validations;

public enum Lingua {
    ITALIANO("bannedWords_IT.txt"), INGLESE("bannedWords_EN.txt");

    String nomeFile;

    Lingua(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    @Override
    public String toString() {
        return nomeFile;
    }
}
