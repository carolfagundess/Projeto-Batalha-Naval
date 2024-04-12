
package classes;

/**
 *
 * @author carol
 */
public class Campo {
    private boolean torpedoLancado;
    private boolean temNavio;
    private Navio navio;

    public Campo() {
        torpedoLancado = false;
        temNavio = false;
    }

    public boolean isTorpedoLancado() {
        return torpedoLancado;
    }

    public void setTorpedoLancado(boolean torpedoLancado) {
        this.torpedoLancado = torpedoLancado;
    }

    public boolean isTemNavio() {
        return temNavio;
    }

    public void setTemNavio(boolean temNavio) {
        this.temNavio = temNavio;
    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
        temNavio = true;
    }


    public String mostra(){
        String string = " ";
        if(torpedoLancado && temNavio){
            string = "x";
        }else if (torpedoLancado == true) {
            string = "o";
        }
        return string;
    }

    @Override
    public String toString() {
        return "Campo{" + "navio=" + navio + '}';
    }
}

