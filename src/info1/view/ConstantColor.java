package info1.view;

public enum ConstantColor {
    BACKGROUND(0x6d82f5);

    private int color;
    private ConstantColor(int color){this.color = color;}
    public int getColor() { return color; }
}
