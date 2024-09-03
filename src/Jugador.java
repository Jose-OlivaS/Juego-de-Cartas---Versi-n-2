import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private float saldo;
    private List<Cartas> cartas;

    public Jugador(String nombre, float saldo){
        this.nombre = nombre;
        this.saldo = saldo;
        this.cartas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cartas> getCartas() {
        return cartas;
    }

    public int getValorTotal() {
        int total = 0;
        for (Cartas carta : cartas) {
            total += carta.getValorNumerico(total);
        }
        return total;
    }

    public void agregarCarta(Cartas carta) {
        cartas.add(carta);
    }

    public boolean isBust() {
        return getValorTotal() > 21;
    }

    public boolean tieneBlackJack() {
        return getValorTotal() == 21 && cartas.size() == 2;
    }

    public void borrarCartas() {
        cartas.clear();
    }

    @Override
    public String toString() {
        return nombre + " tiene " + cartas + " con un total de " + getValorTotal();
    }
}
