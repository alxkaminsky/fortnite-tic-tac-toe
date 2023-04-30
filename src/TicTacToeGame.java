import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class TicTacToeGame extends JPanel implements MouseListener, ActionListener {
    private final Timer timer = new Timer(250, this);
    Image title;
    Image player2;
    Image lobby;
    Image background;
    Image banshee;
    Image hawk;
    Image headhunter;
    Image jonesy;
    Image ramirez;
    Image renegade;
    Image stan;
    Image wildcat;
    Image victory;
    Image background2;
    Image repaintplayerselect;
    Image playerselect1;
    Image playerselect2;
    Image xImage;
    Image oImage;
    Image selectRect;
    Image buttons;
    Font fortnite;
    int screen = 1;
    int players = 1;
    int turn = 1;
    int moves1 = 0;
    int moves2 = 0;
    int dotCount = 0;
    int mousepressed = 1;
    int mousepressed2 = 0;
    int keypressed = 0;
    int playerselect = 1;
    int[][] board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };
    boolean player1win = false;
    boolean player2win = false;
    boolean tie = false;

    /**
     * Constructor
     */
    public TicTacToeGame(JFrame frame) {
        addMouseListener(this);

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                Graphics g = getGraphics();

                // exit game
                if (keyCode == KeyEvent.VK_ESCAPE) {
                    if (screen == 0) {
                        System.exit(0);
                    }
                    screen--;
                    resetGameStats();
                    repaint();
                }

                // restart game
                else if (keyCode == KeyEvent.VK_R) {
                    if (screen == 0) {
                        System.exit(0);
                    }
                    resetGameStats();
                    repaint();
                }

                if (screen == 2) {
                    if (keyCode == KeyEvent.VK_RIGHT) {
                        keypressed++;
                        Font biggerFont2 = fortnite.deriveFont(36f);
                        g.setFont(biggerFont2);

                        if (keypressed % 2 == 1) {
                            drawLobby(g);
                        }

                        if (keypressed % 2 == 0) {
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
                }
            }
        });

        try {
            title = ImageIO.read(new File("Title.png"));
            lobby = ImageIO.read(new File("LobbyScreenMain.png"));
            player2 = ImageIO.read(new File("player2.png"));
            background = ImageIO.read(new File("dustydepot.jpg"));
            victory = ImageIO.read(new File("victoryRoyale.png"));
            background2 = ImageIO.read(new File("Board2.png"));
            banshee = ImageIO.read(new File("Banshee.png"));
            hawk = ImageIO.read(new File("Hawk.png"));
            headhunter = ImageIO.read(new File("Headhunter.png"));
            jonesy = ImageIO.read(new File("Jonesy.png"));
            ramirez = ImageIO.read(new File("Ramirez.png"));
            renegade = ImageIO.read(new File("Renegade.png"));
            stan = ImageIO.read(new File("Stan.png"));
            wildcat = ImageIO.read(new File("Wildcat.png"));
            repaintplayerselect = ImageIO.read(new File("repaintPlayerselect.png"));
            playerselect1 = ImageIO.read(new File("Playerselect1.png"));
            playerselect2 = ImageIO.read(new File("Playerselect2.png"));
            selectRect = ImageIO.read(new File("SelectRect.png"));
            buttons = ImageIO.read(new File("buttons.png"));
            xImage = hawk.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
            oImage = banshee.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.exit(1);
        }
    }

    /**
     * Reset game stats.
     */
    public void resetGameStats() {
        // Reset board array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }

        // Reset win variables
        player1win = false;
        player2win = false;
        tie = false;
        playerselect = 1;

        // Reset game stats
        turn = 1;
        moves1 = 0;
        moves2 = 0;
    }

    /**
     * Draw screen.
     */
    public void paint(Graphics g) {
        if (screen == 1) {
            startScreen(g);
        } else if (screen == 2) {
            drawLobby(g);
        } else if (screen == 3) {
            drawCharacterSelect(g);
            resetGameStats();
        } else if (screen == 4) {
            DrawBoard(g);

            if (players == 2 && !player1win && !player2win && !tie) {
                Animation(g);
            }
        } else {
            System.exit(1);
        }
    }

    /**
     * 0 - Loading screen.
     */
    public void startScreen(Graphics g) {
        g.drawImage(title, 0, 0, null);

        // draw start game
        g.setColor(Color.decode("#ffff1c"));
        g.fillRect(412, 383, 262, 50);

        fortnite = new Font("BurbankBigCondensed-Black", Font.PLAIN, 33);
        g.setFont(fortnite);
        g.setColor(Color.black);
        g.drawString("PRESS TO START", 446, 421);
    }

    /**
     * 1 - Lobby screen.
     */
    public void drawLobby(Graphics g) {
        g.drawImage(lobby, 0, 0, null);
        g.setFont(fortnite.deriveFont(53f));
        g.setColor(Color.black);

        if (playerselect == 1){

            g.drawString("SETUP", 870, 557);

            if (mousepressed %2 == 1)
                players=1;
            else if (mousepressed %2 == 0)
                players=2;
        }
        else if (playerselect == 2){
            g.drawString("PLAY!", 877, 557);
            if (mousepressed %2 == 1)
                players=1;
            else if (mousepressed %2 == 0)
                players=2;
        }

        if (players == 1){
            repaint();
            g.setFont(fortnite.deriveFont(36f));
            g.setColor(Color.white);
            g.drawString("SOLO", 894, 454);
        }
        else if (players == 2){
            repaint();
            g.setColor(Color.decode("#2b3348"));
            g.fillRect(850, 427, 151, 31);
            g.setFont(fortnite.deriveFont(36f));
            g.setColor(Color.white);
            g.drawString("DUOS", 891, 454);
            g.drawImage(player2, 124, 107, null);
        }

    }

    /**
     * 2 - Main game screen.
     */

    public void drawCharacterSelect(Graphics g) {
        if (playerselect == 1) {
            g.drawImage(playerselect1, 0, 0, null);
        } else if (playerselect == 2) {
            g.drawImage(repaintplayerselect, 0, 0, null);
            g.drawImage(playerselect2, 0, 0, null);
        }
    }

    public void DrawBoard(Graphics g) {
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
        if (turn == 2 && players == 1 && moves1 + moves2 <= 8) {
            if (board [0][0]==1 && board [1][1] ==0 || board [0][2]== 1 && board [1][1] ==0 || board [2][0] == 1 && board [1][1] ==0 || board [2][2] == 1 && board [1][1] ==0 )
                board [1][1] = 2;
            else if (board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 0)
                board[0][2] = 2;
            else if (board[0][0] == 0 && board[0][1] == 1 && board[0][2] == 1)
                board[0][0] = 2;
            else if (board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 0)
                board[1][2] = 2;
            else if (board[1][0] == 0 && board[1][1] == 1 && board[1][2] == 1)
                board[1][0] = 2;
            else if (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 0)
                board[2][2] = 2;
            else if (board[2][0] == 0 && board[2][1] == 1 && board[2][2] == 1)
                board[2][0] = 2;
            else if (board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 0)
                board[2][0] = 2;
            else if (board[0][0] == 0 && board[1][0] == 1 && board[2][0] == 1)
                board[0][0] = 2;
            else if (board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 0)
                board[2][1] = 2;
            else if (board[0][1] == 0 && board[1][1] == 1 && board[2][1] == 1)
                board[0][1] = 2;
            else if (board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 0)
                board[2][2] = 2;
            else if (board[0][2] == 0 && board[1][2] == 1 && board[2][2] == 1)
                board[0][2] = 2;
            else if (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 0)
                board[2][2] = 2;
            else if (board[0][0] == 0 && board[1][1] == 1 && board[2][2] == 1)
                board[0][0] = 2;
            else if (board[0][0] == 1 && board[0][1] == 0 && board[0][2] == 1)
                board[0][1] = 2;
            else if (board[1][0] == 1 && board[1][1] == 0 && board[1][2] == 1)
                board[1][1] = 2;
            else if (board[2][0] == 1 && board[2][1] == 0 && board[2][2] == 1)
                board[2][1] = 2;
            else if (board[0][0] == 1 && board[1][0] == 0 && board[2][0] == 1)
                board[1][0] = 2;
            else if (board[0][1] == 1 && board[1][1] == 0 && board[2][1] == 1)
                board[1][1] = 2;
            else if (board[0][2] == 1 && board[1][2] == 0 && board[2][2] == 1)
                board[1][2] = 2;
            else if (board[0][0] == 1 && board[1][1] == 0 && board[2][2] == 1)
                board[1][1] = 2;
            else if (board[0][2] == 1 && board[1][1] == 0 && board[2][0] == 1)
                board[1][1] = 2;
            else {
                int x, y;
                do {
                    x = (int) (Math.random() * 3);
                    y = (int) (Math.random() * 3);
                    if (moves1 + moves2 > 8)
                        break;
                } while (board[x][y] != 0 && (!player1win && !player2win && !tie));
                board[x][y] = 2;
            }

            moves2++;
            turn = 1;
        }

        //Draw x and o
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0)
                    continue;
                g.drawImage(board[i][j] == 1 ? xImage : oImage, j * 201, i * 201, null);
            }
        }
        checkWinner(g);
    }

    public void Animation(Graphics g) {
        g.setColor(Color.white);
        g.setFont(fortnite.deriveFont(45f));
        g.drawString("PLAYER " + turn + "'S TURN", 695, 315);
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
        }
        if (moves1 + moves2 == 9) {
            tie = true;
            playervictory(g);
        }
    }

    public void playervictory(Graphics g) {
        g.drawImage(buttons, 0, 0, null);
        g.setColor(Color.white);
        g.setFont(fortnite);

        if (player1win) {
            g.drawImage(victory, 652, 60, null);
            g.drawString("PLAYER 1 WON IN " + moves1 + " MOVES!", 694, 304);
        } else if (player2win) {
            g.drawImage(victory, 652, 60, null);
            g.drawString("PLAYER 2 WON IN " + moves2 + " MOVES!", 694, 304);
        } else if (tie) {
            g.setFont(fortnite.deriveFont(50f));
            g.drawString("TIE!", 800, 314);
        }

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
                repaint();
            }

            if (x >= 794 && x <= 1061 && y >= 500 && y <= 582) {
                mousepressed2++;
                if (mousepressed2 % 2 == 1) {
                    screen = 3;
                    repaint();
                } else if (mousepressed2 % 2 == 0) {
                    screen = 4;
                    repaint();
                }
            }
        }

        if (screen == 3) // character selection
        {
            if (x >= 42 && x <= 242 && y >= 52 && y <= 296) {
                if (playerselect == 1) {
                    xImage = hawk.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect1, 0, 0, null);
                    g.drawImage(selectRect, 40, 52, null);
                } else if (playerselect == 2) {
                    oImage = renegade.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect2, 0, 0, null);
                    g.drawImage(selectRect, 40, 52, null);
                }
            }
            if (x >= 310 && x <= 507 && y >= 52 && y <= 296) {
                if (playerselect == 1) {
                    xImage = headhunter.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect1, 0, 0, null);
                    g.drawImage(selectRect, 306, 52, null);

                } else if (playerselect == 2) {
                    oImage = stan.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect2, 0, 0, null);
                    g.drawImage(selectRect, 306, 52, null);
                }
            }
            if (x >= 567 && x <= 763 && y >= 52 && y <= 296) {
                if (playerselect == 1) {
                    xImage = jonesy.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect1, 0, 0, null);
                    g.drawImage(selectRect, 563, 52, null);
                } else if (playerselect == 2) {
                    oImage = ramirez.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect2, 0, 0, null);
                    g.drawImage(selectRect, 563, 52, null);
                }
            }
            if (x >= 830 && x <= 1028 && y >= 52 && y <= 296) {
                if (playerselect == 1) {
                    xImage = wildcat.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect1, 0, 0, null);
                    g.drawImage(selectRect, 826, 52, null);

                } else if (playerselect == 2) {
                    oImage = banshee.getScaledInstance(199, 199, Image.SCALE_SMOOTH);
                    g.drawImage(playerselect2, 0, 0, null);
                    g.drawImage(selectRect, 826, 52, null);
                }
            }
            if (x >= 315 && x <= 756 && y >= 500 && y <= 573) {
                if (playerselect == 1) {
                    playerselect = 2;
                    drawCharacterSelect(g);
                } else if (playerselect == 2) {
                    screen = 2;
                    repaint();
                }
            }
        }

        if (screen == 4) // game
        {
            if (!player1win && !player2win && !tie) {
                for (int i = 0; i < 403; i += 201) { // check x
                    for (int j = 0; j < 403; j += 201) { // check y
                        if (y > i && y < i + 201 && x > j && x < j + 201 && board[i / 201][j / 201] == 0) {
                            board[i / 201][j / 201] = turn;
                            if (turn == 1)
                                moves1++;
                            else if (turn == 2)
                                moves2++;
                            turn = turn == 1 ? 2 : 1;
                            dotCount = 0;  // reset dot count for animamtion
                        }
                    }
                }
                repaint();
            }


            if (x >= 692 && x <= 994 && y >= 415 && y <= 475 && player1win || x >= 692 && x <= 994 && y >= 415 && y <= 475 && player2win || x >= 692 && x <= 994 && y >= 415 && y <= 475 && tie) {
                screen = 2;
                resetGameStats();
                repaint();
            }
            if (x >= 687 && x <= 1005 && y >= 483 && y <= 584 && player1win || x >= 687 && x <= 1005 && y >= 483 && y <= 584 && player2win || x >= 687 && x <= 1005 && y >= 483 && y <= 584 && tie) {
                repaint();
                resetGameStats();
            }
        }
    }


    public void actionPerformed(ActionEvent evt) {

        if (screen == 4 && players == 2 && !player1win && !player2win && !tie) {
            Graphics g = getGraphics();
            g.setColor(Color.WHITE);
            g.setFont(fortnite.deriveFont(45f));

            if (dotCount == 0) {
                g.drawString(".", 961, 315);
                dotCount++;
            } else if (dotCount == 1) {
                g.drawString(".", 976, 315);
                dotCount++;
            } else if (dotCount == 2) {
                g.drawString(". ", 991, 315);
                dotCount++;
            } else if (dotCount == 3) {
                dotCount = 0;
                g.drawImage(background2, 609, 0, null);
                g.drawString("PLAYER " + turn + "'S TURN", 695, 315);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
