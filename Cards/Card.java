package Cards;
public abstract class Card {
    protected String name;
    protected char type;

    public Card(String name1,char t){
        name = name1;
        type = t;
    }
    public char getType(){
        return type;
    }
    public String getName(){
        return name;
    }

    public abstract int getLevel();
    public abstract int getTreasure();
    public abstract int getDiscard();
    public abstract int getLevelGain();
    public abstract int getLevelLoss();
    public abstract int getItemLoss();
    public abstract int getDeath();
    public abstract int getBonus();
    public abstract int getCurse();
    public abstract String getPart();
}