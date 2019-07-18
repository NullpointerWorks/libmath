/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.utility;

import com.nullpointerworks.math.Approximate;
import com.nullpointerworks.math.matrix.Matrix3;
import com.nullpointerworks.math.vector.Vector3;

public class Utility2D implements Utility
{
	private static final Matrix3 M3 = new Matrix3();
	private static final Vector3 V3 = new Vector3();
	
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
		
		return M3.mul(sscm,aspt);
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

	// ===================================================
	
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
