package Cards;

public class LevelUpCard extends Card {
    private int levelamt;
    public LevelUpCard(String name1, char type1, int amtlvl){
        super(name1,type1);
        levelamt = amtlvl;
    }
    public int getLvl(){
        return levelamt;
    }
}