package elementos;

public class EntradaNum {
    private boolean fixo;
    private int valor, linha, coluna;
    
    public EntradaNum(int valor, int linha, int coluna, boolean fixo) {
        this.valor = valor;
        this.linha = linha;
        this.coluna = coluna;
        this.fixo = fixo;
    }

    public EntradaNum(int valor, int linha, int coluna) {
        this.valor = valor;
        this.linha = linha;
        this.coluna = coluna;
        this.fixo = false;
    }
    
    public int getColuna() {
        return coluna;
    }
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    public int getLinha() {
        return linha;
    }
    public void setLinha(int linha) {
        this.linha = linha;
    }
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public void setFixo(boolean fixo) {
        this.fixo = fixo;
    }
    public boolean isFixo() {
        return fixo;
    }

}
