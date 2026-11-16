package house;

public class ShoppingCenter extends House {

    private int shops;        // количество магазинов
    private int parkingSpots; // количество парковочных мест

    public ShoppingCenter(double area, int rooms, double pricePerMeter,
                          int shops, int parkingSpots) {
        super(area, rooms, pricePerMeter);
        this.shops = shops;
        this.parkingSpots = parkingSpots;
    }

    // Специализированный метод — только у ТЦ
    public void openNewShop(String shopName) {
        shops++;
        System.out.println("В ТЦ открыт новый магазин: \"" + shopName +
                "\". Всего магазинов: " + shops);
    }

    // Ещё один специализированный метод
    public void showParkingInfo() {
        System.out.println("Парковочных мест: " + parkingSpots);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Магазинов: " + shops);
        System.out.println("Парковочных мест: " + parkingSpots);
    }
}