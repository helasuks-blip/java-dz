package house;


public interface IHouse {

    // Построить дом (с параметрами: площадь и число комнат)
    void build(double area, int rooms);

    // Рассчитать цену за квадратный метр
    double pricePerSquareMeter();

    // Узнать, сколько комнат в доме
    int getRoomsCount();

    // Увеличить площадь (достроить что-то)
    void increaseArea(double extraArea);

    // Сдать дом в аренду
    void rent();

    // Сделать ремонт в конкретной комнате (комната задаётся номером)
    void renovate(int roomNumber);
}