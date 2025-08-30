package elementos;

public class Celula {
    private int valor;
    private boolean fixo;
    private boolean vazio;

    public Celula(int valor, boolean fixo, boolean vazio) {
        this.valor = valor;
        this.fixo = fixo;
        this.vazio = vazio;
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

    public void setVazio(boolean vazio) {
        this.vazio = vazio;
    }
    public boolean isVazio() {
        return vazio;
    }

    @Override
    public String toString() {
        return String.format("%d", valor);
    }
}
