package riemannsum;

import java.awt.Color;

import polyfun.Polynomial;
import riemannsum.*;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

/**
 * @author Annabel Strauss
 * Riemann class
 * makes rs method (finds area) and rsPlot method (graphs the area under the curve)
 */
public abstract class Riemann {
	
	abstract double slice(Polynomial p, double x, double y);
	abstract void slicePlot(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright);

	double delta = 0;
	double left = 0;
	double right = 0;
	int subintervals = 0;
	
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getRight() {
		return right;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public int getSubintervals() {
		return subintervals;
	}
	public void setSubintervals(int subintervals) {
		this.subintervals = subintervals;
	}
	
	/**
	 * @param polynomial (from the test class) 
	 * @param left end-point of the interval over which the Riemann sum is to be calculated 
	 * @param right end-point of the interval over which the Riemann sum is to be calculated 
	 * @param subintervals (number of subintervals used to calculate the Riemann sum)
	 * 
	 * figures out the area under the curve (as a number) 
	 * 
	 * @return the area under the curve from the specified left and right end-points
	 */
	public double rs(Polynomial p, double left, double right, int subintervals){
		
		double sum = 0;
		double range = right-left;
		double delta = range/subintervals;
		
		for (int i = 0; i < subintervals; i++) {
			sum += slice(p, (left+(i*delta)), (left+((i+1)*delta)));
		}
		
		return sum;
	}//rs method
	
	/**
	 * 
	 * @param pframe - PlotFrame on which the polynomial and the Riemann sum are drawn
	 * @param poly -  the polynomial whose Riemann sum is to be drawn
	 * @param index - the number associated to the collection of (x,y) coordinates that make up the dataset which, when plotted, is the graph of the polynomial
	 * @param precision - the difference between the x-coordinates of two adjacent points on the graph of the polynomial
	 * @param left - the left hand endpoint of the Riemann sum
	 * @param right - the right hand endpoint of the Riemann sum
	 * @param subintervals - the number of subintervals in the Riemann sum
	 * 
	 * This method graphs the input polynomial on the input PlotFrame. It also draws the regions used to calculate a particular Riemann sum
	 */
	public void rsPlot(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, int index, double precision, double left, double right, int subintervals)
	{
		double range = right-left;
		double delta = range/subintervals;
		//double height = 0;
		//double ycoord = 0;
		
		Trail t = new Trail();  //instantiate a Trail object
        for (double i = -100; i < 101; i+=.1) 
        {
        	 t.addPoint(i, PolyPractice.eval(poly,i)); //evaluate the poly at i and add to the trail
             pframe.addDrawable(t); //adds the Trail to the PlotFrame pframe so it will appear on the screen               
             
		}
   	 	t.closeTrail(); //connects the last point to the first and prevents more points from being added.
   	 	
   	 	for (double i = 0; i < subintervals; i++) 
   	 	{
			slicePlot(pframe, poly, (left+(i*delta)), (left+((i+1)*delta)));
		}
	}//method (rsPlot)
	
	/**
	 * @param pframe - the PlotFrame on which the polynomial and the Riemann sum are drawn
	 * @param poly - the polynomial whose accumulation function is to be calculated
	 * @param index - the number associated to the collection of (x,y) coordinates that make up the dataset which, when plotted, is the graph of the accumulation function
	 * @param precision - the difference between the x-coordinates of two adjacent points on the graph of the accumulation function
	 * @param base - the left hand endpoint of the accumulation function
	 * 
	 * This method graphs the accumulation function corresponding to the input polynomial and the input left hand endpoint
	 */
	public void rsAcc(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, int index, double precision, double base)
	{
		Trail tAcc = new Trail();  //instantiate a Trail object
		
		for (double i = -10; i < 10; i+=precision) 
		{
			tAcc.addPoint(i, this.rs(poly, base, i, 200)); //graphing the acc function using rs
            pframe.addDrawable(tAcc); //adds the Trail to the PlotFrame pframe so it will appear on the screen  
			
		}
	}
	

	
	
	

	
}

