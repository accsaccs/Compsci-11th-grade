import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;



/*
 * Annabel Strauss
 * The Dalton School
 * ACSL Quine-McClusky Algorithm
 * 4/11/15
 */
public class QuineMcCluskyAlgorithm {

  public static ArrayList<String> toBinary(int[] termnumbers){

    String binary = "";
    //List binaries = new ArrayList();
    ArrayList<String> binaries = new ArrayList<String>(); 

    for (int i = 0; i < termnumbers.length; i++) {
      if(termnumbers[i] == 0) binary = "0000";
      if(termnumbers[i] == 1) binary = "0001";
      if(termnumbers[i] == 2) binary = "0010";
      if(termnumbers[i] == 3) binary = "0011";
      if(termnumbers[i] == 4) binary = "0100";
      if(termnumbers[i] == 5) binary = "0101";
      if(termnumbers[i] == 6) binary = "0110";
      if(termnumbers[i] == 7) binary = "0111";
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
//    for(int i = 0; i < binaries.size(); i++) {
//      System.out.println(binaries.get(i));
//    }

    return binaries;
  }//method


  public static boolean canCombine(String firstBinary, String secondBinary){
    int differences = 0;
    for (int i = 0; i < 4; i++) {
      //System.out.println(firstBinary);
      //System.out.println(secondBinary);
      if(firstBinary.charAt(i) != secondBinary.charAt(i))
      {
        differences++; 
      }
    }
    if(differences > 1){
      return false;
    }
    return true;
  }//method

  public static String combine(String firstString, String secondString){
    String simplified = "";
    for (int i = 0; i < 4; i++) {
      if(firstString.charAt(i) == secondString.charAt(i)){
        simplified = simplified + firstString.charAt(i);
      }
      else{
        simplified = simplified + 'x';
      }
    }//for
    return simplified;
  }

  public static ArrayList<String> simplify(ArrayList<String> binaries){

    ArrayList<String> index0 = new ArrayList<String>(); 
    ArrayList<String> index1 = new ArrayList<String>(); 
    ArrayList<String> index2 = new ArrayList<String>(); 
    ArrayList<String> index3 = new ArrayList<String>(); 
    ArrayList<String> index4 = new ArrayList<String>();
    int counter = 0;

    for (int i = 0; i < binaries.size(); i++) {
      for (int j = 0; j < binaries.get(i).length(); j++) {
        if(binaries.get(i).charAt(j) == '1') counter++;
      }//for j

      if(counter == 0) index0.add(binaries.get(i));
      else if(counter == 1) index1.add(binaries.get(i));
      else if(counter == 2) index2.add(binaries.get(i));
      else if(counter == 3) index3.add(binaries.get(i));
      else if(counter == 4) index4.add(binaries.get(i));
      counter = 0; //reset the counter
    }//for i

    //prints which binaries are in each index list 
//    for(int i = 0; i < index1.size(); i++) System.out.println("ind 1: " + index1.get(i));
//    for(int i = 0; i < index2.size(); i++) System.out.println("ind 2: " + index2.get(i));
//    for(int i = 0; i < index3.size(); i++) System.out.println("ind 3: " + index3.get(i));  
//    for(int i = 0; i < index4.size(); i++) System.out.println("ind 4: " + index4.get(i));

    ArrayList<String> simpPairs = new ArrayList<String>(); 

    for (int i = 0; i < 4; i++) { 
      ArrayList<String> currentIndex = new ArrayList<String>(); 
      ArrayList<String> nextIndex = new ArrayList<String>(); 
      if(i == 0){
        currentIndex = index0;
        nextIndex = index1;
      }
      else if(i == 1){
        currentIndex = index1;
        nextIndex = index2;
      }
      else if(i == 2){
        currentIndex = index2;
        nextIndex = index3;
      }
      else if(i == 3){
        currentIndex = index3;
        nextIndex = index4;
      }

      for (int j = 0; j < currentIndex.size(); j++) { 
        for (int k = 0; k < nextIndex.size(); k++) { 
          if(canCombine(currentIndex.get(j), nextIndex.get(k))){
            simpPairs.add(combine(currentIndex.get(j), nextIndex.get(k)));
          }//if
//          else if(canCombine(currentIndex.get(j), nextIndex.get(k)) == false){ //something is wrong here
//            rejects.add(nextIndex.get(k));
//          }//else if
        }//for k     
      }//for j     
    }//for i 

    //for(int i = 0; i < simpPairs.size(); i++) System.out.println("simp pairs: " + simpPairs.get(i));
    
    Set<String> hs = new HashSet<>();
    ArrayList<String> al = new ArrayList<>();
    al.addAll(simpPairs);
    hs.addAll(al);
    al.clear();
    al.addAll(hs);
    
    //for(int i = 0; i < al.size(); i++) System.out.println("simp pairs2: " + al.get(i));
    
    return al;
  }//method

  public static ArrayList<String> extendedSimplify(ArrayList<String> simpPairs){
    if(simplify(simpPairs).size() == 0) return simpPairs;
    else return simplify(simpPairs);
  }

  public static String toLetters(ArrayList<String> simpPairs, int run){
    String newVersion = ""; //this is for when just ABC
    String sameVersion = ""; //this is for when ABCD
    String word = "";
    ArrayList<String> sequence = new ArrayList<String>(); 
    //System.out.println("run = " + run);
    for (int i = 0; i < simpPairs.size(); i++) {
      if(run <= 2){
        //System.out.println("hi1");
        newVersion = simpPairs.get(i).substring(1);
        word = "";
        if(newVersion.charAt(0) == '1') word += "A";
        else if(newVersion.charAt(0) == '0') word += "a";
        if(newVersion.charAt(1) == '1') word += "B";
        else if(newVersion.charAt(1) == '0') word += "b";
        if(newVersion.charAt(2) == '1') word += "C";
        else if(newVersion.charAt(2) == '0') word += "c";
      }
      else if(run > 2){
        //System.out.println("hi2");
        sameVersion = simpPairs.get(i);
        word = "";
        if(sameVersion.charAt(0) == '1') word += "A";
        else if(sameVersion.charAt(0) == '0') word += "a";
        if(sameVersion.charAt(1) == '1') word += "B";
        else if(sameVersion.charAt(1) == '0') word += "b";
        if(sameVersion.charAt(2) == '1') word += "C";
        else if(sameVersion.charAt(2) == '0') word += "c";
        if(sameVersion.charAt(3) == '1') word += "D";
        else if(sameVersion.charAt(3) == '0') word += "d";
      }
      sequence.add(word);
    }//for i 

    for (int i = 0; i < sequence.size(); i++) {
      System.out.print(sequence.get(i));
      if(i < sequence.size()-1) System.out.print(" + ");
    }
    System.out.println();
    return "";

  }//method

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    for (int i = 0; i < 12; i++) {

      String[] input = scan.nextLine().split(", ");
      int[] termnumbers = new int[input.length-1];

      for (int j = 0; j < input.length-1; j++) {
        termnumbers[j] = Integer.parseInt(input[j]);
      }    

      ArrayList<String> result = new ArrayList<String>();
      //System.out.println("i = " + i);
      toLetters(extendedSimplify(simplify(toBinary(termnumbers))), i);
      //simplify(toBinary(termnumbers));




    }//big for (so Will can input many lines)


  }//main
}//class
