package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class test {

    private static final double EPS = 1e-9;


    @Nested
    @DisplayName("Конструктор и копирование")
    class ConstructorAndCopy {

        @Test
        @DisplayName("Конструктор сохраняет координаты")
        void constructorStoresCoordinates() {
            Vector v = new Vector(1.0, 2.0, 3.0);
            assertEquals(1.0, v.get(0), EPS);
            assertEquals(2.0, v.get(1), EPS);
            assertEquals(3.0, v.get(2), EPS);
        }

        @Test
        @DisplayName("Конструктор создаёт пустой вектор при отсутствии аргументов")
        void constructorWithNoArgs() {
            Vector v = new Vector();
            assertEquals("[]", v.toString());
        }

        @Test
        @DisplayName("Конструктор делает защитную копию массива")
        void constructorDefensiveCopy() {
            double[] original = {1.0, 2.0, 3.0};
            Vector v = new Vector(original);

            original[0] = 999.0;
            assertEquals(1.0, v.get(0), EPS);
        }

        @Test
        @DisplayName("copy() возвращает независимую копию")
        void copyReturnsIndependentInstance() {
            Vector original = new Vector(1.0, 2.0, 3.0);
            Vector copy = original.copy();

            copy.set(0, 100.0);

            assertEquals(1.0, original.get(0), EPS);
            assertEquals(100.0, copy.get(0), EPS);
        }

        @Test
        @DisplayName("copy() возвращает новый объект")
        void copyReturnsNewObject() {
            Vector original = new Vector(1.0, 2.0);
            Vector copy = original.copy();
            assertNotSame(original, copy);
        }
    }



    @Nested
    @DisplayName("Методы get и set")
    class GetSet {

        @Test
        @DisplayName("get возвращает координату по индексу")
        void getReturnsCoordinate() {
            Vector v = new Vector(5.0, 6.0, 7.0);
            assertEquals(5.0, v.get(0), EPS);
            assertEquals(7.0, v.get(2), EPS);
        }

        @Test
        @DisplayName("set изменяет координату по индексу")
        void setChangesCoordinate() {
            Vector v = new Vector(1.0, 2.0, 3.0);
            v.set(1, 42.0);
            assertEquals(42.0, v.get(1), EPS);
        }

        @Test
        @DisplayName("get с некорректным индексом выбрасывает исключение")
        void getThrowsOnInvalidIndex() {
            Vector v = new Vector(1.0, 2.0);
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> v.get(5));
        }

        @Test
        @DisplayName("set с некорректным индексом выбрасывает исключение")
        void setThrowsOnInvalidIndex() {
            Vector v = new Vector(1.0, 2.0);
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> v.set(5, 1.0));
        }
    }

    @Nested
    @DisplayName("Инкремент и декремент")
    class IncrementDecrement {

        @Test
        @DisplayName("increment увеличивает каждую координату на 1")
        void incrementIncreasesAllCoordinates() {
            Vector v = new Vector(1.0, -2.0, 3.5);
            v.increment();

            assertEquals(2.0, v.get(0), EPS);
            assertEquals(-1.0, v.get(1), EPS);
            assertEquals(4.5, v.get(2), EPS);
        }

        @Test
        @DisplayName("decrement уменьшает каждую координату на 1")
        void decrementDecreasesAllCoordinates() {
            Vector v = new Vector(1.0, -2.0, 3.5);
            v.decrement();

            assertEquals(0.0, v.get(0), EPS);
            assertEquals(-3.0, v.get(1), EPS);
            assertEquals(2.5, v.get(2), EPS);
        }

        @Test
        @DisplayName("increment затем decrement возвращает исходный вектор")
        void incrementThenDecrementReturnsOriginal() {
            Vector v = new Vector(1.5, -2.5, 0.0);
            v.increment();
            v.decrement();

            assertEquals(1.5, v.get(0), EPS);
            assertEquals(-2.5, v.get(1), EPS);
            assertEquals(0.0, v.get(2), EPS);
        }

        @Test
        @DisplayName("increment на пустом векторе не падает")
        void incrementOnEmptyVector() {
            Vector v = new Vector();
            assertDoesNotThrow(v::increment);
            assertDoesNotThrow(v::decrement);
        }
    }



    @Nested
    @DisplayName("Скалярное произведение")
    class DotProduct {

        @Test
        @DisplayName("Скалярное произведение двух векторов")
        void dotProductBasic() {
            Vector a = new Vector(1.0, 2.0, 3.0);
            Vector b = new Vector(4.0, 5.0, 6.0);
            assertEquals(32.0, a.dotProduct(b), EPS);
        }

        @Test
        @DisplayName("Скалярное произведение ортогональных векторов равно 0")
        void dotProductOrthogonal() {
            Vector a = new Vector(1.0, 0.0);
            Vector b = new Vector(0.0, 1.0);
            assertEquals(0.0, a.dotProduct(b), EPS);
        }

        @Test
        @DisplayName("Скалярное произведение с нулевым вектором равно 0")
        void dotProductWithZeroVector() {
            Vector a = new Vector(1.0, 2.0, 3.0);
            Vector zero = new Vector(0.0, 0.0, 0.0);
            assertEquals(0.0, a.dotProduct(zero), EPS);
        }

        @Test
        @DisplayName("Скалярное произведение коммутативно")
        void dotProductCommutative() {
            Vector a = new Vector(1.0, 2.0, 3.0);
            Vector b = new Vector(-1.0, 4.0, 0.5);
            assertEquals(a.dotProduct(b), b.dotProduct(a), EPS);
        }

        @Test
        @DisplayName("Разная размерность — исключение")
        void dotProductDifferentDimensionsThrows() {
            Vector a = new Vector(1.0, 2.0);
            Vector b = new Vector(1.0, 2.0, 3.0);
            assertThrows(IllegalArgumentException.class, () -> a.dotProduct(b));
        }

        @Test
        @DisplayName("Скалярное произведение вектора на себя равно квадрату длины")
        void dotProductWithSelfEqualsLengthSquared() {
            Vector v = new Vector(3.0, 4.0);
            assertEquals(25.0, v.dotProduct(v), EPS);
        }
    }


    @Nested
    @DisplayName("Длина вектора")
    class Length {

        @Test
        @DisplayName("Длина вектора (3, 4) равна 5")
        void length345Triangle() {
            Vector v = new Vector(3.0, 4.0);
            assertEquals(5.0, v.length(), EPS);
        }

        @Test
        @DisplayName("Длина нулевого вектора равна 0")
        void lengthOfZeroVector() {
            Vector v = new Vector(0.0, 0.0, 0.0);
            assertEquals(0.0, v.length(), EPS);
        }

        @Test
        @DisplayName("Длина единичного вектора равна 1")
        void lengthOfUnitVector() {
            Vector v = new Vector(1.0, 0.0, 0.0);
            assertEquals(1.0, v.length(), EPS);
        }

        @Test
        @DisplayName("Длина пустого вектора равна 0")
        void lengthOfEmptyVector() {
            Vector v = new Vector();
            assertEquals(0.0, v.length(), EPS);
        }

        @Test
        @DisplayName("Длина отрицательных координат")
        void lengthWithNegativeCoordinates() {
            Vector v = new Vector(-3.0, -4.0);
            assertEquals(5.0, v.length(), EPS);
        }
    }

    // ==================== angleBetween ====================

    @Nested
    @DisplayName("Угол между векторами")
    class AngleBetween {

        @Test
        @DisplayName("Угол между одинаковыми векторами равен 0")
        void angleBetweenSameVectorsIsZero() {
            Vector a = new Vector(1.0, 2.0, 3.0);
            Vector b = new Vector(1.0, 2.0, 3.0);
            assertEquals(0.0, Vector.angleBetween(a, b), EPS);
        }

        @Test
        @DisplayName("Угол между ортогональными векторами равен π/2")
        void angleBetweenOrthogonalVectors() {
            Vector a = new Vector(1.0, 0.0);
            Vector b = new Vector(0.0, 1.0);
            assertEquals(Math.PI / 2, Vector.angleBetween(a, b), EPS);
        }

        @Test
        @DisplayName("Угол между противоположными векторами равен π")
        void angleBetweenOppositeVectors() {
            Vector a = new Vector(1.0, 0.0);
            Vector b = new Vector(-1.0, 0.0);
            assertEquals(Math.PI, Vector.angleBetween(a, b), EPS);
        }

        @Test
        @DisplayName("Угол между векторами (1,0) и (1,1) равен π/4")
        void angleBetween45Degrees() {
            Vector a = new Vector(1.0, 0.0);
            Vector b = new Vector(1.0, 1.0);
            assertEquals(Math.PI / 4, Vector.angleBetween(a, b), EPS);
        }

        @Test
        @DisplayName("Угол симметричен: angle(a,b) == angle(b,a)")
        void angleIsSymmetric() {
            Vector a = new Vector(1.0, 2.0, 3.0);
            Vector b = new Vector(4.0, -5.0, 6.0);
            assertEquals(Vector.angleBetween(a, b), Vector.angleBetween(b, a), EPS);
        }

        @Test
        @DisplayName("Угол с нулевым вектором даёт NaN")
        void angleWithZeroVectorIsNaN() {
            Vector a = new Vector(1.0, 2.0);
            Vector zero = new Vector(0.0, 0.0);
            assertTrue(Double.isNaN(Vector.angleBetween(a, zero)));
        }

        @Test
        @DisplayName("Одинаково направленные векторы разной длины — угол 0")
        void angleBetweenScaledVectors() {
            Vector a = new Vector(1.0, 2.0, 3.0);
            Vector b = new Vector(10.0, 20.0, 30.0);
            assertEquals(0.0, Vector.angleBetween(a, b), EPS);
        }

        @Test
        @DisplayName("Косинус не выходит за пределы [-1, 1] (защита от NaN)")
        void angleClampingPreventsNaN() {
            Vector a = new Vector(1e10, 1e10, 1e10);
            Vector b = new Vector(1e10, 1e10, 1e10);
            double angle = Vector.angleBetween(a, b);
            assertFalse(Double.isNaN(angle));
            assertEquals(0.0, angle, 1e-6);
        }
    }
    

    @Nested
    @DisplayName("toString")
    class ToStringTest {

        @Test
        @DisplayName("toString возвращает массив в квадратных скобках")
        void toStringFormatsArray() {
            Vector v = new Vector(1.0, 2.0, 3.0);
            assertEquals("[1.0, 2.0, 3.0]", v.toString());
        }

        @Test
        @DisplayName("toString пустого вектора — []")
        void toStringEmptyVector() {
            Vector v = new Vector();
            assertEquals("[]", v.toString());
        }

        @Test
        @DisplayName("toString с дробными значениями")
        void toStringWithFractions() {
            Vector v = new Vector(1.5, -2.25);
            assertEquals("[1.5, -2.25]", v.toString());
        }
    }
}