import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Player {
    protected int mCurrentLevel; 
    protected int xPosition;
    protected int yPosition;
    protected Image mPiece;
    protected String mImageName; 

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
}

class Player1 extends Player {
    Player1() {
        mCurrentLevel = 1;
        mPiece = loadImage("dragon.png");
        mImageName = "dragon.png";
        xPosition = 310;
        yPosition = 610;
    }

    @Override
    public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel] + 10;
        yPosition = Board.yPoints[mCurrentLevel] + 10;
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
    Player2() {
        mCurrentLevel = 1;
        mPiece = loadImage("shrek.png");
        mImageName = "shrek.png";
        xPosition = 360;
        yPosition = 610;
    }

	@Override
	public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel] + 60;
        yPosition = Board.yPoints[mCurrentLevel] + 10; 
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
    Player3() {
        mCurrentLevel = 1;
        mPiece = loadImage("sword.png");
        mImageName = "sword.png";
        xPosition = 360;
        yPosition = 660;
    }

	@Override
	public void updatePosition() {
        xPosition = Board.xPoints[mCurrentLevel] + 60; 
        yPosition = Board.yPoints[mCurrentLevel] + 60;
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