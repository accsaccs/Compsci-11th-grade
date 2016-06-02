package test_classes;

import polyfun.Polynomial;
import riemannsum.PolyPractice;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * test class which tests eval 
 */
public class evalTest {

	public static void main(String[] args) 
	{
		PolyPractice popeye = new PolyPractice(); 
        Polynomial poly = new Polynomial(new double[] {3,2,1}); //polynomial 3 + 2x + x^2
        System.out.println(popeye.eval(poly,2.5)); //popeye evaluates poly at 2.5
	}

}
