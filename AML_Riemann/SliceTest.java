package test_classes;

import polyfun.Polynomial;
import riemannsum.*;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * test class which tests slice for all the ways of calculating area under a curve  
 */
public class SliceTest 
{
	public static void main(String[] args) 
    {
            LeftHandRule LHR= new LeftHandRule();  //LeftHandRule extends Riemann
            RightHandRule RHR = new RightHandRule();  //RightHandRule extends Riemann
            TrapezoidRule TR= new TrapezoidRule(); //TrapezoidRule extends Riemann
            MidpointRule MR = new MidpointRule(); //MidpointRule extends Riemann
            MaximumRule MaxR = new MaximumRule(); //MaximumRule extends Riemann
            MinimumRule MinR = new MinimumRule(); //MinimumRule extends Riemann
            RandomRule RR = new RandomRule(); //RandomRule extends Riemann
            SimpsonsRule SR = new SimpsonsRule(); //SimpsonsRule extends Riemann
    
            Polynomial p = new Polynomial(new double[] {0,4,0,3}); // p=3x^3+4x
            
            System.out.println(LHR.slice(p,1,1.1)+"\n"); //the area of a left hand rule slice of 3x^3+4x, from x=1 to x=1.1
            
            System.out.println(RHR.slice(p,1,1.1)+"\n"); //the area of a right hand rule slice of 3x^3+4x, from x=1 to x=1.1
            
            System.out.println(TR.slice(p,1,1.1)+"\n"); //the area of a trapezoid rule slice of 3x^3+4x, from x=1 to x=1.1
            
            System.out.println(MR.slice(p,1,1.1)+"\n"); //the area of a midpoint rule slice of 3x^3+4x, from x=1 to x=1.1
            
            System.out.println(MaxR.slice(p,1,1.1)+"\n"); //the area of a maximum rule slice of 3x^3+4x, from x=1 to x=1.1
            
            System.out.println(MinR.slice(p,1,1.1)+"\n"); //the area of a minimum rule slice of 3x^3+4x, from x=1 to x=1.1
            
            System.out.println(RR.slice(p,1,1.1)+"\n"); //the area of a random rule slice of 3x^3+4x, from x=1 to x=1.1
            
            System.out.println(SR.slice(p,1,1.1)+"\n"); //the area of a random rule slice of 3x^3+4x, from x=1 to x=1.1
    }
}

