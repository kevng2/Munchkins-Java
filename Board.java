import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Board extends JFrame {
    private static final long serialVersionUID = 1L;
    private BorderLayout mBorderLayout;
    private JPanel mMunchkinLayout;
    private JPanel mLeftPanel;
    private Player[] mPlayer;
    private JLabel mTitle;
    private JButton[] mLeftPanelButton;
    private ButtonHandler mButtonHandler;

    Board() {
        super("Munchkin");
        setSize(700, 900);
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
        title = scaleImage(700, 150, title);
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

        for (int i = 0; i < mLeftPanelButton.length; i++) {
            mLeftPanel.add(mLeftPanelButton[i], mLeftPanel);
            mLeftPanelButton[i].addActionListener(mButtonHandler);
        }

        setVisible(true);
    }

    private class BoardGUI extends JPanel {
        private static final long serialVersionUID = 1L;
        private final int[] mNumbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        private ImageIcon mMunchkinLogo;
        private JLabel mLogoHolder;
        private Image[] mRomanNumeral;

        BoardGUI() {
            setLayout(new FlowLayout(FlowLayout.LEFT));
            setSize(600, 800);
            setBackground(new Color(204, 102, 0));

            // Image
            mMunchkinLogo = new ImageIcon(getClass().getResource("munchkin_logo.png"));
            mMunchkinLogo = scaleImage(200, 200, mMunchkinLogo);
            mLogoHolder = new JLabel(mMunchkinLogo, JLabel.LEFT);
            add(mLogoHolder);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // parallel arrays that hold the x, y, width, and height for the rectangles
            int[] xPoints = { 300, 375, 225, 200, 70, 70, 200, 375, 375, 240 };
            int[] yPoints = { 600, 470, 480, 350, 350, 225, 190, 150, 25, 75 };

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
            }
            g2d.drawImage(mRomanNumeral[9], 265, 75, null);

            // Treasure card
            placeImage(g2d, "treasure_card.png", 350, 300, 170, 100);

            // Treasure Text
            placeImage(g2d, "treasure_word.png", 360, 400, 150, 30);

            // Treasure Picture
            placeImage(g2d, "treasure.png", 260, 120, 50, 50);

            // Door Card
            placeImage(g2d, "door_card.png", 20, 480, 170, 100);

            // Door Text
            placeImage(g2d, "door_word.png", 40, 580, 120, 30);
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

    // will set position of pieces
    void setPlayerLevel(int level) {

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
}