package test_classes;

import polyfun.Polynomial;

import org.opensourcephysics.frames.*;

import riemannsum.*;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * test class which plots the slice for all the ways of calculating area under a curve  
 */
public class SlicePlotTest 
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
    
            Polynomial p = new Polynomial(new double[] {3,0,1}); // p=x^2+3
                    
            PlotFrame f = new PlotFrame("x","y","Left Hand Rule Slice");
            f.setPreferredMinMaxX(.5, 1.5);
            f.setDefaultCloseOperation(3);                  
            f.setVisible(true);
                    
            PlotFrame g = new PlotFrame("x","y","Right Hand Rule Slice");                   
            g.setPreferredMinMaxX(.5,1.5);
            g.setDefaultCloseOperation(3);
            g.setVisible(true);
            
            PlotFrame h = new PlotFrame("x","y","Trapezoid Rule Slice");                   
            h.setPreferredMinMaxX(.5,1.5);
            h.setDefaultCloseOperation(3);
            h.setVisible(true);
            
            PlotFrame i = new PlotFrame("x","y","Midpoint Rule Slice");                   
            i.setPreferredMinMaxX(.5,1.5);
            i.setDefaultCloseOperation(3);
            i.setVisible(true);
            
            PlotFrame j = new PlotFrame("x","y","Maximum Rule Slice");                   
            j.setPreferredMinMaxX(.5,1.5);
            j.setDefaultCloseOperation(3);
            j.setVisible(true);
            
            PlotFrame k = new PlotFrame("x","y","Minimum Rule Slice");
            k.setPreferredMinMaxX(.5, 1.5);
            k.setDefaultCloseOperation(3);                  
            k.setVisible(true);
            
            PlotFrame l = new PlotFrame("x","y","Random Rule Slice");
            l.setPreferredMinMaxX(.5, 1.5);
            l.setDefaultCloseOperation(3);                  
            l.setVisible(true);
            
            PlotFrame m = new PlotFrame("x","y","Simpson's Rule Slice");
            m.setPreferredMinMaxX(.5, 1.5);
            m.setDefaultCloseOperation(3);                  
            m.setVisible(true);
             
                    
            LHR.slicePlot(f, p, 1, 1.1); //a left hand rule slice of poly defined above, from x=1 to x=1.1
                    
            RHR.slicePlot(g, p, 1, 1.1); //a right hand rule slice of poly defined above, from x=1 to x=1.1
            
            TR.slicePlot(h, p, 1, 1.1); //a trapezoid rule slice of poly defined above, from x=1 to x=1.1
            
            MR.slicePlot(i, p, 1, 1.1); //a midpoint rule slice of poly defined above, from x=1 to x=1.1
            
            MaxR.slicePlot(j, p, 1, 1.1); //a maximum rule slice of poly defined above, from x=1 to x=1.1
            
            MinR.slicePlot(k, p, 1, 1.1); //a minimum rule slice of poly defined above, from x=1 to x=1.1
            
            RR.slicePlot(l, p, 1, 1.1); //a random rule slice of poly defined above, from x=1 to x=1.1
            
            SR.slicePlot(m, p, 1, 1.1); //a Simpson's rule slice of poly defined above, from x=1 to x=1.1
            
            
    }
}

