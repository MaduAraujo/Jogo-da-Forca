import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordDealer {
    private List<String> animals; 
    private List<String> fruits; 

    public WordDealer() {
        animals = loadWordsFromFile("animals.txt"); 
        fruits = loadWordsFromFile("fruits.txt");   
    }

    private List<String> loadWordsFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(getClass().getClassLoader().getResourceAsStream(filename))) {
            while (fileScanner.hasNextLine()) {
                words.add(fileScanner.nextLine().trim().toLowerCase());
            }
        } catch (NullPointerException e) {
            System.err.println("Erro: Arquivo " + filename + " não encontrado.");
        }
        return words;
    }

    public String selectRandomWord(String category) {
        Random random = new Random();
        List<String> chosenList;

        switch (category.toLowerCase()) {
            case "animais":
                chosenList = this.animals; 
                break;
            case "frutas":
                chosenList = this.fruits;  
                break;
            default:
                System.out.println("Categoria inválida. Usando animais por padrão.");
                chosenList = this.animals; 
                break;
        }

        // Verifica se a lista escolhida não está vazia antes de tentar pegar uma palavra
        if (chosenList == null || chosenList.isEmpty()) {
            System.err.println("Nenhuma palavra encontrada na categoria '" + category + "'.");
            return null; 
        }
        return chosenList.get(random.nextInt(chosenList.size()));
    }

    public String buildHiddenWord(String gameWord) {
        return "_".repeat(gameWord.length());
    }
}