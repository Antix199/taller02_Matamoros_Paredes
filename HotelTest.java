
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelTest {
    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel();
    }
    @Test
    void testInicializarHabitaciones() {
        hotel.inicializarHabitaciones();
        for (int i = 0; i < 10; i++) {
            assertEquals(0, hotel.getEstadoHabitacion(i));
            assertEquals(0, hotel.getComidasHabitacion(i));
            assertEquals(0, hotel.getDiasHabitacion(i));
        }
    }
    @Test
    void testCambiarEstadoHabitacion() {
        hotel.cambiarEstadoHabitacion(5, 1);
        assertEquals(1, hotel.getEstadoHabitacion(5));
    }

    @Test
    void testAsignarComidas() {
        hotel.asignarComidas(3, 1);
        assertEquals(1, hotel.getComidasHabitacion(3));
    }

    @Test
    void teststEsblecerDias() {
        hotel.establecerDias(7, 3);
        assertEquals(3, hotel.getDiasHabitacion(7));
    }

    @Test
    void testVaciarHabitacion() {
        hotel.ocuparHabitacion(6, 0, 2);
        hotel.liberarHabitacion(6);
        assertEquals(0, hotel.getEstadoHabitacion(6));
        assertEquals(0, hotel.getComidasHabitacion(6));
        assertEquals(0, hotel.getDiasHabitacion(6));
    }

    @Test
    void testReiniciarHotel() {
        hotel.ocuparHabitacion(1, 1, 3);
        hotel.reservarHabitacion(2, 0, 5);
        hotel.reiniciarHotel("resetAll");
        for (int i = 0; i < 10; i++) {
            assertEquals(0, hotel.getEstadoHabitacion(i));
            assertEquals(0, hotel.getComidasHabitacion(i));
            assertEquals(0, hotel.getDiasHabitacion(i));
        }
    }
   @Test
    public void testOcuparHabitacion() {
        Hotel hotel = new Hotel();
        int habitacion = 0;
        int comida = 1; // Con comidas
        int dias = 3;

        // Verificar el estado de la habitación antes de ocuparla
        assertEquals(0, getEstadoHabitacion(hotel, habitacion));

        // Ocupar la habitación
        hotel.ocuparHabitacion(habitacion, comida, dias);

        // Verificar el estado de la habitación después de ocuparla
        assertEquals(1, getEstadoHabitacion(hotel, habitacion));
    }

    private int getEstadoHabitacion(Hotel hotel, int habitacion) {
        try {
            Field habitacionesField = Hotel.class.getDeclaredField("habitaciones");
            habitacionesField.setAccessible(true);
            int[][] habitaciones = (int[][]) habitacionesField.get(hotel);
            return habitaciones[habitacion][0];
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1; // Manejar el error de alguna manera
        }
    }
}

