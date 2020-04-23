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

        boolean wincondition = false;
        int winner = 0;

        while(wincondition){
            if(player[0].getCurrentLevel() == 10){
                winner = 1;
                break;
            }
            //add player 1 logic here
            //event handle if player wants to add item and use player.addItem function

            if(player[1].getCurrentLevel() == 10){
                winner = 1;
                break;
            }
            //add player 2 logic here
            if(player[2].getCurrentLevel() == 10){
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
            //update
            if(p.getPowerLevel() < door.getLevel()){
                //request help
                //event handle if the player wants to help
                //we would change help to be a boolean
                int option = 0;
                //change option to see what user wants
                if(option == 1){
                    Random ran = new Random();
                    int result = ran.nextInt(6);
                    if(result >= 5){
                        System.out.println("Successfully ran away");
                    }
                    else{
                        wincondition = false;
                    }
                }
                else{
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
                if(door.getLevelLoss()!=0){
                    p.setCurrentLevel(p.getCurrentLevel()-door.getLevelLoss());
                }
                if(door.getItemLoss()!=0){
                    if(door.getDeath()!=0){
                        p.stripItem(-1);
                    }
                    else{
                        p.stripItem(door.getItemLoss());
                    }
                }
                //add mor econditions
            }
                
            
        }
        else{
            if(door.getCurse() == -1){
                p.setCurrentLevel(p.mCurrentLevel-1);
            }
            else if(door.getCurse()==-3){
                p.stripItem(1);
            }
            else if(door.getCurse()==-99){
                p.discardCard(-1);
            }
        }
    }
}