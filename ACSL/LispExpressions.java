/*
 * Annabel Strauss
 * The Dalton School
 * LISP expression
 * 2/14/15
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author student
 *
 */
public class LispExpressions {
	
  /**
   * 
   * @param tokens
   * @return
   */
	public static int count(String[] tokens) 
	{
		//System.out.println("hi");
//		for (int k = 0; k < tokens.length; k++) {
//			System.out.println(tokens[k]);
//		}
		int parenCounter = 0;
		int counter = 0;
		for (int i = 0; i < tokens.length; i++) 
		{
			String piece = tokens[i];
			parenCounter = 0;
			for (int j = 0; j < piece.length(); j++)
			{
				char c = piece.charAt(j); 
				if(c == '(')
				{
					parenCounter++;
					//System.out.println("me");
					//System.out.println(ParenCounter);
				}
				if(c == ')')
				{
				  parenCounter++;
					//System.out.println(ParenCounter);
					break;
				}
				
			}//inner for		
			if(parenCounter == 2)
			{
				counter++;
				//System.out.println("counter=" + counter);
			}
		}//outer for
	
		 return counter;
	}//count method
	
	public static String remove(String[] tokens, int firstNum, int secondNum) 
	{
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(tokens)); 
		//System.out.println("here: " + list); //debug

		for (int i = 0; i < (secondNum-firstNum+1); i++) {
//			System.out.println("secondNum = " + secondNum); //debug
//			System.out.println(list.get(secondNum)); //debug

			list.remove(firstNum);
		}
		//System.out.println("new: " + list); //debug
		
		String[] toprint = new String[list.size()]; //turns it back into an array
		toprint = list.toArray(toprint);
		//for(String s : toprint) System.out.print(s+" "); //prints it
	
		String blah = "";
		for (int i = 0; i < toprint.length; i++) 
		{
			blah = toprint[i];
			
			if(i == toprint.length-1 && blah != toprint[toprint.length-1])
			{
				blah = blah.concat(")");
			}
			System.out.print(blah + " ");
		}//outer for 

		String ab = ""; //this does nothing
		return ab;
	}
	
	public static String sort(String[] tokens, int firstNum, int secondNum) 
	{
		int size = 0;
		size = (secondNum - firstNum) + 1;
		String[] mini = new String[size]; //makes new string array of size "size" (secondnum - firstnum)
		//System.out.println("mini length=" + mini.length);
		
		//fills mini array (just the sublists that you want to sort) 
		for (int i = 0; i < mini.length; i++) {
			mini[i] = tokens[(firstNum)+i];
			//System.out.println(tokens[(firstNum)+i]);
			//System.out.println("hi");
			//System.out.println(tokens[6]);
		}
		
		Arrays.sort(mini); //mini is now sorted
		//System.out.println("mini array in string form: " + Arrays.toString(mini)); //debug (prints the mini array)
		
		ArrayList<String> miniList = new ArrayList<String>(Arrays.asList(mini)); //makes mini into a list 	
		String[] miniNice = new String[miniList.size()]; //turns mini back into an array
		miniNice = miniList.toArray(miniNice);
		//for(String s : miniNice) System.out.print(s+" "); //prints mini prettily
		//System.out.println();
		
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(tokens)); //makes tokens into a list 
		
		list.set(firstNum, Arrays.toString(miniNice)); //sticks the sorted list (mini) INTO tokens array list 
		//System.out.println("list at 4" + list.get(4)); //debug (shows that the whole chunk of mini is now it's own element)
		//System.out.println("list = " + list); //debug (shows the array before stuff has been removed) 
		
		//removes the stuff after the sort THIS NEEDS HELP BECAUSE WILL ONLY WORK FOR SAMPLE CODE or maybe not... TEST!!!
		for (int i = 0; i < (secondNum-firstNum); i++) {
//			System.out.println("secondNum = " + secondNum); //debug
//			System.out.println(list.get(secondNum)); //debug

			//list.remove(secondNum-1); //MIGHT BE -- list.remove(firstNum+1);
			list.remove(firstNum+1);
		}
		//System.out.println("NOW list = " + list);
		
		
		String[] toprint = new String[list.size()]; //turns it back into an array
		toprint = list.toArray(toprint);
		//for(String s : toprint) System.out.print(s+" "); //prints it
		//System.out.println();
		
		String blah = "";
		for (int i = 0; i < toprint.length; i++) 
		{
			blah = toprint[i];
			for (int j = 0; j < blah.length(); j++) 
			{
				//System.out.println("hi"); //debug - GOT IN 
				if(blah.charAt(j) == '[')
				{	
					blah = blah.replace("[", "");
				}
				else if(blah.charAt(j) == ']')
				{
					blah = blah.replace("]", "");
				}
				
			}//inner for
			if(blah.contains("))"))
			{
				//System.out.println("hi");
				blah = blah.replaceAll("(.)\\1+", "$1");
			}
			if(blah.contains(","))
			{
				//System.out.println("hi");
				blah = blah.replace(",", "");
			}
			if(i == toprint.length-1)
			{
				//System.out.print("hi");
				blah = blah.concat(")");
			}
			
			//System.out.print("blah = " + blah);
			System.out.print(blah + " ");
		}//outer for 
		
		
		
		String ab = ""; //this does nothing
		return ab;
	}
	
	public static String reverse(String[] tokens, int firstNum, int secondNum) 
	{
		int size = 0;
		size = (secondNum - firstNum) + 1;
		String[] mini = new String[size]; //makes new string array of size "size" (secondnum - firstnum)
		//System.out.println("mini length=" + mini.length);
		
		//fills mini array (just the sublists that you want to reverse) 
		for (int i = 0; i < mini.length; i++) {
			mini[i] = tokens[(firstNum)+i];
			//System.out.println(tokens[(firstNum)+i]);
			//System.out.println("hi");
			//System.out.println(tokens[6]);
		}		
		
		//this is used much later in the program. had to define it here tho (bc couldn't be defined in the for loop) 
		//String[] allReversed = new String[(tokens.length)-(secondNum-firstNum)+1]; //wrong version 
		String[] allReversed = new String[(secondNum-firstNum)+1];
		
		ArrayList<String> minilist = new ArrayList<String>(Arrays.asList(mini)); //makes mini array into a list (so can manipulate)	
		Collections.reverse(minilist); //reverses the order of the sublists 
		//System.out.println("REVERSED list (minilist)= " + minilist);
		
		String element = "";
		for (int i = 0; i < mini.length; i++) 
		{
			element = minilist.get(i);
			//System.out.println("get it= " + element);
			
			String[] elementArray = element.split("\\s+"); //makes element into an array 
			for (int j = 0; j < elementArray.length; j++) { //prints the array
				//System.out.println("HI");
				//System.out.println("e array= " + elementArray[j]);
			}
			
			ArrayList<String> elementlist = new ArrayList<String>(Arrays.asList(elementArray)); //makes elementArray into a list (so can manipulate)
			elementlist.remove(0);
			Collections.reverse(elementlist); //reverses the order of the elements in the sublist 
			//System.out.println("REVERSED list (elementlist)= " + elementlist);
			String theelement = "";
			theelement = elementlist.toString();
			//System.out.println("theelement = " + theelement);
			
			//System.out.println("element rn = " + element);
			elementArray[1] = theelement;
			//System.out.println(elementArray[1]);
			
			for (int j = 0; j < elementArray.length; j++) { //prints the array
				//System.out.println("HI");
				//System.out.println("FINAL= " + elementArray[j]);
				//System.out.print(elementArray[j] + " ");
			}
			//System.out.println();
			
			ArrayList<String> NewElementlist = new ArrayList<String>(Arrays.asList(elementArray));
			//System.out.println("elementArray.length = " + elementArray.length);
			for (int j = 0; j < elementArray.length-2; j++) 
			{
					//System.out.println(NewElementlist.get(2));
					NewElementlist.remove(2);
			}
			//System.out.println("NewElementlist = " + NewElementlist);
			
			String stringNewElementList = NewElementlist.toString();
			for (int j = 0; j < stringNewElementList.length(); j++) 
			{
				//System.out.println("hi"); //debug - GOT IN 
				if(stringNewElementList.charAt(j) == '[')
				{	
					stringNewElementList = stringNewElementList.replace("[", "");
				}
				else if(stringNewElementList.charAt(j) == ']')
				{
					stringNewElementList = stringNewElementList.replace("]", "");
				}
				else if(stringNewElementList.charAt(j) == ',')
				{
					stringNewElementList = stringNewElementList.replace(",", "");
				}
				else if(stringNewElementList.charAt(j) == ')')
				{
					stringNewElementList = stringNewElementList.replace(")", "");
				}
				
				stringNewElementList = stringNewElementList.concat(")");
				
//				if(stringNewElementList.contains("))"))
//				{
//					//System.out.println("hi");
//					stringNewElementList = stringNewElementList.replaceAll("(.)\\1+", "$1");
//				}
//				
			}
			if(stringNewElementList.contains("))"))
			{
				//System.out.println("hi");
				stringNewElementList = stringNewElementList.replaceAll("(.)\\1+", "$1");
			}
			//System.out.println("hi! = " + stringNewElementList);
			//System.out.println(stringNewElementList + " ");
			
			allReversed[i] = stringNewElementList; //puts all the chunks into an array (this will now be put into the original eqn) 

		}//for
		
		for (int j = 0; j < allReversed.length; j++) {
			//System.out.println("all rev at " + j + " " + allReversed[j]);
		}
		
		ArrayList<String> allRevList = new ArrayList<String>(Arrays.asList(allReversed));
		
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(tokens)); //makes tokens into a list 
		
		list.set(firstNum, Arrays.toString(allReversed)); //sticks the sorted list (mini) INTO tokens array list 
		//System.out.println("list at 4" + list.get(4)); //debug (shows that the whole chunk of mini is now it's own element)
		//System.out.println("list = " + list); //debug (shows the array before stuff has been removed) 
		
		//removes the stuff after the sort THIS NEEDS HELP BECAUSE WILL ONLY WORK FOR SAMPLE CODE or maybe not... TEST!!!
		for (int i = 0; i < (secondNum-firstNum); i++) {
//			System.out.println("secondNum = " + secondNum); //debug
//			System.out.println(list.get(secondNum)); //debug

			list.remove(firstNum+1);
		}
		//System.out.println("NOW list = " + list);
		
		String[] toprint = new String[list.size()]; //turns it back into an array
		toprint = list.toArray(toprint);
		//for(String s : toprint) System.out.print(s+" "); //prints it
		//System.out.println();
		
		String blah = "";
		for (int i = 0; i < toprint.length; i++) 
		{
			blah = toprint[i];
			for (int j = 0; j < blah.length(); j++) 
			{
				//System.out.println("hi"); //debug - GOT IN 
				if(blah.charAt(j) == '[')
				{	
					blah = blah.replace("[", "");
				}
				else if(blah.charAt(j) == ']')
				{
					blah = blah.replace("]", "");
				}
				
			}//inner for
			if(blah.contains("))"))
			{
				//System.out.println("hi");
				blah = blah.replaceAll("(.)\\1+", "$1");
			}
			if(blah.contains(","))
			{
				//System.out.println("hi");
				blah = blah.replace(",", "");
			}
			if(i == toprint.length-1)
			{
				//System.out.print("hi");
				blah = blah.concat(")");
			}
			
			//System.out.print("blah = " + blah);
			System.out.print(blah + " ");
		}//outer for 
		

       	String ab = ""; //this does nothing
		return ab;
	}
	
	public static String maximum(String[] tokens)
	{
		String blah = "";
		String blah2 = "";
		int counter = 0;
		String done = "";
		
		for (int j = 0; j < tokens.length; j++) { //prints the array that is the eqn
			//System.out.println("token= " + tokens[j]);
		}
		
		for (int i = 0; i < tokens.length; i++) 
		{
			blah = tokens[i];
			//System.out.println(blah.length()); //debug (says length of each element in array)
//			for (int j = 0; j < blah.length(); j++) 
//			{
//				String firstNumber = strScore.replaceFirst(".*?(\\d+).*", "$1");
//			}
			blah2 = blah.replaceAll("\\D+","");
			//System.out.println("blah2= " + blah2);
			
			if(blah2.length() > counter)
			{
				counter = blah2.length();
				done = tokens[i];
			}
			
		}
		System.out.println(done);
		
		
		String ab = ""; //this does nothing
		return ab;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		for (int j = 0; j < 2; j++) 
		{
			String input = scan.nextLine(); //takes in the original LISP eqn
			//System.out.println(input);
			
			String equation = input;      
			//String regex = "(?=\\()|(?<=\\)\\d)";
			String regex = "\\s(?=\\()|(?<=\\))\\s"; //new one
			String[] tokens = equation.split(regex); //makes eqn into an array 
			for (int k = 0; k < tokens.length; k++) { //prints the array
				//System.out.println(tokens[k]);
			}
			
			//allows you to go thru and enter commands 
			for (int i = 0; i < 5; i++) 
			{
				String input2 = scan.nextLine(); //takes in the command
				//String[] input2 = scan.nextLine().split(" ");
				
				//(ADD (EXP -3 2) (SQR 5) (SUB 6 2) (MULT 6 7 -2 3) (DIV 15 5))
				
				if(input2.equalsIgnoreCase("COUNT"))
				{
					System.out.println(count(tokens));
				}
				else if(input2.equalsIgnoreCase("MAXIMUM"))
				{
					System.out.println(maximum(tokens)); 
				}
				else
				{
					String[] input3 = input2.split(" ");
					String command = input3[0];
					int firstNum = Integer.parseInt(input3[1]);
					int secondNum = Integer.parseInt(input3[2]);
					
					if(command.equalsIgnoreCase("remove"))
					{
						System.out.println(remove(tokens, firstNum, secondNum));
					}
					
					if(command.equalsIgnoreCase("sort"))
					{
						System.out.println(sort(tokens, firstNum, secondNum));
					}
					if(command.equalsIgnoreCase("reverse"))
					{
						System.out.println(reverse(tokens, firstNum, secondNum));
					}
				}
				
			}//for loop that lets you enter commands
		}//big for loop
		
		
		
	}//main
}//class
