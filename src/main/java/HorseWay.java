/**
 * Class contains methods to solve Knight's tour problem.
 */
public class HorseWay {
    /**
     * Size of a board on which horse will move.
     */
    public final int BOARD_SIZE = 8;

    /**
     * Total number of moves that horse should make.
     */
    private final int TOTAL_MOVES;

    /**
     * Contains state of solution.
     */
    private boolean solution;

    /**
     * board on which horse will move
     */
    int [][] board;

    /**
     * Horse that will moves around the board.
     */
    private Horse horse;

    /**
     * Constructor.
     * Initialize TOTAL_MOVES, board and horse.
     * Initialize solution by false.
     * @param startX x coordinate of the start position.
     * @param startY y coordinate of the start position.
     */
    public HorseWay(int startX, int startY) {
        TOTAL_MOVES = BOARD_SIZE * BOARD_SIZE;
        this.solution = false;
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.horse = new Horse(startX, startY, board);
    }


    /**
     * Puts the horse in the first position and begins to move the horseю
     */
    public void run(){
        board[horse.y][horse.x] = ++horse.madeMoves;
        go();
    }

    /**
     * Warnsdorff's rule. Looking for position that has a minimum moves.
     * If horse hasn't available moves and it makes a total number of moves, solution is found.
     * Outputs solution in console.
     */
    public void go(){
        if(!horse.hasMove() && horse.madeMoves == TOTAL_MOVES) {
            if(!solution) {
                drawBoard();
                solution = true;
            }
        }
        else{
            boolean[] minWays = minWays(horse.x, horse.y);
            for (int i = 0; i < minWays.length; i++) {
                if(minWays[i]){
                    goTo(i);
                    go();
                    goBack();
                }
            }
        }
    }

    /**
     * Moves horse to current direction and mark position on board.
     * @param direction movement direction.
     */
    public void goTo(int direction){
        horse.goTo(direction);
        board[horse.y][horse.x] = ++horse.madeMoves;
    }

    /**
     * Move horse back on one position.
     */
    public void goBack(){
        board[horse.y][horse.x] = 0;
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board.length; j++) {
                if (board[i][j] == horse.madeMoves - 1) {
                    horse.move(j,i);
                }
            }
        horse.madeMoves--;
    }

    /**
     * Looking next position which has a minimum moves.
     * @param fromX x coordinate.
     * @param fromY y coordinate.
     * @return array that contains directions to minimum ways.
     */
    public boolean[] minWays(int fromX, int fromY){
        int moves = 8; //maximum value for possible moves
        boolean[] minWays = new boolean[8]; //total number of ways.
        for(int move = 0; move < horse.availableMoves.length; move++) {
            if (horse.availableMoves[move]) {
                horse.goTo(move);

                if(waysCount() < moves) {
                    clearWays(minWays);
                    moves = waysCount();
                    minWays[move] = true;
                }

                if(waysCount() == moves) {
                    minWays[move] = true;
                }

                horse.move(fromX, fromY);
            }
        }

        return minWays;
    }

    /**
     * sets the value of each array element - false.
     * @param minWays array.
     */
    private void clearWays(boolean[] minWays){
        for(int i = 0; i < minWays.length; i++)
            minWays[i] = false;
    }
    /**
     * count each available moves.
     * @return number of moves.
     */
    private int waysCount(){
        int waysCount = 0;
        for(int i = 0; i < horse.availableMoves.length; i++){
            if(horse.availableMoves[i])
                waysCount++;
        }
        return waysCount;
    }

    /**
     * draw board in console.
     */
    public void drawBoard(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] < 10 ? "   " + board[i][j] : "  " + board[i][j]);
            }
            System.out.println();
        }
        System.out.println("Total moves: " + horse.madeMoves);
        System.out.println("------------");
    }
}
