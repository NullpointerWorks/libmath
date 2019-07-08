package com.nullpointerworks.math.matrix;

import com.nullpointerworks.math.Function;

public class Matrix 
{

	/**
	 * add value to the entire matrix
	 */
	public static float[][] add(float[][] m, float n)
	{
		int rows = m.length;
		int cols = m[0].length;
		
		float[][] res = new float[rows][cols];
		for (int y=0; y<rows; y++)
		{
			float[] r = res[y];
			for (int x=0; x<cols; x++)
				r[x] = r[x] + n;
		}
		return res;
	}
	
	/**
	 * multiply two matrices with each other.
	 * returns the resulting matrix
	 */
	public static float[][] mul(float[][] m1, float[][] m2)
	{
		int rows = m1.length;
		int cols = m2[0].length;
		
		// if the columns and rows don't match, return an empty matrix
		if (m1[0].length != m2.length) return new float[0][0];
		
		float[][] res = new float[rows][cols];
		for (int r=0;r<rows;r++)
		{
			for (int c=0;c<cols;c++)
			{
				float d = dot( row(m1,r) , col(m2,c) );
				res[r][c] = d;
			}
		}
		return res;
	}

	/**
	 * multiply two matrices with each other. takes transposition flags
	 * returns the resulting matrix. does not modify original matrices
	 */
	public static float[][] mul(float[][] m1, boolean t1, float[][] m2, boolean t2)
	{
		// if no transposition is done
		if (!t1)
		if (!t2)
		{
			return mul(m1,m2);
		}
		
		// if first is transposed
		if (t1)
		if (!t2)
		{
			int rows = m1[0].length;
			int cols = m2[0].length;
			
			float[][] res = new float[rows][cols];
			
			for (int r=0;r<rows;r++)
			{
				float[] res_row = res[r];
				
				for (int c=0;c<cols;c++)
				{
					res_row[c] = dot( col(m1,r) , col(m2,c) );
				}
			}
			return res;
		}
		
		// if second is transposed
		if (!t1)
		if (t2)
		{
			int rows = m1.length;
			int cols = m2.length;
			
			float[][] res = new float[rows][cols];
			
			for (int r=0;r<rows;r++)
			{
				float[] res_row = res[r];
				
				for (int c=0;c<cols;c++)
				{
					res_row[c] = dot( row(m1,r) , row(m2,c) );
				}
			}
			return res;
		}
		
		// if both transposed
		if (t1)
		if (t2)
		{
			int rows = m1[0].length;
			int cols = m2.length;
			
			float[][] res = new float[rows][cols];
			
			for (int r=0;r<rows;r++)
			{
				float[] res_row = res[r];
				
				for (int c=0;c<cols;c++)
				{
					res_row[c] = dot( col(m1,r) , row(m2,c) );
				}
			}
			return res;
		}
		
		//return empty matrix
		return new float[0][0];
	}
	
	/**
	 * perform a function on each element of the given matrix
	 */
	public static float[][] map(float[][] m, Function s)
	{
		int rows = m.length;
		int cols = m[0].length;
		float[][] res = new float[rows][cols];
		for (int y=0; y<rows; y++)
		{
			float[] r = res[y];
			float[] mm = m[y];
			
			for (int x=0; x<cols; x++)
			{
				r[x] = s.calculate(mm[x]);
			}
		}
		return res;
	}
	
	/**
	 * perform a multiplication on each element of the given matrix
	 */
	public static float[][] mul(float[][] m, float value)
	{
		int rows = m.length;
		int cols = m[0].length;
		float[][] res = new float[rows][cols];
		for (int y=0; y<rows; y++)
		{
			float[] r = res[y];
			float[] mm = m[y];
			
			for (int x=0; x<cols; x++)
			{
				r[x] = value * mm[x];
			}
		}
		return res;
	}
	
	/**
	 * transpose a matrix
	 */
	public static float[][] transpose(float[][] matrix)
	{
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		float[][] res = new float[cols][rows];
		
		for (int r=0; r<rows; r++)
		{
			float[] row = matrix[r];
			
			for (int c=0; c<cols; c++)
			{
				res[c][r] = row[c];
			}
		}
		
		return res;
	}
	
	/**
	 * copy a matrix
	 */
	public static float[][] copy(float[][] matrix) 
	{
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		float[][] res = new float[rows][cols];
		
		for (int r=0; r<rows; r++)
		{
			float[] row = matrix[r];
			
			for (int c=0; c<cols; c++)
			{
				res[r][c] = row[c];
			}
		}
		
		return res;
	}

	/**
	 * print a matrix into the console
	 */
	public static void print(float[][] matrix)
	{
		print(matrix, 5);
	}
	
	/**
	 * print a matrix into the console
	 */
	public static void print(float[][] matrix, int digits)
	{
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		System.out.println("printing matrix:");
		
		for (int r=0; r<rows; r++)
		{
			float[] row = matrix[r];
			
			String row_text = "";
			
			for (int c=0; c<cols; c++)
			{
				row_text += toString(row[c], digits);
			}

			System.out.println("|"+row_text+" |");
		}
	}

	// ========================================
	
	public static String toString(float v, int l)
	{
		return String.format("%" + l + "." + l + "s", Float.toString(v));
	}
	
	public static float dot(float[] a, float[] b)
	{
		float r=0f;
		for (int i=0,l=a.length; i<l; i++) 
			r += (a[i]*b[i]);
		return r;
	}
	
	public static float[] col(float[][] matrix, int i)
	{
		int l = matrix.length;
		float[] r = new float[l];
		for (int it=0;it<l;it++)
			r[it] = matrix[it][i];
		return r;
	}
	
	public static float[] row(float[][] matrix,int i)
	{
		return matrix[i];
	}
	
}
