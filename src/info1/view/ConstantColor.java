package info1.view;

public enum ConstantColor {
    BACKGROUND(0x6d82f5),
    MISSED(0xfcfcfc),
    HIT(0xffcc00),
    SUNK(0xff7700),
    BASECOLOR(0x78939A),
    SCOPED(0x253662),
    FIREENABLE(0xff0000),
    FIREDISABLE(0xadadad),
    LABEL(1),
    QUIT(2),
    JOUER(3),
    BUTTON(4),
    LIST(5);
    private final int color;
    ConstantColor(int color){this.color = color;}
    public int getColor() { return color; }
}
