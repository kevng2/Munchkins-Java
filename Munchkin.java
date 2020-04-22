import Cards.*;

public class Munchkin {
    public static final Deck newDeck = new Deck();
    public static Player player1 = new Player1(newDeck);
    public static Player player2 = new Player2(newDeck);    
    public static Player player3 = new Player3(newDeck);

    public static void main(String[] args) {
        Board game = new Board();

        game.updateHand(player1);

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

    void kickDoor(Player p, Deck d,int playnum, Player OP1, Player OP2){
        Card door = d.popDoor();
        int help1=0,help2=0;
        boolean wincondition = false;
        if(door.getType() == 'm'){
            if(p.getPowerLevel() < door.getLevel()){
                //request help
                //event handle if the player wants to help
                if(playnum==1){
                    boolean help = false;
                    //here ask player 2 for help
                    if(help){
                        help1 = OP1.getPowerLevel();
                    }
                    //here ask player 3 for help
                    if(help){
                        help2=OP2.getPowerLevel();
                    }
                    
                }
                else if(playnum==2){
                    boolean help = false;
                    //here
                    //here ask player 1 for help
                    if(help){
                        help1 = OP1.getPowerLevel();
                    }
                    //here ask palyer 3 for help
                    if(help){
                        help2=OP2.getPowerLevel();
                    }
                }
                else{
                    boolean help = false;
                    //here ask player 1
                    if(help){
                        help1 = OP1.getPowerLevel();
                    }
                    //here ask player 2 for help
                    if(help){
                        help2=OP2.getPowerLevel();
                    }
                }
                if(p.getPowerLevel()+help1+help2>=door.getLevel()){
                    wincondition = true;
                }
            }
            else{
                wincondition=true;
            }
            if(wincondition){
                p.setCurrentLevel(door.getLevelGain());
                Card reward = d.popTreas();
                if(reward.getType() == 'L' ){
                    p.setCurrentLevel(p.getCurrentLevel()+reward.getLevel());
                }
                else if(reward.getType() == 'A'){
                    p.addItem(reward.getName(),reward.getBonus() );
                }
                else{
                    p.addCardHand(reward);
                }
                //implement logic to get treasure and add to char
            }
            else if(!wincondition){
                if(door.getDiscard()!=0){
                    p.discardCard(door.getDiscard());
                }
                //add mor econditions
            }
        }
    }
}