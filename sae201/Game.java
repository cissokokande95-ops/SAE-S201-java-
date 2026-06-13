import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final Word motSecret;
    private final List<String> propositions;
    private final int tentativesMax;
    private int tentativeCourante;
    private final WordRepository repertoire;
    private boolean termine;
    private boolean victoire;
    private String playerName;
    private final Scanner scanner;

    public Game(WordRepository repertoire) {
        this.repertoire = repertoire;
        this.motSecret = repertoire.getRandomWord();
        this.propositions = new ArrayList<>();
        this.tentativesMax = 6;
        this.tentativeCourante = 0;
        this.termine = false;
        this.victoire = false;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty()) {
            nom = "Joueur";
        }
        this.playerName = nom;
        System.out.println("Bienvenue " + playerName + " dans FindMyWord !");
        System.out.println("Devinez le mot de 5 lettres en 6 tentatives.");
        while (!termine) {
            afficherGrille();
            System.out.print("Entrez une proposition : ");
            String proposition = scanner.nextLine().trim().toLowerCase();
            jouerTour(proposition);
        }
        afficherFin();
    }

    private void jouerTour(String proposition) {
        if (!estPropositionValide(proposition)) {
            System.out.println("Proposition invalide. Tapez un mot de 5 lettres en minuscules.");
            return;
        }
        propositions.add(proposition);
        tentativeCourante++;
        List<LetterStatus> etats = verifierProposition(proposition);
        afficherResultat(etats);
        if (proposition.equals(motSecret.getTexte())) {
            victoire = true;
            termine = true;
        } else if (tentativeCourante >= tentativesMax) {
            termine = true;
        }
    }

    private boolean estPropositionValide(String proposition) {
        return proposition.length() == 5 && proposition.matches("[a-z]+") && repertoire.isValidWord(proposition);
    }

    private List<LetterStatus> verifierProposition(String proposition) {
        Word mot = new Word(proposition);
        return mot.compareToSecret(motSecret);
    }

    private void afficherGrille() {
        System.out.println("\nGrille actuelle :");
        for (int i = 0; i < tentativesMax; i++) {
            if (i < propositions.size()) {
                System.out.println(propositions.get(i));
            } else {
                System.out.println("_____");
            }
        }
    }

    private void afficherResultat(List<LetterStatus> etats) {
        String dernier = propositions.get(propositions.size() - 1);
        for (int i = 0; i < etats.size(); i++) {
            System.out.print(dernier.charAt(i) + "(" + etats.get(i) + ") ");
        }
        System.out.println();
    }

    private void afficherFin() {
        if (victoire) {
            System.out.println("Bravo " + playerName + " ! Vous avez trouvé le mot : " + motSecret);
            System.out.println("Score : " + calculerScore());
        } else {
            System.out.println(playerName + ", vous avez perdu. Le mot était : " + motSecret);
        }
    }

    private int calculerScore() {
        return victoire ? tentativesMax - tentativeCourante + 1 : 0;
    }
}