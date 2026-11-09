package lab5;

import java.util.ArrayList;
import java.util.List;

public class Mobile {

    // ===== ВНУТРЕННИЙ КЛАСС =====
    // Обрати внимание: НЕТ слова static. Это non-static inner class.
    public class Model {
        private String brand;
        private String name;
        private int year;
        private String os;
        private int ramGb;
        private double screenInches;

        public Model(String brand, String name, int year, String os, int ramGb, double screenInches) {
            this.brand = brand;
            this.name = name;
            this.year = year;
            this.os = os;
            this.ramGb = ramGb;
            this.screenInches = screenInches;
        }

        public String getBrand() { return brand; }
        public String getName()  { return name; }
        public int getYear()     { return year; }
        public String getOs()    { return os; }
        public int getRamGb()    { return ramGb; }
        public double getScreenInches() { return screenInches; }

        @Override
        public String toString() {
            return String.format("%s %s (%d), OS: %s, RAM: %d ГБ, экран: %.1f\"",
                    brand, name, year, os, ramGb, screenInches);
        }
    }
    // ===== КОНЕЦ ВНУТРЕННЕГО КЛАССА =====

    private List<Model> models = new ArrayList<>();


    public Model addModel(String brand, String name, int year,
                          String os, int ramGb, double screenInches) {

        Model m = this.new Model(brand, name, year, os, ramGb, screenInches);
        models.add(m);
        return m;
    }

    public List<Model> getModels() {
        return models;
    }


    public List<Model> filter(ModelFilter filter) {
        List<Model> result = new ArrayList<>();
        for (Model m : models) {
            if (filter.test(m)) {
                result.add(m);
            }
        }
        return result;
    }

    public void printAll() {
        System.out.println("=== Все модели (" + models.size() + ") ===");
        for (Model m : models) {
            System.out.println("  " + m);
        }
    }
}