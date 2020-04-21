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
}