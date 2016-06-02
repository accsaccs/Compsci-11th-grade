package test_classes;

import riemannsum.*;
import polyfun.Polynomial;

import org.opensourcephysics.frames.*;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * test class which plots the accumulation functions for all the ways of calculating area under a curve  
 */
public class RSAccTest 
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
                        
                Polynomial p = new Polynomial(new double[] {0,1,1}); // p=x^2+x
        
                PlotFrame f = new PlotFrame("x","y","Left Hand Rule Accumulation Function Graph");
                f.setPreferredMinMaxX(-3, 3);
                f.setDefaultCloseOperation(3);
                f.setVisible(true);
                        
                PlotFrame g = new PlotFrame("x","y","Right Hand Rule Accumulation Function Graph");
                g.setPreferredMinMaxX(-3, 3);
                g.setDefaultCloseOperation(3);
                g.setVisible(true);
                        
                PlotFrame h = new PlotFrame("x","y","Trapezoid Rule Accumulation Function Graph");
                h.setPreferredMinMaxX(-3, 3);
                h.setDefaultCloseOperation(3);                  
                h.setVisible(true);
                
                PlotFrame i = new PlotFrame("x","y","Midpoint Rule Accumulation Function Graph");
                i.setPreferredMinMaxX(-3, 3);
                i.setDefaultCloseOperation(3);                  
                i.setVisible(true);
                
                PlotFrame j = new PlotFrame("x","y","Maximum Rule Accumulation Function Graph");
                j.setPreferredMinMaxX(-3, 3);
                j.setDefaultCloseOperation(3);                  
                j.setVisible(true);
                
                PlotFrame k = new PlotFrame("x","y","Minimum Rule Accumulation Function Graph");
                k.setPreferredMinMaxX(-3, 3);
                k.setDefaultCloseOperation(3);                  
                k.setVisible(true);
                
                PlotFrame l = new PlotFrame("x","y","Random Rule Accumulation Function Graph");
                l.setPreferredMinMaxX(-3, 3);
                l.setDefaultCloseOperation(3);                  
                l.setVisible(true);
                
                PlotFrame m = new PlotFrame("x","y","Simpson's Rule Accumulation Function Graph");
                m.setPreferredMinMaxX(-3, 3);
                m.setDefaultCloseOperation(3);                  
                m.setVisible(true);
                        
                LHR.rsAcc(f, p, 2, .01, -1.0); //plots the left hand rule acccumulation function of x^2+x, with base x=-1;
                        
                RHR.rsAcc(g, p, 2, .01, -1.0); //plots the right hand rule acccumulation function of x^2+x, with base x=-1;
                        
                TR.rsAcc(h, p, 2, .01, -1.0); //plots the trapezoid rule acccumulation function of x^2+x, with base x=-1;  
                
                MR.rsAcc(i, p, 2, .01, -1.0); //plots the midpoint rule acccumulation function of x^2+x, with base x=-1;  
                
                MaxR.rsAcc(j, p, 2, .01, -1.0); //plots the maximum rule acccumulation function of x^2+x, with base x=-1;  
                
                MinR.rsAcc(k, p, 2, .01, -1.0); //plots the minimum rule acccumulation function of x^2+x, with base x=-1;  
                
                RR.rsAcc(l, p, 2, .01, -1.0); //plots the random rule acccumulation function of x^2+x, with base x=-1;  
                
                SR.rsAcc(m, p, 2, .01, -1.0); //plots the simpson's rule acccumulation function of x^2+x, with base x=-1;  
        }//main
}//class

