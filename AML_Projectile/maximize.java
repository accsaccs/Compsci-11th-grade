/**
 * Annabel Strauss
 * Bove physics 
 * Projectile Lab Report
 * 
 * this program maximizes the distance of the projectile 
 */

public class maximize {
	 public static void main(String[] args) {
		
		 double v = 0;
		 double theda = 0;
		 double height = .27; //1.02 --.27
		 double max = 0;
		 double maxRtemp = 0;
		
		v = 7.05578; //2 clicks initial velocity = 7.05578. 1 click initial velocity = 4.49311
		
		for (double i = 0; i < 90; i+=.1) 
		{
			theda = i;
			double vox = v*Math.cos(Math.toRadians(theda)); //calculates x initial velocity 
			double voy = v*Math.sin(Math.toRadians(theda)); //calculates y initial velocity 
			double root = Math.sqrt((v*v*Math.sin(Math.toRadians(theda))*Math.sin(Math.toRadians(theda))) - (4*-4.9*height));
			//that ^^ is the stuff under the root (in the quadratic) 
			
			maxRtemp = vox * ((-1*voy - root)/-9.8); //calculates the max range 
			
			if(maxRtemp > max) //if max range is greater than 0 (because I declared max as 0 above)
			{
				max = maxRtemp; //replace the max with the temporary max (that was calculated above)
				System.out.println("new range: " + max); //print the range 
				System.out.println("angle: " + i); //print the angle 
			}		
		}
		
	}//main
}//class
