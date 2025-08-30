import elementos.*;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int resposta = 0;
        String entradaString;
        //registro dos argumentos de linha de comando numa lista
        List<EntradaNum> entradaPrompt = new ArrayList<>();
        
        for (String arg : args) {
            int valor = Integer.parseInt(arg.substring(4, 5));
            int linha = Integer.parseInt(arg.substring(0, 1)) + 1;
            int coluna = Integer.parseInt(arg.substring(2, 3)) + 1;
            String fixo = arg.substring(5);
            
            entradaPrompt.add(new EntradaNum(valor, linha, coluna, fixo.equalsIgnoreCase("True") ? true : false ));
        }
        
        //iniciacao da interface
        InterfaceSudoku interfaceApp = new InterfaceSudoku(entradaPrompt);

        System.out.println("Bem-vindo ao Sudoku!");
        interfaceApp.mostrarJogo();
        
        //menu
        do {
            do {
                System.out.println("O que deseja fazer?\n1 - adicionar numero\n2 - remover numero\n3 - finalizar\n4 - reiniciar\n5 - sair");
                entradaString = input.nextLine();
            } while (!ehNumerico(entradaString));
            resposta = Integer.parseInt(entradaString);

            switch (resposta) {
                case 1 -> { //adicionar
                    System.out.println("Insira no formato: \nValor,Linha,Coluna");
                    String[] inserir;
                    do {
                        entradaString = input.nextLine();
                        inserir = entradaString.split(",");
                    } while (!ehNumerico(inserir));

                    System.out.println(interfaceApp.adicionarElemento(new EntradaNum(Integer.parseInt(inserir[0]),
                                                        Integer.parseInt(inserir[1]), Integer.parseInt(inserir[2])))
                    ? "\nValor adicionado." : "\nValor nao adicionado");
                }
                case 2 -> { //remover
                    System.out.println("Insira no formato: \nLinha,Coluna");
                    String[] inserir;
                    do {
                        entradaString = input.nextLine();
                        inserir = entradaString.split(",");
                    } while (!ehNumerico(inserir));

                    System.out.println(interfaceApp.removerElemento(Integer.parseInt(inserir[0]), Integer.parseInt(inserir[1]))
                    ? "\nValor removido.\n" : "\nValor nao removido.\n");
                }
                case 3 -> { //finalizar
                    System.out.println(interfaceApp.finalizar()
                    ? "\nJOGO CONCLUIDO COM SUCESSO!\n" : "\nJogo nao concluido, revise.");
                    if (!interfaceApp.isJogo_iniciado()) {
                        interfaceApp.reiniciar();
                        System.out.println("\nJogo reiniciado.");
                    }
                }
                case 4 -> { //reiniciar
                    System.out.println("Você tem certeza? (s/n)");
                    entradaString = input.nextLine();
                    if (entradaString.startsWith("s")) {
                        interfaceApp.reiniciar();
                        System.out.println("\nJogo reiniciado.");
                    }
                    else 
                        System.out.println("Jogo não reiniciado.");
                }
                case 5 -> { //sair
                    System.out.println("Você tem certeza? (s/n)");
                    entradaString = input.nextLine();
                    if (!entradaString.startsWith("s")) {
                        resposta = 0;
                    }
                }
            }

            System.out.println();
            interfaceApp.mostrarJogo();

        } while (resposta != 5);

        System.out.println("Jogo abortado. Tchau!");

        input.close();
        System.exit(0);
    }

    //funcoes para validacao de entrada numerica
    public static boolean ehNumerico(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            System.out.println("Formatacao incorreta! Insira um numero.\n");
            return false;
        }
    }

    public static boolean ehNumerico(String[] ss) {
        for (int i = 0; i < ss.length; i++) {
            try {
                Integer.parseInt(ss[i]);
            }
            catch (NumberFormatException e) {
                System.out.println("Formatacao incorreta! Insira um numero.");
                return false;
            }
        }
        return true;
    }
}