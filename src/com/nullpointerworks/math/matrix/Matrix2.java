package com.nullpointerworks.math.matrix;

import com.nullpointerworks.math.FastMath;

public class Matrix2 
{
	/**
	 * creates a new null matrix
	 */
	public static float[][] zero()
	{
		return new float[][]{{0f,0f},
							 {0f,0f}};
	}
	
	/**
	 * creates a new identity matrix
	 */
	public static float[][] identity()
	{
		return new float[][]{{1f,0f},
							 {0f,1f}};
	}
	
	/**
	 * creates a scalar matrix
	 */
	public static float[][] scale(float sx,float sy)
	{
		return new float[][]{{sx,0f},
							 {0f,sy}};
	}
	public static float[][] scale(float[] s)
	{
		return new float[][]{{s[0],0f},
							 {0f,s[1]}};
	}
	
	public static float[][] shear(float angle, boolean hor)
	{
		if (hor)
		{
			float tan = -1f * (float)(Math.tan(angle*0.5f));
			return new float[][]{{ 1f,-tan}, 
				 				 { 0f, 1f}};
		}
		float sin = (float) FastMath.sin(angle);
		return new float[][]{{ 1f, 0f}, 
							 {sin, 1f}};
	}
	
	/**
	 * get the rotation matrix from the given rotations
	 */
	public static float[][] rotation(float angle)
	{
		float cos = (float) FastMath.cos(angle);
		float sin = (float) FastMath.sin(angle);
		return new float[][]{{cos, -sin},
							 {sin, cos}};
	}
	
	/**
	 * get the row of a matrix by a given index.
	 * returns a float[]
	 */
	public static float[] row(float[][] matrix,int i)
	{
		return matrix[i];
	}

	/**
	 * load a float[] into a row of the given matrix
	 */
	public static float[][] setRow(float[][] matrix, float[] arr, int i)
	{
		matrix[i][0] = arr[0];
		matrix[i][1] = arr[1];
		return matrix;
	}
	
	/**
	 * get the column of a matrix by the given index
	 */
	public static float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i],
							matrix[1][i]};
	}
	
	/**
	 * load a float[] into a column of the given matrix
	 */
	public static float[][] setColumn(float[][] matrix, float[] arr, int i)
	{
		matrix[0][i] = arr[0];
		matrix[1][i] = arr[1];
		return matrix;
	}
	
	/**
	 * returns the transpose of the given matrix.
	 */
	public static float[][] transpose(float[][] mat)
	{
		float[][] tpMat = zero();
		setColumn(tpMat, mat[0] ,0);
		setColumn(tpMat, mat[1] ,1);
		return tpMat;
	}
	
	/**
	 * multiply a matrix with a factor
	 * returns a new matrix
	 */
	public static float[][] mul(float[][] m, float f)
	{
		float[][] matrix = new float[2][2];
		for (int y=0;y<2;y++)
		for (int x=0;x<2;x++)
			matrix[y][x] = m[y][x]*f;
		return matrix;
	}
	
	/**
	 * multiply two matrices with each other.
	 * returns the resulting matrix
	 */
	public static float[][] mul(float[][] m1, float[][] m2)
	{
		float[][] res = new float[2][2];
		for (int y=0;y<2;y++)
		{
			for (int x=0;x<2;x++)
			{
				float d = dot( m1[y] , column(m2,x) );
				res[y][x] = d;
			}
		}
		return res;
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
	public static float[][] inverse(float[][] a)
	{
	    float inv_det = 1f / det(a);
	    float[][] r = adjugate(a);
	    return Matrix2.mul(r,inv_det);
	}
	
	/**
	 * mass multiply an array of float[2] arrays with the given matrix.
	 * useful for applying a matrix to many vertices in one pass
	 */
	public static float[][] transform(float[][] m, float[][] v)
	{
		int l = v.length;
		float[][] res = new float[l][2];
		for (int i=0; i<l;i++)
		{
			res[i][0] = dot(m[0], v[i]);
			res[i][1] = dot(m[1], v[i]);
		}
		return res;
	}
	
	/**
	 * multiply a float[2] array with the given matrix.
	 */
	public static float[] transform(float[][] m, float[] v)
	{
		float[] res = new float[2];
		res[0] = dot(m[0], v);
		res[1] = dot(m[1], v);
		return res;
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
	 * print a matrix into the console
	 */
	public static void print(float[][] matrix, int digits)
	{
		System.out.println("printing matrix:");
		float[] r1 = matrix[0];
		float[] r2 = matrix[1];
		System.out.println("|"+ toString(r1[0], digits) +" "+ toString(r1[1], digits)+" |");
		System.out.println("|"+ toString(r2[0], digits) +" "+ toString(r2[1], digits)+" |");
	}
	
	/**
	 * print a matrix into the console
	 */
	public static void print(float[][] matrix)
	{
		print(matrix, 6);
	}
	private static String toString(float v, int l)
	{
		return String.format("%" + l + "." + l + "s", Float.toString(v));
	}
}
