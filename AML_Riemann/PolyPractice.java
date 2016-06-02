package riemannsum;

import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame; //or frames.DisplayFrame if you use a DisplayFrame instead of a PlotFrame

import polyfun.Polynomial;
import polyfun.Term;

/**
 * @author Annabel Strauss
 * PolyPractice class
 * makes the eval function and the addXsquared function
 * eval allows you to evaluate a point so you get the height of it
 * addXsquared adds x squared to the input polynomial, and then graphs it on the plot frame 
 */
public class PolyPractice {
	
	public PolyPractice() {}


	/**
	 * takes a polynomial and a double and it returns a double. The value of the returned double is the polynomial evaluated at the input double.
	 * @param p
	 * @param x coordinate
	 * @return y coordinate
	 */
	public static double eval(Polynomial p, double x){
		
		double y = 0;
		
		y = p.evaluate(x).getTerms()[0].getTermDouble();
		return y; 
	}
	
	/**
	 * takes a polynomial and returns void. It prints the sum of the polynomial x^2 and the input polynomial, and it graphs this sum of polynomials on a PlotFrame.
	 * @param p
	 */
	public void addXsquared(Polynomial p){
		
        Polynomial Xsquared = new Polynomial(new double[] {0,0,1.0}); //polynomial x^2
        p = p.plus(Xsquared);
        p.print();
        
        Trail t = new Trail();  //instantiate a Trail object
        PlotFrame pf = new PlotFrame("x","y","Drawing Shapes");
        pf.setVisible(true);
        pf.setDefaultCloseOperation(3);
        
        for (double i = -100; i < 101; i+=.1) 
        {
        	 t.addPoint(i, this.eval(p,i)); //want to evaluate the addXsquared poly 
             pf.addDrawable(t); //adds the Trail to the PlotFrame pf so it will appear on the screen      
		}
   	 	t.closeTrail(); //connects the last point to the first and prevents more points from being added.


	}
	
}
