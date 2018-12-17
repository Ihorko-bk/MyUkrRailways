package Entity;

public class Place {
    // перейменував з id на number. Бо то ж номер місця. Хоч він і є так званим ідентифікатором.
    // Але так як місць в нас в базі нема, тому використання ім'я змінної як "id" недуже..як на мене..ну хз..
    private int number;
    private boolean free;   // чому busy? чому не free? так по-прощє. перейменую з вашого дозволу..
    // короче щоб не вишукувати довго той вагон(а з ним і маршрут). Для кнопки знадобиться. Знаю що трохи тупо, сорі(
    private Carriage carriage;
    public Carriage getCarriage() {
        return carriage;
    }

    // видалив конструктор з одним id(вже number), бо ми як будемо створювати місця то одразу вказуватимемо
    // іх "свободу". Ну і додав Вагон.
    public Place(int number, boolean free, Carriage carriage) {
        this.number = number;
        this.free = free;
        this.carriage = carriage;
    }

    // видалив сеттери для встановлення номеру та "вільності"

    public int getNumber() {
        return number;
    }

    public boolean isFree() {
        return free;
    }
}
