package riemannsum;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;

import polyfun.Polynomial;
import riemannsum.*;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * class that makes Trapezoid Rule
 * calculates and graphs the area under the curve according to the Trapezoid Rule
 * trapezoid rule: instead of rectangles, it is now a trapezoid. the trapezoid is made by drawing a line from the y-coordinate of the left point to the y-coordinate of the right point 
 */
public class TrapezoidRule extends Riemann  
{
	double leftCord = 0;
	double rightCord = 0;
	
	public double getLeftCord() {
		return leftCord;
	}

	public void setLeftCord(double leftCord) {
		this.leftCord = leftCord;
	}

	public double getRightCord() {
		return rightCord;
	}

	public void setRightCord(double rightCord) {
		this.rightCord = rightCord;
	}
	
	/**
	 * @param the polynomial from the test class 
	 * @param the left coordinate of the slice
	 * @param the right coordinate of the slice 
	 * 
	 * finds the area of a certain slice (aka rectangle) under a curve according to the trapezoid rule
	 * trapezoid rule: instead of rectangles, it is now a trapezoid. the trapezoid is made by drawing a line from the y-coordinate of the left 
	 * point to the y-coordinate of the right point 
	 * 
	 * @return the area of the slice from the specified left and right coordinates  
	 */
	public double slice(Polynomial p, double leftCord, double rightCord)
	{
		double theSlice = 0;
		double height = 0;
		double base1 = 0;
		double base2 = 0;
		double bothBases = 0;
		
		height = rightCord-leftCord;
		base1 = p.evaluate(leftCord).getTerms()[0].getTermDouble();
		base2 = p.evaluate(rightCord).getTerms()[0].getTermDouble();
		bothBases = base1 + base2;

		
		theSlice = (.5) * (height) * (bothBases);
		//(1/2) * h * (b1 + b2) is the area of a trapezoid
		
		return theSlice;
	}//slice method
	
	/**
	 * @param plot frame (makes the frame so you can graph stuff) 
	 * @param the polynomial (made in the test class) 
	 * @param left-most point of the slice 
	 * @param right-most point of the slice 
	 * 
	 * graphs the slice (which shows the area under the curve but the curve is not on the graph) according to the trapezoid rule
	 */
	public void slicePlot(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	{
		double xcoordL = 0;
		double ycoordL = 0;
		double xcoordR = 0;
		double ycoordR = 0;
		double slope = 0;
		double yInt = 0;
		
		xcoordL = sleft;
		ycoordL = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		xcoordR = sright; 
		ycoordR = poly.evaluate(sright).getTerms()[0].getTermDouble();
		
		slope = (ycoordL - ycoordR)/(xcoordL - xcoordR); //slope of the line between the two y-coordinates 
		yInt = slope*(-xcoordL)+ycoordL; //the y-intercept of the line
		
		Polynomial line = new Polynomial(new double[] {yInt, slope}); // y=mx+b (equation of the line)
		
		Trail trap = new Trail();  //instantiate a Trail object
		
		for (double i = sleft; i < sright; i+=.001) //the area of the trapezoid slice is filled in by a trail 
		{
			trap.addPoint(i, line.evaluate(i).getTerms()[0].getTermDouble()); //evaluate the poly at i and add to the trail
            pframe.addDrawable(trap); //adds the Trail to the PlotFrame pframe so it will appear on the screen 
            trap.addPoint(i, 0);

		}//for

	}//method
	
}//class
