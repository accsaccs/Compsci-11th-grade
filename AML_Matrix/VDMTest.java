package testclasses;

import org.opensourcephysics.frames.PlotFrame;

import matrixmath.VDM;
import polyfun.Polynomial;

/**
 * Annabel Strauss
 * November 2014
 * 
 * Test class that tests the slopeAtPoint function in the VDM class
 *
 */
public class VDMTest {
	public static void main(String[] args) {
		
		VDM VerticalDif = new VDM(); //creates objects VDM
		
		//makes the polynomial 
		Polynomial p = new Polynomial(new double[] {0,0,3}); //p=3x^2 //0,0,3
		
		//specifies the point at which the derivative should be found
		double point = 2;
		
		System.out.println(VerticalDif.slopeAtPoint(p, point)); //prints the answer (aka the slope of the tangent line at that point)
		
		
	}
}
