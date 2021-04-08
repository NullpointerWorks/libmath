/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.matrix;

import com.nullpointerworks.math.Approximate;
import com.nullpointerworks.math.vector.Vector3;

/**
 * A 3 dimensional implementation of the {@code Matrix} interface. This class contains various 3-by-3 matrix operations.
 * @since 1.0.0
 */
public class Matrix3 implements Matrix 
{
	@Override
	public float[][] zero() 
	{
		return new float[][]{{0f,0f,0f},
							 {0f,0f,0f},
							 {0f,0f,0f}};
	}

	@Override
	public float[][] identity() 
	{
		return new float[][]{{1f,0f,0f},
							 {0f,1f,0f},
							 {0f,0f,1f}};
	}

	@Override
	public float[][] transpose(float[][] m) 
	{
		float[] r0 = {m[0][0],0f,0f};
		float[] r1 = {0f,m[1][1],0f};
		float[] r2 = {0f,0f,m[2][2]};
		
		r0[1] = m[1][0];
		r1[0] = m[0][1];
		
		r0[2] = m[2][0];
		r2[0] = m[0][2];
		
		r1[2] = m[2][1];
		r2[1] = m[1][2];
		return new float[][] {r0,r1,r2};
	}

	@Override
	public float[][] mul(float[][] m, float f) 
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

	@Override
	public float[][] mul(float[][] m1, float[][] m2) 
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
		
		res0[0] = V3.dot( row0 , col0 );
		res0[1] = V3.dot( row0 , col1 );
		res0[2] = V3.dot( row0 , col2 );
		
		res1[0] = V3.dot( row1 , col0 );
		res1[1] = V3.dot( row1 , col1 );
		res1[2] = V3.dot( row1 , col2 );
		
		res2[0] = V3.dot( row2 , col0 );
		res2[1] = V3.dot( row2 , col1 );
		res2[2] = V3.dot( row2 , col2 );
		return new float[][] {res0,res1,res2};
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
		float det = 0f;
        det = det + ( m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1]) );
        det = det + ( m[0][1] * (m[1][2] * m[2][0] - m[1][0] * m[2][2]) );
        det = det + ( m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]) );
		return det;
	}

	@Override
	public float[][] adjugate(float[][] m)
	{
		float[] r0 = {0f,0f,0f};
		float[] r1 = {0f,0f,0f};
		float[] r2 = {0f,0f,0f};
		float[] a1 = m[1];
		float[] a2 = m[2];
		float[] a0 = m[0];
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

	@Override
	public float[][] inverse(float[][] m) 
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
	
	/*
	 * get the column of a matrix by the given index
	 */
	private float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i],
							matrix[1][i],
							matrix[2][i]};
	}
	
	// ===================================================
	
	private final Vector3 V3 = new Vector3();
	
	/**
	 * Intended to be used for transforming a list of vertices {@code v} defined as an {@code float[n][3]} array.
	 * @param m - the transformation matrix
	 * @param v - the array of vertices
	 * @return a list of all transformed vertices
	 * @since 1.0.0
	 */
	public float[][] transform(float[][] m, float[][] v)
	{
		int l = v.length;
		float[][] res = new float[l][3];
		for (int i=0; i<l;i++)
		{
			res[i][0] = V3.dot(m[0], v[i]);
			res[i][1] = V3.dot(m[1], v[i]);
			res[i][2] = V3.dot(m[2], v[i]);
		}
		return res;
	}
	
	/**
	 * Intended to be used for transforming a vertex.
	 * @param m - the transformation matrix
	 * @param v - a vertex
	 * @return the transformed vertex
	 * @since 1.0.0
	 */
	public float[] transform(float[][] m, float[] v)
	{
		float[] res = new float[3];
		res[0] = V3.dot(m[0], v);
		res[1] = V3.dot(m[1], v);
		res[2] = V3.dot(m[2], v);
		return res;
	}

	/**
	 * Creates a translation matrix.
	 * @param x - the Cartesian x location
	 * @param y - the Cartesian y location
	 * @param z - the Cartesian z location
	 * @return a translation matrix
	 * @since 1.0.0
	 */
	public float[][] translate(float x,float y,float z)
	{
		return new float[][]{{1f,0f,x},
							 {0f,1f,y},
							 {0f,0f,z}};
	}

	/**
	 * Creates a translation matrix with the content of {@code float[3]} vector {@code v}.
	 * @param v - the vector with the Cartesian x, y and z location
	 * @return a translation matrix
	 * @since 1.0.0
	 */
	public float[][] translate(float[] v)
	{
		return new float[][]{{1f,0f,v[0]},
							 {0f,1f,v[1]},
							 {0f,0f,v[2]}};
	}

	/**
	 * Creates a scaling matrix.
	 * @param sx - the scale on x
	 * @param sy - the scale on y
	 * @param sz - the scale on z
	 * @return a scaling matrix
	 * @since 1.0.0
	 */
	public float[][] scale(float sx,float sy,float sz)
	{
		return new float[][]{{sx,0f,0f},
							 {0f,sy,0f},
							 {0f,0f,sz}};
	}

	/**
	 * Creates a scaling matrix with the content of {@code float[3]} vector {@code s}.
	 * @param s - the vector with the x, y and z scales
	 * @return a scaling matrix
	 * @since 1.0.0
	 */
	public float[][] scale(float[] s)
	{
		return new float[][]{{s[0],0f,0f},
							 {0f,s[1],0f},
							 {0f,0f,s[2]}};
	}
	
	/**
	 * Creates a 2D screen-space correction matrix.
	 * @param width - the width of the screen
	 * @param height - the height of the screen
	 * @return a 2D screen-space correction matrix
	 * @since 1.0.0
	 */
	public float[][] correction(float width, float height)
	{
		float hw = width*0.5f;
		float hh = height*0.5f;
		float[][] sscm = {{hw, 0f,hw-0.5f},
						  {0f,-hh,hh-0.5f},
						  {0f, 0f,     1f}};
		return sscm;
	}
	
	/**
	 * Create a counter screen-space transformation matrix. This method can be used to transform a mouse location and project it into world-space.
	 * @param width - the width of the screen
	 * @param height - the height of the screen
	 * @return a 2D counter screen-space correction matrix
	 * @since 1.0.0
	 */
	public float[][] counter(float width, float height)
	{
		float asp = width / height;
		float m00 = 2f / height;
		return new float[][]{{m00,  0f,-asp},
							 { 0f,-m00,  1f},
							 { 0f,  0f,  1f}};
	}
	
	/**
	 * Creates a rotation matrix from the given angle. This method uses the approximation library for computing the sine and cosine.
	 * @param angle - angle in radians
	 * @return a rotation matrix
	 * @since 1.0.0
	 * @see Approximate
	 */
	public float[][] rotation(float angle)
	{
		float cos = (float) Approximate.cos(angle);
		float sin = (float) Approximate.sin(angle);
		float[][] mRoll = new float[][]{{cos,-sin, 0f},
										{sin, cos, 0f},
										{ 0f,  0f, 1f}};
		return mRoll;
	}
	
	/**
	 * Creates a shearing matrix at the specified angle. This method uses the approximation library for computing the sine.
	 * @param angle - the angle to shear
	 * @param axes - {@code true} for horizontal, {@code false} for vertical
	 * @return a shearing matrix
	 * @since 1.0.0
	 * @see Approximate
	 */
	public float[][] shear(float angle, boolean axes)
	{
		if (axes) // horizontal
		{
			float tan = -1f * (float)(Math.tan(angle*0.5f));
			return new float[][]{{ 1f,-tan, 0f}, 
								 { 0f, 1f, 0f}, 
				 				 { 0f, 0f, 1f}};
		}
		float sin = (float) Approximate.sin(angle);
		return new float[][]{{ 1f, 0f, 0f}, 
							 {sin, 1f, 0f}, 
			 				 { 0f, 0f, 1f}};
	}
}
