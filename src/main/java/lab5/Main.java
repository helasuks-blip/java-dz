package lab5;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Mobile catalog = new Mobile();

        catalog.addModel("Apple",   "iPhone 13",  2021, "iOS",      4,  6.1);
        catalog.addModel("Samsung", "Galaxy S22", 2022, "Android",  8,  6.1);
        catalog.addModel("Xiaomi",  "Redmi Note 11", 2022, "Android", 4, 6.43);
        catalog.addModel("Apple",   "iPhone 15",  2023, "iOS",      6,  6.1);
        catalog.addModel("Nokia",   "3310",       2000, "Symbian",  0,  1.5);


        catalog.printAll();
        System.out.println();



        System.out.println("=== Только Apple (анонимный класс #1) ===");
        List<Mobile.Model> apples = catalog.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return m.getBrand().equalsIgnoreCase("Apple");
            }
        });
        apples.forEach(m -> System.out.println("  " + m));
        System.out.println();

        System.out.println("=== RAM >= 6 ГБ (анонимный класс #2) ===");
        List<Mobile.Model> powerful = catalog.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return m.getRamGb() >= 6;
            }
        });
        powerful.forEach(m -> System.out.println("  " + m));
        System.out.println();

        System.out.println("=== Выпущены после 2021 (анонимный класс #3) ===");
        List<Mobile.Model> fresh = catalog.filter(new ModelFilter() {
            @Override
            public boolean test(Mobile.Model m) {
                return m.getYear() > 2021;
            }
        });
        fresh.forEach(m -> System.out.println("  " + m));
    }
}