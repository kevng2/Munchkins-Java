package Cards;

public class AccessoryCard extends Card{
    private int bonus;
    private String bodypart;
    public AccessoryCard(String name1, char type1, int powerup, String where){
        super(name1,type1);
        bonus = powerup;
        bodypart = where;
    }
    public int getBonus(){
        return bonus;
    }
    public String getPart(){
        return bodypart;
    }
    public int getCurse(){
        return -100;
    }
    public int getLevel(){
        return -100;
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
}