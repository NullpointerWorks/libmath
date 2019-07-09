package com.nullpointerworks.math.matrix;

/**
 * Provides mathematical operations for 4x4 matrices. 
 * @author Michiel Drost - nullpointerworks
 */
public class Matrix4 
{
	/**
	 * creates a new null matrix
	 */
	public static float[][] zero()
	{
		return new float[][]{{0f,0f,0f,0f},
							 {0f,0f,0f,0f},
							 {0f,0f,0f,0f},
							 {0f,0f,0f,0f}};
	}
	
	/**
	 * creates a new identity matrix
	 */
	public static float[][] identity()
	{
		return new float[][]{{1f,0f,0f,0f},
							 {0f,1f,0f,0f},
							 {0f,0f,1f,0f},
							 {0f,0f,0f,1f}};
	}
	
	/**
	 * Transpose the given 4-by-4 matrix and returns it in a new matrix.
	 * @return the transpose of the given 4-by-4 matrix
	 */
	public static float[][] transpose(float[][] mat)
	{
		float[] r0 = {mat[0][0],0f,0f,0f};
		float[] r1 = {0f,mat[1][1],0f,0f};
		float[] r2 = {0f,0f,mat[2][2],0f};
		float[] r3 = {0f,0f,0f,mat[3][3]};
		
		r0[1] = mat[1][0];
		r1[0] = mat[0][1];
		
		r0[2] = mat[2][0];
		r2[0] = mat[0][2];
		
		r0[3] = mat[3][0];
		r3[0] = mat[0][3];
		
		r1[2] = mat[2][1];
		r2[1] = mat[1][2];
		
		r1[3] = mat[3][1];
		r3[1] = mat[1][3];
		
		r2[3] = mat[3][2];
		r3[2] = mat[2][3];
		return new float[][] {r0,r1,r2,r3};
	}
	
	/**
	 * multiply a matrix with a factor
	 * returns a new matrix
	 */
	public static float[][] mul(float[][] m, float f)
	{
		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = m[0][0]*f;
		r0[1] = m[0][1]*f;
		r0[2] = m[0][2]*f;
		r0[3] = m[0][3]*f;

		r1[0] = m[1][0]*f;
		r1[1] = m[1][1]*f;
		r1[2] = m[1][2]*f;
		r1[3] = m[1][3]*f;

		r2[0] = m[2][0]*f;
		r2[1] = m[2][1]*f;
		r2[2] = m[2][2]*f;
		r2[3] = m[2][3]*f;

		r3[0] = m[3][0]*f;
		r3[1] = m[3][1]*f;
		r3[2] = m[3][2]*f;
		r3[3] = m[3][3]*f;
		return new float[][] {r0,r1,r2,r3};
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
		float[] col3 = column(m2,3);

		float[] row0 = m1[0];
		float[] row1 = m1[1];
		float[] row2 = m1[2];
		float[] row3 = m1[3];

		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = dot( row0 , col0 );
		r0[1] = dot( row0 , col1 );
		r0[2] = dot( row0 , col2 );
		r0[3] = dot( row0 , col3 );
		
		r1[0] = dot( row1 , col0 );
		r1[1] = dot( row1 , col1 );
		r1[2] = dot( row1 , col2 );
		r1[3] = dot( row1 , col3 );
		
		r2[0] = dot( row2 , col0 );
		r2[1] = dot( row2 , col1 );
		r2[2] = dot( row2 , col2 );
		r2[3] = dot( row2 , col3 );
		
		r3[0] = dot( row3 , col0 );
		r3[1] = dot( row3 , col1 );
		r3[2] = dot( row3 , col2 );
		r3[3] = dot( row3 , col3 );
		return new float[][] {r0,r1,r2,r3};
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
		float[] m0 = m[0];
		float[] m1 = m[1];
		float[] m2 = m[2];
		float[] m3 = m[3];
		
		float s0 = m0[0] * m1[1] - m1[0] * m0[1];
		float s1 = m0[0] * m1[2] - m1[0] * m0[2];
		float s2 = m0[0] * m1[3] - m1[0] * m0[3];
		float s3 = m0[1] * m1[2] - m1[1] * m0[2];
		float s4 = m0[1] * m1[3] - m1[1] * m0[2];
		float s5 = m0[2] * m1[3] - m1[2] * m0[3];

		float c5 = m2[2] * m3[3] - m3[2] * m2[3];
		float c4 = m2[1] * m3[3] - m3[1] * m2[3];
		float c3 = m2[1] * m3[2] - m3[1] * m2[2];
		float c2 = m2[0] * m3[3] - m3[0] * m2[3];
		float c1 = m2[0] * m3[2] - m3[0] * m2[2];
		float c0 = m2[0] * m3[1] - m3[0] * m2[1];
		return (s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);
	}

	/**
	 * returns the adjugate matrix.<br>
	 * the transpose of the cofactor matrix
	 */
	public static float[][] adjugate(float[][] m)
	{
		float[] m0 = m[0];
		float[] m1 = m[1];
		float[] m2 = m[2];
		float[] m3 = m[3];
		
		float s0 = m0[0] * m1[1] - m1[0] * m0[1];
		float s1 = m0[0] * m1[2] - m1[0] * m0[2];
		float s2 = m0[0] * m1[3] - m1[0] * m0[3];
		float s3 = m0[1] * m1[2] - m1[1] * m0[2];
		float s4 = m0[1] * m1[3] - m1[1] * m0[2];
		float s5 = m0[2] * m1[3] - m1[2] * m0[3];

		float c5 = m2[2] * m3[3] - m3[2] * m2[3];
		float c4 = m2[1] * m3[3] - m3[1] * m2[3];
		float c3 = m2[1] * m3[2] - m3[1] * m2[2];
		float c2 = m2[0] * m3[3] - m3[0] * m2[3];
		float c1 = m2[0] * m3[2] - m3[0] * m2[2];
		float c0 = m2[0] * m3[1] - m3[0] * m2[1];
		
		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = ( m1[1]*c5 - m1[2]*c4 + m1[3]*c3);
		r0[1] = (-m0[1]*c5 + m0[2]*c4 - m0[3]*c3);
		r0[2] = ( m3[1]*s5 - m3[2]*s4 + m3[3]*s3);
		r0[3] = (-m2[1]*s5 + m2[2]*s4 - m2[3]*s3);

		r1[0] = (-m1[0]*c5 + m1[2]*c2 - m1[3]*c1);
		r1[1] = ( m0[0]*c5 - m0[2]*c2 + m0[3]*c1);
		r1[2] = (-m3[0]*s5 + m3[2]*s2 - m3[3]*s1);
		r1[3] = ( m2[0]*s5 - m2[2]*s2 + m2[3]*s1);
		
		r2[0] = ( m1[0]*c4 - m1[1]*c2 + m1[3]*c0);
		r2[1] = (-m0[0]*c4 + m0[1]*c2 - m0[3]*c0);
		r2[2] = ( m3[0]*s4 - m3[1]*s2 + m3[3]*s0);
		r2[3] = (-m2[0]*s4 + m2[1]*s2 - m2[3]*s0);
		
		r3[0] = (-m1[0]*c3 + m1[1]*c1 - m1[2]*c0);
		r3[1] = ( m0[0]*c3 - m0[1]*c1 + m0[2]*c0);
		r3[2] = (-m3[0]*s3 + m3[1]*s1 - m3[2]*s0);
		r3[3] = ( m2[0]*s3 - m2[1]*s1 + m2[2]*s0);
		
		return new float[][] {r0,r1,r2,r3};
	}

	/**
	 * returns the inverse matrix of the given matrix
	 */
	public static float[][] inverse(float[][] m)
	{
		float[] m0 = m[0];
		float[] m1 = m[1];
		float[] m2 = m[2];
		float[] m3 = m[3];
		
		float s0 = m0[0] * m1[1] - m1[0] * m0[1];
		float s1 = m0[0] * m1[2] - m1[0] * m0[2];
		float s2 = m0[0] * m1[3] - m1[0] * m0[3];
		float s3 = m0[1] * m1[2] - m1[1] * m0[2];
		float s4 = m0[1] * m1[3] - m1[1] * m0[2];
		float s5 = m0[2] * m1[3] - m1[2] * m0[3];

		float c5 = m2[2] * m3[3] - m3[2] * m2[3];
		float c4 = m2[1] * m3[3] - m3[1] * m2[3];
		float c3 = m2[1] * m3[2] - m3[1] * m2[2];
		float c2 = m2[0] * m3[3] - m3[0] * m2[3];
		float c1 = m2[0] * m3[2] - m3[0] * m2[2];
		float c0 = m2[0] * m3[1] - m3[0] * m2[1];
		float inv_det = 1f / (s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);
		
		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = ( m1[1]*c5 - m1[2]*c4 + m1[3]*c3) * inv_det;
		r0[1] = (-m0[1]*c5 + m0[2]*c4 - m0[3]*c3) * inv_det;
		r0[2] = ( m3[1]*s5 - m3[2]*s4 + m3[3]*s3) * inv_det;
		r0[3] = (-m2[1]*s5 + m2[2]*s4 - m2[3]*s3) * inv_det;

		r1[0] = (-m1[0]*c5 + m1[2]*c2 - m1[3]*c1) * inv_det;
		r1[1] = ( m0[0]*c5 - m0[2]*c2 + m0[3]*c1) * inv_det;
		r1[2] = (-m3[0]*s5 + m3[2]*s2 - m3[3]*s1) * inv_det;
		r1[3] = ( m2[0]*s5 - m2[2]*s2 + m2[3]*s1) * inv_det;
		
		r2[0] = ( m1[0]*c4 - m1[1]*c2 + m1[3]*c0) * inv_det;
		r2[1] = (-m0[0]*c4 + m0[1]*c2 - m0[3]*c0) * inv_det;
		r2[2] = ( m3[0]*s4 - m3[1]*s2 + m3[3]*s0) * inv_det;
		r2[3] = (-m2[0]*s4 + m2[1]*s2 - m2[3]*s0) * inv_det;
		
		r3[0] = (-m1[0]*c3 + m1[1]*c1 - m1[2]*c0) * inv_det;
		r3[1] = ( m0[0]*c3 - m0[1]*c1 + m0[2]*c0) * inv_det;
		r3[2] = (-m3[0]*s3 + m3[1]*s1 - m3[2]*s0) * inv_det;
		r3[3] = ( m2[0]*s3 - m2[1]*s1 + m2[2]*s0) * inv_det;
		
		return new float[][] {r0,r1,r2,r3};
	}
	
	/**
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2] + a[3]*b[3];
	}
	
	/**
	 * get the column of a matrix by the given index
	 */
	private static float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i],
							matrix[1][i],
							matrix[2][i],
							matrix[3][i]};
	}
}
