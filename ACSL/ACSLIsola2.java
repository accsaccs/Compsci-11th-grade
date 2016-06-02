import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
 * Annabel Strauss
 * The Dalton School
 * ACSL Isola
 * 3/2/15
 */
public class ACSLIsola2 {
  
  public static void check(int cmarker, int xmarker, int[] empties) {
   
    int[][] board = new int[7][7];
    //double[][] m = new double[4][4];

    for(int i=0; i<7; i++) {
      for(int j=0; j<7; j++) {
          board[i][j] = (i+j)+1+(6*i);
      }
    }
      //prints board
//    for(int r=0; r<board.length; r++) {
//      for(int c=0; c<board[r].length; c++)
//          System.out.print(board[r][c] + " ");
//      System.out.println();
//    }
    
    int cmarkercol = 0;
    int xmarkercol = 0; 
    int cmarkerrow = 0;
    int xmarkerrow = 0;
    int emptiescol = 0;
    int emptiesrow = 0;
    
    
    if(1 <= cmarker && cmarker <= 7) cmarkerrow = 0;
    else if(8 <= cmarker && cmarker <= 14) cmarkerrow = 1;
    else if(15 <= cmarker && cmarker <= 21) cmarkerrow = 2;
    else if(22 <= cmarker && cmarker <= 28) cmarkerrow = 3;
    else if(29 <= cmarker && cmarker <= 35) cmarkerrow = 4;
    else if(36 <= cmarker && cmarker <= 42) cmarkerrow = 5;
    else if(43 <= cmarker && cmarker <= 49) cmarkerrow = 6;
    
    if(1 <= xmarker && xmarker <= 7) xmarkerrow = 0;
    else if(8 <= xmarker && xmarker <= 14) xmarkerrow = 1;
    else if(15 <= xmarker && xmarker <= 21) xmarkerrow = 2;
    else if(22 <= xmarker && xmarker <= 28) xmarkerrow = 3;
    else if(29 <= xmarker && xmarker <= 35) xmarkerrow = 4;
    else if(36 <= xmarker && xmarker <= 42) xmarkerrow = 5;
    else if(43 <= xmarker && xmarker <= 49) xmarkerrow = 6;
    
    //System.out.println("cmarkerROW: " + cmarkerrow);
    //System.out.println("xmarkerROW: " + xmarkerrow);
    
    if(cmarker%7 == 0){
      cmarkercol = 6; 
    }
    else if(cmarker%7 != 0){
      cmarkercol = (cmarker%7) - 1;
    }
    if(xmarker%7 == 0){
      xmarkercol = 6; 
      //xmarkercol = (xmarker-(6-xmarkerrow)-1);
    }
    else if(xmarker%7 != 0){
      xmarkercol = (xmarker%7) - 1;
    }
//    System.out.println("cmarker: " + cmarker);
//    System.out.println("cmarkerCOL: " + cmarkercol);
//    System.out.println("xmarker: " + xmarker);
//    System.out.println("xmarkerCOL: " + xmarkercol);
    
    board[cmarkerrow][cmarkercol] = cmarker;
    board[xmarkerrow][xmarkercol] = xmarker;
    
    for (int i = 0; i < empties.length; i++) {
     
      if(1 <= empties[i] && empties[i] <= 7) emptiesrow = 0;
      else if(8 <= empties[i] && empties[i] <= 14) emptiesrow = 1;
      else if(15 <= empties[i] && empties[i] <= 21) emptiesrow = 2;
      else if(22 <= empties[i] && empties[i] <= 28) emptiesrow = 3;
      else if(29 <= empties[i] && empties[i] <= 35) emptiesrow = 4;
      else if(36 <= empties[i] && empties[i] <= 42) emptiesrow = 5;
      else if(43 <= empties[i] && empties[i] <= 49) emptiesrow = 6;
      
      if(empties[i]%7 == 0){
        emptiescol = 6;
      }
      else if(empties[i]%7 != 0){
        emptiescol = (empties[i]%7) - 1;
      }
      
      board[emptiesrow][emptiescol] = 0; 
    }
      
      //PRINTS BOARD
//    for(int r=0; r<board.length; r++) {
//      for(int c=0; c<board[r].length; c++)
//          System.out.print(board[r][c] + " ");
//      System.out.println();
//    }
    
    //if one piece minus other piece is divisible by 7, then it's vertical 
    //if one piece minus other piece is less than or equal to 6, then it's horizontal  
    //if one piece minus other piece is divisible by 2, then it's diagonal 
    //if one minus other is divis by 8, diag going towards upper right
    //if one minus other is divis by 6, diag going towards upper left 
    
    int goodspot = 0;
    int goodspotrow = 0;
    int goodspotcol = 0;
    ArrayList<Integer> good = new ArrayList<Integer>(); //list.add(3);
    if(cmarker == 1){
      if(board[cmarkerrow][cmarkercol+1] != 0) good.add(board[cmarkerrow][cmarkercol+1]);
      if(board[cmarkerrow+1][cmarkercol+1] != 0) good.add(board[cmarkerrow+1][cmarkercol+1]);
      if(board[cmarkerrow+1][cmarkercol] != 0) good.add(board[cmarkerrow+1][cmarkercol]);
    }
    else if(cmarker == 7){
      if(board[cmarkerrow][cmarkercol-1] != 0) good.add(board[cmarkerrow][cmarkercol-1]);
      if(board[cmarkerrow+1][cmarkercol-1] != 0) good.add(board[cmarkerrow+1][cmarkercol-1]);
      if(board[cmarkerrow+1][cmarkercol] != 0) good.add(board[cmarkerrow+1][cmarkercol]);
    }
    else if(cmarker == 43){
      if(board[cmarkerrow][cmarkercol+1] != 0) good.add(board[cmarkerrow][cmarkercol+1]);
      if(board[cmarkerrow-1][cmarkercol+1] != 0) good.add(board[cmarkerrow-1][cmarkercol+1]);
      if(board[cmarkerrow-1][cmarkercol] != 0) good.add(board[cmarkerrow-1][cmarkercol]);
    }
    else if(cmarker == 49){
      if(board[cmarkerrow][cmarkercol-1] != 0) good.add(board[cmarkerrow][cmarkercol-1]);
      if(board[cmarkerrow-1][cmarkercol-1] != 0) good.add(board[cmarkerrow-1][cmarkercol-1]);
      if(board[cmarkerrow-1][cmarkercol] != 0) good.add(board[cmarkerrow-1][cmarkercol]);
    }
    else if(cmarker > 1 && cmarker < 7){
      if(board[cmarkerrow+1][cmarkercol-1] != 0) good.add(board[cmarkerrow+1][cmarkercol-1]);
      if(board[cmarkerrow+1][cmarkercol] != 0) good.add(board[cmarkerrow+1][cmarkercol]);
      if(board[cmarkerrow+1][cmarkercol+1] != 0) good.add(board[cmarkerrow+1][cmarkercol+1]);
    }
    else if(cmarker > 43 && cmarker < 49){
      if(board[cmarkerrow-1][cmarkercol-1] != 0) good.add(board[cmarkerrow-1][cmarkercol-1]);
      if(board[cmarkerrow-1][cmarkercol+1] != 0) good.add(board[cmarkerrow-1][cmarkercol+1]);
      if(board[cmarkerrow-1][cmarkercol] != 0) good.add(board[cmarkerrow-1][cmarkercol]);
    }
    else if(cmarker%7 == 1){
      if(board[cmarkerrow-1][cmarkercol+1] != 0) good.add(board[cmarkerrow-1][cmarkercol+1]);
      if(board[cmarkerrow][cmarkercol+1] != 0) good.add(board[cmarkerrow][cmarkercol+1]);
      if(board[cmarkerrow+1][cmarkercol+1] != 0) good.add(board[cmarkerrow+1][cmarkercol+1]);
    }
    else if(cmarker%7 == 0){
      if(board[cmarkerrow-1][cmarkercol-1] != 0) good.add(board[cmarkerrow-1][cmarkercol-1]);
      if(board[cmarkerrow][cmarkercol-1] != 0) good.add(board[cmarkerrow][cmarkercol-1]);
      if(board[cmarkerrow+1][cmarkercol-1] != 0) good.add(board[cmarkerrow+1][cmarkercol-1]);
      if(board[cmarkerrow-1][cmarkercol] != 0) good.add(board[cmarkerrow-1][cmarkercol]);
      if(board[cmarkerrow+1][cmarkercol] != 0) good.add(board[cmarkerrow+1][cmarkercol]);
    }
    else{
      //System.out.println("hi");
      if(board[cmarkerrow-1][cmarkercol-1] != 0) good.add(board[cmarkerrow-1][cmarkercol-1]);
      if(board[cmarkerrow-1][cmarkercol] != 0) good.add(board[cmarkerrow-1][cmarkercol]);
      if(board[cmarkerrow-1][cmarkercol+1] != 0) good.add(board[cmarkerrow-1][cmarkercol+1]);
      if(board[cmarkerrow][cmarkercol+1] != 0) good.add(board[cmarkerrow][cmarkercol+1]);
      if(board[cmarkerrow+1][cmarkercol+1] != 0) good.add(board[cmarkerrow+1][cmarkercol+1]);
      if(board[cmarkerrow+1][cmarkercol] != 0) good.add(board[cmarkerrow+1][cmarkercol]);
      if(board[cmarkerrow+1][cmarkercol-1] != 0) good.add(board[cmarkerrow+1][cmarkercol-1]);
      if(board[cmarkerrow][cmarkercol-1] != 0) good.add(board[cmarkerrow][cmarkercol-1]);
    }
    //System.out.println("GOODSPOT list= " + good.toString());
    goodspot = Collections.min(good);
    //System.out.println("GOODSPOT= " + goodspot);
    
    
//-----------------------------------------------------------------------------
    ArrayList<Integer> caught = new ArrayList<Integer>(); //list.add(3);
    
    //WHEN THEY'RE VERTICALLY ALIGNED
    if((goodspot-xmarker)%7 == 0){
      //System.out.println("***176 goodsopt = " + goodspot);
        int length = ((Math.abs(goodspot-xmarker))/7) - 1;
        for (int i = 1; i < length+1; i++) {
          caught.add((Math.max(goodspot, xmarker))-(7*i));
        }
        
        caught.add(goodspot);
       
        //prints the answer
        String s = caught.toString();
        s = s.substring(1, s.length()-1);
        System.out.println(s);
        
    }//if
    
  
    
  //MOVING DIAGONALLY TOWARDS RIGHT (on my board)
    else if((goodspot-xmarker)%8 == 0){
      //System.out.println("***208 goodsopt = " + goodspot);
      int length = ((Math.abs(goodspot-xmarker))/8) - 1;
      for (int i = 1; i < length+1; i++) {
        caught.add((Math.max(goodspot, xmarker))-(8*i));
      }
      caught.add(goodspot);
      //System.out.println(caught.toString());
      
      if(cmarker > xmarker){
        int biggest = 0;
        biggest = Collections.max(caught);
        //System.out.println("biggest = " + biggest);
        
        if(biggest > 7 && biggest < 43 && biggest%7 != 1 && biggest%7 != 0){ //means: not on border
          if(biggest + 8 != 0 && biggest + 8 != cmarker){
            caught.add(biggest + 8);
          }
        }
      }
      else if(cmarker < xmarker){
        int smallest = 0;
        smallest = Collections.min(caught);
        //System.out.println("biggest = " + biggest);
        
        if(smallest > 7 && smallest < 43 && smallest%7 != 1 && smallest%7 != 0){ //means: not on border
          if(smallest + 8 != 0 && smallest + 8 != cmarker){
            caught.add(smallest + 8);
          }
        }
      }
      //16, 39, 9, 8, 10, 17, 22, 24, 0
      
      //prints the answer
      String s = caught.toString();
      s = s.substring(1, s.length()-1);
      System.out.println(s);
    }//else if 
    
  //MOVING DIAGONALLY TOWARDS LEFT (on my board)
    else if((goodspot-xmarker)%6 == 0){
      //System.out.println("***224 goodsopt = " + goodspot);
      int length = ((Math.abs(goodspot-xmarker))/6) - 1;
      for (int i = 1; i < length+1; i++) {
        caught.add((Math.max(goodspot, xmarker))-(6*i));
      }
      caught.add(goodspot);
      //Collections.sort(caught);
      //System.out.println("CAUGHT NOW: " + caught.toString());
     
      
      if(cmarker > xmarker){
        int biggest = 0;
        biggest = Collections.max(caught);
        //System.out.println("biggest = " + biggest);
        
        if(biggest > 7 && biggest < 43 && biggest%7 != 1 && biggest%7 != 0){ //means: not on border
          if(biggest + 6 != 0 && biggest + 6 != cmarker){
            caught.add(biggest + 6);
          }
        }
      }
      else if(cmarker < xmarker){
        int smallest = 0;
        smallest = Collections.min(caught);
        //System.out.println("biggest = " + biggest);
        
        if(smallest > 7 && smallest < 43 && smallest%7 != 1 && smallest%7 != 0){ //means: not on border
          if(smallest + 6 != 0 && smallest + 6 != cmarker){
            caught.add(smallest + 6);
          }
        }
      }
      
      
      //prints the answer
      String s = caught.toString();
      s = s.substring(1, s.length()-1);
      System.out.println(s);
    }//else if
    
    //WHEN THEY'RE HORIZONTALLY ALIGNED ***fix bc only works on if same row
    else if((Math.abs(goodspot-xmarker)) < 7){
      //System.out.println("***192 goodsopt = " + goodspot);
        int length = ((Math.abs(goodspot-xmarker))/1) - 1;
        for (int i = 1; i < length+1; i++) {
          caught.add((Math.max(goodspot, xmarker))-(1*i));
        }
        caught.add(goodspot);
        
        //prints the answer
        String s = caught.toString();
        s = s.substring(1, s.length()-1);
        System.out.println(s);
        
    }//if
    
        
    //FOR ONES WHERE THERE'S NO SOLUTION
    else{
      System.out.println("NONE");
    }
    
    //return 2;
    //return;
  }
  

  public static void main(String[] args) {
  
    Scanner scan = new Scanner(System.in);
    int cmarker = 0;
    int xmarker = 0;
    
    for (int i = 0; i < 12; i++) {
      
      String[] input = scan.nextLine().split(", ");
      cmarker = Integer.parseInt(input[0]);
//          System.out.println("cmarker: " + cmarker); //debug
      xmarker = Integer.parseInt(input[1]);
//          System.out.println("xmarker: " + xmarker); //debug
      int[] empties = new int[input.length-3];
      
      for (int j = 0; j < input.length-3; j++) {
       empties[j] = Integer.parseInt(input[j+2]);
      }
      
      //System.out.println(check(cmarker, xmarker, empties));
      new ACSLIsola2().check(cmarker, xmarker, empties);
     
    }//big for (so Will can input many lines)
    

  }//main
}//class
