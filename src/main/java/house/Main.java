package house;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- 1. Создаем обычный дом ---");
        House house = new House(100, 4, 85000);
        house.build(100, 4);
        System.out.println("Цена за м²: " + house.pricePerSquareMeter());
        System.out.println("Комнат: " + house.getRoomsCount());
        house.increaseArea(20);
        house.rent();
        house.renovate(2);
        house.printInfo();

        System.out.println("\n--- 2. Офисное здание ---");
        OfficeBuilding office = new OfficeBuilding(500, 20, 120000, 150);
        office.build(500, 20);
        office.rent();
        office.renovate(5);
        office.rentOffice(7); // Специализированный метод
        office.printInfo();

        System.out.println("\n--- 3. Торговый центр ---");
        ShoppingCenter mall = new ShoppingCenter(2000, 10, 150000, 40, 300);
        mall.build(2000, 10);
        mall.openNewShop("Пятёрочка"); // Специализированный метод
        mall.openNewShop("DNS");
        mall.showParkingInfo();
        mall.printInfo();

        System.out.println("\n--- 4. Демонстрация полиморфизма (через интерфейс) ---");
        IHouse[] buildings = { house, office, mall };
        for (IHouse b : buildings) {
            System.out.println("Цена за м²: " + b.pricePerSquareMeter() +
                    ", комнат: " + b.getRoomsCount());
        }

        System.out.println("\n--- 5. Приведение типов (вызов специфичных методов) ---");
        for (IHouse b : buildings) {
            if (b instanceof OfficeBuilding) {
                ((OfficeBuilding) b).rentOffice(1);
            } else if (b instanceof ShoppingCenter) {
                ((ShoppingCenter) b).openNewShop("Wildberries");
            }
        }
    }
}