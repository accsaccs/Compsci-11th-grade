package test_classes;

import polyfun.Polynomial;
import riemannsum.*;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * test class which tests riemann sum for all the ways of calculating area under a curve  
 */
public class RSTest 
{
	public static void main(String[] args) 
    {
            LeftHandRule LHR = new LeftHandRule();  //LeftHandRule extends Riemann
            RightHandRule RHR = new RightHandRule();  //RightHandRule extends Riemann
            TrapezoidRule TR= new TrapezoidRule(); //TrapezoidRule extends Riemann
            MidpointRule MR= new MidpointRule(); //MidpointRule extends Riemann
            MaximumRule MaxR= new MaximumRule(); //MaximumRule extends Riemann
            MinimumRule MinR= new MinimumRule(); //MinimumRule extends Riemann
            RandomRule RR = new RandomRule(); //RandomRule extends Riemann
            SimpsonsRule SR = new SimpsonsRule(); //SimpsonsRule extends Riemann
            
            Polynomial p = new Polynomial(new double[] {0,0,3}); //p=3x^2

            System.out.println(LHR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the left hand rule
            
            System.out.println(RHR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the right hand rule
            
            System.out.println(TR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the trapezoid rule
            
            System.out.println(MR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the midpoint rule
            
            System.out.println(MaxR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the maximum rule
            
            System.out.println(MinR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the minimum rule
            
            System.out.println(RR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using the random rule
            
            System.out.println(SR.rs(p, 0.0, 1.0, 2000)+"\n"); //the approximate area under 3x^2, from 0 to 1, using Simpson's rule
    } 
}

