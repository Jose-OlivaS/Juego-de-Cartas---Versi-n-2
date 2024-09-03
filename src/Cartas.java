public class Cartas {
    private String valor; //El valor que tiene la carta, sea As, 2, 3, 4...
    private String simbolo; //Corazones, Espadas, Trébol y Diamante

    public Cartas(String valor, String simbolo) {
        this.valor = valor;
        this.simbolo = simbolo;
    }

    public String getValor() {
        return valor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public int getValorNumerico(int currentTotal){
        switch (valor){
            case "As": // Si el total actual con el As supera 21, entonces el As debe contar como 1
                return currentTotal + 11 > 21 ? 1 : 11;
            case "J":
            case "Q":
            case "K":
                return 10;
            default:
                return Integer.parseInt(valor);
        }
    }

    @Override
    public String toString(){
        return valor + " de " + simbolo;
    }
}
