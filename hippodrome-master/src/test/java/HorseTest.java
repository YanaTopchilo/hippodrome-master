import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {


    @Test
    public void givenNameIsNull_Constructor_ThenCorrectExceptionAndMassage(){
        Throwable exception =  assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 10, 10));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "   ", "\t" })
    public void givenNameIsSpaceOrTabulation_ThenCorrectExceptionAndMassage(String name){
        Throwable exception =  assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 10, 10));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void giveNegatineNumber_Constructor_ThenCorrectExceptionAndMassage(){
        Throwable exception1 =  assertThrows(IllegalArgumentException.class, () ->
                new Horse("Horse1", -1, 10));
        assertEquals("Speed cannot be negative.", exception1.getMessage());
        Throwable exception2 =  assertThrows(IllegalArgumentException.class, () ->
                new Horse("Horse2", 10, -1));
        assertEquals("Distance cannot be negative.", exception2.getMessage());
    }

    @Test
    void getName_ThenReturnName() {
       Horse horse = new Horse("Horse", 10, 10);
       assertEquals("Horse",horse.getName());
    }

    @Test
    void getSpeed_ThenReturnSpeed() {
        Horse horse = new Horse("Horse", 10, 11);
        assertEquals(10,horse.getSpeed());
    }

    @Test
    void getDistance_ThenReturnDistance() {
        Horse horse = new Horse("Horse", 10, 11);
        assertEquals(11,horse.getDistance());
        Horse horse2 = new Horse("Horse2", 10);
        assertEquals(0, horse2.getDistance());
    }

    @Test
    void move_ThenUseRandom() {
        try  (MockedStatic <Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("Horse", 10, 10).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"2,3","4,5","6,7",})
    void move_ThenUseСorrectFormula(double speed, double distance) {
        Horse horse = new Horse("Horse", speed, distance);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(3.0);
            double correctResult = distance + speed * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(correctResult, horse.getDistance());
        }
    }
}