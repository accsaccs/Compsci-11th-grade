import java.text.DecimalFormat;
import java.util.Scanner;

/* B Period Class
 * Alexis Harris and Makoto Nara
 * ACSL Post Office
 * 2/14/14
 * SAMPLE CODE: 
 * 5, .1, 3, 20394, 36475
 * 4, 5, .6, 283746, 18273
 */

public class postoffice {

	
	/**
	 * calculates how much it will cost to mail the object the distance it is going 
	 * @param mail type 
	 * @param  distance between the starting and ending zip codes
	 * @return returns the price based on the varibles introduced
	 */

	public static double calculate(java.lang.String type, int dist) {
		//calculate the cost of postage based on a mail type and a zone distance
		double price = 0;
		switch(type){
		case "Regular Post Card":
			price = price + .20 + .03 * dist;
			break;
		case "Large Post Card":
			price  = price + .37 + .03 * dist;
			break;
		case "Envelope":
			price = price + .37 + .04 * dist;;
			break;
		case "Large Envelope":
			price = price + .60 + .05 * dist;;
			break;
		case "Package":
			price = price + 2.95 + .25 * dist;;
			break;
		case "Large Package":
			price = price + 3.95 + .35 * dist;;
			break;
		case "Unmailable":
			price = price + 1000000000;
			break;
		}
		return price;
	}

	/**
	 * Determines the mail type based on the variables provided
	 * @param how long the object is
	 * @param how tall the object is
	 * @param how thick the object is
	 * @return the mail type 
	 */
	
	public static  java.lang.String mailType(double length, double height, double thick) {
		//The classification of the mail object based on the classes:
		String cardtype = "Unmailable";
		if(length <= 3.5 || height <= 3.5 || thick <= .007){
			cardtype = "Unmailable";
		}else if(length >= 3.5 && length <= 4.25 && height >= 3.5 && height <= 6 && thick >= .007 && thick <= .016){
			cardtype = "Regular Post Card";
		}else if(length > 4.25 && length < 6 && height > 6 && height < 11.5 && thick >= .007 && thick <= .015){
			cardtype = "Large Post Card";
		}else if(length >= 3.5 && length <= 6.125 && height >= 5 && height <= 11.5 && thick > .016 && thick < .25){
			cardtype = "Envelope";
		}else if(length > 6.125 && length < 24 && height >= 11 && height <= 18 && thick >= .25 && thick <= .5){
			cardtype = "Large Envelope";
		}else if(length >= 24 || height > 18 || thick > .5 && 2 * thick + 2 * height + length <= 84){
			cardtype = "Package";
		}else if(length >= 24 || height > 18 || thick > .5 && 2 * thick + 2 * height + length > 84 && 2 * thick + 2 * height + length < 130){
			cardtype = "Large Package";
		}else cardtype = "Unmailable";


		return cardtype;
	}

	/**
	 * Converts the zip codes to the corresponding zone
	 * @param takes in the zip code
	 * @return the zone
	 */
	
	public static int zipToZone(int zip) {
		int zone = 0;
		// Converts from zip to zone
		if(zip >= 0 && zip <= 6999){
			zone = 1;
		}else if(zip >= 7000 && zip <= 19999){
			zone = 2;
		}else if(zip >= 20000 && zip <= 35999){
			zone = 3;
		}else if(zip >= 36000 && zip <= 62999){
			zone = 4;
		}else if(zip >= 63000 && zip <= 84999){
			zone = 5;
		}else if(zip >= 85000 && zip <= 99999){
			zone = 6;
		}else{
			zone = 7;
		}
		return zone;
		
	}
	
	/**
	 * Takes in the starting and ending zipcodes and finds the distance between them in terms of zones
	 * @param starting zip code
	 * @param ending zip code
	 * @return the distance between the zip codes
	 */
	
	public static int zoneDist(int zip1, int zip2) {
		//distance from zip1 to zip2 two in zones
		int dist = 0;
		dist = zipToZone(zip1) - zipToZone(zip2);
		dist = Math.abs(dist);
		return dist;

	}
	
	/**
	 * Separates out the different provided variables 
	 * @param the user input
	 * @return the user input broken up into different relevant variables
	 */
	
	public static double[] sort(String[] input){
		/*
		 * int[] breaks = new int[6], decimals = new int[6];
		 * int j = 1, m = 0;
		for (int i = 0; i < input.length; i++) {
			if(input[i] == ','){
				breaks[j] = i;
				j ++;
			}else if(input[i] == '.'){
				decimals[m] = i - breaks[j - 1];
			}
		}
		breaks[5] = input.length;
 		breaks[0] = 0;
		for (int i = 0; i < breaks.length; i++) {
			System.out.print(breaks[i] + " ");
		}
		double[] inputs = new double[5];
		for (int i = 0; i < 5; i++) {
			int l = 0 - decimals[i];
			for (int k = 0; k < breaks[i + 1] - breaks[i]; k++) {
				double y = Double.parseDouble(String.valueOf(input[i]));
				inputs[i] = inputs[i] + y*(10^l);
				l++;
			}
		}
		return inputs;
		 */
		double[] sortedValues = new double[5];
		for (int i = 0; i < input.length; i++) {
			sortedValues[i] = Double.parseDouble(input[i]);
		}
		
		return sortedValues;
 		
	}

	public static void main(String[]args) {

		Scanner scan = new Scanner(System.in);
		double length = 0;
		double height = 0;
		double thickness = 0;
		int sZipCode = 0;
		int eZipCode = 0;
		for (int i = 0; i < 12; i++) {
			String[] input = scan.nextLine().split(", ");
			length = sort(input)[0];
			height = sort(input)[1];
			thickness = sort(input)[2];
			eZipCode = (int) sort(input)[3];
			sZipCode = (int) sort(input)[4];
			if(mailType(length, height, thickness).equalsIgnoreCase("Unmailable") || sZipCode < 1 || eZipCode < 1 || sZipCode > 99999 || eZipCode > 99999){
				System.out.println("UNMAILABLE");
			}else if (calculate(mailType(length, height, thickness), zoneDist(eZipCode, sZipCode)) < 1) {
		        DecimalFormat df = new DecimalFormat(".00");
		        System.out.println(df.format(calculate(mailType(length, height, thickness), zoneDist(eZipCode, sZipCode))));
			}else{
				System.out.println(calculate(mailType(length, height, thickness), zoneDist(eZipCode, sZipCode)));
			}
		}		
	}
}
