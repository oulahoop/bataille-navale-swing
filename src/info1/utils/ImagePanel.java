package info1.utils;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class ImagePanel extends JPanel
{
    @Serial
    private static final long serialVersionUID = 1L;
    private final Image image;
    private final int iWidth2;
    private final int iHeight2;

    public ImagePanel(Image image)
    {
        this.image = image;
        this.iWidth2 = image.getWidth(this)/2;
        this.iHeight2 = image.getHeight(this)/2;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
        {
            int x = this.getParent().getWidth()/2 - iWidth2;
            int y = this.getParent().getHeight()/2 - iHeight2;
            g.drawImage(image,x,y,this);
        }
    }
}