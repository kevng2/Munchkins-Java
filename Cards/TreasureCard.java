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
    public int getValue(){
        return value;
    }
}