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
    public abstract Image getPiece();
    public abstract int getXPosition();
    public abstract int getYPosition();
    public abstract void setCurrentLevel(int level);
	public abstract String getImageName();

	public int getCurrentLevel() {
		// TODO Auto-generated method stub
		return mCurrentLevel;
	}
	public int getPowerLevel(){
		int modifiers = 0;
		for(int x: items.values()){
			modifiers+=x;
		}
		return modifiers+mCurrentLevel;
	}
	public void addItem(String x, int y){
		items.put(x,y);
	}

}

class Player1 extends Player {
    Player1(Deck d) {
        mCurrentLevel = 1;
        mPiece = loadImage("dragon.png");
        mImageName = "dragon.png";
        xPosition = 310;
		yPosition = 610;
		for(int i=0;i<4;i++){
			hand.add(d.popDoor());
			hand.add(d.popTreas());
		}
    }
	
    @Override
    public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel - 1] + 10;
        yPosition = Board.yPoints[mCurrentLevel - 1] + 10;
    }

	@Override
	public Image getPiece() {
        return mPiece;
	}

	@Override
	public int getXPosition() {
		return xPosition;
	}

	@Override
	public int getYPosition() {
		return yPosition;
	}

	@Override
	public String getImageName() {
        return mImageName;
	}

	@Override
	public void setCurrentLevel(int level) {
        mCurrentLevel = level;
	}
}

class Player2 extends Player {
    Player2(Deck d) {
        mCurrentLevel = 1;
        mPiece = loadImage("shrek.png");
        mImageName = "shrek.png";
        xPosition = 360;
		yPosition = 610;
		for(int i=0;i<4;i++){
			hand.add(d.popDoor());
			hand.add(d.popTreas());
		}
    }

	@Override
	public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel - 1] + 60;
        yPosition = Board.yPoints[mCurrentLevel - 1] + 10; 
	}

	@Override
	public Image getPiece() {
		return mPiece;
	}

	@Override
	public int getXPosition() {
		return xPosition;
	}

	@Override
	public int getYPosition() {
		return yPosition;
	}

	@Override
	public String getImageName() {
        return mImageName;
	}

	@Override
	public void setCurrentLevel(int level) {
        mCurrentLevel = level;
	}
}

class Player3 extends Player {
    Player3(Deck d) {
        mCurrentLevel = 1;
        mPiece = loadImage("sword.png");
        mImageName = "sword.png";
        xPosition = 360;
		yPosition = 660;
		for(int i=0;i<4;i++){
			hand.add(d.popDoor());
			hand.add(d.popTreas());
		}
    }

	@Override
	public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel - 1] + 60; 
        yPosition = Board.yPoints[mCurrentLevel - 1] + 60;
	}

	@Override
	public Image getPiece() {
		return mPiece;
	}

	@Override
	public int getXPosition() {
		return xPosition;
	}

	@Override
	public int getYPosition() {
		return yPosition;
	}

	@Override
	public String getImageName() {
        return mImageName;
	}

	@Override
	public void setCurrentLevel(int level) {
        mCurrentLevel = level;
	}
}