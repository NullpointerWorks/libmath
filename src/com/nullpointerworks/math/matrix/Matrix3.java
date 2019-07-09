package com.nullpointerworks.math.matrix;

/**
 * Provides mathematical operations for 3x3 matrices. 
 * @author Michiel Drost - nullpointerworks
 */
public class Matrix3 
{
	/**
	 * Creates a 3-by-3 matrix zero matrix.
	 * @return a 3-by-3 matrix filled with zeros
	 */
	public static float[][] zero()
	{
		return new float[][]{{0f,0f,0f},
							 {0f,0f,0f},
							 {0f,0f,0f}};
	}
	
	/**
	 * Creates a 3-by-3 identity matrix.
	 * @return a 3-by-3 identity matrix
	 */
	public static float[][] identity()
	{
		return new float[][]{{1f,0f,0f},
							 {0f,1f,0f},
							 {0f,0f,1f}};
	}
	
	/**
	 * returns the transpose of the given matrix.
	 */
	public static float[][] transpose(float[][] mat)
	{
		float[] r0 = {mat[0][0],0f,0f};
		float[] r1 = {0f,mat[1][1],0f};
		float[] r2 = {0f,0f,mat[2][2]};
		
		r0[1] = mat[1][0];
		r1[0] = mat[0][1];
		
		r0[2] = mat[2][0];
		r2[0] = mat[0][2];
		
		r1[2] = mat[2][1];
		r2[1] = mat[1][2];
		return new float[][] {r0,r1,r2};
	}
	
	/**
	 * multiply a matrix with a factor
	 * returns a new matrix
	 */
	public static float[][] mul(float[][] m, float f)
	{
		float[] r0 = {0f,0f,0f};
		float[] r1 = {0f,0f,0f};
		float[] r2 = {0f,0f,0f};
		
		r0[0] = m[0][0]*f;
		r0[1] = m[0][1]*f;
		r0[2] = m[0][2]*f;

		r1[0] = m[1][0]*f;
		r1[1] = m[1][1]*f;
		r1[2] = m[1][2]*f;

		r2[0] = m[2][0]*f;
		r2[1] = m[2][1]*f;
		r2[2] = m[2][2]*f;
		return new float[][] {r0,r1,r2};
	}
	
	/**
	 * multiply two matrices with each other.
	 * returns the resulting matrix
	 */
	public static float[][] mul(float[][] m1, float[][] m2)
	{
		float[] col0 = column(m2,0);
		float[] col1 = column(m2,1);
		float[] col2 = column(m2,2);

		float[] row0 = m1[0];
		float[] row1 = m1[1];
		float[] row2 = m1[2];
		
		float[] res0 = {0f,0f,0f};
		float[] res1 = {0f,0f,0f};
		float[] res2 = {0f,0f,0f};
		
		res0[0] = dot( row0 , col0 );
		res0[1] = dot( row0 , col1 );
		res0[2] = dot( row0 , col2 );
		
		res1[0] = dot( row1 , col0 );
		res1[1] = dot( row1 , col1 );
		res1[2] = dot( row1 , col2 );
		
		res2[0] = dot( row2 , col0 );
		res2[1] = dot( row2 , col1 );
		res2[2] = dot( row2 , col2 );
		return new float[][] {res0,res1,res2};
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
		float det = 0f;
        det = det + ( m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1]) );
        det = det + ( m[0][1] * (m[1][2] * m[2][0] - m[1][0] * m[2][2]) );
        det = det + ( m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]) );
		return det;
	}
	
	/**
	 * returns the adjugate matrix.<br>
	 * the transpose of the cofactor matrix
	 */
	public static float[][] adjugate(float[][] a)
	{
		float[] r0 = {0f,0f,0f};
		float[] r1 = {0f,0f,0f};
		float[] r2 = {0f,0f,0f};
		float[] a1 = a[1];
		float[] a2 = a[2];
		float[] a0 = a[0];
		r0[0] = (a1[1]*a2[2]) - (a1[1]*a2[2]);
		r1[0] = (a1[2]*a2[0]) - (a1[2]*a2[0]);
		r2[0] = (a1[0]*a2[0]) - (a1[1]*a2[1]);
		r0[1] = (a2[1]*a0[2]) - (a2[1]*a0[2]);
		r1[1] = (a2[2]*a0[0]) - (a2[2]*a0[0]);
		r2[1] = (a2[0]*a0[0]) - (a2[1]*a0[1]);
		r0[2] = (a0[1]*a1[2]) - (a0[1]*a1[2]);
		r1[2] = (a0[2]*a1[0]) - (a0[2]*a1[0]);
		r2[2] = (a0[0]*a1[0]) - (a0[1]*a1[1]);
		return new float[][] {r0,r1,r2};
	}
	
	/**
	 * returns the inverse matrix of the given matrix
	 */
	public static float[][] inverse(float[][] m)
	{
		float det = 0f;
        det = det + ( m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1]) );
        det = det + ( m[0][1] * (m[1][2] * m[2][0] - m[1][0] * m[2][2]) );
        det = det + ( m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]) );
        det = 1f / det;
        float[] r0 = {0f,0f,0f};
		float[] r1 = {0f,0f,0f};
		float[] r2 = {0f,0f,0f};
		float[] a1 = m[1];
		float[] a2 = m[2];
		float[] a0 = m[0];
		r0[0] = ((a1[1]*a2[2]) - (a1[1]*a2[2]))*det;
		r1[0] = ((a1[2]*a2[0]) - (a1[2]*a2[0]))*det;
		r2[0] = ((a1[0]*a2[0]) - (a1[1]*a2[1]))*det;
		r0[1] = ((a2[1]*a0[2]) - (a2[1]*a0[2]))*det;
		r1[1] = ((a2[2]*a0[0]) - (a2[2]*a0[0]))*det;
		r2[1] = ((a2[0]*a0[0]) - (a2[1]*a0[1]))*det;
		r0[2] = ((a0[1]*a1[2]) - (a0[1]*a1[2]))*det;
		r1[2] = ((a0[2]*a1[0]) - (a0[2]*a1[0]))*det;
		r2[2] = ((a0[0]*a1[0]) - (a0[1]*a1[1]))*det;
		return new float[][] {r0,r1,r2};
	}
	
	/**
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
	}
	
	/**
	 * get the column of a matrix by the given index
	 */
	private static float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i],
							matrix[1][i],
							matrix[2][i]};
	}
}
