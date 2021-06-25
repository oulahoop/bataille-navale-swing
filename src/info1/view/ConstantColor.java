package info1.view;

public enum ConstantColor {
    BACKGROUND(0x154086),
    //grille CASE
    MISSED(0x154086),
    HIT(0x010101),
    SUNK(0x010101),
    BASECOLOR(0x83CCDD),
    SCOPED(0x253662),

    //grille CONTOUR
    GRILLELABEL(0x215A9A),
    GRILLELABELTEXT(0xE4B300),

    //BOUTON FEU
    FIREENABLE(0xff0000),
    FIREDISABLE(0xadadad),
    //BOUTONS
    BUTTON(0x215A9A),
    BUTTONFOREGROUND(0xE4B300),
    //LABEL
    LABELTEXT(0xE4B300),
    //TEXT AREA
    JTEXTAREA(0xDEDBC8),
    JTEXTAREATEXT(0x010101),

    LIST(0x83CCDD);

    private final int color;
    ConstantColor(int color){this.color = color;}
    public int getColor() { return color; }
}
