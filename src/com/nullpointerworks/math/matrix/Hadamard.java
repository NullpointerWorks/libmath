package com.nullpointerworks.math.matrix;

public class Hadamard 
{
	/**
	 * add two matrices element-wise
	 */
	public static float[][] add(float[][] m1, float[][] m2)
	{
		int r = m1.length;
		int c = m1[0].length;
		float[][] res = new float[r][];
		for (int y=0;y<r;y++)
		{
			float[] m1y = m1[y];
			float[] m2y = m2[y];
			float[] rsy = new float[c];
			for (int x=0;x<c;x++)
			{
				rsy[x] = m1y[x] + m2y[x];
			}
			res[y] = rsy;
		}
		return res;
	}
	
	/**
	 * add two matrices element-wise
	 */
	public static float[][] sub(float[][] m1, float[][] m2)
	{
		int r = m1.length;
		int c = m1[0].length;
		float[][] res = new float[r][];
		for (int y=0;y<r;y++)
		{
			float[] m1y = m1[y];
			float[] m2y = m2[y];
			float[] rsy = new float[c];
			for (int x=0;x<c;x++)
			{
				rsy[x] = m1y[x] - m2y[x];
			}
			res[y] = rsy;
		}
		return res;
	}
	
	/**
	 * multiply two matrices element-wise
	 */
	public static float[][] mul(float[][] m1, float[][] m2)
	{
		int r = m1.length;
		int c = m1[0].length;
		float[][] res = new float[r][];
		for (int y=0;y<r;y++)
		{
			float[] m1y = m1[y];
			float[] m2y = m2[y];
			float[] rsy = new float[c];
			for (int x=0;x<c;x++)
			{
				rsy[x] = m1y[x] * m2y[x];
			}
			res[y] = rsy;
		}
		return res;
	}
}
