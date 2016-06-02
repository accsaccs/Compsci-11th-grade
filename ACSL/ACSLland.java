import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
 * Annabel Strauss
 * The Dalton School
 * ACSLland
 * 12/12/14
 */
public class ACSLland {

	public static int distance(String city1, String city2) {

		int dist = 0;
		int place1 = 0;
		int place2 = 0;

		int a = 0;
		int b = 450;
		int c = 590;
		int d = 715;
		int e = 1080;
		int f = 1330;
		int g = 1490;
		int h = 1870;
		int j = 2105;
		int k = 2425;

		if(city1.equals("A")) place1 = a;
		if(city1.equals("B")) place1 = b;
		if(city1.equals("C")) place1 = c;
		if(city1.equals("D")) place1 = d;
		if(city1.equals("E")) place1 = e;
		if(city1.equals("F")) place1 = f;
		if(city1.equals("G")) place1 = g;
		if(city1.equals("H")) place1 = h;
		if(city1.equals("J")) place1 = j;
		if(city1.equals("K")) place1 = k;

		if(city2.equals("A")) place2 = a;
		if(city2.equals("B")) place2 = b;
		if(city2.equals("C")) place2 = c;
		if(city2.equals("D")) place2 = d;
		if(city2.equals("E")) place2 = e;
		if(city2.equals("F")) place2 = f;
		if(city2.equals("G")) place2 = g;
		if(city2.equals("H")) place2 = h;
		if(city2.equals("J")) place2 = j;
		if(city2.equals("K")) place2 = k;

		dist = Math.abs(place1 - place2);
		//System.out.println(dist);

		return dist;

	}//distance method

	public static String finalTime(String city1, String city2, double start1, String time1, double start2, String time2, double speed1, double speed2) 
	{
		long time = 0;
		double timedif = 0;
		double distance = distance(city1, city2);
		String result = "";

		if(distance == 0)
		{
			result = "0:00";
		}

		else
		{
			if(start1==12 || start2==12)
			{
				if(time1.equals("AM") && time2.equals("PM"))
				{
					if(start1==12 && start2==12) timedif = -12;
					else 
					{
						timedif = start1-start2;
					}
					//System.out.println(timedif);
				}
				else if(time1.equals("PM") && time2.equals("AM"))
				{
					//System.out.println("hi");
					if(start1==12 && start2==12) timedif = -12;
					else
					{
						timedif = start1-start2;
					}
					//System.out.println(timedif);
				}
				else
				{
					if(start1-start2 == Math.abs(start1-start2)) timedif = (start1-start2)-12;
					else if(start1-start2 != Math.abs(start1-start2)) timedif = (start1-start2)+12;
					//System.out.println(timedif);

				}
			}
			else
			{
				if(time1.equals("AM") && time2.equals("PM"))
				{
					if(start1-start2 == Math.abs(start1-start2)) timedif = (start1-start2)-12;
					else if(start1-start2 != Math.abs(start1-start2)) timedif = (start1-start2)+12;
					//System.out.println(timedif);
				}
				else if(time1.equals("PM") && time2.equals("AM"))
				{
					if(start1-start2 == Math.abs(start1-start2)) timedif = (start1-start2)-12;
					else if(start1-start2 != Math.abs(start1-start2)) timedif = (start1-start2)+12;
					//System.out.println(timedif);
				}
				else
				{
					timedif = start1-start2;
					//System.out.println(timedif);
				}
			}

			time = (long) Math.round(((distance - (speed2*timedif))/(speed1+speed2))*60); 
			//System.out.println("pretime: " + time);
			if(time > 0) time=time;
			else if(time < 0) time=0; //MAKE SURE THIS NEGATIVE STUFF IS TRUE 
			//System.out.println("time: " + time);

			//int min = 125; //time
			//int start = 11; //the starting time

			long hours = TimeUnit.MINUTES.toHours(time);
			//System.out.println("Hours: " + hours);
			//if(hours > 12) hours = hours-12;
			long remainMinute = time - TimeUnit.HOURS.toMinutes(hours);
			result = hours + ":" + String.format("%02d", remainMinute); //String result = String.format("%02d", hours) + ":" + String.format("%02d", remainMinute);

		}//big else 
		return result;
	}//time method



	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		String city1 = "";
		String city2 = "";
		double start1 = 0;
		String time1 = "";
		double start2 = 0;
		String time2 = "";
		double speed1 = 0;
		double speed2 = 0;

		for (int i = 0; i < 12; i++) 
		{
			String[] input = scan.nextLine().split(", ");
			city1 = input[0];
			city2 = input[1];
			start1 = Double.parseDouble(input[2]);
			time1 = input[3];
			start2 = Double.parseDouble(input[4]);
			time2 = input[5];
			speed1 = Double.parseDouble(input[6]);
			speed2 = Double.parseDouble(input[7]);

			System.out.println(finalTime(city1, city2, start1, time1, start2, time2, speed1, speed2));
		}




	}
}//class 
