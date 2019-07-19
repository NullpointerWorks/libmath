/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.matrix;

import com.nullpointerworks.math.Approximate;
import com.nullpointerworks.math.vector.Vector3;

/**
 * 
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
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
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
	 * mass multiply an array of float[3] arrays with the given matrix.
	 * useful for applying a matrix to many vertices in one pass
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
	 * multiply a float[3] array with the given matrix.
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
	 * creates a translation matrix
	 */
	public float[][] translate(float x,float y,float z)
	{
		return new float[][]{{1f,0f,x},
							 {0f,1f,y},
							 {0f,0f,z}};
	}
	
	public float[][] translate(float[] v)
	{
		return new float[][]{{1f,0f,v[0]},
							 {0f,1f,v[1]},
							 {0f,0f,v[2]}};
	}

	/**
	 * creates a scalar matrix
	 */
	public float[][] scale(float sx,float sy,float sz)
	{
		return new float[][]{{sx,0f,0f},
							 {0f,sy,0f},
							 {0f,0f,sz}};
	}
	
	public float[][] scale(float[] s)
	{
		return new float[][]{{s[0],0f,0f},
							 {0f,s[1],0f},
							 {0f,0f,s[2]}};
	}
	
	/**
	 * 
	 */
	public float[][] shear(float angle, boolean hor)
	{
		if (hor)
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
	
	/**
	 * creates a 2d screen-space correction matrix
	 */
	public float[][] correction(float width, float height)
	{
		float hw = width*0.5f;
		float hh = height*0.5f;
		float as = width / height;
		
		float[][] sscm = {{hw, 0f,hw-0.5f},
						  {0f,-hh,hh-0.5f},
						  {0f, 0f,     1f}};

		float[][] aspt = {{1f, 0f, 0f},
						  {0f, as, 0f},
						  {0f, 0f, 1f}};
		
		return mul(sscm,aspt);
	}
	
	/**
	 * Create a counter screen-space transformation matrix
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
	 * get the rotation matrix from the given rotations
	 */
	public float[][] rotation(float roll)
	{
		float cos = (float) Approximate.cos(roll);
		float sin = (float) Approximate.sin(roll);
		float[][] mRoll = new float[][]{{cos,-sin, 0f},
										{sin, cos, 0f},
										{ 0f,  0f, 1f}};
		return mRoll;
	}
}
