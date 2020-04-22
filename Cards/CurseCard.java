package Cards;
public class CurseCard extends Card {
    private int curse;
    public CurseCard(String name,char type1,int cursetype){
        super(name,type1);
        curse = cursetype;
    }
    public int getCurse(){
        return curse;
        //-1 is lose level
        //-2 is add monster
        //-3 is lose armor
        //-99 is lose all cards
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
    public int getBonus(){
        return -100;
    }
	public String getPart() {
		return null;
	}
}