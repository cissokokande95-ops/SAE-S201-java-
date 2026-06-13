import java.util.ArrayList;


public class Word {
    private final String texte;

    public Word(String texte) {
        this.texte = texte.toLowerCase();
    }

    public boolean estValide() {
        return texte != null && texte.length() == 5 && texte.matches("[a-z]+");
    }

    public ArrayList<LetterStatus> compareToSecret(Word secret) {
        ArrayList<LetterStatus> etats = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            char lettre = texte.charAt(i);
            if (lettre == secret.texte.charAt(i)) {
                etats.add(LetterStatus.OK);
            } else if (secret.texte.contains(String.valueOf(lettre))) {
                etats.add(LetterStatus.PRESENT);
            } else {
                etats.add(LetterStatus.ABSENT);
            }
        }
        return etats;
    }

    public String getTexte() {
        return texte;
    }

    @Override
    public String toString() {
        return texte;
    }
}
