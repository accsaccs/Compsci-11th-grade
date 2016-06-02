package matrixmath;

import java.util.Arrays;

/**
 * Annabel Strauss
 * November 2014 
 * @version 1.0
 * 
 * This is the matrix class. It includes all the methods for doing matrix math. It can add matrices, multiply matrices, do elementary row 
 * operations, row reduce a matrix, and invert a matrix. And there's a method to print the matrix.
 * The elementary row operations allow you to switch two rows, multiply a whole row by a number, and combine rows (aka multiply a whole row
 * by a number and then add that row to a different specified row). 
 * This class also includes methods that allow you to make matrices from the specified amount of rows and column, or from a double array. 
 * 
 * 
 * links for checking answers:
 * http://www.math.odu.edu/~bogacki/cgi-bin/lat.cgi //good for row reduce
 * http://matrix.reshish.com/ //good for inverse 
 */
public class Matrix {

	int row; 
	int column; 

	double[][] mat;

	/**
	 * It takes in the number of rows and columns and then makes a matrix (double array) with that amount of rows and columns 
	 * 
	 * @param r -- the number of rows (specified in the test class)
	 * @param c -- the number of columns (specified in the test class)
	 */
	public Matrix(int r,int c)
	{
		this.row = r; 
		this.column = c;

		this.mat = new double[r][c];
	}

	/**
	 * Takes in a double array and makes a matrix out of it
	 * 
	 * @param m (the double array that you want to turn into a matrix)
	 */
	public Matrix(double[][] m)
	{
		this.row = m.length; //the length of the array in the y direction is the number of rows 
		this.column = m[0].length; //the length of the array in the x direction is the number of columns 

		this.mat = m;
	}

	/**
	 * This function sets the entry in a specified spot in the matrix. You give it the row number and the column number, and you give it the 
	 * number you want in that spot, and then it puts the number there
	 * 
	 * @param r -- the specified row 
	 * @param c -- the specified column
	 * @param entry -- the number you want to put in the spot (r, c)
	 */
	public void setEntry(int r, int c, double entry)
	{
		this.mat[r][c] = entry; 
	}

	/**
	 * This prints the matrix. It uses 2 for loops because one goes through the rows and one goes through the columns. 
	 * Essentially, it pick the first row (in the i for loop) and then it goes along that row (in the j for loop) and prints out each 
	 * entry. Then it moves on to the next row and continues until all the spots in the double array have been printed. 
	 */
	public void print()
	{
		for (int i = 0; i < this.mat.length; i++) //go thru the rows 
		{
			for (int j = 0; j < this.mat[0].length; j++) //go thru the columns
			{
				System.out.print(this.mat[i][j] + " "); //print what is in that spot
			}//for j 
			System.out.println();
		}//for i 
	}//print

	/**
	 * This method adds 2 matrices together. First it makes a new matrix that has the same amount of rows and columns as the original matrix. 
	 * Then it goes through the two for loops and fills this new matrix (matAdded) with the sum of the two input matrices. For matrix addition,
	 * you simply add the numbers in the corresponding positions in the matrices. 
	 * You can only add 2 matrices if they have the same dimensions, so this only runs if the 2 input matrices have the same dimensions
	 * 
	 * @param that (the matrix you want to add to the first one) 
	 * @return the matrix which is the sum of the two input matrices 
	 */
	public Matrix plus(Matrix that)
	{	
		//make the new matrix matAdded (new marix is made so that the original isn't changed)
		double[][] matAdded; 
		matAdded = new double[row][column];

		if(this.mat.length == that.mat.length && this.mat[0].length == that.mat[0].length) //it will only go if the two matrices have the same dimensions
		{
			for (int i = 0; i < this.row; i++) //go thru rows
			{
				for (int j = 0; j < this.column; j++) //go thru columns
				{
					matAdded[i][j] = this.mat[i][j] + that.mat[i][j]; //add the numbers in spot (i,j) in each matrix and put that into (i,j) in the new matrix
				}
			}	
			return new Matrix(matAdded);
		}
		else //it'll print this error statement to the console if the matrices don't have the same dimensions
		{
			System.out.println("can't do that");
			return null;
		}
	}

	/**
	 * This method multiplies 2 matrices together. First it makes a new matrix that has the same amount of rows as the first matrix and the same 
	 * amount of columns as the second matrix. In matrix multiplication, you multiply each element in the first row of the first matrix by 
	 * each element in the first column of the second matrix. Then you add those numbers up and you have your (0,0) element of your new (matMultiplied)
	 * matrix. Then you do first row and second column, and that's your (0,1) element. Once you multiplied that first row by all the columns of
	 * the second matrix, you move on to the next row and repeat the process.
	 * Because of this process, matrix multiplication only works when the number of columns in the first matrix is equal to the number
	 * of rows in the second matrix. Therefore this method will only run if that's true
	 * 
	 * @param that (the matrix you want to multiply with the first one) 
	 * @return the matrix which is the product of the two input matrices 
	 */
	public Matrix times(Matrix that)
	{
		//makes the new matrix matMultiplied
		double[][] matMultiplied;
		matMultiplied = new double[this.row][that.column];

		if(this.column == that.row) //will only go if columns of first matrix matches rows of second matrix
		{
			for (int i = 0; i < this.row; i++) //go thru rows of first matrix
			{			
				for (int j = 0; j < that.column; j++) //go thru columns of second matrix
				{			
					for (int k = 0; k < this.column; k++) //go thru columns of first matrix
					{					
						matMultiplied[i][j] += this.mat[i][k] * that.mat[k][j];
					}
				}
			}
			return new Matrix(matMultiplied);
		}
		else //will print this error statement if the dimensions aren't right
		{
			System.out.println("can't do that");
			return null;
		}
	}

	/**
	 * Elementary row operation 1. It multiplies every element in a specified row by a given number. 
	 * 
	 * @param scalar -- the number you want to multiply everything by 
	 * @param rownumber -- the row you want to have multiplied 
	 * @return the new matrix that is the result of the original matrix having a row multiplied by a scalar 
	 */
	public Matrix scalarTimesRow(double scalar, int rownumber)
	{
		//makes the new matrix 
		double[][] matScalar;
		matScalar = new double[row][column]; //same dimensions as original

		for (int i = 0; i < this.row; i++) //go thru rows
		{
			for (int j = 0; j < this.column; j++) //go thru columns
			{
				if(i == rownumber) //when you get to the specified row, multiply the element by the scalar
				{
					matScalar[i][j] = scalar*this.mat[i][j];
				}
				else //if you're not at the specified row, keep that element the same 
				{
					matScalar[i][j] = this.mat[i][j];
				}

			}//for j 
		}//for i 
		return new Matrix(matScalar);
	}
	
	/**
	 * Elementary row operation 2. It switches two specified rows
	 * 
	 * @param firstrow -- the row you want to switch with row 2
	 * @param secondrow -- the row you want to switch with row 1
	 * @return the new matrix that is the result of the original matrix having two rows switched
	 */
	public Matrix switchRows(int firstrow, int secondrow)
	{
		//makes the new matrix
		double[][] matSwitch;
		matSwitch = new double[row][column];

		for (int i = 0; i < this.row; i++) //go thru rows
		{
			for (int j = 0; j < this.column; j++) //go thru columns
			{
				if(i == firstrow) //when you get to the first row, make the element the element from the second row
				{
					matSwitch[i][j] = this.mat[secondrow][j];
				}
				else if(i == secondrow) //when you get to the second row, make the element the element from the first row
				{
					matSwitch[i][j] = this.mat[firstrow][j];
				}

				else //if you're not at a row to be switched, keep that element the same 
				{
					matSwitch[i][j] = this.mat[i][j];
				}

			}//for j 
		}//for i 
		return new Matrix(matSwitch);
	}

	/**
	 * Elementary row operation 3. Multiply every element in a row by a number, and then add this modified row to another row. 
	 * 
	 * @param scalar -- the number to multiply the first row by 
	 * @param firstrow -- every element in this row is multiplied by the scalar (but the elements themselves in the row don't change)
	 * @param secondrow -- the first row is added to this row (the elements in this row do change)
	 * @return the new matrix that is the result of the original matrix having a row changed
	 */
	public Matrix linearCombRows(double scalar, int firstrow, int secondrow)
	{
		//make the new matrix
		double[][] matLinearComb;
		matLinearComb = new double[row][column]; //same dimensions as original

		for (int i = 0; i < this.row; i++) //go thru rows
		{
			for (int j = 0; j < this.column; j++) //go thru columns
			{
				if(i == secondrow) //when you get to the row that is going to be changed, add to it the multiplied version of row 1
				{
					matLinearComb[i][j] = (scalar*this.mat[firstrow][j]) + this.mat[secondrow][j];
				}

				else //if you're not at a specified row, keep that element the same 
				{
					matLinearComb[i][j] = this.mat[i][j];
				}

			}//for j 
		}//for i 
		return new Matrix(matLinearComb);
	}

	/**
	 * This method puts the matrix into row reduced form. A row reduced matrix means that the first number in every row is a 1, and if a column has a 1 in it, 
	 * the spots above and below that 1 have to be zeros. Also, if a row is all zeros, it must be the last row. 
	 * There can be other numbers in the matrix too, as long as the aforementioned rules are followed. 
	 * In order to row reduce the matrix, you have to use the elementary row operations. This method includes an algorithm that I wrote which
	 * uses the elementary row operations to row reduce a given matrix
	 * 
	 * helpful links:
	 * http://www.millersville.edu/~bikenaga/linear-algebra/row-reduction/row-reduction.html
	 * http://www.csun.edu/~panferov/math262/262_rref.pdf
	 * 
	 * @return the row reduced matrix (which is a new matrix so that the original isn't changed)
	 */
	public Matrix rowreduce()
	{
		
		Matrix m = new Matrix(mat); //making the new matrix (the row reduced one)

		for (int c = 0; c < Math.min(m.mat.length, m.mat[0].length); c++) //loop thru the rows or columns, depending on which is smaller (this will choose the smaller one)
		{	
			if(m.mat[c][c] == 0) //if the element along the diagonal is a zero, then...
			{
				for (int r = 0; r < m.mat.length; r++) //go thru the rows 
				{
					if(m.mat[r][c] != 0) //once you find a row with the element not zero, then switch that row with the one you started at (this make sure that there's a leading coefficient. it will be turned into a 1 later)
					{
						m = m.switchRows(r, c);
						break;
					}
				}
			}

			double element = m.mat[c][c];		
			double recip = 1/element;		
			m = m.scalarTimesRow(recip, c); //this will turn the number along the diagonal into a 1

			for (int r = 0; r < m.mat.length; r++)  //go thru the rows (remember you're still in one column - the outmost for loop)
			{
				if(r != c) //if the row number is not the column number... 
				{
					m = m.linearCombRows(-(m.mat[r][c]), c, r); // make the number in that spot a zero (this makes the number above and below the 1 into zeros)
				}
			}

		}//for		
		return m;
	}//method

	/**
	 * This method inverts the input matrix. It appends the identity matrix (of the input matrix) to the input matrix and then row reduces that 
	 * whole thing. Once row reduced, the first half of the matrix will be the identity matrix, and the second half will be the invert. Then the
	 * method makes a new matrix and fills it with that second half of the big row reduced matrix, and that is the inverted matrix. 
	 * 
	 * @return the inverted matrix (which is a new matrix so that the original isn't changed)
	 */
	public Matrix invert()
	{
		Matrix m = new Matrix(mat.length, mat[0].length*2); //making the new matrix (the inverted one)

		//fill the first half of the array with the original matrix
		for (int i = 0; i < mat[0].length; i++) //pick a column
		{
			for (int j = 0; j < mat.length; j++) //go down the rows (in the column)
			{	
				m.mat[j][i] = mat[j][i];
			}//for columns
		}//for rows

		//fill the second half of the array with the identity matrix 
		int j = 0;
		for (int i = mat.length; i < m.mat[0].length; i++) //pick a column
		{
			m.mat[j][i] = 1; 
			j++;
		}
		
		m = m.rowreduce(); //row reduce the big matrix (original with identity appended)
		
		
		Matrix m2 = new Matrix(mat.length, mat[0].length); //making the new matrix (the inverted one)
		
		//fill new matrix with second half of the row reduced one (which is the inverse) so you just have the inverse of the original matrix
		for (int i = 0; i < mat.length; i++) 
		{
			for (int j1 = 0; j1 < mat.length; j1++) 
			{
				m2.mat[j1][i] = m.mat[j1][i+mat.length];
			}
		}

		//return m;
		return m2;

	}//method

}//class


