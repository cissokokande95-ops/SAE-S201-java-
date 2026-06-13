public class Main {

    public static void main(String[] args) {

        WordRepository repo1 = new WordRepositoryAleatoire();
        WordRepository repo2 = new WordRepositoryFixe("agent");

        Game jeu1 = new Game(repo1);
        Game jeu2 = new Game(repo2);

        jeu1.start();
        jeu2.start();
    }
}