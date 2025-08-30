package elementos;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.Set;
import java.util.HashSet;

public class Tabuleiro {
    private List<List<Quadrante>> matrizQuadrantes;
    private Supplier<Quadrante> novoQuadrante = () -> new Quadrante();
    private Supplier<List<Quadrante>> novaLinhaQuadrante = () -> Stream.generate(novoQuadrante).limit(3).toList();

    public Tabuleiro() {
        matrizQuadrantes = Stream.generate(novaLinhaQuadrante).limit(3).toList();
    }

    public boolean estaCheio() {
        for (List<Quadrante> linha : matrizQuadrantes) {
            for (Quadrante q : linha) {
                if (!q.estaCheio()) return false;
            }
        }
        return true;
    }

    public boolean adicionarValor(int valor, int linha, int coluna, boolean fixo) {
        //transforma o indice inserido pelo usuario num indice de um quadrante
        int linhaParaQuadrante = (int)Math.ceil(linha / 3.0) - 1;
        int colunaParaQuadrante = (int) Math.ceil(coluna / 3.0) - 1;
        //System.out.println("Linha: " + linhaParaQuadrante + " Coluna: " + colunaParaQuadrante);

        Quadrante quadranteAlvo = matrizQuadrantes.get(linhaParaQuadrante).get(colunaParaQuadrante);
        if (!quadranteAlvo.estaCheio()) {
            //"traduz" as linhas para o tamanho de um quadrante
            int linhaAlvo = 2;
            int colunaAlvo = 2;

            if (List.of(1, 4, 7).contains(linha)) linhaAlvo = 0;
            else if (List.of(2, 5, 8).contains(linha)) linhaAlvo = 1;
            
            if (List.of(1, 4, 7).contains(coluna)) colunaAlvo = 0;
            else if (List.of(2, 5, 8).contains(coluna)) colunaAlvo = 1;

            if (fixo) {
                quadranteAlvo.adicionarValorFixo(valor, linhaAlvo, colunaAlvo);
                return true;
            }

            return quadranteAlvo.adicionarValor(valor, linhaAlvo, colunaAlvo);
        }

        return false;
    }

    public boolean removerElemento(int linha, int coluna) {
        //transforma o indice inserido pelo usuario num indice de um quadrante
        int linhaParaQuadrante = (int)Math.ceil(linha / 3.0) - 1;
        int colunaParaQuadrante = (int) Math.ceil(coluna / 3.0) - 1;

        Quadrante quadranteAlvo = matrizQuadrantes.get(linhaParaQuadrante).get(colunaParaQuadrante);
        //"traduz" as linhas para o tamanho de um quadrante
        int linhaAlvo = 2;
        int colunaAlvo = 2;

        if (List.of(1, 4, 7).contains(linha)) linhaAlvo = 0;
        else if (List.of(2, 5, 8).contains(linha)) linhaAlvo = 1;
        
        if (List.of(1, 4, 7).contains(coluna)) colunaAlvo = 0;
        else if (List.of(2, 5, 8).contains(coluna)) colunaAlvo = 1;

        return quadranteAlvo.removerValor(linhaAlvo, colunaAlvo);
    }

    @Override
    public String toString() {
        List<String> tabuleiroToString = new ArrayList<>();
        
        //itera por todas as linhas do tabuleiro
        for (List<Quadrante> linhaTabuleiro : matrizQuadrantes)
        {
            //itera por todas as linhas do tabuleiro, ao inves de cada linha de uma matriz
            for (int i = 0; i < 3; i++) {
                String linhaAtual = "";
                for (Quadrante q : linhaTabuleiro) {
                    linhaAtual += q.imprimirLinha(i) + "  ";
                }
                linhaAtual += "\n";
                tabuleiroToString.add(linhaAtual);
            }
            tabuleiroToString.add("\n");
        }

        //variavel para segurar o tabuleiro completo
        String retorno = "";
        for (String linha : tabuleiroToString) {
            retorno += linha;
        }

        return retorno;
    }
    
    public boolean validar() {
        if (!estaCheio())
            return false;

        Set<Integer> valores = new HashSet<>();
        for (List<Quadrante> linhaQ : matrizQuadrantes) {
            for (int i = 0; i < 3; i++) {
                valores.clear();
                for (Quadrante q : linhaQ) {
                    for (Celula c : q.getConteudo().get(i)) {
                        if (!valores.add(c.getValor()))
                            return false;
                    }
                }
            }
        }

        for (List<Quadrante> linha : matrizQuadrantes) {
            for (Quadrante q : linha) {
                if (!q.validar())
                    return false;
            }
        }

        return true;
    }
}
