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
 * class that makes Maximum Rule
 * calculates and graphs the area under the curve according to the Maximum Rule
 * maximum rule: the height of the rectangle is whichever side of the slice is taller 
 */
public class MaximumRule extends Riemann 
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
	 * finds the area of a certain slice (aka rectangle) under a curve according to the maximum rule
	 * maximum rule: the height of the rectangle is whichever side of the slice is taller 
	 * 
	 * @return the area of the slice from the specified left and right coordinates  
	 */
	public double slice(Polynomial p, double leftCord, double rightCord)
	{
		double theSlice = 0;
		double base = 0;
		double height = 0;
		double heightA = 0;
		double heightB = 0;
		
		base = (rightCord-leftCord);
		//System.out.println(base);
		
		heightA = p.evaluate(leftCord).getTerms()[0].getTermDouble(); //evaluates the height at the left coordinate
		heightB = p.evaluate(rightCord).getTerms()[0].getTermDouble(); //evaluates the height at the right coordinate 
	
		//do this to find which one is the maximum
		if(heightA > heightB)
		{
			height = heightA;
		}
		else
		{
			height = heightB;
		}
			
		theSlice = base*height;
		
		return theSlice;
		
	}//slice method
	
	/**
	 * @param plot frame (makes the frame so you can graph stuff) 
	 * @param the polynomial (made in the test class) 
	 * @param left-most point of the slice 
	 * @param right-most point of the slice 
	 * 
	 * graphs the slice (which shows the area under the curve but the curve is not on the graph) according to the maximum rule
	 */
	public void slicePlot(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	{
		double width = 0;
		double height = 0;
		double xcoord = 0;
		double ycoord = 0;
		double heightA = 0;
		double heightB = 0;
	
		heightA = poly.evaluate(sleft).getTerms()[0].getTermDouble(); //evaluates the height at the left coordinate
		heightB = poly.evaluate(sright).getTerms()[0].getTermDouble(); //evaluates the height at the right coordinate 
	
		//do this to find which one is the maximum
		if(heightA > heightB)
		{
			height = heightA;
		}
		else
		{
			height = heightB;
		}
		
		width = Math.abs(sright - sleft); //has to be absolute value so it can work with polynomials that have a negative leading coeff or are under x-axis
		xcoord = width/2;
		ycoord = height/2;
		
		
		//Creates a rectangle with center (sleft+xcoord, ycoord), width of width, and height of height
        DrawableShape slice = DrawableShape.createRectangle((sleft+xcoord),ycoord,width,Math.abs(height));  
        slice.setMarkerColor(Color.blue,Color.green);  //changes the interior to blue and the border to green.   
        pframe.addDrawable(slice); //draws the slice on the graph 
       
	}
	
}//class
