package test_classes;

import polyfun.Polynomial;
import riemannsum.PolyPractice;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * test class which tests addXsquared 
 */
public class addXSquaredTest {
	public static void main(String[] args) 
    {
            PolyPractice popeye = new PolyPractice(); 
            Polynomial poly = new Polynomial(new double[] {3,2,1}); //polynomial 3 + 2x + x^2
            popeye.addXsquared(poly); //popeye adds x^2 to poly, and then both prints and graphs the resulting polynomial
    }
}

