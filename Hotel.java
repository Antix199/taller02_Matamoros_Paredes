import java.util.Scanner;

public class Hotel {
    private int[][] habitaciones;
    private Scanner scanner;

    public static void main(String[] args) {
    }

    public Hotel() {
        habitaciones = new int[10][3];
        scanner = new Scanner(System.in);
        inicializarHabitaciones();
    }

    public void inicializarHabitaciones() {
        for (int i = 0; i < 10; i++) {
            habitaciones[i][0] = 0; // Estado: Disponible
            habitaciones[i][1] = 0; // Sin comidas
            habitaciones[i][2] = 0; // Cero días
        }
    }

    public void reservarHabitacion(int habitacion, int opcionComida, int dias) {
        cambiarEstadoHabitacion(habitacion, 2); // Reservado
        asignarComidas(habitacion, opcionComida);
        establecerDias(habitacion, dias);
        System.out.println("Habitación " + habitacion + " reservada exitosamente.");

    }

    public void cancelarReservacion(int habitacion) {
        if (habitaciones[habitacion][0] != 2) {
            System.out.println("La habitación " + habitacion + " no está reservada, no hay reservación que cancelar.");
        } else {
            cambiarEstadoHabitacion(habitacion, 0); // Disponible
            System.out.println("La reservación de la habitación " + habitacion + " ha sido cancelada exitosamente.");
        }
    }

}
