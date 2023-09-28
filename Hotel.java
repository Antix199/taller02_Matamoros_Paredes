
import java.util.Scanner;
import java.util.InputMismatchException;
public class Hotel {
    private int[][] habitaciones;
    private Scanner scanner;
    public static void main(String[] args){
        iniciarMain();

    }

    public static void iniciarMain(){
        Hotel hotel = new Hotel();
        int opcion;
        do {
            hotel.mostrarMenu();
            opcion = entradaUsuario();
            hotel.ejecutarMenu(opcion);
        } while (opcion != 9);
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
    // 0 representará disponibilidad, sin comidas y cero días.
    // 1 reperesentara ocupado, con comidas.
    // 2 representará una habitación reservada.
    // Posiciones matriz: 0= estado; 1= comidas; 2= cantidad noches.
    public void cambiarEstadoHabitacion(int habitacion, int estado) {
        habitaciones[habitacion][0] = estado;
    }

    public void asignarComidas(int habitacion, int opcionComida) {
        habitaciones[habitacion][1] = opcionComida;
    }

    public void establecerDias(int habitacion, int dias) {
        habitaciones[habitacion][2] = dias;
    }

    public void imprimirHabitaciones() {
        System.out.println("\nEstado de las habitaciones:");
        for (int i = 0; i < 10; i++) {
            String estado = "";
            switch (habitaciones[i][0]) {
                case 0:
                    estado = "Disponible";
                    break;
                case 1:
                    estado = "Ocupada";
                    break;
                case 2:
                    estado = "Reservada";
                    break;
            }

            String comidas = habitaciones[i][1] == 0 ? "Sin comidas" : "Con comidas";
            int dias = habitaciones[i][2];

            System.out.println("Habitación " + i + ": ESTADO: " + estado + ", " + comidas + ", POR " + dias + " NOCHES.");
        }
        System.out.println();
    }

    public static int entradaUsuario() {
        Scanner scanner = new Scanner(System.in);
        int entradaUsuario = 0;
        try {
            entradaUsuario = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida; debes ingresar un número entero.");
            scanner.nextLine();
        }
        return entradaUsuario;
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


    public void vaciarHabitacion(int habitacion) {
        cambiarEstadoHabitacion(habitacion, 0);
        asignarComidas(habitacion, 0);
        establecerDias(habitacion, 0);
        System.out.println("la habitación n°" + habitacion + " ha sido desocupada exitosamente.");
    }

    public int calcularMontoTotal(int habitacion){
        int precioDiario = habitaciones[habitacion][1] == 0 ? 30000 : 45000;
        int noches = habitaciones[habitacion][2];
        return precioDiario * noches;
    }
    public void imprimirBoleta(int habitacion) {
        int total = calcularMontoTotal(habitacion);
        System.out.println("\nBoleta de la habitación " + habitacion + ":");
        String comidas = habitaciones[habitacion][1] == 0 ? "Sin comidas" : "Con comidas";
        System.out.println(comidas);
        System.out.println("Cantidad de noches: " + habitaciones[habitacion][2]);
        System.out.println("Monto total a pagar: $" + total);
    }

    public void pagarHabitacion(int habitacion){
        if (habitaciones[habitacion][0]==1){
            imprimirBoleta(habitacion);
            System.out.println("¿Desea confirmar el pago de la habitación? /n 1.Confirmar /n 2.Cancelar");
            int confirmacion = entradaUsuario();
            switch (confirmacion) {
                case 1:
                    vaciarHabitacion(habitacion);
                    System.out.println("Pago registrado con éxito, habitación nuevamente disponible.");
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
        else{
            System.out.println("Esta habitación no se encuentra ocupada");
            }
    }

    public void reiniciarHotel() {
        System.out.println("Ingrese la clave para confirmar la operación:");
        String clave = scanner.next();
        if (clave.equals("resetAll")) {
            inicializarHabitaciones();
            System.out.println("Hotel reiniciado exitosamente, todas las habitaciones se encuentran disponibles");
        } else {
            System.out.println("Clave incorrecta. No se ha podido reiniciar el hotel.");
        }
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
        int habitacion;
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
                habitacion = scanner.nextInt();
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
