import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import Cards.*;

public class Board extends JFrame {
    private static final long serialVersionUID = 1L;

    // parallel arrays that hold the x, y, width, and height for the rectangles
    public static final int[] xPoints = { 300, 375, 225, 200, 70, 70, 200, 375, 375, 240 };
    public static final int[] yPoints = { 600, 470, 480, 350, 350, 225, 190, 150, 25, 75 };

    private BorderLayout mBorderLayout;
    private JPanel mMunchkinLayout;
    private JPanel mLeftPanel;
    private JPanel mRightPanel;
    private JLabel mTitle;
    private JButton[] mLeftPanelButton;
    private ButtonHandler mButtonHandler;
    private JLabel mTreasureButton;
    private JLabel mDoorButton;
    private Graphics2D g2d;
    private int currentPlayer = 1;
    private JLabel mPlayerTurn;
    MouseHandler mouseHandler; 

    Board() {
        super("Munchkin");
        setSize(1300, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution/15000866
        // center window in the middle of the user's screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        mBorderLayout = new BorderLayout();
        setLayout(mBorderLayout);

        mMunchkinLayout = new BoardGUI();
        add(mMunchkinLayout, BorderLayout.CENTER);

        // Title image
        ImageIcon title = new ImageIcon(getClass().getResource("munchkin_title.jpg"));
        title = scaleImage(800, 150, title);
        mTitle = new JLabel(title);
        add(mTitle, BorderLayout.NORTH);

        // Left Hand Button Panel
        mLeftPanel = new JPanel();
        mLeftPanel.setLayout(new GridLayout(3, 0));
        add(mLeftPanel, BorderLayout.WEST);
        mLeftPanel.setSize(100, 950);
        mLeftPanelButton = new JButton[3];
        mLeftPanelButton[0] = new JButton("Main Menu");
        mLeftPanelButton[1] = new JButton("Rules");
        mLeftPanelButton[2] = new JButton("Quit");

        // Listens for Button events
        mButtonHandler = new ButtonHandler();

        // add Buttons to panel and listener class
        for (int i = 0; i < mLeftPanelButton.length; i++) {
            mLeftPanel.add(mLeftPanelButton[i], mLeftPanel);
            mLeftPanelButton[i].addActionListener(mButtonHandler);
        }

        mRightPanel = new JPanel();
        mRightPanel.setLayout(new GridLayout(4, 5));
        mRightPanel.setSize(400, 950);
        add(mRightPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private class BoardGUI extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image[] mRomanNumeral;

        BoardGUI() {
            setSize(600, 800);
            setBackground(new Color(204, 102, 0));
            setLayout(null);

            // Treasure Card
            mTreasureButton = placeImageButton(mTreasureButton, "treasure_card.png", 350, 300, 170, 100);
            mouseHandler = new MouseHandler();
            mTreasureButton.addMouseListener(mouseHandler);
            
            // Door Card
            mDoorButton = placeImageButton(mDoorButton, "door_card.png", 20, 480, 170, 100);
            mDoorButton.addMouseListener(mouseHandler);

            mPlayerTurn = new JLabel("Player 1's Turn");
            mPlayerTurn.setBounds(500, 0, 300, 50);
            mPlayerTurn.setFont(new Font("Serif", Font.BOLD, 24));
            add(mPlayerTurn);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2d = (Graphics2D) g;

            g2d.setColor(Color.gray);
            g2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            mRomanNumeral = new Image[10];
            mRomanNumeral[0] = loadImage("roman_1.png"); 
            mRomanNumeral[1] = loadImage("roman_2.png"); 
            mRomanNumeral[2] = loadImage("roman_3.png"); 
            mRomanNumeral[3] = loadImage("roman_4.png"); 
            mRomanNumeral[4] = loadImage("roman_5.png"); 
            mRomanNumeral[5] = loadImage("roman_6.png"); 
            mRomanNumeral[6] = loadImage("roman_7.png"); 
            mRomanNumeral[7] = loadImage("roman_8.png"); 
            mRomanNumeral[8] = loadImage("roman_9.png"); 
            mRomanNumeral[9] = loadImage("roman_10.png"); 

            // draw Rectangles
            for(int i = 0; i < 10; i++) {
                mRomanNumeral[i] = mRomanNumeral[i].getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                g2d.drawRect(xPoints[i], yPoints[i], 100, 100);

                // Logic is different for 10th slot because of treasure picture
                if(i != 9)
                    g2d.drawImage(mRomanNumeral[i], xPoints[i] + 25, yPoints[i] + 25, null);
                else {
                    g2d.drawImage(mRomanNumeral[9], 265, 75, null);
                }
            }

            // Top Left Munchkin Logo
            placeImage(g2d, "munchkin_logo.png", 0, 0, 200, 200);

            // Treasure Text
            placeImage(g2d, "treasure_word.png", 360, 400, 150, 30);

            // Treasure Picture on 10th square
            placeImage(g2d, "treasure.png", 260, 120, 50, 50);

            // Door Text
            placeImage(g2d, "door_word.png", 40, 580, 120, 30);

            
            setPlayerPosition(Munchkin.player[0]);
            setPlayerPosition(Munchkin.player[1]);
            setPlayerPosition(Munchkin.player[2]); 
        }

        public JLabel placeImageButton(JLabel imageButton, String imageName, int x, int y, int width, int height) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(imageName));
            imageIcon = scaleImage(width, height, imageIcon); 
            imageButton = new JLabel(imageIcon);
            add(imageButton);
            imageButton.setBounds(x, y, width, height);
            return imageButton;
        }
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == mLeftPanelButton[0]) {
                System.out.println("Main Menu");
            }
            else if(event.getSource() == mLeftPanelButton[1]) {
                // opens to .pdf of rules in the user's default browswer
                openWebpage("https://munchkin.game/site-munchkin/assets/files/1138/munchkin_rules-1.pdf");
            }
            else if(event.getSource() == mLeftPanelButton[2]) {
                // quit program
                System.exit(0);
            }
        }
    }

    private class MouseHandler implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == mTreasureButton) {
                Munchkin.player[Munchkin.playerTurn].drawTreasure(Munchkin.newDeck);
                placeButtons(Munchkin.player[Munchkin.playerTurn]);
            }
            else if(e.getSource() == mDoorButton) {
                System.out.println("Door Button Pressed");
            }
            else {
                Vector<JButton> tempButton = Munchkin.player[Munchkin.playerTurn].getCardButtons(); 
                Vector<Card> cardList = Munchkin.player[Munchkin.playerTurn].getHand();
                for(int i = 0; i < tempButton.size(); i++) {
                    if(e.getSource() == tempButton.get(i)) {
                        if(cardList.get(i).getType() == 'A') {
                            Munchkin.player[Munchkin.playerTurn].setCurrentPowerLevel(cardList.get(i).getBonus());
                            System.out.println(Munchkin.player[Munchkin.playerTurn].getCurrentPowerLevel());

                            // Remove card from the player list
                            cardList.remove(i);

                            // Remove the button
                            tempButton.remove(i); 

                            // Update the button UI
                            placeButtons(Munchkin.player[Munchkin.playerTurn]);
                        }
                    }
                }
            }
        }

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
    }

    // will set position of pieces
    void setPlayerPosition(Player player) {
        player.updatePosition();
        placeImage(g2d, player.getImageName(), player.getXPosition(), player.getYPosition(), 30, 30);
    }

    ImageIcon scaleImage(int width, int height, ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    // https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // https://stackoverflow.com/questions/10489773/getting-images-from-a-jar-file
    public BufferedImage loadImage(String fileName) {
        BufferedImage buff = null;
        try {
            buff = ImageIO.read(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return buff;
    }

    public void placeImage(Graphics2D g2d, String imageName, int x, int y, int width, int height) {
        Image image = loadImage(imageName); 
        image = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); 
        g2d.drawImage(image, x, y, null);
    }

    public void placeButtons(Player player) {
        player.updateHand();
        Vector<JButton> buttons = player.getCardButtons(); 
        addButtons(buttons);
    }

    public void addButtons(Vector<JButton> button) {
        mRightPanel.removeAll();
        for(JButton x : button) {
            mRightPanel.add(x);
            x.addMouseListener(mouseHandler);
        }
        revalidate();
    }
}