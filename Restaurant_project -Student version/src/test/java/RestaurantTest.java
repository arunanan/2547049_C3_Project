import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE



    @BeforeEach
    public void setUp_restaurantObject(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        Restaurant spiedRestuarant = Mockito.spy(restaurant);
        Mockito.when(spiedRestuarant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:00") , LocalTime.parse("11:30:00") , LocalTime.parse("22:00:00"));

        assertTrue(spiedRestuarant.isRestaurantOpen());
        assertTrue(spiedRestuarant.isRestaurantOpen());
        assertTrue(spiedRestuarant.isRestaurantOpen());
        //WRITE UNIT TEST CASE HERE
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        Restaurant spiedRestuarant = Mockito.spy(restaurant);
        Mockito.when(spiedRestuarant.getCurrentTime()).thenReturn(LocalTime.parse("10:29:00") , LocalTime.parse("22:01:00") , LocalTime.parse("23:59:00"));

        assertFalse(spiedRestuarant.isRestaurantOpen());
        assertFalse(spiedRestuarant.isRestaurantOpen());
        assertFalse(spiedRestuarant.isRestaurantOpen());

        //WRITE UNIT TEST CASE HERE

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void calculate_total_order_value_when_items_from_the_menu_is_Selected(){
        List<String> items = Arrays.asList("Sweet corn soup" , "Vegetable lasagne");


        assertEquals(388, restaurant.calculateOrderValue(items));
    }

    @Test
    public void throw_exception_when_item_that_does_not_exist_from_the_menu_is_selected_to_calculate_order_value() {
        List<String> items = Arrays.asList("Sweet corn soup", "Vegetable lasagne", "veg burger");

        assertThrows(itemNotFoundException.class, () -> restaurant.calculateOrderValue(items));

    }
}