
package classes;

/**
 *
 * @author carol
 */
public class Navio {
    private TipoNavio tipoNavio;
    private int tamanho;
    private boolean afundado;
    private int dano;

    public Navio(TipoNavio tipoNavio, int tamanho) {
        this.tipoNavio = tipoNavio;
        this.tamanho = tamanho;
        dano = 0;
        afundado = false;
    }

    public TipoNavio getTipoNavio() {
        return tipoNavio;
    }

    public int getTamanho() {
        return tamanho;
    }
    
    public int getDano(){
        return dano;
    }
    
    public void atingir(){
        dano +=1;
        if(dano == tamanho){
            afundado = true;
        }
    }
    
    public boolean afundadao(){
        return afundado;
    }

    @Override
    public String toString() {
        return "Navio{" + "tipoNavio=" + tipoNavio + ", tamanho=" + tamanho + '}';
    } 
}
