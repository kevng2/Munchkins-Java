package Cards;
public class Card {
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
}



