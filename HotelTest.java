import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Field;

public class HotelTest {

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
