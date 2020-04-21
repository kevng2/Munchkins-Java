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
        try   
        {  
            
            BufferedReader br = new BufferedReader(new FileReader(filename));  
            while ((line = br.readLine()) != null) 
            {  
                String[] attrb = line.split(splitBy);    // use comma as separator  
                if (attrb[0].compareTo("monster") == 0){
                    doorStack.push(new MonsterCard(attrb[0],Integer.parseInt(attrb[1]),Integer.parseInt(attrb[2]),Integer.parseInt(attrb[3]),Integer.parseInt(attrb[4]),Integer.parseInt(attrb[5]),Integer.parseInt(attrb[6]),Integer.parseInt(attrb[7])));
                }
            }  
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }   
    }
    public static void main(String args[]){
        Deck deqe = new Deck();
        System.out.println(deqe.size());
    }
    public int size(){
        return doorStack.size()+treasureStack.size();
    }
}