
package classes;

/**
 *
 * @author carol
 */
public enum Dificuldade {
    FACIL, NORMAL, DIFICIL;

    public int getTamanhoPadraoNavio() {
        return switch (this) {
            case FACIL -> 1;
            case NORMAL -> 0;
            case DIFICIL -> -1;
            default -> 0;
        };
    }
}
