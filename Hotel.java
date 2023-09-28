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

    public void confirmarReserva(int habitacion) {
        if (habitaciones[habitacion][0] != 2) {
            System.out.println("La habitación " + habitacion + " no está reservada, no se puede confirmar la reserva.");
        } else {
            cambiarEstadoHabitacion(habitacion, 1); // Confirmada
            System.out.println("La reserva de la habitación " + habitacion + " ha sido confirmada exitosamente.");
        }
    }

    public void reservarHabitacion(int habitacion, int opcionComida, int dias) {
        cambiarEstadoHabitacion(habitacion, 2); // Reservado
        asignarComidas(habitacion, opcionComida);
        establecerDias(habitacion, dias);
        System.out.println("La habitación " + habitacion + " fue reservada exitosamente.");
    }

    public void cancelarReservacion(int habitacion) {
        if (habitaciones[habitacion][0] != 2) {
            System.out.println("La habitación " + habitacion + " no está reservada, no hay reservación que cancelar.");
        } else {
            cambiarEstadoHabitacion(habitacion, 0); // Disponible
            System.out.println("La reservación de la habitación " + habitacion + " ha sido cancelada exitosamente.");
        }
    }

    public void ocuparHabitacion(int habitacion, int comida, int dias){
        cambiarEstadoHabitacion(habitacion, 1);
        asignarComidas(habitacion, comida);
        establecerDias(habitacion, dias);
        System.out.println("La Habitacion:" + habitacion + " fue ocupada");
    }

    public void mostrarMenu(){
        System.out.println("Ingrese el numero para la opcion correspondiente");
        System.out.println("1.Para mostrar habitaciones");
        System.out.println("2.Ocupar habitacion");
        System.out.println("3.Reservar habitacion");
        System.out.println("4.Confirmar reserva");
        System.out.println("5.Cancerlar reserva");
        System.out.println("6.Desocupar habitacion");
        System.out.println("7.Reiniciar hotel");
        System.out.println("8.Boleta habitacion");
        System.out.println("9.Salir del sistema");
    }

    public void ejecutarMenu(int opcion) {
        int habitacion = entradaUsuario();
        int estado, opcionComida, dias;
        switch (opcion) {
            case 1:
                imprimirHabitaciones();
                break;
            case 2:
                System.out.println("Ingrese el número de habitación a reservar (0-9):");
                habitacion = scanner.nextInt();
                System.out.println("Seleccione el tipo de reserva (0: Sin comidas, 1: Con comidas):");
                opcionComida = scanner.nextInt();
                System.out.println("Ingrese la cantidad de días:");
                dias = scanner.nextInt();
                ocuparHabitacion(habitacion, opcionComida, dias);
                break;
            case 3:
                System.out.println("Ingrese el número de habitación a ocupar (0-9):");
                habitacion = scanner.nextInt();
                System.out.println("Seleccione el tipo de ocupación (0: Sin comidas, 1: Con comidas):");
                opcionComida = scanner.nextInt();
                System.out.println("Ingrese la cantidad de días:");
                dias = scanner.nextInt();
                reservarHabitacion(habitacion, opcionComida, dias);
                break;
            case 4:
                habitacion = scanner.nextInt();
                confirmarReserva(habitacion);
                break;
            case 5:
                habitacion = scanner.nextInt();
                cancelarReservacion(habitacion);
                break;
            case 6:
                System.out.println("Ingrese el número de habitación a liberar (0-9):");
                habitacion = scanner.nextInt();
                vaciarHabitacion(habitacion);
                break;
            case 7:
                reiniciarHotel();
                break;
            case 8:
                pagarHabitacion(habitacion);
                break;
            case 9:
                System.out.println("Saliendo");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}
