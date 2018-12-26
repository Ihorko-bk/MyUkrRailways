package Entity;

public enum CarriageType {
    SV(18), COMPARATMENT(36), COUCHETTE(54);

    private int capacity;

    CarriageType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}