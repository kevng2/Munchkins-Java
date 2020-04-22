package Cards;

public class LevelUpCard extends Card {
    private int levelamt;
    public LevelUpCard(String name1, char type1, int amtlvl){
        super(name1,type1);
        levelamt = amtlvl;
    }
    public int getLevel(){
        return levelamt;
    }
    public  int getTreasure(){
        return -100;
    }
    public  int getDiscard(){
        return -100;
    }
    public  int getLevelGain(){
        return -100;
    }
    public  int getLevelLoss(){
        return -100;
    }
    public  int getItemLoss(){
        return -100;
    }
    public  int getDeath(){
        return -100;
    }
    public int getBonus(){
        return -100;
    }
    public int getCurse(){
        return -100;
    }
	public String getPart(){
        return "";
    }
}