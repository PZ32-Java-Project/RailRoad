package models;
public enum ClientTypes{
    Disabled(0),
    WithBaby(1),
    Veteran(2),
    Ordinary(3);
    public final int priority;
    private ClientTypes(int priority) {
        this.priority = priority;
    }
}