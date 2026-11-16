package lab5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MobileTest {

    private Mobile makeCatalog() {
        Mobile c = new Mobile();
        c.addModel("Apple", "iPhone 13", 2021, "iOS", 4, 6.1);
        c.addModel("Samsung", "Galaxy S22", 2022, "Android", 8, 6.1);
        c.addModel("Xiaomi", "Redmi Note 11", 2022, "Android", 4, 6.43);
        c.addModel("Apple", "iPhone 15", 2023, "iOS", 6, 6.1);
        c.addModel("Nokia", "3310", 2000, "Symbian", 0, 1.5);
        return c;
    }


    @Test
    void testFilterCaseInsensitive() {
        Mobile c = makeCatalog();

        List<Mobile.Model> apples = c.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return m.getBrand().equalsIgnoreCase("apple");
            }
        });

        assertEquals(2, apples.size(), "должно ловить 'apple' и 'Apple'");
    }

    @Test
    void testFilterByRam() {
        Mobile c = makeCatalog();

        List<Mobile.Model> powerful = c.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return m.getRamGb() >= 6;
            }
        });

        assertEquals(2, powerful.size(), "RAM>=6 — 2 модели (S22 и iPhone 15)");
        assertTrue(powerful.stream().allMatch(m -> m.getRamGb() >= 6),
                "в результате только RAM>=6");
        assertTrue(powerful.stream().anyMatch(m -> m.getName().equals("Galaxy S22")),
                "Galaxy S22 должен попасть (RAM=8)");
    }

    @Test
    void testFilterByYear() {
        Mobile c = makeCatalog();

        List<Mobile.Model> fresh = c.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return m.getYear() > 2021;
            }
        });

        assertEquals(3, fresh.size(), "после 2021 — 3 модели (2022, 2022, 2023)");
        assertTrue(fresh.stream().allMatch(m -> m.getYear() > 2021),
                "в результате только год > 2021");
    }

    @Test
    void testFilterNothing() {
        Mobile c = makeCatalog();

        List<Mobile.Model> none = c.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return false;
            }
        });

        assertEquals(0, none.size(), "фильтр 'false' ничего не пропускает");
    }

    @Test
    void testFilterEverything() {
        Mobile c = makeCatalog();

        List<Mobile.Model> all = c.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return true;
            }
        });

        assertEquals(c.getModels().size(), all.size(), "фильтр 'true' пропускает все");
    }
}