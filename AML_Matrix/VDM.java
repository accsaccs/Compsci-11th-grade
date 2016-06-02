package matrixmath;

import polyfun.Polynomial;
import polyfun.Coef;
import polyfun.Term;
import polyfun.Atom;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.Circle;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;

/**
 * Annabel Strauss
 * November 2014 
 * Version 1.0
 * 
 * This program does the vertical difference method. It can find the slope of the tangent line at a given point on the given polynomial.  
 * It can also graph the slope function of any polynomial. It uses matrix math in order to perform these functions, so it uses methods from the 
 * Matrix class. 
 */
public class VDM {
	
	/**
	 * This function finds the slope of the tangent line at a specified point on a given polynomial. It uses functions from the Matrix class, 
	 * because it uses a matrix in order to find the tangent line. There is a pattern for filling a matrix, which corresponds to the degree 
	 * of the input polynomial and also the specified point. This function uses that pattern to fill the matrix. Then it inverts that matrix (using
	 * the function from the Matrix class) and multiplies the inverted matrix with a matrix of the coefficients of the input poly. The inverted matrix's 
	 * dimensions are the degree of the poly + 1 by the degree of the poly + 1. The dimensions of the matrix of the poly's coefficients is just 
	 * a single column. The inverted matrix is multiplied on the left of the coeff one. 
	 * 
	 * @param p - the input polynomial (from the test class)
	 * @param atX - the point on the given poly at which you want to find the slope of tangent line 
	 * @return the slope of the tangent line at the given point on the given poly
	 */
	public double slopeAtPoint(Polynomial p, double atX)
	{
		
		Matrix mat = new Matrix(p.getDegree()+1, p.getDegree()+1); //makes the matrix 

		//fills the matrix based on the pattern
		for (int i = 0; i < mat.row; i++) 
		{
			mat.mat[i][i] = 1;

			if(i != 0 && i < mat.row-1)
			{
				mat.mat[i][i+1] = -2*atX;
			}

			if(i < mat.row-2)
			{
				mat.mat[i][i+2] = atX*atX;
			}

		}
		
		//mat.print();
		
		mat = mat.invert(); //inverts the matrix 
		
		//mat.print();
		
		Matrix toMult = new Matrix(p.getDegree()+1, 1); //makes the matrix to multiply the inverse by
		
		//gets the coefficients of the input poly and fills the coeff matrix
		for (int i = 0; i < toMult.row; i++) 
		{
			Coef co = p.getCoefficients()[i];
			Term t = co.getTerms()[0];
			double a = t.getTermDouble();
			toMult.mat[i][0] = a;
		}
		
		//toMult.print();
		
		Matrix solutionsMat = new Matrix(p.getDegree()+1, 1); //makes the mat which is the solutions
		solutionsMat = mat.times(toMult); //multiplies the big poly and the coeff poly 
		
		//solutionsMat.print();

		double slope = solutionsMat.mat[1][0]; //gets the "m" spot in the solution matrix, which is the slope
		//System.out.println(slope);
		return slope; 
	}

	/**
	 * This graphs the slope function of the input polynomial. It creates a trail object and then adds points to the trail (in a for loop)
	 * so that you get the graph of the slope function. It uses the slopeAtPoint function above to find the slope at a ton of points, 
	 * and then uses the output to graph the function. 
	 * 
	 * @param pframe - the plot frame where the graph goes
	 * @param p - the input poly (from the test class) 
	 */
	public void slopeFunction(org.opensourcephysics.frames.PlotFrame pframe, Polynomial p)
	{
		Trail graph = new Trail();  //instantiate a Trail object

		for (double i = -100; i < 100; i+=.01) 
		{
			graph.addPoint(i, this.slopeAtPoint(p, i)); //point at i,f'(i)
			pframe.addDrawable(graph); //adds the Trail to the PlotFrame pframe so it will appear on the screen  
		}
	}

}
