package Cards;
public class MonsterCard extends Card{
    private int level;
    private int treasureAmt;
    private int discard;
    private int levelup;
    private int leveldown;
    private int itemloss;
    private int death;
    public MonsterCard(String name1, char type1, int levels, int treasure, int discardAmt, int addlevel, int loselevel, int loseitem, int isdeath){
        super(name1,type1);
        level = levels;
        treasureAmt = treasure;
        discard = discardAmt;
        levelup = addlevel;
        leveldown = loselevel;
        itemloss = loseitem;
        death = isdeath;
    }
    public int getLevel(){
        return level;
    }
    public int getTreasure(){
        return treasureAmt;
    }
    public int getDiscard(){
        return discard;
    }
    public int getLevelGain(){
        return levelup;
    }
    public int getLevelLoss(){
        return leveldown;
    }
    public int getItemLoss(){
        return itemloss;
    }
    public int getDeath(){
        return death;
    }
    
}