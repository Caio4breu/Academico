package Semestre1.JogoForca;

public class SistemaJogo {
    public static void main(String[] args) {
        Forca regras = new Forca();

        regras.intro();
        regras.tutorial();
        regras.modoJogo();
        regras.contandoLetras();
        regras.jogo();
    }
}