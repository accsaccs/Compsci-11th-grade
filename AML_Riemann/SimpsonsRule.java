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
 * class that makes Simpson's Rule
 * calculates and graphs the area under the curve according to Simpson's Rule
 * Simpson's Rule: the "top" of the slice lies on the graph of the quadratic function which passes through the points (a,p(a)), (m,p(m)), and (b,p(b))
 */
public class SimpsonsRule extends Riemann
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
	 * finds the area of a certain slice (aka rectangle) under a curve according to Simpson's Rule
	 * Simpson's Rule: the "top" of the slice lies on the graph of the quadratic function which passes through the points (a,p(a)), (m,p(m)), and (b,p(b))
	 * 
	 * @return the area of the slice from the specified left and right coordinates  
	 */
	public double slice(Polynomial p, double leftCord, double rightCord)
	{
		MidpointRule MR = new MidpointRule(); //MidpointRule extends Riemann
		TrapezoidRule TR= new TrapezoidRule(); //TrapezoidRule extends Riemann
		
		double theSlice = ((2*MR.slice(p, leftCord, rightCord))+(TR.slice(p, leftCord, rightCord)))/3; //2*midpoint rule + trapezoid rule ALL divided by 3 
		
		return theSlice;
		
	}//slice method
	
	/**
	 * @param plot frame (makes the frame so you can graph stuff) 
	 * @param the polynomial (made in the test class) 
	 * @param left-most point of the slice 
	 * @param right-most point of the slice 
	 * 
	 * graphs the slice (which shows the area under the curve but the curve is not on the graph) according to Simpson's rule
	 */
	public void slicePlot(org.opensourcephysics.frames.PlotFrame pframe, polyfun.Polynomial poly, double sleft, double sright)
	{
				
		double x1 = sleft; //left most point (x coord)
		double x2 = (sright-sleft)/2 + sleft; //midpoint (x coord)
		double x3 = sright; //right most point (x cord)
		double y1 = poly.evaluate(x1).getTerms()[0].getTermDouble(); //left most point evaluated so you get the height (aka y coord)
		double y2 = poly.evaluate(x2).getTerms()[0].getTermDouble(); //midpoint  evaluated so you get the height (aka y coord)
		double y3 = poly.evaluate(x3).getTerms()[0].getTermDouble(); //right most point evaluated so you get the height (aka y coord)
		
		//you now how 3 different point, each with an x and y coordinate. you need to use these 3 points to find the equation of the parabola
		//set up a system of equations to find A B and C (parabola = ax^2 + bx + c)
		double denom = (x1 - x2)*(x1 - x3)*(x2 - x3);
		double A = (x3 * (y2 - y1) + x2 * (y1 - y3) + x1 * (y3 - y2))/denom;
		double B = (Math.pow(x3, 2) * (y1 - y2) + Math.pow(x2, 2) * (y3 - y1) + Math.pow(x1, 2) * (y2 - y3))/denom;
		double C = (x2 * x3 * (x2 - x3) * y1 + x3 * x1 * (x3 - x1) * y2 + x1 * x2 * (x1 - x2) * y3)/denom;
		
		//makes the new polynomial that you just found A B and C for 
		Polynomial simpsonPoly = new Polynomial(new double[] {C,B,A}); // p=ax^2+bx+c
		
		//draws the new parabola on the graph (per slice)
		Trail t = new Trail();  //instantiate a Trail object
		t.color = Color.red;
		for (double i = -100; i < 101; i+=.1) 
        {
        	 t.addPoint(i, PolyPractice.eval(simpsonPoly,i)); //want to evaluate the simpson poly a bunch of times to get a picture on graph
             pframe.addDrawable(t); //adds the Trail to the PlotFrame pf so it will appear on the screen      
		}
   	 	t.closeTrail(); //connects the last point to the first and prevents more points from being added.

	}
	
	
}
