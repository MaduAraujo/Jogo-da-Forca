import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set; 

public class LetterDealer {
    private Set<Character> guessedLetters; 
    private int errors;
    private StringBuilder currentHiddenWord; 
    public LetterDealer() {
        this.guessedLetters = new HashSet<>(); 
        this.errors = 0;
        this.currentHiddenWord = new StringBuilder(); 
    }

    /**
     * Inicializa a palavra oculta para um novo jogo.
     * Deve ser chamada no início de cada nova rodada.
     * @param gameWord A palavra secreta do jogo.
     */
    public void initializeHiddenWord(String gameWord) {
        this.currentHiddenWord.setLength(0); 
        for (int i = 0; i < gameWord.length(); i++) {
            this.currentHiddenWord.append('_');
        }
        this.guessedLetters.clear(); 
        this.errors = 0; 
    }

    /**
     * Processa a tentativa de uma letra.
     * @param gameWord A palavra secreta do jogo.
     * @param guess A letra tentada pelo jogador.
     * @return true se a letra foi encontrada na palavra, false caso contrário (incluindo se já foi tentada).
     */
    public boolean guessLetter(String gameWord, char guess) {
        if (guessedLetters.contains(guess)) {
            System.out.println("Você já tentou a letra '" + guess + "' antes.");
            return false;
        }

        guessedLetters.add(guess);

        boolean found = false;
        for (int i = 0; i < gameWord.length(); i++) {
            if (gameWord.charAt(i) == guess) {
                currentHiddenWord.setCharAt(i, guess);
                found = true;
            }
        }

        if (!found) {
            errors++; 
        }
        return found;
    }

    /**
     * Retorna a palavra oculta atual.
     * @return A string que representa a palavra com letras adivinhadas e underscores.
     */
    public String getCurrentHiddenWord() {
        return currentHiddenWord.toString();
    }

    /**
     * Formata a palavra oculta adicionando espaços entre as letras para melhor legibilidade.
     * @param hiddenWord A string da palavra oculta.
     * @return A palavra oculta formatada com espaços.
     */
    public String formatHiddenWord(String hiddenWord) {
        StringBuilder formatted = new StringBuilder();
        for (char c : hiddenWord.toCharArray()) {
            formatted.append(c).append(" ");
        }
        return formatted.toString().trim();
    }

    /**
     * Verifica se todas as letras da palavra foram adivinhadas.
     * @param gameWord A palavra secreta.
     * @return true se a palavra oculta é igual à palavra secreta, false caso contrário.
     */
    public boolean isWordGuessed(String gameWord) {
        return currentHiddenWord.toString().equals(gameWord);
    }

    /**
     * Retorna a lista de letras já tentadas.
     * @return Uma lista de caracteres das letras tentadas.
     */
    public List<Character> getGuessedLetters() {
        return new ArrayList<>(guessedLetters); 
    }

    /**
     * Retorna o número atual de erros.
     * @return O número de erros.
     */
    public int getErrors() {
        return errors;
    }

    /**
     * Desenha a forca no console.
     * @param errors O número de erros para determinar qual parte da forca desenhar.
     */
    public void drawHangman(int errors) {
        System.out.println("  ____");
        System.out.println(" |    |");
        System.out.println(" |    " + (errors > 0 ? "O" : ""));
        System.out.println(" |   " + (errors > 2 ? "/" : "") + (errors > 1 ? "|" : "") + (errors > 3 ? "\\" : ""));
        System.out.println(" |   " + (errors > 4 ? "/" : "") + " " + (errors > 5 ? "\\" : ""));
        System.out.println("_|___");
    }
}