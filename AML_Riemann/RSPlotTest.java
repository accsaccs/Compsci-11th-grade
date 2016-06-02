package test_classes;

import riemannsum.*;
import polyfun.Polynomial;

import org.opensourcephysics.frames.*;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * test class which plots the Riemann sum for all the ways of calculating area under a curve  
 */
public class RSPlotTest 
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
            
            Polynomial p = new Polynomial(new double[] {1,0,-3,0,-1}); // p=3x^2-6x+3  3,-6,3
            
            PlotFrame f = new PlotFrame("x","y","Left Hand Rule Riemann Sum Graph");
            f.setPreferredMinMaxX(-1, 3);
            f.setDefaultCloseOperation(3);
            f.setVisible(true);
            
            PlotFrame g = new PlotFrame("x","y","Right Hand Rule Riemann Sum Graph");
            g.setPreferredMinMaxX(-1, 3);
            g.setDefaultCloseOperation(3);
            g.setVisible(true);
            
            PlotFrame h = new PlotFrame("x","y","Trapezoid Rule Riemann Sum Graph");
            h.setPreferredMinMaxX(-1, 3);
            h.setDefaultCloseOperation(3);
            h.setVisible(true);
            
            PlotFrame i = new PlotFrame("x","y","Midpoint Rule Riemann Sum Graph");
            i.setPreferredMinMaxX(-1, 3);
            i.setDefaultCloseOperation(3);
            i.setVisible(true);
            
            PlotFrame j = new PlotFrame("x","y","Maximum Rule Riemann Sum Graph");                   
            j.setPreferredMinMaxX(-1, 3);
            j.setDefaultCloseOperation(3);
            j.setVisible(true);
            
            PlotFrame k = new PlotFrame("x","y","Minimum Rule Riemann Sum Graph");
            k.setPreferredMinMaxX(-1, 3);
            k.setDefaultCloseOperation(3);
            k.setVisible(true);
            
            PlotFrame l = new PlotFrame("x","y","Random Rule Riemann Sum Graph");
            l.setPreferredMinMaxX(-1, 3);
            l.setDefaultCloseOperation(3);
            l.setVisible(true);
            
            PlotFrame m = new PlotFrame("x","y","Simpson's Rule Riemann Sum Graph");
            m.setPreferredMinMaxX(-1, 3);
            m.setDefaultCloseOperation(3);
            m.setVisible(true);
            
            
            LHR.rsPlot(f, p, 1, 0.01, 0.0, 2.0, 10); // the left hand rule from x=0 to x=2, with 10 rectangles      
            
            RHR.rsPlot(g, p, 1, 0.01, 0.0, 2.0, 10); // the right hand rule from x=0 to x=2, with 10 rectangles    
          
            TR.rsPlot(h, p, 1, 0.01, 0.0, 2.0, 10); // the trapezoid rule from x=0 to x=2, with 10 rectangles    
            
            MR.rsPlot(i, p, 1, 0.01, 0.0, 2.0, 10); // the midpoint rule from x=0 to x=2, with 10 rectangles    
            
            MaxR.rsPlot(j, p, 1, 0.01, 0.0, 2.0, 10); // the maximum rule from x=0 to x=2, with 10 rectangles    
            
            MinR.rsPlot(k, p, 1, 0.01, 0.0, 2.0, 10); // the minimum rule from x=0 to x=2, with 10 rectangles    
            
            RR.rsPlot(l, p, 1, 0.01, 0.0, 2.0, 10); // the minimum rule from x=0 to x=2, with 10 rectangles 
            
            SR.rsPlot(m, p, 1, 0.01, 0.0, 2.0, 2); // the minimum rule from x=0 to x=2, with 10 rectangles 
    }
}

