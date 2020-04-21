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
                System.out.println(attrb[0]);  
            }  
        }   
        catch (IOException e)   
        {  
        e.printStackTrace();  
        }   
    }
    public static void main(String args[]){
        Deck deqe = new Deck();

    }
}