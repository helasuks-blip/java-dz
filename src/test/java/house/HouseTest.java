package house;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    private House house;
    private OfficeBuilding office;
    private ShoppingCenter mall;

    @BeforeEach
    void setUp() {
        house  = new House(100, 4, 85000);
        office = new OfficeBuilding(500, 20, 120000, 150);
        mall   = new ShoppingCenter(2000, 10, 150000, 40, 300);
    }

    @Test
    void testBuild() {
        //метод build() устанавливает новые значения площади и количества комнат
        house.build(120, 5);
        assertEquals(120, house.area, 0.0001);
        assertEquals(5, house.getRoomsCount());
    }

    @Test
    void testPricePerSquareMeter() {
        //метод pricePerSquareMeter() возвращает цену за 1 м²
        assertEquals(85000, house.pricePerSquareMeter(), 0.0001);
    }

    @Test
    void testGetRoomsCount() {
        //метод getRoomsCount() возвращает корректное число комнат
        assertEquals(4, house.getRoomsCount());
    }

    @Test
    void testIncreaseAreaPositive() {
        //метод increaseArea() увеличивает площадь, если передано положительное число
        house.increaseArea(20);
        assertEquals(120, house.area, 0.0001);
    }

    @Test
    void testIncreaseAreaNegativeDoesNothing() {
        //метод increaseArea() НЕ меняет площадь, если передано отрицательное число
        house.increaseArea(-10);
        assertEquals(100, house.area, 0.0001);
    }

    @Test
    void testRent() {
        //метод rent() меняет статус дома на "сдан в аренду"
        house.rent();
        assertTrue(house.isRented);
    }

    @Test
    void testRenovateValidRoom() {
        //метод renovate() работает без ошибок, если комната существует
        assertDoesNotThrow(() -> house.renovate(2));
    }

    @Test
    void testRenovateInvalidRoom() {
        //метод renovate() не вызывает ошибок (не ломает программу), если комнаты нет
        assertDoesNotThrow(() -> house.renovate(10));
    }


    @Test
    void testRentOffice() {
        //специфичный метод rentOffice() сдает офис и работает без ошибок
        assertDoesNotThrow(() -> office.rentOffice(7));
    }

    @Test
    void testOpenNewShop() {
        //специфичный метод openNewShop() открывает магазин и работает без ошибок
        assertDoesNotThrow(() -> mall.openNewShop("Пятёрочка"));
        assertDoesNotThrow(() -> mall.openNewShop("DNS"));
    }

    @Test
    void testShowParkingInfo() {
        // специфичный метод showParkingInfo() выводит информацию и не ломает программу
        assertDoesNotThrow(() -> mall.showParkingInfo());
    }
}