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
    }
}