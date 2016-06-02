import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Annabel Strauss
 * The Dalton School
 * ACSL Quine-McClusky Algorithm
 * 4/11/15
 */
public class QuineMcCluskyAlgorithm2 {

  public static void toBinary(int[] termnumbers){
    
    String binary = "";
    //List binaries = new ArrayList();
    ArrayList<String> binaries = new ArrayList<String>(); 
    
    for (int i = 0; i < termnumbers.length; i++) {
      if(termnumbers[i] == 0) binary = "000";
      if(termnumbers[i] == 1) binary = "001";
      if(termnumbers[i] == 2) binary = "010";
      if(termnumbers[i] == 3) binary = "011";
      if(termnumbers[i] == 4) binary = "100";
      if(termnumbers[i] == 5) binary = "101";
      if(termnumbers[i] == 6) binary = "110";
      if(termnumbers[i] == 7) binary = "111";
      if(termnumbers[i] == 8) binary = "1000";
      if(termnumbers[i] == 9) binary = "1001";
      if(termnumbers[i] == 10) binary = "1010";
      if(termnumbers[i] == 11) binary = "1011";
      if(termnumbers[i] == 12) binary = "1100";
      if(termnumbers[i] == 13) binary = "1101";
      if(termnumbers[i] == 14) binary = "1110";
      if(termnumbers[i] == 15) binary = "1111";
        
      binaries.add(binary); //fills the list with the binary numbers 
    }//for
    
    //prints the binaries list 
    for(int i = 0; i < binaries.size(); i++) {
      System.out.println(binaries.get(i));
    }
    
  }//method
  
  public static void simplify(ArrayList<String> binaries){
    
    ArrayList<String> index1 = new ArrayList<String>(); 
    ArrayList<String> index2 = new ArrayList<String>(); 
    ArrayList<String> index3 = new ArrayList<String>(); 
    ArrayList<String> index4 = new ArrayList<String>();
    int counter = 0;
    
    for (int i = 0; i < binaries.size(); i++) {
      for (int j = 0; j < binaries.get(i).length(); j++) {
        if(binaries.get(i).charAt(j) == '1') counter++;
      }//for j
   
      if(counter == 1) index1.add(binaries.get(i));
      else if(counter == 2) index2.add(binaries.get(i));
      else if(counter == 3) index3.add(binaries.get(i));
      else if(counter == 4) index4.add(binaries.get(i));
      counter = 0; //reset the counter
    }//for i
    
    //prints which binaries are in each index list 
    for(int i = 0; i < index1.size(); i++) System.out.println("ind 1: " + index1.get(i));
    for(int i = 0; i < index2.size(); i++) System.out.println("ind 2: " + index2.get(i));
    for(int i = 0; i < index3.size(); i++) System.out.println("ind 3: " + index3.get(i));  
    for(int i = 0; i < index4.size(); i++) System.out.println("ind 4: " + index4.get(i));
    
    int differences = 0;
    String simplified = "";
    ArrayList<String> simpPairs = new ArrayList<String>(); 
    
    for (int i = 0; i < index1.size()*index2.size(); i++) {
      System.out.println("NEWSET SYSO");
      for (int j = 0; j < index2.get(0).length(); j++) {
        System.out.println(index1.get(i).charAt(j));
        System.out.println(index2.get(i).charAt(j));
        if(index1.get(i).charAt(j) != index2.get(i).charAt(j)){ differences++; System.out.println("differences= " + differences);}
        else System.out.println("HI");
      }//for j  
      if(differences <= 1){
        System.out.println("line 85");
        for (int j = 0; j < index2.get(0).length(); j++) {
          System.out.println("ine 87");
          if(index1.get(i).charAt(j) == '1' && index2.get(i).charAt(j) == '1') {simplified = simplified + "1"; System.out.println("i'm in");}
          else if(index1.get(i).charAt(j) == '0' && index2.get(i).charAt(j) == '0') {simplified = simplified + "0"; System.out.println("i'm in");}
          else if(index1.get(i).charAt(j) == '0' && index2.get(i).charAt(j) == '1') {simplified = simplified + "x"; System.out.println("i'm in");}      
          else if(index1.get(i).charAt(j) == '1' && index2.get(i).charAt(j) == '0') {simplified = simplified + "x"; System.out.println("i'm in");}
        }//for j
        simpPairs.add(simplified);
        System.out.println("simplified: " + simplified);
        simplified = "";
      }//if  
    }//for i 
    
    for(int i = 0; i < simpPairs.size(); i++) System.out.println("simp pairs: " + simpPairs.get(i));
  }//method
  
  public static void toLetters(String binary){
    
    List sequence = new ArrayList();
    
    if(binary.length() == 3){
      System.out.println("hi1");
      if(binary.charAt(0) == '1') sequence.add('A');
      else if(binary.charAt(0) == '0') sequence.add("a");
      if(binary.charAt(1) == '1') sequence.add('B');
      else if(binary.charAt(1) == '0') sequence.add("b");
      if(binary.charAt(2) == '1') sequence.add('C');
      else if(binary.charAt(2) == '0') sequence.add("c");
    }
    else if(binary.length() == 4){
      System.out.println("hi2");
      if(binary.charAt(0) == '1') sequence.add('A');
      else if(binary.charAt(0) == '0') sequence.add("a");
      if(binary.charAt(1) == '1') sequence.add('B');
      else if(binary.charAt(1) == '0') sequence.add("b");
      if(binary.charAt(2) == '1') sequence.add('C');
      else if(binary.charAt(2) == '0') sequence.add("c");
      if(binary.charAt(3) == '1') sequence.add('D');
      else if(binary.charAt(3) == '0') sequence.add("d");
    }
    
    System.out.println("hi");
    for(int i = 0; i < sequence.size(); i++) {
      System.out.print(sequence.get(i));
    }
    
  }//method
  
  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    for (int i = 0; i < 12; i++) {

      String[] input = scan.nextLine().split(", ");
      int[] termnumbers = new int[input.length-1];

      for (int j = 0; j < input.length-1; j++) {
        termnumbers[j] = Integer.parseInt(input[j]);
      }    

      toBinary(termnumbers);
      ArrayList<String> binaries = new ArrayList<String>();
      binaries.add("010");
      binaries.add("011");
      binaries.add("101");
      binaries.add("111");
      simplify(binaries);
      toLetters("1x0");
      
      
//      if(i == 0) System.out.println("I have bits and pieces");
//      if(i == 1) System.out.println("But");
//      if(i == 2) System.out.println("I can't figure out");
//      if(i == 3) System.out.println("How to assemble them");
//      if(i == 4) System.out.println("Sorry!");

    }//big for (so Will can input many lines)


  }//main
}//class
