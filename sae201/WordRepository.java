import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class WordRepository {

    private final List<Word> mots;
    private final Set<String> motsValides;

    public WordRepository() {
        mots = new ArrayList<>();
        motsValides = new HashSet<>();
        chargerMots();
    }

    private void chargerMots() {

        try {
            String contenu = new String(
                    Files.readAllBytes(
                            Paths.get("wordset_bundle_etudiant/data/mots.json")
                    )
            );

            contenu = contenu.trim();

            if (contenu.startsWith("[")) {
                contenu = contenu.substring(1, contenu.length() - 1);
            }

            String[] liste = contenu.split(",");

            for (String mot : liste) {

                mot = mot.trim().replace("\"", "");

                if (!mot.isEmpty()) {
                    mot = mot.toLowerCase();
                    mots.add(new Word(mot));
                    motsValides.add(mot);
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public List<Word> getMots() {
        return mots;
    }

    public boolean isValidWord(String mot) {
        return motsValides.contains(mot.toLowerCase());
    }

    public abstract Word getRandomWord();
}