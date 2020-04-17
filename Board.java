import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.java2d.pipe.BufferedBufImgOps;

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
import java.awt.image.BufferedImage;
import java.io.File;
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
            mMunchkinLogo = scaleImage(170, 170, mMunchkinLogo);
            mLogoHolder = new JLabel(mMunchkinLogo, JLabel.LEFT);
            add(mLogoHolder);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // parallel arrays that hold the x, y, width, and height for the rectangles
            int[] xPoints = { 300, 375, 225, 200, 70, 70, 200, 375, 375, 240 };
            int[] yPoints = { 650, 470, 480, 350, 350, 225, 190, 150, 25, 75 };
            int[] width = { 150, 75, 100, 100, 100, 100, 100, 100, 100, 100 };
            int[] height = { 75, 150, 100, 100, 100, 100, 120, 100, 100, 100 };

            g2d.setColor(Color.gray);
            g2d.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));


            /*
            Image img = null;
            try {
                img = ImageIO.read(new File("roman_numerals.png")); 
            }
            catch (IOException e) {}
*/

            // draw Rectangles
            for(int i = 0; i < 10; i++) {
//                img.getScaledInstance(width[i], height[i], java.awt.Image.SCALE_SMOOTH);
                g2d.drawRect(xPoints[i], yPoints[i], width[i], height[i]);
 //               g2d.drawImage(img, xPoints[i], yPoints[i], null);
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
}