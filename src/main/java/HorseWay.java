/**
 * Created by admin on 4/15/14.
 */
public class HorseWay {
    //Size of a board on wich horse will move
    public static final int SIZE = 8;

    int [][] board = new int[SIZE][SIZE];

    Horse horse = new Horse(4,0,board);

    public static void main(String[] args){
        HorseWay horseWay = new HorseWay();

        horseWay.go();
        horseWay.drawBoard();


    }

    /*
    Warnsdorff's rule. Looking for position that has a minimum moves.
     */
    public void go(){
        while (horse.hasMove()){
            board[horse.y][horse.x] = horse.madeMoves++;
            horse.goTo(minWays(horse.x, horse.y));

        }
        board[horse.x][horse.y] = horse.madeMoves; //Mark the last move
        System.out.println("moves: " + horse.madeMoves);
    }

    public void goBack(){
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] == horse.madeMoves) {
                    board[i][j] = 0;
                }
                if (board[i][j] == horse.madeMoves - 1) {
                    horse.move(i,j);
                    horse.madeMoves--;
                }
            }

    }

    /*
    search for a minimum moves
     */
    public int minWays(int fromX, int fromY){
        int moves = 8; //maximum value for possible moves
        int min = 0;
        for(int move = 0; move < horse.availableMoves.length; move++) {
            if (horse.availableMoves[move]) {
                horse.goTo(move);
                if(waysCount() < moves) {
                    moves = waysCount();
                    min = move;
                }
                horse.move(fromX, fromY);
            }
        }
        return min;
    }


    /*
    count each available move
     */
    public int waysCount(){
        int waysCount = 0;
        for(int i = 0; i < horse.availableMoves.length; i++){
            if(horse.availableMoves[i])
                waysCount++;
        }
        return waysCount;
    }

    /*
    draw board in console
     */
    public void drawBoard(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] < 10 ? "   " + board[i][j] : "  " + board[i][j]);
            }
            System.out.println();
        }
    }
}
