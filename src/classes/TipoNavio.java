package classes;

/**
 *
 * @author carol
 */
public enum TipoNavio {
    PORTAAVIOES, 
    NAVIOTANQUE, 
    CONTRATORPEDO, 
    SUBMARINO;

    public String get() {
        return switch (this) {
            case PORTAAVIOES ->
                "Navio Porta-Avioes";
            case NAVIOTANQUE ->
                "Navio-Tanque";
            case CONTRATORPEDO ->
                "Navio Contra-Torpedo";
            case SUBMARINO ->
                "Submarino";
            default ->
                "Não existe";
        };
    }

    public int getTamanhoPadraoNavio() {
        return switch (this) {
            case PORTAAVIOES ->
                5;
            case NAVIOTANQUE ->
                4;
            case CONTRATORPEDO ->
                3;
            case SUBMARINO ->
                2;
            default ->
                2;
        };
    }
}
