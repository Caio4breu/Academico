package Semestre1.JogoSenha;

import java.util.Scanner;
import java.util.Random;

// Jogo da senha, descobrir os numeros corretos com as pocições corretas.
public class SenhaOJogo {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);
        Random random = new Random();

        String nome,YorN;
        boolean jogando = true;

        int[] digital = new int[3];
        int[] digitalUser = new int[3];

        int senhaUsuario;

        int posicaoCorreta, numeroCorreto, numeroTentativa = 3; // Contadores

        // Gerando senha do sistema
        for (int i = 0; i < 3; i++) {
            digital[i] = random.nextInt(10);
        }
        int senhaSistema = (digital[0] * 100) + (digital[1] * 10) + digital[2];
        
        // INTRO -----------------------------------------------------------------------------------

        System.out.println("_-SENHA-_");
        System.out.println("\n" + "Descubra a sequencia de codigos secreta ou tenha o acesso bloqueado para sempre");
        System.out.println("|--------------------|");
        System.out.println("Digite seu nome: ");
        nome = leia.nextLine();

        System.out.println("Você gostaria de ler as regras antes de começar? (S/N)");
        YorN = leia.nextLine();

        // TUTORIAL --------------------------------------------------------------------------------
        
        do {
            if (YorN.equalsIgnoreCase("s")) {
                System.out.println("\n" + "\n");
                System.out.println(
                nome + ", o seu objetivo é descobrir qual é a senha de acesso." + "\n" + 
                "A senha possui três (3) digitos e você têm três (3) chances de acertar" + "\n" +
                "A cada tentativa você recebe algumas dicas, o sistema irá avisar quantos numeros você encontrou e quantos digitos foram colocados na posição correta." + "\n" + "\n" +

                "Por exemplo, a senha do sistema é 321, e você digita 123, a dica será: " + "\n" +
                "Numeros Corretos: 3" + "\n" +
                "Posições Encontradas: 1" + "\n" + "\n" +

                "Cabe a você ler os dados fornecidos e encontrar a resposta correta!" + "\n" + "\n" +
                "Deseja ler as regras novamente? (S/N)"
                );
                YorN = leia.nextLine();
                System.out.println("\n" + "\n");
            } else {
                System.out.println("Boa sorte." + "\n" + "\n");
            }
        } while (YorN.equalsIgnoreCase("s"));
        
        // JOGO -----------------------------------------------------------------------------------
        
        do {
            posicaoCorreta = 0;
            numeroCorreto = 0;
            
            System.out.println("Login: " + nome);
            System.out.println("Senha: ***");
            System.out.println("Digite uma senha de 3 digitos");
            senhaUsuario = leia.nextInt();
            
            // Descobrindo numeros individuais da senha oferecida pelo usuario
            digitalUser[0] = senhaUsuario / 100;
            digitalUser[1] = (senhaUsuario % 100) / 10;
            digitalUser[2] = senhaUsuario % 10;
            
            for (int i = 0; i < 3; i++) { // Encontrando quantidade de posições corretas
                if (digital[i] == digitalUser[i]) {
                    posicaoCorreta++;
                }
            }

            int[] freqSistema = new int[10];
            int[] freqUser = new int[10];

            for (int i = 0; i < 3; i++) {
                freqSistema[digital[i]]++;
                freqUser[digitalUser[i]]++;
            }

            for (int i = 0; i <= 9; i++) { // Encontrando quantos numeros foram encontrados
                numeroCorreto += Math.min(freqSistema[i], freqUser[i]);
            }
            System.out.println("\n" + "\n");
            
            System.out.println("Login: " + nome);
            System.out.println("Senha: " + senhaUsuario);
            System.out.println("\n" + "\n");
    
            System.out.println("Sistema carregando.");
            System.out.println("Sistema carregando..");
            System.out.println("Sistema carregando...");
            
            // CONCLUSÕES -----------------------------------------------------------------------------------
        
            if (posicaoCorreta == 3) { // CENARIO: Senha correta
                System.out.println("Senha correta!");
                System.out.println("\n" + "\n");
                System.out.println("Digitos encontrados: " + numeroCorreto);
                System.out.println("Posições corretas: " + posicaoCorreta);
                System.out.println("Tentativas restantes: " + numeroTentativa);
                jogando = false;
            } else {
                numeroTentativa--;
                if (numeroTentativa > 0) { // CENARIO: incorreto com mais chances
                    System.out.println("Senha inserida pelo usuario: " + senhaUsuario);
                    System.out.println("Senha incorreta");
                    System.out.println("Tentativas restantes: " + numeroCorreto);
                    System.out.println("\n" + "\n");
                    
                    System.out.println("Informações encontradas:");
                    System.out.println("Digitos encontrados: " + numeroCorreto);
                    System.out.println("Posições corretas: " + posicaoCorreta);
                } else { // CENARIO: Fim de Jogo
                    System.out.println("Senha inserida pelo usuario: " + senhaUsuario);
                    System.out.println("Senha incorreta");
                    System.out.println("Tentativas restantes: 0");
                    System.out.println("Senha correta: " + senhaSistema);
                    System.out.println("\n" + "\n");
                    System.out.println("Boa sorte na proxima vez");

                    System.out.println("JOGO ENCERRADO");
                    jogando = false;
                }
            }
        } while (jogando);    
    }
}