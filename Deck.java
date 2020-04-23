import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException; 
import java.util.*; 
import Cards.*;
public class Deck {
    private Stack<Card> treasureStack = new Stack<Card>();
    private Stack<Card> doorStack = new Stack<Card>();
    public Deck(){
        String filename = "cardset.csv";
        String line = "";  
        String splitBy = ",";  
        ArrayList<Card> tlist = new ArrayList<Card>();
        ArrayList<Card> dlist = new ArrayList<Card>();
        try   
        {  
            
            BufferedReader br = new BufferedReader(new FileReader(filename));  
            while ((line = br.readLine()) != null) 
            {  
                String[] attrb = line.split(splitBy);    // use comma as separator  
                if (attrb[0].compareTo("monster") == 0){
                    dlist.add(new MonsterCard(attrb[1],'M',Integer.parseInt(attrb[2]),Integer.parseInt(attrb[3]),Integer.parseInt(attrb[4]),Integer.parseInt(attrb[5]),Integer.parseInt(attrb[6]),Integer.parseInt(attrb[7]),Integer.parseInt(attrb[8])));
                }
                else if(attrb[0].compareTo("treas") == 0){
                    // System.out.println(attrb[1]);
                    tlist.add(new TreasureCard(attrb[1],'T',Integer.parseInt(attrb[2]),Integer.parseInt(attrb[3])));
                }
                else if(attrb[0].compareTo("curse")== 0){
                    dlist.add(new CurseCard(attrb[1],'C',Integer.parseInt(attrb[2])));
                }
                else if(attrb[0].compareTo("lvlup")==0){
                    tlist.add(new LevelUpCard(attrb[1],'L',Integer.parseInt(attrb[2])));
                }
                else if(attrb[0].compareTo("acc")==0){  
                    tlist.add(new AccessoryCard(attrb[1],'A', Integer.parseInt(attrb[2]),attrb[3]));
                }
            }
            
            Collections.shuffle(dlist);
            Collections.shuffle(tlist);
            // System.out.println(dlist.get(0).getName());
            doorStack.addAll(dlist);
            treasureStack.addAll(tlist);
        }   
    
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }   
    }
    public int size(){
        return doorStack.size()+treasureStack.size();
    }
    public Card popDoor(){
        return doorStack.pop();
    }
    public Card popTreas(){
        return treasureStack.pop();
    }
}