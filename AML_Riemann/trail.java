package riemannsum;

import org.opensourcephysics.frames.PlotFrame; //or frames.DisplayFrame if you use a DisplayFrame instead of a PlotFrame
import org.opensourcephysics.display.Trail; 

import polyfun.Polynomial;
import riemannsum.PolyPractice;
import test_classes.evalTest;
/**
 * @author Annabel Strauss
 * trail class for drawing a trail on the plot frame
 */
public class trail {
	public static void main(String[] args)
	{
		/*First create a DisplayFrame or PlotFrame to put the Trail on
        This code is the same as above
        */
        PlotFrame pf = new PlotFrame("x","y","Drawing Shapes");
        pf.setVisible(true);
        pf.setDefaultCloseOperation(3);

        Trail t = new Trail();  //instantiate a Trail object
        
        t.closeTrail(); //connects the last point to the first and prevents more points from being added.

        pf.addDrawable(t); //adds the Trail to the PlotFrame pf so it will appear on the screen
        
	}

}


