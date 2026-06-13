package wordset_bundle_etudiant.tests;


public class TestWordSet {

    /**
     * 
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        JsonWordSet words = new JsonWordSet();

        System.out.println("Number of words: " + words.size());
        System.out.println("Random word: " + words.random());
        System.out.println("Word at index 1: " + words.word(1));
        System.out.println("Word starting with 'm': " + words.startingWith('m'));
    }
}
