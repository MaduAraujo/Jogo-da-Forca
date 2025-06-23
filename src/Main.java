import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Player player = new Player(); 

        // --- Início do Jogo ---
        clearConsole();
        System.out.println("Bem-vindo(a) ao Jogo da Forca!");
        System.out.print("Qual é o seu nome, aventureiro(a)? ");
       
        String playerName = player.setName(scan); 
        System.out.println("\nOlá, " + playerName + "! Preparado para testar seus conhecimentos?");
        System.out.println("Seu objetivo é acertar a palavra antes de cometer 7 erros.");
        System.out.println("Vamos começar!");
        pressEnterToContinue(scan);

        // --- Loop Principal do Jogo ---
        while (true) {
            clearConsole();
            WordDealer dealWord = new WordDealer();
            LetterDealer dealLetter = new LetterDealer(); 

            String category = "";
            String gameWord = null;

            // Loop para garantir que uma categoria válida seja escolhida e uma palavra carregada
            while (gameWord == null) {
                System.out.println("\n--- Novo Jogo ---");
                System.out.println("Escolha uma categoria para começar (ex: animais, frutas):");
                System.out.print("Sua escolha: ");
                category = scan.nextLine().trim(); // Lê a categoria e remove espaços em branco

                gameWord = dealWord.selectRandomWord(category);

                if (gameWord == null) {
                    System.out.println("\n----------------------------------------------------");
                    System.out.println("Ops! Não foi possível carregar palavras para a categoria '" + category + "'.");
                    System.out.println("Verifique se digitou corretamente ou se o arquivo existe para essa categoria.");
                    System.out.println("----------------------------------------------------");
                    System.out.println("Deseja tentar outra categoria ou sair do jogo? (t/s)");
                    String choice = scan.nextLine().toLowerCase();
                    if (choice.equals("s")) {
                        break; // Sai do loop externo e encerra o jogo
                    }
                    clearConsole(); // Limpa a tela antes de pedir a categoria novamente
                }
            }

            if (gameWord == null) { // Se o usuário escolheu sair do loop de seleção de categoria
                break;
            }

            // Inicializa a palavra oculta no LetterDealer APENAS UMA VEZ por jogo
            dealLetter.initializeHiddenWord(gameWord);

            // --- Loop de Tentativas dentro de um Jogo ---
            while (true) {
                clearConsole();
                System.out.println("--- Jogo da Forca ---");
                System.out.println("Categoria: " + category.substring(0, 1).toUpperCase() + category.substring(1)); // Capitaliza a categoria
                System.out.println("\nPalavra: " + dealLetter.formatHiddenWord(dealLetter.getCurrentHiddenWord())); // Pega a palavra oculta atualizada do LetterDealer
                System.out.println("Letras já tentadas: " + (dealLetter.getGuessedLetters().isEmpty() ? "Nenhuma" : dealLetter.getGuessedLetters().toString().replace("[", "").replace("]", ""))); // Formata a exibição das letras tentadas
                System.out.println("Erros: " + dealLetter.getErrors() + "/7");
                dealLetter.drawHangman(dealLetter.getErrors()); // Desenha a forca

                System.out.print("\nDigite uma letra (ou 'sair' para desistir): ");
                String input = scan.nextLine().trim().toLowerCase(); // Lê e limpa a entrada

                if (input.equals("sair")) {
                    System.out.println("\nVocê desistiu! A palavra era: " + gameWord.toUpperCase());
                    player.incrementGamesLost();
                    pressEnterToContinue(scan);
                    break; // Sai do loop do jogo atual
                }

                // Validação de entrada
                if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                    System.out.println("Entrada inválida! Por favor, digite apenas uma letra válida.");
                    pressEnterToContinue(scan);
                    continue;
                }
                char guess = input.charAt(0);

                boolean letterWasFound = dealLetter.guessLetter(gameWord, guess); 
                
                // As verificações de vitória ou derrota são feitas APÓS a tentativa.
                if (dealLetter.isWordGuessed(gameWord)) { // Assumindo isWordGuessed no LetterDealer
                    System.out.println("\n--- 🎉 PARABÉNS, " + playerName.toUpperCase() + "! 🎉 ---");
                    System.out.println("Você adivinhou a palavra: " + gameWord.toUpperCase() + "!");
                    player.incrementGamesWon();
                    player.addScore(100 - (dealLetter.getErrors() * 10)); // Exemplo de pontuação
                    pressEnterToContinue(scan);
                    break; // Sai do loop de tentativas, termina o jogo atual
                } else if (dealLetter.getErrors() >= 7) { 
                    System.out.println("\n--- 😭 GAME OVER! 😭 ---");
                    System.out.println("Você excedeu o número de erros.");
                    System.out.println("A palavra era: " + gameWord.toUpperCase());
                    dealLetter.drawHangman(7); // Desenha a forca completa
                    player.incrementGamesLost();
                    pressEnterToContinue(scan);
                    break; // Sai do loop de tentativas, termina o jogo atual
                } else if (!letterWasFound) {
                    System.out.println("Que pena! A letra '" + guess + "' não está na palavra. Erros: " + dealLetter.getErrors() + "/7");
                    pressEnterToContinue(scan);
                } else {
                    System.out.println("Boa! A letra '" + guess + "' está na palavra!");
                    pressEnterToContinue(scan);
                }
            }

            // Pergunta se o jogador quer jogar novamente
            System.out.println("\n----------------------------------------------------");
            System.out.print("Deseja jogar outra rodada? (s/n): ");
            String playAgain = scan.nextLine().trim().toLowerCase();
            if (playAgain.equals("n")) {
                break; // Sai do loop principal do jogo
            }
        }

        // --- Fim do Jogo ---
        clearConsole();
        player.displayStats(); // Mostra as estatísticas do jogador
        System.out.println("\nObrigado por jogar o Jogo da Forca, " + playerName + "!");
        System.out.println("Até a próxima!");
        scan.close(); // Fecha o Scanner
    }

    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("\nPressione ENTER para continuar...");
        try {
            scanner.nextLine();
        } catch (Exception e) {
        }
    }
}