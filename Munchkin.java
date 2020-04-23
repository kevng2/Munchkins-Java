import Cards.*;
import java.util.Random;

public class Munchkin {
    public static final Deck newDeck = new Deck();
    /*
    public static Player player1 = new Player1(newDeck);
    public static Player player2 = new Player2(newDeck);    
    public static Player player3 = new Player3(newDeck);
    */
    public static Player[] player = new Player[3];
    public static int playerTurn = 0;

    public static void main(String[] args) {
        player[0] = new Player1(newDeck);
        player[1] = new Player2(newDeck);
        player[2] = new Player3(newDeck);
        Board game = new Board();

        game.placeButtons(player[0]);

        boolean wincondition = true;
        int winner = 0;
        int currplayer=0;

        while(wincondition){
            if(player[currplayer].getCurrentLevel() == 10) {
                winner = 1;
                break;
            }
            //kickDoor(player[currplayer],newDeck,currplayer,player);
            //currplayer++;
        }
    }
}