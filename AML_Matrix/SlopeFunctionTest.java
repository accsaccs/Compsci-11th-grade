package testclasses;

import org.opensourcephysics.frames.PlotFrame;

import matrixmath.VDM;
import polyfun.Polynomial;

/**
 * Annabel Strauss
 * November 2014
 * 
 * Test class that tests the slopeFunction function in the VDM class
 */
public class SlopeFunctionTest {
	public static void main(String[] args) 
	{
		VDM VerticalDif = new VDM(); //makes object VDM
		
		//makes the polynomial
		Polynomial p = new Polynomial(new double[] {7,5,4,3}); //p = x^3 + -6x^2 + 8x   0,8,-6,1
		
		//makes the frame so that the graph can appear on the screen 
		PlotFrame f = new PlotFrame("x","y","Slope Function");
        f.setPreferredMinMaxX(-3, 3);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
        
        VerticalDif.slopeFunction(f, p); //plots the slope function
	}
}
