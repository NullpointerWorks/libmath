/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.matrix;

public class Matrix2 
{
	/**
	 * Creates a new zero matrix.
	 * @return a new zero matrix
	 * @since 1.0.0
	 */
	public static float[][] zero()
	{
		return new float[][]{{0f,0f}, {0f,0f}};
	}
	
	/**
	 * creates a new identity matrix
	 */
	public static float[][] identity()
	{
		return new float[][]{{1f,0f}, {0f,1f}};
	}
	
	/**
	 * returns the transpose of the given matrix.
	 */
	public static float[][] transpose(float[][] mat)
	{
		float[] r0 = {mat[0][0],mat[1][0]};
		float[] r1 = {mat[0][1],mat[1][1]};
		return new float[][] {r0,r1};
	}
	
	/**
	 * multiply a matrix with a factor
	 * returns a new matrix
	 */
	public static float[][] mul(float[][] m, float f)
	{
		float[] r0 = {0f,0f};
		float[] r1 = {0f,0f};
		r0[0] = m[0][0]*f;
		r0[1] = m[0][1]*f;
		r1[0] = m[1][0]*f;
		r1[1] = m[1][1]*f;
		return new float[][] {r0,r1};
	}
	
	/**
	 * multiply two matrices with each other.
	 * returns the resulting matrix
	 */
	public static float[][] mul(float[][] m1, float[][] m2)
	{
		float[] col0 = column(m2,0);
		float[] col1 = column(m2,1);
		float[] row0 = m1[0];
		float[] row1 = m1[1];
		float[] res0 = {0f,0f};
		float[] res1 = {0f,0f};
		res0[0] = dot( row0 , col0 );
		res0[1] = dot( row0 , col1 );
		res1[0] = dot( row1 , col0 );
		res1[1] = dot( row1 , col1 );
		return new float[][] {res0, res1};
	}
	
	/**
	 * mass multiply a list of matrices with each other in the given order
	 */
	public static float[][] mul(float[][]... m)
	{
		float[][] res = identity();
		for (float[][] matrix : m)
		{
			res = mul(res,matrix);
		}
		return res;
	}
	
	/**
	 * returns the determinant of the given matrix
	 */
	public static float det(float[][] m)
	{
		float a,b,c,d;
		a = m[0][0];
		b = m[0][1];
		c = m[1][0];
		d = m[1][1];
		return (a*d)-(b*c);
	}

	/**
	 * returns the adjugate matrix.<br>
	 * the transpose of the cofactor matrix
	 */
	public static float[][] adjugate(float[][] m)
	{
		float a,b,c,d;
		a = m[0][0];
		b = m[0][1];
		c = m[1][0];
		d = m[1][1];
		return new float[][]{{d,-b},
							 {-c,a}};
	}

	/**
	 * returns the inverse matrix of the given matrix
	 */
	public static float[][] inverse(float[][] m)
	{
		float a,b,c,d;
		a = m[0][0];
		b = m[0][1];
		c = m[1][0];
		d = m[1][1];
		float inv_det = 1f / ((a*d)-(b*c));
		
		float[][] r = new float[][]{{d*inv_det, -b*inv_det},
									{-c*inv_det, a*inv_det}};
		return r;
	}
	
	/**
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1];
	}
	
	/**
	 * get the column of a matrix by the given index
	 */
	private static float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i], matrix[1][i]};
	}
	
}
