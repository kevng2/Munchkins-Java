import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Image;

public class Board extends JFrame {
    private static final long serialVersionUID = 1L;
    private BorderLayout mBorderLayout;
    private JPanel mMunchkinLayout;

    Board() {
        super("Munchkin");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mBorderLayout = new BorderLayout();
        setLayout(mBorderLayout);

        mMunchkinLayout = new BoardGUI();
        add(mMunchkinLayout, BorderLayout.CENTER);

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
            setBackground(new Color(3, 252, 148));
            mMunchkinLogo = new ImageIcon(getClass().getResource("munchkin_logo.png"));
            Image image = mMunchkinLogo.getImage();
            Image newImg = image.getScaledInstance(170, 170, java.awt.Image.SCALE_SMOOTH);
            mMunchkinLogo = new ImageIcon(newImg);
            mLogoHolder = new JLabel(mMunchkinLogo, JLabel.LEFT);
            add(mLogoHolder);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // load Munchkin logo
            mMunchkinLogo = new ImageIcon(getClass().getResource("munchkin_logo.png"));
            mLogoHolder = new JLabel(mMunchkinLogo, JLabel.LEFT);
            add(mLogoHolder);

            // parallel arrays that hold the x, y, width, and height for the rectangles
            int[] xPoints = { 300, 375, 225, 200, 70, 70, 200, 375, 375, 240 };
            int[] yPoints = { 650, 470, 480, 350, 350, 225, 190, 150, 25, 75 };
            int[] width = { 150, 75, 100, 100, 100, 100, 100, 100, 100, 100 };
            int[] height = { 75, 150, 100, 100, 100, 100, 120, 100, 100, 100 };

            g2d.setColor(Color.gray);

            // draw Rectangles
            for (int i = 0; i < 10; i++) {
                g2d.fillRect(xPoints[i], yPoints[i], width[i], height[i]);
            }
        }
    }
}