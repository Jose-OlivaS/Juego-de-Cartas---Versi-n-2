import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Juego {
    private List<Jugador> jugadores;
    private List<Cartas> cartas;
    private Jugador dealer;

    public Juego(int numeroJugadores) {
        this.jugadores = crearJugadores(numeroJugadores);
        this.dealer = new Jugador("Dealer", 0); // El crupier
    }

    private List<Jugador> crearJugadores(int numeroJugadores) {
        List<Jugador> jugadores = new ArrayList<>();
        for(int i = 0; i < numeroJugadores; i++) {
            jugadores.add(new Jugador("Jugador " + (i + 1), 50));
        }
        return jugadores;
    }

    public List<Cartas> crearBaraja() {
        List<Cartas> baraja = new ArrayList<>();
        String[] simbolos = {"Corazón", "Diamante", "Trébol", "Espadas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for(String simbolo : simbolos) {
            for(String valor : valores) {
                Cartas carta = new Cartas(valor, simbolo);
                baraja.add(carta);
            }
        }
        return baraja;
    }

    private void barajar() {
        Collections.shuffle(cartas);
    }

    private void repartirCartas() {
        for (Jugador jugador : jugadores) {
            jugador.agregarCarta(cartas.remove(0));
            jugador.agregarCarta(cartas.remove(0));
        }
        dealer.agregarCarta(cartas.remove(0));
        dealer.agregarCarta(cartas.remove(0));
    }

    public void jugar() {
        Scanner sc = new Scanner(System.in);
        this.cartas = crearBaraja();
        barajar();
        repartirCartas();

        // Turno de cada jugador
        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getNombre() + ", tu mano es: " + jugador.getCartas());
            while (true) {
                System.out.println("Total actual: " + jugador.getValorTotal());
                System.out.println("¿Quieres otra carta? (1. Sí, 2. No)");
                int eleccion = sc.nextInt();
                if (eleccion == 1) {
                    jugador.agregarCarta(cartas.remove(0));
                    System.out.println("Nueva carta: " + jugador.getCartas().get(jugador.getCartas().size() - 1));
                    if (jugador.isBust()) {
                        System.out.println(jugador.getNombre() + " se pasó con " + jugador.getValorTotal());
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        // Turno del dealer
        while (dealer.getValorTotal() < 17) {
            dealer.agregarCarta(cartas.remove(0));
        }
        System.out.println("Dealer tiene " + dealer.getCartas() + " con un total de " + dealer.getValorTotal());

        // Determinar ganador
        for (Jugador jugador : jugadores) {
            if (!jugador.isBust()) {
                if (dealer.isBust() || jugador.getValorTotal() > dealer.getValorTotal()) {
                    System.out.println(jugador.getNombre() + " gana con " + jugador.getValorTotal());
                } else if (jugador.getValorTotal() < dealer.getValorTotal()) {
                    System.out.println(jugador.getNombre() + " pierde contra el dealer");
                } else {
                    System.out.println(jugador.getNombre() + " empata con el dealer");
                }
            } else {
                System.out.println(jugador.getNombre() + " ha perdido por pasarse.");
            }
        }
        sc.close();
    }
}
