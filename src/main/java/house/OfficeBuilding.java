package house;

public class OfficeBuilding extends House {

    private int workplaces; // количество рабочих мест — специфика офиса

    public OfficeBuilding(double area, int rooms, double pricePerMeter, int workplaces) {
        super(area, rooms, pricePerMeter); // вызываем конструктор родителя (House)
        this.workplaces = workplaces;
    }

    // Специализированный метод — только у офисного здания
    public void rentOffice(int officeNumber) {
        System.out.println("Офис №" + officeNumber + " сдан в аренду (рабочих мест: " + workplaces + ").");
    }

    @Override
    public void printInfo() {
        super.printInfo(); // сначала печатаем общую информацию
        System.out.println("Рабочих мест: " + workplaces);
    }
}