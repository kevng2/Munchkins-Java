import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Cards.*;
import java.util.*; 
import javafx.util.Pair;

public abstract class Player {
    protected int mCurrentLevel; 
    protected int xPosition;
    protected int yPosition;
    protected Image mPiece;
	protected String mImageName;
	protected HashMap<String,Integer> items = new HashMap<String,Integer>();  
    protected Vector<Card> hand = new Vector<Card>();
    
    Player(Deck d) {
        mCurrentLevel = 1;
        for(int i=0;i<4;i++) {
			hand.add(d.popDoor());
			hand.add(d.popTreas());
		}
    }

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

    public abstract void updatePosition();
    public void discardCard(int amt){
        for(int i=0;i<amt;i++){
            hand.remove(0);
        }
    }

	public int getCurrentLevel() {
		return mCurrentLevel;
    }

	public int getPowerLevel(){
		int modifiers = 0;
		for(int x: items.values()){
			modifiers+=x;
		}
		return modifiers+mCurrentLevel;
    }

    public Vector<Card> getHand() {
        return hand;
    }

	public void addItem(String x, int y){
		items.put(x,y);
    }

	public Image getPiece() {
        return mPiece;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public String getImageName() {
        return mImageName;
	}

	public void setCurrentLevel(int level) {
        mCurrentLevel = level;
    }

    public void drawTreasure(Deck d) {
        hand.add(d.popTreas());
    }

    public void drawDoor(Deck d) {
        hand.add(d.popDoor());
    }
}

class Player1 extends Player {
    Player1(Deck d) {
        super(d);

        mPiece = loadImage("dragon.png");
        mImageName = "dragon.png";
        xPosition = 310;
		yPosition = 610;
    }
	
    @Override
    public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel - 1] + 10;
        yPosition = Board.yPoints[mCurrentLevel - 1] + 10;
    }
}

class Player2 extends Player {
    Player2(Deck d) {
        super(d);
        mPiece = loadImage("shrek.png");
        mImageName = "shrek.png";
        xPosition = 360;
		yPosition = 610;
    }

	@Override
	public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel - 1] + 60;
        yPosition = Board.yPoints[mCurrentLevel - 1] + 10;
	}
}

class Player3 extends Player {
    Player3(Deck d) {
        super(d);
        mPiece = loadImage("sword.png");
        mImageName = "sword.png";
        xPosition = 360;
		yPosition = 660;
    }

	@Override
	public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel - 1] + 60; 
        yPosition = Board.yPoints[mCurrentLevel - 1] + 60;
	}
}