package riemannsum;

import java.awt.Color;

import org.opensourcephysics.display.DrawableShape;

import polyfun.Polynomial;
import riemannsum.*;

/**
 * @author Annabel Strauss
 * @version 1.0 
 * October 2014 
 * 
 * class that makes Left Hand Rule
 * calculates and graphs the area under the curve according to the Left Hand Rule
 * left hand rule: the height of the rectangle is the height of the left most point
 */
public class LeftHandRule extends Riemann 
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
	 * finds the area of a certain slice (aka rectangle) under a curve according to the left hand rule
	 * left hand rule: the height of the rectangle is the height of the left most point
	 * 
	 * @return the area of the slice from the specified left and right coordinates  
	 */
	public double slice(Polynomial p, double leftCord, double rightCord)
	{
		
		double theSlice = 0;
		double base = 0;
		double height = 0;
		
		base = (rightCord-leftCord);
		//System.out.println(base);
		
		height = p.evaluate(leftCord).getTerms()[0].getTermDouble(); //evaluates the left coordinate to get the y coordinate (height) at that point
		//System.out.println(height);
		
		theSlice = base*height; //formula for area of rectangle 
		
		return theSlice;
		
	}//slice method
	
	/**
	 * @param plot frame (makes the frame so you can graph stuff) 
	 * @param the polynomial (made in the test class) 
	 * @param left-most point of the slice 
	 * @param right-most point of the slice 
	 * 
	 * graphs the slice (which shows the area under the curve but the curve is not on the graph) according to the left hand rule
	 */
	public void slicePlot(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	{
		double width = 0;
		double height = 0;
		double xcoord = 0;
		double ycoord = 0;
		
		width = Math.abs(sright - sleft); //has to be absolute value so it can work with polynomials that have a negative leading coeff or are under x-axis
		height = poly.evaluate(sleft).getTerms()[0].getTermDouble();
		xcoord = width/2;
		ycoord = height/2;
		
		
		//Creates a rectangle with center (sleft+xcoord, ycoord), width of width, and height of height
        DrawableShape slice = DrawableShape.createRectangle((sleft+xcoord),ycoord,width,Math.abs(height));  
        slice.setMarkerColor(Color.blue,Color.green);  //changes the interior to blue and the border to green.   
        pframe.addDrawable(slice); //draws the slice on the graph 
       
	}
	
}//class
