import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;

public class TicTacToeGame extends JPanel implements MouseListener, ActionListener {
    Image title;
    Image player2;
    Image lobby;
    Image background;
    Image xImage;
    Image oImage;
    Image victory;
    Image background2;
    Font fortnite;
    int screen = 1;
    int players = 1;
    int turn = 1;
    int moves1 = 0;
    int moves2 = 0;
    int dotCount = 0;
    int mousepressed = 1;
    int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}};
    boolean player1win = false;
    boolean player2win = false;
    boolean tie = false;

    /**
     * Constructor. Load images.
     */
    public TicTacToeGame()
    {
        addMouseListener(this);

        try {
            title = ImageIO.read(new File("Title.png"));
            lobby = ImageIO.read(new File("LobbyScreenMain.png"));
            player2 = ImageIO.read(new File("player2.png"));
            background = ImageIO.read(new File("dustydepot.jpg"));
            xImage = ImageIO.read(new File("flopper.png"));
            oImage = ImageIO.read(new File("mini.png"));
            victory = ImageIO.read(new File("victoryRoyale.png"));
            background2 = ImageIO.read(new File("Board2.png"));
        } catch (IOException e) {
        }
    }

    public void paint(Graphics g)
    {
        if (screen == 1)
        {
            startScreen(g);
        } else if (screen == 2) {
            drawLobby(g);
        } else if (screen == 3) {
            DrawBoard(g);

            if (player1win == false && player2win == false && tie == false)
            {
                Animation(g);
            }
        }
    }

    public void startScreen(Graphics g)
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

    public void drawLobby(Graphics g)
    {
        g.drawImage(lobby, 0, 0, null);
        g.setColor(Color.white);

        //draw start game button
        Font biggerFont = fortnite.deriveFont(53f);
        g.setFont(biggerFont);
        g.setColor(Color.black);
        g.drawString("PLAY!", 877, 557);

        //draw game mode button
        g.setColor(Color.decode("#2b3348"));
        g.drawRect(1012, 430, 20, 24); //right button of game mode
        Font biggerFont2 = fortnite.deriveFont(36f);
        g.setFont(biggerFont2);
        g.setColor(Color.white);
        g.drawString("SOLO", 894, 454);
    }

    public void DrawBoard(Graphics g)
    {
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.black);
        g.drawRect(0, 0, 603, 603);

        //vertical lines
        g.fillRect(199, 0, 4, 603);
        g.fillRect(400, 0, 4, 603);
        g.fillRect(601, 0, 4, 603);

        //horizontal lines
        g.fillRect(0, 199, 603, 4);
        g.fillRect(0, 400, 603, 4);
        g.fillRect(0, 601, 603, 4);
        //draw x's and o's
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 1) {
                    g.drawImage(xImage, i * 201, j * 201, null);
                } else if (board[i][j] == 2) {
                    g.drawImage(oImage, i * 201, j * 201, null);
                }
            }
        }
        checkWinner(g);
    }

    public void Animation(Graphics g)
    {
        Timer timer = new Timer(600, this);
        g.setColor(Color.white);
        g.setFont(fortnite);
        g.drawString("PLAYER " + turn + "'S TURN", 694, 354);
        timer.start();
    }

    public void checkWinner(Graphics g) {

        for (int i = 0; i < 3; i++) {
            // Check horizontal
            if (board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1) {
                player1win = true;
                playervictory(g);
            }
            // Check vertical
            else if (board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1) {
                player1win = true;
                playervictory(g);
            }
            // Check horizontal for p2
            else if (board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 2) {
                player2win = true;
                playervictory(g);
            }
            // Check vertical for p2
            else if (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2) {
                player2win = true;
                playervictory(g);
            }
        }
        if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1) {
            player1win = true;
            playervictory(g);
        } else if (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2) {
            player2win = true;
            playervictory(g);
        } else if (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1) {
            player1win = true;
            playervictory(g);
        } else if (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2) {
            player2win = true;
            playervictory(g);
        } else if (moves1 + moves2 == 10) {
            tie = true;
            playervictory(g);
        }
    }

    public void playervictory(Graphics g) {

        if (player1win == true) {
            g.drawImage(victory, 652, 86, null);
            g.setColor(Color.white);
            g.setFont(fortnite);
            g.drawString("PLAYER 1 WON IN " + moves1 + " MOVES!", 694, 354);
        } else if (player2win == true) {
            g.drawImage(victory, 652, 86, null);
            g.setColor(Color.white);
            g.setFont(fortnite);
            g.drawString("PLAYER 2 WON IN " + moves2 + " MOVES!", 694, 354);
        } else if (tie == true) {
            g.setColor(Color.white);
            Font biggerFont2 = fortnite.deriveFont(50f);
            g.setFont(biggerFont2);
            g.drawString("TIE!", 700, 354);
        }

    }

    public void GameModeSelector(Graphics g) {
        Font biggerFont2 = fortnite.deriveFont(36f);
        g.setFont(biggerFont2);

        if (mousepressed % 2 == 1) {
            drawLobby(g);
        }

        if (mousepressed % 2 == 0) {
            g.setColor(Color.decode("#2b3348"));
            g.fillRect(850, 427, 151, 31);
            g.setColor(Color.white);
            g.setFont(biggerFont2);
            g.drawString("DUOS", 891, 454);
            g.drawImage(player2, 124, 107, null);
            g.setFont(fortnite);
            players = 2;
        }
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e) {
        Graphics g = getGraphics();
        int x = e.getX();
        int y = e.getY();

        if (screen == 1) //title screen
        {
            if (x >= 412 && x <= 674 && y >= 383 && y <= 433) {
                screen = 2;
                repaint();
            }
        }

        if (screen == 2) //lobby screen
        {
            if (x >= 1012 && x <= 1032 && y >= 430 && y <= 454) {
                mousepressed++;
                GameModeSelector(g);
            }

            if (x >= 794 && x <= 1061 && y >= 500 && y <= 582) {
                screen = 3;
                repaint();
            }
        }

        if (screen == 3) {
            for (int i = 0; i < 403; i += 201) { // check x
                for (int j = 0; j < 403; j += 201) { // check y
                    if (x > i && x < i + 201 && y > j && y < j + 201 && board[i / 201][j / 201] == 0) {
                        board[i / 201][j / 201] = turn;
                        if (turn == 1) {
                            moves1++;
                        }
                        if (turn == 2) {
                            moves2++;
                        }
                        turn = turn == 1 ? 2 : 1;
                    }
                }
            }
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }


    public void actionPerformed(ActionEvent evt)
    {
        if (screen == 3 && player1win == false && player2win == false && tie == false)
        {

            Graphics g = getGraphics();

           if (dotCount == 0)
           {
               g.setColor(Color.WHITE);
               g.setFont(fortnite);
               g.drawString(".", 885, 354);
               dotCount++;
           }
            else if (dotCount == 1)
            {
                g.setColor(Color.WHITE);
                g.setFont(fortnite);
                g.drawString(".", 895, 354);
                dotCount++;
            }

            else if (dotCount == 2)
            {
                g.setColor(Color.WHITE);
                g.setFont(fortnite);
                g.drawString(". ", 905, 354);
                dotCount++;
            }

           else if (dotCount == 3)
           {
               dotCount = 0;
               g.drawImage(background2, 689, 161, null);
               g.setColor(Color.WHITE);
               g.setFont(fortnite);
               g.drawString("PLAYER " + turn + "'S TURN", 694, 354);
           }

            System.out.println(dotCount);
        }


    }
}