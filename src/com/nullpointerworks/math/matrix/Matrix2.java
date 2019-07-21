/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.matrix;

/**
 * A 2 dimensional implementation of the {@code Matrix} interface. This class contains various 2-by-2 matrix operations.
 * @since 1.0.0
 */
public class Matrix2 implements Matrix 
{
	@Override
	public float[][] zero()
	{
		return new float[][]{{0f,0f}, {0f,0f}};
	}
	
	@Override
	public float[][] identity()
	{
		return new float[][]{{1f,0f}, {0f,1f}};
	}
	
	@Override
	public float[][] transpose(float[][] m)
	{
		float[] r0 = {m[0][0],m[1][0]};
		float[] r1 = {m[0][1],m[1][1]};
		return new float[][] {r0,r1};
	}
	
	@Override
	public float[][] mul(float[][] m, float f)
	{
		float[] r0 = {0f,0f};
		float[] r1 = {0f,0f};
		r0[0] = m[0][0]*f;
		r0[1] = m[0][1]*f;
		r1[0] = m[1][0]*f;
		r1[1] = m[1][1]*f;
		return new float[][] {r0,r1};
	}
	
	@Override
	public float[][] mul(float[][] m1, float[][] m2)
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
	
	@Override
	public float[][] mul(float[][]... m)
	{
		float[][] res = identity();
		for (float[][] matrix : m)
		{
			res = mul(res,matrix);
		}
		return res;
	}
	
	@Override
	public float det(float[][] m)
	{
		float a,b,c,d;
		a = m[0][0];
		b = m[0][1];
		c = m[1][0];
		d = m[1][1];
		return (a*d)-(b*c);
	}

	@Override
	public float[][] adjugate(float[][] m)
	{
		float a,b,c,d;
		a = m[0][0];
		b = m[0][1];
		c = m[1][0];
		d = m[1][1];
		return new float[][]{{d,-b},
							 {-c,a}};
	}
	
	@Override
	public float[][] inverse(float[][] m)
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
	
	/*
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1];
	}
	
	/*
	 * get the column of a matrix by the given index
	 */
	private float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i], matrix[1][i]};
	}
	
	/**
	 * Gauss's shoelace area formula. Calculates the area of a counter-clockwise winded, irregular, non-intersecting polygon. The polygon must be defined by an array of {@code float[n][2]} vertices.
	 * @param polygon - the array that defines the polygon
	 * @returns the area of the polygon
	 * @since 1.0.0
	 */
	public float area(float[][] polygon)
	{
		float area = 0f;
		int leng = polygon.length - 1;
		for (int i=0; i<leng; i++)
		{
			float[] v = polygon[i];
			float[] n = polygon[i+1];
			float x_hand = v[0] * n[1];
			float y_hand = n[0] * v[1];
			area += (x_hand - y_hand);
		}
		return area * 0.5f;
	}
}
