package elementos;
import java.util.List;

public class InterfaceSudoku {
    private boolean jogo_iniciado = false;
    private Tabuleiro tabuleiro;
    private List<EntradaNum> numerosIniciais; //toda vez que o tabuleiro for reiniciado, eh usada essa lista

    public InterfaceSudoku(List<EntradaNum> numerosIniciais) {
        tabuleiro = new Tabuleiro();
        this.numerosIniciais = numerosIniciais;
        for (EntradaNum n : numerosIniciais) {
            if (validacao(n)) {
                tabuleiro.adicionarValor(n.getValor(), n.getLinha(), n.getColuna(), n.isFixo());
            }
        }
        
        jogo_iniciado = true;
    }
    
    public boolean finalizar() {
        if (tabuleiro.estaCheio() && tabuleiro.validar()) {
            jogo_iniciado = false;
            return true;
        }

        return false;
    }
    
    public void reiniciar() {
        tabuleiro = new Tabuleiro();
        for (EntradaNum n: numerosIniciais) {
            if (validacao(n)) {
                tabuleiro.adicionarValor(n.getValor(), n.getLinha(), n.getColuna(), n.isFixo());
            }
        }
        jogo_iniciado = true;
    }

    public boolean adicionarElemento(EntradaNum n) {
        if (validacao(n))
            return tabuleiro.adicionarValor(n.getValor(), n.getLinha(), n.getColuna(), n.isFixo());
        
        return false;
    }

    public boolean removerElemento(int linha, int coluna) {
        if (validacao(new EntradaNum(1, linha, coluna))) {
            return tabuleiro.removerElemento(linha, coluna);
        }

        return false;
    }

    public void mostrarJogo() {
        System.out.println(tabuleiro.toString());
    }

    public boolean isJogo_iniciado() {
        return jogo_iniciado;
    }

    private boolean validacao(EntradaNum n) {
        return n.getValor() > 0 && n.getValor() <= 9 &&
            n.getLinha() >= 0 && n.getLinha() < 10 && n.getColuna() >= 0 && n.getColuna() < 10;
    }

}
