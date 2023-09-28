import java.util.Scanner;
public class Hotel {
    private int[][] habitaciones;
    private Scanner scanner;
    public static void main(String[] args){
        Hotel hotel = new Hotel();

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

    public void vaciarHabitacion(int habitacion) {
        cambiarEstadoHabitacion(habitacion, 0);
        asignarComidas(habitacion, 0);
        establecerDias(habitacion, 0);
        System.out.println("la habitación n°" + habitacion + " ha sido desocupada exitosamente.");
    }

    public void imprimirBoleta(int habitacion, int total) {
        System.out.println("\nBoleta de la habitación " + habitacion + ":");
        String comidas = habitaciones[habitacion][1] == 0 ? "Sin comidas" : "Con comidas";
        System.out.println(comidas);
        System.out.println("Cantidad de noches: " + habitaciones[habitacion][2]);
        System.out.println("Monto total a pagar: $" + total);
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

}
