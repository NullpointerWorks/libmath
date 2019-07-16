/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.matrix;

import com.nullpointerworks.math.FastMath;

public class Utility2D 
{
	/**
	 * mass multiply an array of float[3] arrays with the given matrix.
	 * useful for applying a matrix to many vertices in one pass
	 */
	public static float[][] transform(float[][] m, float[][] v)
	{
		int l = v.length;
		float[][] res = new float[l][3];
		for (int i=0; i<l;i++)
		{
			res[i][0] = dot(m[0], v[i]);
			res[i][1] = dot(m[1], v[i]);
			res[i][2] = dot(m[2], v[i]);
		}
		return res;
	}
	
	/**
	 * multiply a float[3] array with the given matrix.
	 */
	public static float[] transform(float[][] m, float[] v)
	{
		float[] res = new float[3];
		res[0] = dot(m[0], v);
		res[1] = dot(m[1], v);
		res[2] = dot(m[2], v);
		return res;
	}

	/**
	 * creates a translation matrix
	 */
	public static float[][] translate(float x,float y,float z)
	{
		return new float[][]{{1f,0f,x},
							 {0f,1f,y},
							 {0f,0f,z}};
	}
	public static float[][] translate(float[] v)
	{
		return new float[][]{{1f,0f,v[0]},
							 {0f,1f,v[1]},
							 {0f,0f,v[2]}};
	}

	/**
	 * creates a scalar matrix
	 */
	public static float[][] scale(float sx,float sy,float sz)
	{
		return new float[][]{{sx,0f,0f},
							 {0f,sy,0f},
							 {0f,0f,sz}};
	}
	public static float[][] scale(float[] s)
	{
		return new float[][]{{s[0],0f,0f},
							 {0f,s[1],0f},
							 {0f,0f,s[2]}};
	}
	
	/**
	 * 
	 */
	public static float[][] shear(float angle, boolean hor)
	{
		if (hor)
		{
			float tan = -1f * (float)(Math.tan(angle*0.5f));
			return new float[][]{{ 1f,-tan, 0f}, 
								 { 0f, 1f, 0f}, 
				 				 { 0f, 0f, 1f}};
		}
		float sin = (float) FastMath.sin(angle);
		return new float[][]{{ 1f, 0f, 0f}, 
							 {sin, 1f, 0f}, 
			 				 { 0f, 0f, 1f}};
	}
	
	/**
	 * creates a 2d screen-space correction matrix
	 */
	public static float[][] correction(float width, float height)
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
		
		return Matrix3.mul(sscm,aspt);
	}
	
	/**
	 * Create a counter screen-space transformation matrix
	 */
	public static float[][] counter(float width, float height)
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
	public static float[][] rotation(float roll)
	{
		float cos = (float) FastMath.cos(roll);
		float sin = (float) FastMath.sin(roll);
		float[][] mRoll = new float[][]{{cos,-sin, 0f},
										{sin, cos, 0f},
										{ 0f,  0f, 1f}};
		return mRoll;
	}
	
	/**
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
	}
}
