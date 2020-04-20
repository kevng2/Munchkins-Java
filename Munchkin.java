public class Munchkin {
    public static void main(String[] args) {
        Board game = new Board();
        Player player1 = new Player1();
        Player player2 = new Player2();
        Player player3 = new Player3();

        game.setPlayerPosition(player1);
        game.setPlayerPosition(player2);
        game.setPlayerPosition(player3);
    }
}