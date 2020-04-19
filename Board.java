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
        setSize(700, 950);
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


            Image[] img = new Image[10];
            img[0] = loadImage("roman_1.png"); 
            img[1] = loadImage("roman_2.png"); 
            img[2] = loadImage("roman_3.png"); 
            img[3] = loadImage("roman_4.png"); 
            img[4] = loadImage("roman_5.png"); 
            img[5] = loadImage("roman_6.png"); 
            img[6] = loadImage("roman_7.png"); 
            img[7] = loadImage("roman_8.png"); 
            img[8] = loadImage("roman_9.png"); 
            img[9] = loadImage("roman_10.png"); 

            // draw Rectangles
            for(int i = 0; i < 10; i++) {
                img[i] = img[i].getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                g2d.drawRect(xPoints[i], yPoints[i], 100, 100);
                g2d.drawImage(img[i], xPoints[i] + 25, yPoints[i] + 25, null);
            }
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return buff;
    }
}