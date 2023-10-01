import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    public void givenHorsesIsNull_Constructor_ThenCorrectExceptionAndMassage(){
        Throwable exception =  assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void givenHorsesIsEmpty_Constructor_ThenCorrectExceptionAndMassage(){
        List<Horse> horses = new ArrayList<>();
        Throwable exception =  assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ThenSameHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i=1; i<=30; i++){
            horses.add(new Horse("i", i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move_ThenAllHorsesMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i=0; i<50; i++){
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse:horses){
            verify(horse).move();
        }
    }

    @Test
    void getWinner_ThenReturnHorseWithMaxDistant() {
        List<Horse> horses = new ArrayList<>();
        for (int i=1; i<=5; i++){
            horses.add(new Horse("Horse"+i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses.get(4), hippodrome.getWinner());
    }
}