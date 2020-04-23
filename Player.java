import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

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
    protected Vector<JButton> cardButtons = new Vector<JButton>();
    
    Player(Deck d) {
        mCurrentLevel = 1;
        for(int i = 0; i < 2; i++) {
			hand.add(d.popDoor());
			hand.add(d.popTreas());
		}
    }

    public void updateHand() {
        cardButtons.clear();

        // iterate through the hand
        for (Card card : hand) {
            String text = card.getName(); 

            // concatenate the html string together with the content. If statements checks the type of the cards
            if(card.getType() == 'T') {
                text = "<html>Treasure Card<br/>" + "+" + card.getBonus() + " Bonus<br/>" + card.getName() +
                 "</html>";
            }
            else if(card.getType() == 'L') {
                text = "<html> Level Card <br/>" + card.getName() + "<br/>" + "<br/></html>";
            }
            else if(card.getType() == 'M') {
                text = "<html> Monster Card <br/> Level " + card.getLevel() + "<br/>" + card.getName() + "<br/>" 
                + "Level Loss: " + card.getLevelLoss() + "<br/>" + "Treasure: " + card.getTreasure()
                 + "<br/>" + "Item Loss: " + card.getItemLoss() + "<br/>"+ "Level Gain: " + card.getLevelGain()
                 + "<br/>" + "Discard: " + card.getDiscard() + "<br/>" + "Death: " + card.getDeath();
            }
            else if(card.getType() == 'C') {
                int curse = card.getCurse();
                String curseType;
                if(curse == -1) {
                    curseType = "Lose Level";
                }
                else if(curse == -2) {
                    curseType = "Add Monster";
                }
                else if(curse == -3) {
                    curseType = "Lose armor";
                }
                else {
                    curseType = "Lose all cards";
                }

                text = "<html>" + "CURSE!" + "<br/>" + card.getName() + "<br/>" +
                curseType + "</html>";
            }
            else if(card.getType() == 'A') {
                text = "<html> Accessory Card <br/>" + card.getName() + "<br/>" + "Bonus: " + card.getBonus() +
                "<br/>" + "Bodypart: " + card.getPart();
            }
            
            // add the button the array to print out later
            cardButtons.add(new JButton(text));
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

    public void addCardHand(Card c){
        hand.add(c);
    }
    public void stripItem(int amt){
        if(amt == -1){
            items.clear();
        }
        else{
            int i = 0;
            for(String key: items.keySet()){
                if(i++==amt-1){
                    break;
                }
                items.remove(key);
                
            }
        }
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
    
    public Vector<JButton> getCardButtons() {
        return cardButtons;
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