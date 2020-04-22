import Cards.*;

public class Munchkin {
    public static void main(String[] args) {
        Board game = new Board();
        Deck newDeck = new Deck();
        Player player1 = new Player1(newDeck);
        Player player2 = new Player2(newDeck);
        Player player3 = new Player3(newDeck);
        game.setPlayerPosition(player1);
        game.setPlayerPosition(player2);
        game.setPlayerPosition(player3);
        boolean wincondition = false;
        int winner = 0;

        
        
        while(wincondition){
            if(player1.getCurrentLevel() == 10){
                winner = 1;
                break;
            }
            //add player 1 logic here
            //event handle if player wants to add item and use player.addItem function
            if(player2.getCurrentLevel() == 10){
                winner = 1;
                break;
            }
            //add player 2 logic here
            if(player3.getCurrentLevel() == 10){
                winner = 1;
                break;
            }
            //add player 3 logic here
        }



    }

    void kickDoor(Player p, Deck d){
        Card door = d.popDoor();
        if(door.getType() == 'm'){
            if(p.getPowerLevel() < door.getLevel()){
                
            }
        }
    }
}