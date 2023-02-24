import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Font;

public class TicTacToeGame extends JPanel implements MouseListener
{
    Image title;
    Image startGame;
    Image background;
    Image xImage;
    Image oImage;
    Font fortnite;
    int screen = 1;
    int players;

    public TicTacToeGame()
    {
        addMouseListener(this);

        try
        {
            title = ImageIO.read(new File("Title.png"));
        }

        catch (IOException e)
        {
        }
    }

    public void paint (Graphics g)
    {
        if (screen == 1)
        {
            startScreen(g);
        }
        else if (screen == 2)
        {
            drawBoard(g);
        }
    }

    public void startScreen (Graphics g)
    {
        g.drawImage(title, 0, 0, null);

        // draw start game
        g.setColor(Color.decode("#ffff1c"));
        g.fillRect(412, 383, 262, 50);

        fortnite = new Font("BurbankBigCondensed-Black", Font.PLAIN, 33);
        g.setFont(fortnite);
        g.setColor(Color.black);
        g.drawString("PRESS TO START", 446, 421);
    }

    public void drawBoard (Graphics g)
    {

    }
    public void mouseClicked (MouseEvent e)
    {
    }
    public void mousePressed (MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        if (screen == 1) //title screen
        {
            if (x >= 412 && x <= 674 && y >= 383 && y <= 433)
            {
                screen = 2;
                repaint();
            }
        }
    }
    public void mouseReleased (MouseEvent e)
    {
    }
    public void mouseEntered (MouseEvent e)
    {
    }
    public void mouseExited (MouseEvent e)
    {
    }
}