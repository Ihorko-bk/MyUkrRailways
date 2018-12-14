package Entity;

public class Place {
    // перейменував з id на number. Бо то ж номер місця. Хоч він і є так званим ідентифікатором.
    // Але так як місць в нас в базі нема, тому використання ім'я змінної як "id" недуже..як на мене..ну хз..
    private int number;
    private boolean free;   // чому busy? чому не free? так по-прощє. перейменую з вашого дозволу..

    // видалив конструктор з одним id(вже number), бо ми як будемо створювати місця то одразу вказуватимемо
    // іх "свободу"
    public Place(int number, boolean free) {
        this.number = number;
        this.free = free;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFree() {
        return free;
    }
    public void setFree(boolean free) {
        this.free = free;
    }
}
