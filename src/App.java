public class App  {
 public static void main(String[] args) {
    Board gameBoard = new Board(8,8,10);
    gameBoard.printBoard();
    gameBoard.printAdjacent();
 }

}
