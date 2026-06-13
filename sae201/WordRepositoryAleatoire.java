import java.util.Random;

public class WordRepositoryAleatoire extends WordRepository {

    private final Random random;

    public WordRepositoryAleatoire() {
        super();
        random = new Random();
    }

    @Override
    public Word getRandomWord() {

        if (getMots().isEmpty()) {
            return null;
        }

        return getMots().get(
                random.nextInt(getMots().size())
        );
    }
}
