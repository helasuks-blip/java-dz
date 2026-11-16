package house;

public class House implements IHouse {

    //состояние объекта
    protected double area;              // площадь в м²
    protected int rooms;                // количество комнат
    protected double pricePerMeter;     // цена за 1 м² (в рублях)
    protected boolean isRented;         // сдан ли в аренду


    public House(double area, int rooms, double pricePerMeter) {
        this.area = area;
        this.rooms = rooms;
        this.pricePerMeter = pricePerMeter;
        this.isRented = false;
    }



    @Override
    public void build(double area, int rooms) {
        this.area = area;
        this.rooms = rooms;
        System.out.println("Дом построен: площадь = " + area + " м², комнат = " + rooms);
    }

    @Override
    public double pricePerSquareMeter() {
        return pricePerMeter;
    }

    @Override
    public int getRoomsCount() {
        return rooms;
    }

    @Override
    public void increaseArea(double extraArea) {
        if (extraArea <= 0) {
            System.out.println("Площадь должна быть положительной!");
            return;
        }
        this.area += extraArea;
        System.out.println("Площадь увеличена на " + extraArea +
                " м². Новая площадь = " + this.area + " м²");
    }

    @Override
    public void rent() {
        if (isRented) {
            System.out.println("Дом уже сдан в аренду.");
        } else {
            isRented = true;
            System.out.println("Дом сдан в аренду.");
        }
    }

    @Override
    public void renovate(int roomNumber) {
        if (roomNumber < 1 || roomNumber > rooms) {
            System.out.println("Комнаты с номером " + roomNumber + " нет в доме!");
            return;
        }
        System.out.println("Сделан ремонт в комнате №" + roomNumber + ".");
    }


    public void printInfo() {
        System.out.println("--- Информация о здании ---");
        System.out.println("Площадь: " + area + " м²");
        System.out.println("Комнат: " + rooms);
        System.out.println("Цена за м²: " + pricePerMeter);
        System.out.println("В аренде: " + (isRented ? "да" : "нет"));
    }
}