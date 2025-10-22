package Semestre1.JogoForca;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Forca {
    Scanner leia = new Scanner(System.in);
    Random random = new Random();

    protected String nome;
    int modo, letraErrada = 0, letraContada = 0;
    public Forca(){}
    
    

    public String palavraEscolhida, forcaVitoria;
    private char[] palavraEscondida;
    private char novaLetra;
    private ArrayList<Character> letrasUsadas = new ArrayList<>();

    // ideia? ingles
    public void intro(){ // INTRODUÇÃO -------------------------------------
        System.out.println(
            "_______________\n" +
            "|   __________|\n" +
            "|  |       |\n" +
            "|  |       |\n" +
            "|  |       0\n" +
            "|  |        \n" +
            "|  |        \n" +
            "|  |        \n" +
            "|  |______________\n" +
            "|_________________\\" + "\n\n" +

            "|-------- FORCA --------|"
        );
    }

    public void tutorial(){ // TUTORIAL -------------------------------------
        boolean YorNtutorial = true;
        String respostaTutorial;
        System.out.println("\n\n\nDeseja ler o Tutorial? (S/N)");
        respostaTutorial = leia.nextLine();
        
        do {
            if (respostaTutorial.equalsIgnoreCase("S")) {
                System.out.println(
                    "[TUTORIAL]\n\n"+

                    "O objetivo do(a) jogador(a) é encontrar a palavra e salvar a personagem da corda.\n\n" +

                    "O jogo irá escolher aleatoriamente uma palavra para que o jogador descubra\n\n" +

                    "O(a) jogador(a) possui seis (6) tentativas antes de perder o jogo, e ser virtualmente enforcado pelo fim de jogo\n\n"
                );
                System.out.println("Deseja reler o tutorial?");
                respostaTutorial = leia.nextLine();
                if (respostaTutorial.equalsIgnoreCase("N")) {
                    YorNtutorial = false;
                    System.out.println("Boa Sorte!");
                }
            } else {
                YorNtutorial = false;
                System.out.println("Boa Sorte!");
            }
        } while (YorNtutorial);
    }

    // planos para futuros updates
    // public void recebeNome(String nome){ // RECEBE NOME
    //     this.nome = nome;
    //     System.out.println("Jogador(a), qual é seu nome?");
    //     this.nome = leia.nextLine();
    // }
    // planos para futuros updates

    public void modoJogo(){ // DIFICULDADE -------------------------------
        boolean repeteModo = true;

        do {
            System.out.println(
            "[{---- DIFICULDADE ----}]\n" +
            "[1] FÁCIL\n" +
            "[2] MÉDIO\n" +
            "[3] DÍFICIL\n\n"
        );
        modo = leia.nextInt();
        leia.nextLine();

        switch (modo) {
            case 1:
                System.out.println("Dificuldade FÁCIL escolhida\n\n");
                System.out.println("Palavra aleatoria sendo gerada...\n");
                
                ArrayList<String> listaFacil = new ArrayList<>();
                    listaFacil.add("abelha");
                    listaFacil.add("abacate");
                    listaFacil.add("banana");
                    listaFacil.add("dia");
                    listaFacil.add("uva");
            
                palavraEscolhida = listaFacil.get(random.nextInt(listaFacil.size()));
                repeteModo = false;
                break;
            case 2:
                System.out.println("Dificuldade MÉDIA escolhida\n\n");
                System.out.println("Palavra aleatoria sendo gerada...\n");
                ArrayList<String> listaMedia = new ArrayList<>();
                    listaMedia.add("carnaval");
                    listaMedia.add("discoteca");
                    listaMedia.add("cachimbo");
                    listaMedia.add("combo");
                    listaMedia.add("aranha");
            
                palavraEscolhida = listaMedia.get(random.nextInt(listaMedia.size()));
                repeteModo = false;
                break;
            case 3:
                System.out.println("Dificuldade DIFÍCIL escolhida\n\n");
                System.out.println("Palavra aleatoria sendo gerada...\n");
                ArrayList<String> listaDificil = new ArrayList<>();
                    listaDificil.add("catacumba");
                    listaDificil.add("morcego");
                    listaDificil.add("muralha");
                    listaDificil.add("fantasma");
                    listaDificil.add("justiça");
            
                palavraEscolhida = listaDificil.get(random.nextInt(listaDificil.size()));
                repeteModo = false;
                break;
            default:
                System.out.println("Erro: valor invalido");
                break;
            }
        } while (repeteModo);
        contandoLetras();
    }

    public char[] contandoLetras(){ // ESCONDE PALAVRA -----------------------------        
        palavraEscondida = new char[palavraEscolhida.length()];

        for (int i = 0; i < palavraEscolhida.length(); i++) {
            palavraEscondida[i] = '_';
        }
        return palavraEscondida;

    }

    public boolean contandoVitoria(){ // Conta se falta letras para concluir a contagem -----------------------------        
        // palavraEscondida = new char[palavraEscolhida.length()];
        int contadorUnderline = 0;
        for (int i = 0; i < palavraEscolhida.length(); i++) {
            if (palavraEscondida[i] == '_') {
                contadorUnderline++;
            }
        }
        if (contadorUnderline == 0) {
            // CONTINUA AQUI
            letraErrada = 10;
            enforcado();
        }
        return contadorUnderline == 0;

    }

    public void mostrarProgresso() {
        for (int i = 0; i < palavraEscondida.length; i++) {
            System.out.print(palavraEscondida[i] + " ");
        }
        System.out.println("\n");
    }

    public void erro(){ // Registra uma letra errada
        letraErrada++;
    }

    public void jogo(){ // CONTEUDO DO JOGO -----------------------------------------
        while (letraErrada < 6) {
            pedeLetra();
            if (contandoVitoria()) {
            System.out.println("Parabéns! Você venceu!");
            enforcado(); // mostra a forca final
            break;
        }
        enforcado();

        }
    }

    public void pedeLetra(){ // Registra a letra que foi usada pelo jogador
        novaLetra = verificadorLetras();
        
        letrasUsadas.add(novaLetra);

        if (palavraEscolhida.contains(String.valueOf(novaLetra))) { // Se na palavra do jogo contem a letra inserida pelo jogador
            for (int i = 0; i < palavraEscolhida.length(); i++) {
                if (palavraEscolhida.charAt(i) == novaLetra) {
                    palavraEscondida[i] = novaLetra;
                }
            }
        } else { // se a letra nao existir na palavra:
            this.erro();
            enforcado();
        }
    }

    public void listaUsada(){ // lista completa com todas as letras usadas
        for (char letra : letrasUsadas) {
            System.out.print(letra + " ");
        }
        System.out.println("\n");
    }

    public char verificadorLetras(){
        boolean letraValida = false;
        String entrada;
        
        do {
            System.out.println("Escreva uma nova letra: ");
            entrada = leia.nextLine().toLowerCase();

            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("Erro: Digite somente uma letra");
            } else {
                novaLetra = entrada.charAt(0);
                
                if (letrasUsadas.contains(novaLetra)) {
                    System.out.println("Essa letra já foi usada");
                    listaUsada();
                } else {
                    if (!palavraEscolhida.contains(String.valueOf(novaLetra))) {
                        System.out.println("Letra incorreta");
                        listaUsada();
                    } else {
                        letraValida = true;
                        return novaLetra;
                    }
                }
            }
        } while (!letraValida);
        return ' '; // nao chega aqui, mas é necesario para compilar
    }

    public int enforcado(){
        switch (letraErrada) {
            case 0:
                forcaVitoria = 
                    "_______________\n" +
                    "|   __________|\n" +
                    "|  |       |\n" +
                    "|  |       |\n" +
                    "|  |       0\n" +
                    "|  |        \n" +
                    "|  |        \n" +
                    "|  |        \n" +
                    "|  |______________\n" +
                    "|_________________\\" + "\n\n";
                System.out.println(forcaVitoria);
                mostrarProgresso();
                break;
            case 1:
                forcaVitoria = // Erro 1: Cabeça
                    "_______________\n" +
                    "|   __________|\n" +
                    "|  |       |\n" +
                    "|  |       |\n" +
                    "|  |       O\n" +
                    "|  |        \n" +
                    "|  |        \n" +
                    "|  |        \n" +
                    "|  |______________\n" +
                    "|_________________\\" + "\n\n";
                System.out.println(forcaVitoria);
                mostrarProgresso();
                break;
            case 2:
                forcaVitoria = // Erro 2: Tronco
                    "_______________\n" +
                    "|   __________|\n" +
                    "|  |       |\n" +
                    "|  |       |\n" +
                    "|  |       O\n" +
                    "|  |       )\n" +
                    "|  |        \n" +
                    "|  |        \n" +
                    "|  |______________\n" +
                    "|_________________\\" + "\n\n";
                System.out.println(forcaVitoria);
                mostrarProgresso();
                break;
            case 3:
                forcaVitoria =  // Erro 3: Braço Direito
                    "_______________\n" +
                    "|   __________|\n" +
                    "|  |       |\n" +
                    "|  |       |\n" +
                    "|  |       O\n" +
                    "|  |       )\\\n" +
                    "|  |        \n" +
                    "|  |        \n" +
                    "|  |______________\n" +
                    "|_________________\\" + "\n\n";        
                System.out.println(forcaVitoria);
                mostrarProgresso();
                break;
            case 4:
                forcaVitoria =  // Erro 4: Braço Esquerdo
                    "_______________\n" +
                    "|   __________|\n" +
                    "|  |       |\n" +
                    "|  |       |\n" +
                    "|  |       O\n" +
                    "|  |      /)\\\n" +
                    "|  |        \n" +
                    "|  |        \n" +
                    "|  |______________\n" +
                    "|_________________\\" + "\n\n";
                System.out.println(forcaVitoria);
                mostrarProgresso();
                break;
            case 5:
                forcaVitoria =  // Erro 5: Pé Esquerdo
                    "_______________\n" +
                    "|   __________|\n" +
                    "|  |       |\n" +
                    "|  |       |\n" +
                    "|  |       O\n" +
                    "|  |      /)\\\n" +
                    "|  |      / \n" +
                    "|  |        \n" +
                    "|  |______________\n" +
                    "|_________________\\" + "\n\n";
                System.out.println(forcaVitoria);
                mostrarProgresso();
                break;
            case 6:
                forcaVitoria = // Erro 6: Pé Direito FINAL
                    "_______________\n" +
                    "|   __________|\n" +
                    "|  |       |\n" +
                    "|  |       |\n" +
                    "|  |       O\n" +
                    "|  |      /)\\\n" +
                    "|  |      / \\\n" +
                    "|  |        \n" +
                    "|  |______________\n" +
                    "|_________________\\" + "\n\n";            
                System.out.println(forcaVitoria);
                System.out.println("|-------- FIM DE JOGO --------|\n");
                mostrarProgresso();
                System.out.println(palavraEscolhida); // deixar bonito depois!!!
                break;
            case 10: // caso vitoria, depois vejo o que fazer
                System.out.println(forcaVitoria);
                mostrarProgresso();
                System.out.println("Palavra " + palavraEscolhida + " encontrada!");
                break;
            default:
                break;
        }
        return letraErrada;
    }
}