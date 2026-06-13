public class WordRepositoryFixe extends WordRepository {

    private final String motFixe;

    public WordRepositoryFixe(String motFixe) {
        super();
        this.motFixe = motFixe.toLowerCase();
    }

    @Override
    public Word getRandomWord() {

        for (Word mot : getMots()) {

            if (mot.toString().equals(motFixe)) {
                return mot;
            }
        }

        return null;
    }
}
