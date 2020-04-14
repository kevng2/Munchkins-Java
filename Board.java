import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Color;

public class Board extends JFrame {
    private static final long serialVersionUID = 1L;
    private BorderLayout mBorderLayout;
    private JPanel mMunchkinLayout;

    Board() {
        super("Munchkin");

        mBorderLayout = new BorderLayout();
        setLayout(mBorderLayout);
        mMunchkinLayout = new BoardGUI();
        add(mMunchkinLayout, BorderLayout.CENTER);
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class BoardGUI extends JPanel {
        private static final long serialVersionUID = 1L;
        private final int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            setSize(600, 800);
            // parallel arrays that hold the x, y coordinates for the rectangles
            int[] xPoints = { 300, 375, 225, 200, 70, 70, 200, 375, 375, 240 };
            int[] yPoints = { 650, 470, 480, 350, 350, 225, 190, 150, 25, 75 };
            int[] width = { 150, 75, 100, 100, 100, 100, 100, 100, 100, 100 };
            int[] height = { 75, 150, 100, 100, 100, 100, 120, 100, 100, 100 };

            g2d.setColor(Color.gray);

            for (int i = 0; i < 10; i++) {
                g2d.fillRect(xPoints[i], yPoints[i], width[i], height[i]);
            }
        }
    }
}