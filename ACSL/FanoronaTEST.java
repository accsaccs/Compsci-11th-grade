
public class FanoronaTEST {
 
  
  
  
  public static void main(String[] args) {
    
    int white1 = 12;
    int white2 = 17;
    int white3 = 22;
    int black1 = 4;
    int black2 = 14;
    int black3 = 10;
    
    int[][] board = new int[7][7];
    //double[][] m = new double[4][4];

    for(int i=0; i<7; i++) {
        for(int j=0; j<7; j++) {
            board[i][j] = (i+j)+1+(6*i);
        }
    }
    
    for(int r=0; r<board.length; r++) {
      for(int c=0; c<board[r].length; c++)
          System.out.print(board[r][c] + " ");
      System.out.println();
    }
    
    
    
    
    
  }//main
}//class


