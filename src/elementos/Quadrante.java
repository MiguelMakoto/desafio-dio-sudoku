package elementos;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.Set;
import java.util.HashSet;


public class Quadrante {
    private List<List<Celula>> matrizCelulas;
    private Supplier<Celula> novaCelula = () -> new Celula(0, false, true);
    private Supplier<List<Celula>> novaLinha = () -> Stream.generate(novaCelula).limit(3).toList();
    
    public Quadrante() {
        matrizCelulas = Stream.generate(novaLinha).limit(3).toList();
    }

    public List<List<Celula>> getConteudo() {
        return matrizCelulas;
    }

    public boolean estaCheio() {
        for (List<Celula> linha : matrizCelulas) {
            for (Celula c : linha) {
                if (c.isVazio()) return false;
            }
        }
        return true;
    }

    public void adicionarValorFixo(int valor, int linha, int coluna) {
        List<Celula> linhaAlvo = matrizCelulas.get(linha);
        Celula celulaAlvo = linhaAlvo.get(coluna);

        if (celulaAlvo.isVazio()) {
            celulaAlvo.setValor(valor);
            celulaAlvo.setFixo(true);
            celulaAlvo.setVazio(false);
        }
    }

    public boolean adicionarValor(int valor, int linha, int coluna) {
        List<Celula> linhaAlvo = matrizCelulas.get(linha);
        Celula celulaAlvo = linhaAlvo.get(coluna);

        if (celulaAlvo.isFixo() || !celulaAlvo.isVazio())
            return false;
        
        celulaAlvo.setValor(valor);
        celulaAlvo.setVazio(false);
        return true;
    }

    public boolean removerValor(int linha, int coluna) {
        List<Celula> linhaAlvo = matrizCelulas.get(linha);
        Celula celulaAlvo = linhaAlvo.get(coluna);

        if (celulaAlvo.isVazio() || celulaAlvo.isFixo())
            return false;

        celulaAlvo.setValor(0);
        celulaAlvo.setVazio(true);
        return true;
    }
     
    @Override
    public String toString() {
        String retorno = "";
        for (List<Celula> linha : matrizCelulas) {
            for (Celula c : linha) {
                retorno += String.format("%d ", c.getValor());
            }
            retorno += "\n";
        }
        return retorno;
    }

    //funcao auxiliar ao toString do tabuleiro
    public String imprimirLinha(int i) {
        String retorno = "";
        for (Celula c : matrizCelulas.get(i))
            retorno += c.toString() + " ";
        return retorno;
    }

    public boolean validar() {
        if (!estaCheio())
            return false;
        
        Set<Integer> valores = new HashSet<>();

        for (List<Celula> linha : matrizCelulas) {
            for (Celula c : linha) {
                if (!valores.add(c.getValor()))
                    return false;
            }
        }
        
        return true;
    }
    
}
