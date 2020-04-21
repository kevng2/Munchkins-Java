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
}