/**
 * Horse class contains state of available moves and methods to move the horse.
 */
public class Horse {
    /**
     * number of horse moves.
     */
    int madeMoves = 0;

    /**
     * X coordinate.
     */
    int x;

    /**
     * Y coordinate.
     */
    int y;

    /**
     * Board.
     */
    int[][] board;

    /**The first move availableMoves[0] is upHigh (x+1; y-2)
    *Total horse moves = 8.
    */
    boolean[] availableMoves = new boolean[8];

    public Horse(int x, int y, int[][] board){
        this.x = x;
        this.y = y;
        this.board = board;
        availableMovesUpdate();
    }

    public boolean[] getAvailableMoves() {
        return availableMoves;
    }

    /**
     * Method goTo, define which method use to move horse
     * @param direction number of direction
     */
    public void goTo(int direction){
        switch (direction){
            case(0): goUpHigh(); break;
            case(1): goUpLow(); break;
            case(2): goRightHigh(); break;
            case(3): goRightLow(); break;
            case(4): goDownHigh(); break;
            case(5): goDownLow(); break;
            case(6): goLeftHigh(); break;
            case(7): goLeftLow(); break;
            default:
                System.out.println("can't go there");
        }

    }

    /*
    Methods that define specific horse moves
     */
    private void goUpHigh(){
        move(x + 1, y - 2);
    }

    private void goUpLow(){
        move(x + 2, y - 1);
    }

    private void goRightHigh(){
        move(x + 2, y + 1);
    }

    private void goRightLow(){
        move(x + 1, y + 2);
    }

    private void goDownHigh(){
        move(x - 2, y + 1);
    }

    private void goDownLow(){
        move(x - 1, y + 2);
    }

    private void goLeftHigh(){
        move(x - 2, y - 1);
    }

    private void goLeftLow(){
        move(x - 1, y - 2);
    }

    /**
     * Moves horse to x, y coordinates.
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public void move(int x, int y){
        this.x = x;
        this.y = y;
        availableMovesUpdate();
    }


    /**
     * Checks if available move exist.
     * @return true if exist.
     */
    public boolean hasMove(){
        for(int i = 0; i < availableMoves.length; i++){
            if (availableMoves[i])
                return true;
        }
        return false;
    }

    /**
     * Update list of available moves.
     */
    public void availableMovesUpdate(){
        for(int i = 0; i < availableMoves.length; i++){
            switch (i){
                case(0): availableMoves[i] = tryUpHigh(); break;
                case(1): availableMoves[i] = tryUpLow(); break;
                case(2): availableMoves[i] = tryRightHigh(); break;
                case(3): availableMoves[i] = tryRightLow(); break;
                case(4): availableMoves[i] = tryDownHigh(); break;
                case(5): availableMoves[i] = tryDownLow(); break;
                case(6): availableMoves[i] = tryLeftHigh(); break;
                case(7): availableMoves[i] = tryLeftLow(); break;
                default:
                    throw new IllegalArgumentException("Illegal move");
            }
        }
    }

    //Is the up moves available
    private boolean tryUpHigh(){
        if(y - 2 >= 0 && x + 1 < board.length && board[y-2][x+1] == 0){
            return true;
        }
        return false;
    }

    private boolean tryUpLow(){
        if(y - 1 >= 0 && x + 2 < board.length && board[y-1][x+2] == 0) {
            return true;
        }
        return false;
    }

    //Is the right moves available
    private boolean tryRightHigh(){
        if(x + 2 < board.length && y + 1 < board.length && board[y+1][x+2] == 0){
            return true;
        }
        return false;
    }

    private boolean tryRightLow(){
        if(x + 1 < board.length && y + 2 < board.length && board[y+2][x+1] == 0){
            return true;
        }
        return false;
    }

    //Is the down moves available
    private boolean tryDownHigh(){
        if(x - 2 >= 0 && y + 1 < board.length && board[y+1][x-2] == 0){
            return true;
        }
        return false;
    }

    private boolean tryDownLow(){
        if(x - 1 > 0 && y + 2 < board.length && board[y+2][x-1] == 0){
            return true;
        }
        return false;
    }

    //Is the left moves available
    private boolean tryLeftHigh(){
        if(x - 2 >= 0 && y - 1 >= 0 && board[y-1][x-2] == 0){
            return true;
        }
        return false;
    }

    private boolean tryLeftLow(){
        if(x - 1 >= 0 && y - 2 >= 0 && board[y-2][x-1] == 0){
            return true;
        }
        return false;
    }
}
