package Cards;
public class TreasureCard extends Card{
    private int bonus;
    private int value;
    public TreasureCard(String name,char type1,int b,int v){
        super(name,type1);
        bonus = b;
        value = b;
    }
    public int getBonus(){
        return bonus;
    }
    public int getLevel(){
        return -100;
    }
    public  int getTreasure(){
        return value;
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
	public int getCurse() {
		return -100;
	}
	public String getPart() {
		return null;
	}
}