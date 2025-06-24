# 🎮 Jogo da Forca

Este é um jogo da forca simples e interativo, desenvolvido em Java, onde os jogadores tentam adivinhar uma palavra oculta em uma categoria específica antes de cometerem 7 erros. 
O jogo oferece uma experiência de console envolvente com funcionalidade de limpeza de tela e estatísticas do jogador.

### ✨ Funcionalidades

  * **Seleção de Categoria**: Escolha entre diversas categorias (ex: `animais`, `frutas`) para jogar.
  * **Adivinhação de Letras**: Digite letras para adivinhar a palavra.
  * **Contador de Erros**: O jogo rastreia seus erros e exibe o progresso da forca.
  * **Estatísticas do Jogador**: Acompanhe o número de jogos ganhos, perdidos e sua pontuação.
  * **Limpeza de Console**: Uma experiência de jogo mais limpa e organizada no terminal.
  * **Reiniciar Jogo**: Opção para jogar múltiplas rodadas.

## 🚀 Como Jogar

1.  **Execute o Programa**: Compile e execute a classe `Main`.
2.  **Informe Seu Nome**: Digite seu nome quando solicitado para começar.
3.  **Escolha uma Categoria**: Digite uma categoria (ex: `animais`, `frutas`) e pressione Enter.
      * **Observação**: As palavras para as categorias são carregadas de arquivos externos. Certifique-se de que os arquivos de palavras (`animais.txt`, `frutas.txt`, etc.) estejam na estrutura de diretórios esperada pelo `WordDealer` para que o jogo funcione corretamente. Cada palavra deve estar em uma nova linha no arquivo.
4.  **Adivinhe a Palavra**: Digite uma letra por vez e pressione Enter.
5.  **Desistir**: A qualquer momento durante o jogo, digite `sair` para desistir da rodada.
6.  **Fim do Jogo**: O jogo termina quando você adivinha a palavra ou atinge 7 erros. Você terá a opção de jogar novamente.
7.  **Estatísticas Finais**: Ao sair do jogo principal, suas estatísticas (jogos ganhos, perdidos, pontuação) serão exibidas.

## 🛠️ Requisitos e Configuração

  * **Java Development Kit (JDK)**: Versão 8 ou superior.

### Estrutura de Pastas Esperada para Palavras

Para que o jogo funcione corretamente, você precisará de arquivos de texto contendo as palavras para cada categoria. Crie um arquivo na raiz do seu projeto `.txt` com o nome da categoria.
Cada arquivo de texto deve ter uma palavra por linha:

**`animais.txt`**:

```
elefante
girafa
leao
tigre
```

### 📁 Estrutura do Projeto

  * `Main.java`: Classe principal que inicia e gerencia o fluxo do jogo.
  * `Player.java`: Gerencia as informações e estatísticas do jogador (nome, jogos ganhos/perdidos, pontuação).
  * `WordDealer.java`: Responsável por carregar palavras de arquivos e selecionar uma palavra aleatória para o jogo com base na categoria.
  * `LetterDealer.java`: Gerencia a lógica de adivinhação das letras, exibe a palavra oculta, rastreia erros e desenha a forca.
