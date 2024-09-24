
class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        
        int rowSize = board.length;
        int colSize = board[0].length;
        
        int[][] dp = new int[rowSize][colSize];
        
        for(int row = 0; row < rowSize; row++) {
            for(int col = 0; col < colSize; col++) {
                if(board[row][col] > 0) {
                    if(!inRange(row, col, rowSize, colSize)) {
                        answer = Math.max(1,answer);
                        continue;
                    }
                    answer = Math.max(calcSquareWidth(row, col, board), answer);
                }
            }
        }
        

        return (int)Math.pow(answer,2);
    }
    
    public int calcSquareWidth(int row, int col, int[][] dp) {
        int width = Math.min(Math.min(dp[row-1][col], dp[row-1][col-1]), dp[row][col-1]) + 1;
        dp[row][col] = width;
        return width;
    }
    
    public boolean inRange(int row, int col, int maxRow, int maxCol) {
        if(row == 0 || col == 0) return false;
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
    }
}