package com.nullpointerworks.math.matrix;

import com.nullpointerworks.math.FastMath;

public class Matrix3 
{
	/**
	 * creates a new null matrix
	 */
	public static float[][] zero()
	{
		return new float[][]{{0f,0f,0f},
							 {0f,0f,0f},
							 {0f,0f,0f}};
	}
	
	/**
	 * creates a new identity matrix
	 */
	public static float[][] identity()
	{
		return new float[][]{{1f,0f,0f},
							 {0f,1f,0f},
							 {0f,0f,1f}};
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
	 * get the rotation matrix from the given rotations
	 */
	public static float[][] rotateX(float yaw)
	{
		float cos = (float) FastMath.cos(yaw);
		float sin = (float) FastMath.sin(yaw);
		return new float[][]{{ 1f, 0f,  0f},
						 	 { 0f,cos,-sin},
						 	 { 0f,sin, cos}};
	}
	
	/**
	 * get the rotation matrix from the given rotations
	 */
	public static float[][] rotateY(float pitch)
	{
		float cos = (float) FastMath.cos(pitch);
		float sin = (float) FastMath.sin(pitch);
		return new float[][]{{ cos, 0f,sin},
							 {  0f, 1f, 0f},
							 {-sin, 0f,cos}};
	}
	
	/**
	 * get the rotation matrix from the given rotations
	 */
	public static float[][] rotateZ(float roll)
	{
		float cos = (float) FastMath.cos(roll);
		float sin = (float) FastMath.sin(roll);
		float[][] mRoll = new float[][]{{cos,-sin, 0f},
										{sin, cos, 0f},
										{ 0f,  0f, 1f}};
		return mRoll;
	}
	
	/**
	 * get the rotation matrix from the given rotations
	 */
	public static float[][] rotation(float roll, float pitch, float yaw)
	{
		float cos = (float) FastMath.cos(roll);
		float sin = (float) FastMath.sin(roll);
		float[][] mRoll = new float[][]{{cos,-sin, 0f},
										{sin, cos, 0f},
										{ 0f,  0f, 1f}};
		
		cos = (float) FastMath.cos(yaw);
		sin = (float) FastMath.sin(yaw);
		float[][] mPitch = new float[][]{{ cos, 0f,sin},
										 {  0f, 1f, 0f},
										 {-sin, 0f,cos}};
		
		cos = (float) FastMath.cos(pitch);
		sin = (float) FastMath.sin(pitch);
		float[][] mYaw = new float[][]{ { 1f, 0f,  0f},
									 	{ 0f,cos,-sin},
									 	{ 0f,sin, cos}};
		
		return mul(mYaw, mPitch, mRoll);
	}
	
	/**
	 * creates a 2d screen-space correction matrix
	 */
	public static float[][] screenSpace(float width, float height)
	{
		float hw = width*0.5f;
		float hh = height*0.5f;
		return new float[][]{{hw, 0f,hw-0.5f},
							 {0f,-hh,hh-0.5f},
							 {0f, 0f,     1f}};
	}
	
	// ================================================================
	
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
		matrix[i][2] = arr[2];
		return matrix;
	}
	
	/**
	 * get the column of a matrix by the given index
	 */
	public static float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i],
							matrix[1][i],
							matrix[2][i]};
	}
	
	/**
	 * load a float[] into a column of the given matrix
	 */
	public static float[][] setColumn(float[][] matrix, float[] arr, int i)
	{
		matrix[0][i] = arr[0];
		matrix[1][i] = arr[1];
		matrix[2][i] = arr[2];
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
		setColumn(tpMat, mat[2] ,2);
		return tpMat;
	}
	
	/**
	 * multiply a matrix with a factor
	 * returns a new matrix
	 */
	public static float[][] mul(float[][] m, float f)
	{
		float[][] matrix = zero();
		for (int y=0;y<3;y++)
		for (int x=0;x<3;x++)
			matrix[y][x] = m[y][x]*f;
		return matrix;
	}
	
	/**
	 * multiply two matrices with each other.
	 * returns the resulting matrix
	 */
	public static float[][] mul(float[][] m1, float[][] m2)
	{
		float[][] res = zero();
		for (int y=0;y<3;y++)
		{
			for (int x=0;x<3;x++)
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
		float det = 0f;
		for(int i=0;i<3;i++)
		{
	    	int i1 = (i+1)%3;
	    	int i2 = (i+2)%3;
	        det = det + ( m[0][i] * (m[1][i1] * m[2][i2] - m[1][i2] * m[2][i1]) );
		}
		return det;
	}
	
	/**
	 * returns the adjugate matrix.<br>
	 * the transpose of the cofactor matrix
	 */
	public static float[][] adjugate(float[][] a)
	{
	    float[][] r = zero();
	    for(int i=0,j;i<3;i++)
	    {
	    	int i1 = (i+1)%3;
	    	int i2 = (i+2)%3;
	        for(j=0;j<3;j++)
	        {
		    	int j1 = (j+1)%3;
		    	int j2 = (j+2)%3;
	        	float ad = (a[i1][j1]*a[i2][j2]) - (a[i1][j2]*a[i2][j1]);
	        	r[j][i] = ad;
	        }
	    }
	    return r;
	}
	
	/**
	 * returns the inverse matrix of the given matrix
	 */
	public static float[][] inverse(float[][] a)
	{
	    float inv_det = 1f / det(a);
	    float[][] r = adjugate(a);
	    return mul(r,inv_det);
	}
	
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
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
	}
	
	/**
	 * print a matrix into the console
	 */
	public static void print(float[][] matrix, int digits)
	{
		System.out.println("printing matrix:");
		float[] r1 = matrix[0];
		float[] r2 = matrix[1];
		float[] r3 = matrix[2];
		System.out.println("|"+ toString(r1[0], digits) +" "+ toString(r1[1], digits)+" "+ toString(r1[2], digits)+" |");
		System.out.println("|"+ toString(r2[0], digits) +" "+ toString(r2[1], digits)+" "+ toString(r2[2], digits)+" |");
		System.out.println("|"+ toString(r3[0], digits) +" "+ toString(r3[1], digits)+" "+ toString(r3[2], digits)+" |");
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
