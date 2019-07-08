package com.nullpointerworks.math.matrix;

public class Hadamard 
{
	
	// =====================================================
	
	/**
	 * add two matrices element-wise
	 */
	public static float[][] add(float[][] m1, float[][] m2)
	{
		int r = m1.length;
		int c = m1[0].length;
		
		float[][] res = new float[r][c];
		for (int y=0;y<r;y++)
		{
			for (int x=0;x<c;x++)
			{
				res[y][x] = m1[y][x] + m2[y][x];
			}
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
		
		float[][] res = new float[r][c];
		for (int y=0;y<r;y++)
		{
			for (int x=0;x<c;x++)
			{
				res[y][x] = m1[y][x] - m2[y][x];
			}
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
		
		float[][] res = new float[r][c];
		for (int y=0;y<r;y++)
		{
			for (int x=0;x<c;x++)
			{
				res[y][x] = m1[y][x] * m2[y][x];
			}
		}
		return res;
	}
}
