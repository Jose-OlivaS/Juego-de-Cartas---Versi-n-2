import java.util.Scanner;

public class JuegoDeCartas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el número de jugadores:");
        int numJugadores = sc.nextInt();

        Juego juego = new Juego(numJugadores);
        juego.jugar();
    }
}
