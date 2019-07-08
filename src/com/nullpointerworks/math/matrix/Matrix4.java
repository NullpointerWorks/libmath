package com.nullpointerworks.math.matrix;

import com.nullpointerworks.math.FastMath;
import com.nullpointerworks.math.vector.Vector;
import com.nullpointerworks.math.vector.Vector3;

/**
 * matrix mathematics for 4x4 matrices. <br>
 * matrix math is is non-commutative, so mind the multiplication order<br>
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
	 * creates a translation matrix
	 */
	public static float[][] translate(float x,float y,float z)
	{
		return new float[][]{{1f,0f,0f,x},
							 {0f,1f,0f,y},
							 {0f,0f,1f,z},
							 {0f,0f,0f,1f}};
	}
	public static float[][] translate(float[] v)
	{
		return new float[][]{{1f,0f,0f,v[0]},
							 {0f,1f,0f,v[1]},
							 {0f,0f,1f,v[2]},
							 {0f,0f,0f,1f}};
	}

	/**
	 * creates a scalar matrix
	 */
	public static float[][] scale(float sx,float sy,float sz)
	{
		return new float[][]{{sx,0f,0f,0f},
							 {0f,sy,0f,0f},
							 {0f,0f,sz,0f},
							 {0f,0f,0f,1f}};
	}
	public static float[][] scale(float[] s)
	{
		return new float[][]{{s[0],0f,0f,0f},
							 {0f,s[1],0f,0f},
							 {0f,0f,s[2],0f},
							 {0f,0f,0f,1f}};
	}

	/**
	 * creates a look-at transform matrix
	 */
	public static float[][] lookAt(float[] eye, float[] target, float[] up)
	{
		float[] Z = Vector3.sub(eye, target);
		Z = Vector3.normalize(Z);
		
		float[] X = Vector3.cross(up, Z);
		float[] Y = Vector3.cross(Z, X);
		
		X = Vector3.normalize(X);
		Y = Vector3.normalize(Y);
		
		float Xd = -Vector3.dot(X, eye);
		float Yd = -Vector3.dot(Y, eye);
		float Zd = -Vector3.dot(Z, eye);
		
		X = Vector.merge(X, Xd);
		Y = Vector.merge(Y, Yd);
		Z = Vector.merge(Z, Zd);
		
		return new float[][]{X, Y, Z,
							 {0f,0f,0f,1f}};
	}
	
	/**
	 * creates a TGN tangent-space matrix to transform normal map vectors<br>
	 * provide the tangent, bitangent and normal vectors of the plane in question
	 */
	public static float[][] tangentSpace(float[] tan, float[] bitan, float[] norm)
	{
		float[][] tanm = zero();
		setColumn(tanm, tan,   0);
		setColumn(tanm, bitan, 1);
		setColumn(tanm, norm,  2);
		return tanm;
	}
	
	/**
	 * creates a screen-space correction matrix
	 */
	public static float[][] screenSpace(float width, float height)
	{
		float hw = width*0.5f;
		float hh = height*0.5f;
		return new float[][]{{hw, 0f,0f,hw-0.5f},
							 {0f,-hh,0f,hh-0.5f},
							 {0f, 0f,1f,0f},
							 {0f, 0f,0f,1f}};
	}
	
	/**
	 * create a counter screen-space transformation matrix
	 */
	public static float[][] counterSpace(float width, float height, float aspect)
	{
		float m00 = (2f*aspect)/width;
		float m03 = -1f*aspect;
		float m11 = -2f/(float)height;
		float m13 = 1f;
		
		return new float[][]{{m00, 0f, 0f,m03},
							 { 0f,m11, 0f,m13},
							 { 0f, 0f, 1f, 0f},
							 { 0f, 0f, 0f, 1f}};
	}
	
	/**
	 * creates a perspective homogeneous transform matrix
	 */
	public static float[][] perspective(float fov, float aspect, float near, float far)
	{
		float t = near * (float)Math.tan(radian * fov * 0.5f);
		float b = -t;
		float r = t*aspect;
		float l = -r;
		
		float n11 = 2*near / (r-l);
		float n13 = (r+l) / (r-l);
		float n22 = 2*near / (t-b);
		float n23 = (t+b) / (t-b);
		float n33 = (far+near) / (far-near);
		float n34 = -(2*far*near) / (far-near);
		
	    return new float[][]{{n11, 0f, n13, 0f},
							 { 0f,n22, n23, 0f},
							 { 0f, 0f,-n33,n34},
							 { 0f, 0f, -1f, 0f}};
	}
	
	/**
	 * creates an orthographic transform matrix
	 */
	public static float[][] orthographic(float fov, float aspect, float near, float far)
	{
		float t = near * (float)Math.tan(radian * fov * 0.5f);
		float b = -t;
		float r = t*aspect;
		float l = -r;
		float s = 0.7f;
		float fd = 1f / (far-near);
		
		float n11 = s / (r-l);
		float n14 = -(r+l) / (r-l);
		float n22 = s / (t-b);
		float n24 = -(t+b) / (t-b);
		float n33 = -s*fd;
		float n34 = far*near*n33;
		
	    return new float[][]{{n11, 0f,  0f, n14},
							 { 0f,n22,  0f, n24},
							 { 0f, 0f, n33, n34},
							 { 0f, 0f,  0f,  1f}};
	}
	
	/**
	 * creates an orthographic transform matrix for lightbox transformation
	 */
	public static float[][] orthographic(float width, float height, float length)
	{
		float n11 = 2f / width;
		float n22 = 2f / height;
		float n33 = -2f / length;
		
	    return new float[][]{{n11, 0f, 0f, 0f},
							 { 0f,n22, 0f, 0f},
							 { 0f, 0f,n33, 0f},
							 { 0f, 0f, 0f, 1f}};
	}
	
	/**
	 * get the rotation matrix from the given rotations
	 */
	public static float[][] rotation(float roll, float pitch, float yaw)
	{
		float cos = (float) FastMath.cos(roll);
		float sin = (float) FastMath.sin(roll);
		
		float[][] mRoll = new float[][]{{cos,-sin, 0f, 0f},
										{sin, cos, 0f, 0f},
										{ 0f,  0f, 1f, 0f},
										{ 0f,  0f, 0f, 1f}};
		
		cos = (float) FastMath.cos(yaw);
		sin = (float) FastMath.sin(yaw);
		float[][] mPitch = new float[][]{{ cos, 0f,sin, 0f},
										 {  0f, 1f, 0f, 0f},
										 {-sin, 0f,cos, 0f},
										 {  0f, 0f, 0f, 1f}};
		
		cos = (float) FastMath.cos(pitch);
		sin = (float) FastMath.sin(pitch);
		float[][] mYaw = new float[][]{ { 1f, 0f,  0f, 0f},
									 	{ 0f,cos,-sin, 0f},
									 	{ 0f,sin, cos, 0f},
									 	{ 0f, 0f,  0f, 1f}};
		
		return mul(mYaw, mPitch, mRoll);
	}
	
	// =============================================================
	
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
		matrix[i][3] = arr[3];
		return matrix;
	}
	
	/**
	 * get the column of a matrix by the given index
	 */
	public static float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i],
							matrix[1][i],
							matrix[2][i],
							matrix[3][i]};
	}
	
	/**
	 * load a float[] into a column of the given matrix
	 */
	public static float[][] setColumn(float[][] matrix, float[] arr, int i)
	{
		matrix[0][i] = arr[0];
		matrix[1][i] = arr[1];
		matrix[2][i] = arr[2];
		matrix[3][i] = arr[3];
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
		setColumn(tpMat, mat[3] ,3);
		return tpMat;
	}
	
	/**
	 * multiply a matrix with a factor
	 * returns a new matrix
	 */
	public static float[][] mul(float[][] m, float f)
	{
		float[][] matrix = new float[4][4];
		for (int y=0;y<4;y++)
		for (int x=0;x<4;x++)
			matrix[y][x] = m[y][x]*f;
		return matrix;
	}
	
	/**
	 * multiply two matrices with each other.
	 * returns the resulting matrix
	 */
	public static float[][] mul(float[][] m1, float[][] m2)
	{
		float[][] res = new float[4][4];
		for (int y=0;y<4;y++)
		{
			for (int x=0;x<4;x++)
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
		float s0 = m[0][0] * m[1][1] - m[1][0] * m[0][1];
		float s1 = m[0][0] * m[1][2] - m[1][0] * m[0][2];
		float s2 = m[0][0] * m[1][3] - m[1][0] * m[0][3];
		float s3 = m[0][1] * m[1][2] - m[1][1] * m[0][2];
		float s4 = m[0][1] * m[1][3] - m[1][1] * m[0][2];
		float s5 = m[0][2] * m[1][3] - m[1][2] * m[0][3];

		float c5 = m[2][2] * m[3][3] - m[3][2] * m[2][3];
		float c4 = m[2][1] * m[3][3] - m[3][1] * m[2][3];
		float c3 = m[2][1] * m[3][2] - m[3][1] * m[2][2];
		float c2 = m[2][0] * m[3][3] - m[3][0] * m[2][3];
		float c1 = m[2][0] * m[3][2] - m[3][0] * m[2][2];
		float c0 = m[2][0] * m[3][1] - m[3][0] * m[2][1];
		return (s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);
	}

	/**
	 * returns the adjugate matrix.<br>
	 * the transpose of the cofactor matrix
	 */
	public static float[][] adjugate(float[][] m)
	{
		float s0 = m[0][0] * m[1][1] - m[1][0] * m[0][1];
		float s1 = m[0][0] * m[1][2] - m[1][0] * m[0][2];
		float s2 = m[0][0] * m[1][3] - m[1][0] * m[0][3];
		float s3 = m[0][1] * m[1][2] - m[1][1] * m[0][2];
		float s4 = m[0][1] * m[1][3] - m[1][1] * m[0][3];
		float s5 = m[0][2] * m[1][3] - m[1][2] * m[0][3];
		
		float c5 = m[2][2] * m[3][3] - m[3][2] * m[2][3];
		float c4 = m[2][1] * m[3][3] - m[3][1] * m[2][3];
		float c3 = m[2][1] * m[3][2] - m[3][1] * m[2][2];
		float c2 = m[2][0] * m[3][3] - m[3][0] * m[2][3];
		float c1 = m[2][0] * m[3][2] - m[3][0] * m[2][2];
		float c0 = m[2][0] * m[3][1] - m[3][0] * m[2][1];
		
		float[][] r = zero();
		r[0][0] = ( m[1][1]*c5 - m[1][2]*c4 + m[1][3]*c3);
		r[0][1] = (-m[0][1]*c5 + m[0][2]*c4 - m[0][3]*c3);
		r[0][2] = ( m[3][1]*s5 - m[3][2]*s4 + m[3][3]*s3);
		r[0][3] = (-m[2][1]*s5 + m[2][2]*s4 - m[2][3]*s3);

		r[1][0] = (-m[1][0]*c5 + m[1][2]*c2 - m[1][3]*c1);
		r[1][1] = ( m[0][0]*c5 - m[0][2]*c2 + m[0][3]*c1);
		r[1][2] = (-m[3][0]*s5 + m[3][2]*s2 - m[3][3]*s1);
		r[1][3] = ( m[2][0]*s5 - m[2][2]*s2 + m[2][3]*s1);
		
		r[2][0] = ( m[1][0]*c4 - m[1][1]*c2 + m[1][3]*c0);
		r[2][1] = (-m[0][0]*c4 + m[0][1]*c2 - m[0][3]*c0);
		r[2][2] = ( m[3][0]*s4 - m[3][1]*s2 + m[3][3]*s0);
		r[2][3] = (-m[2][0]*s4 + m[2][1]*s2 - m[2][3]*s0);
		
		r[3][0] = (-m[1][0]*c3 + m[1][1]*c1 - m[1][2]*c0);
		r[3][1] = ( m[0][0]*c3 - m[0][1]*c1 + m[0][2]*c0);
		r[3][2] = (-m[3][0]*s3 + m[3][1]*s1 - m[3][2]*s0);
		r[3][3] = ( m[2][0]*s3 - m[2][1]*s1 + m[2][2]*s0);
		return r;
	}

	/**
	 * returns the inverse matrix of the given matrix
	 */
	public static float[][] inverse(float[][] m)
	{
		float s0 = m[0][0] * m[1][1] - m[1][0] * m[0][1];
		float s1 = m[0][0] * m[1][2] - m[1][0] * m[0][2];
		float s2 = m[0][0] * m[1][3] - m[1][0] * m[0][3];
		float s3 = m[0][1] * m[1][2] - m[1][1] * m[0][2];
		float s4 = m[0][1] * m[1][3] - m[1][1] * m[0][3];
		float s5 = m[0][2] * m[1][3] - m[1][2] * m[0][3];
		
		float c5 = m[2][2] * m[3][3] - m[3][2] * m[2][3];
		float c4 = m[2][1] * m[3][3] - m[3][1] * m[2][3];
		float c3 = m[2][1] * m[3][2] - m[3][1] * m[2][2];
		float c2 = m[2][0] * m[3][3] - m[3][0] * m[2][3];
		float c1 = m[2][0] * m[3][2] - m[3][0] * m[2][2];
		float c0 = m[2][0] * m[3][1] - m[3][0] * m[2][1];
		float inv_det = 1f / (s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);
		
		float[][] r = zero();
		r[0][0] = ( m[1][1]*c5 - m[1][2]*c4 + m[1][3]*c3) * inv_det;
		r[0][1] = (-m[0][1]*c5 + m[0][2]*c4 - m[0][3]*c3) * inv_det;
		r[0][2] = ( m[3][1]*s5 - m[3][2]*s4 + m[3][3]*s3) * inv_det;
		r[0][3] = (-m[2][1]*s5 + m[2][2]*s4 - m[2][3]*s3) * inv_det;

		r[1][0] = (-m[1][0]*c5 + m[1][2]*c2 - m[1][3]*c1) * inv_det;
		r[1][1] = ( m[0][0]*c5 - m[0][2]*c2 + m[0][3]*c1) * inv_det;
		r[1][2] = (-m[3][0]*s5 + m[3][2]*s2 - m[3][3]*s1) * inv_det;
		r[1][3] = ( m[2][0]*s5 - m[2][2]*s2 + m[2][3]*s1) * inv_det;
		
		r[2][0] = ( m[1][0]*c4 - m[1][1]*c2 + m[1][3]*c0) * inv_det;
		r[2][1] = (-m[0][0]*c4 + m[0][1]*c2 - m[0][3]*c0) * inv_det;
		r[2][2] = ( m[3][0]*s4 - m[3][1]*s2 + m[3][3]*s0) * inv_det;
		r[2][3] = (-m[2][0]*s4 + m[2][1]*s2 - m[2][3]*s0) * inv_det;
		
		r[3][0] = (-m[1][0]*c3 + m[1][1]*c1 - m[1][2]*c0) * inv_det;
		r[3][1] = ( m[0][0]*c3 - m[0][1]*c1 + m[0][2]*c0) * inv_det;
		r[3][2] = (-m[3][0]*s3 + m[3][1]*s1 - m[3][2]*s0) * inv_det;
		r[3][3] = ( m[2][0]*s3 - m[2][1]*s1 + m[2][2]*s0) * inv_det;
		return r;
	}
	
	/**
	 * mass multiply an array of float[4] arrays with the given matrix.
	 * useful for applying a matrix to many vertices in one pass
	 */
	public static float[][] transform(float[][] m, float[][] v)
	{
		int l = v.length;
		float[][] res = new float[l][4];
		for (int i=0; i<l;i++)
		{
			res[i][0] = dot(m[0], v[i]);
			res[i][1] = dot(m[1], v[i]);
			res[i][2] = dot(m[2], v[i]);
			res[i][3] = dot(m[3], v[i]);
		}
		return res;
	}
	
	/**
	 * multiply a float[4] array with the given matrix.
	 */
	public static float[] transform(float[][] m, float[] v)
	{
		float[] res = new float[4];
		res[0] = dot(m[0], v);
		res[1] = dot(m[1], v);
		res[2] = dot(m[2], v);
		res[3] = dot(m[3], v);
		return res;
	}
	
	// =============================================================
	
	/**
	 * print a matrix into the console
	 */
	public static void print(float[][] matrix, int digits)
	{
		System.out.println("printing matrix:");
		float[] r1 = matrix[0];
		float[] r2 = matrix[1];
		float[] r3 = matrix[2];
		float[] r4 = matrix[3];
		System.out.println("|"+ toString(r1[0], digits) +" "+ toString(r1[1], digits)+" "+ toString(r1[2], digits)+" "+ toString(r1[3], digits) +" |");
		System.out.println("|"+ toString(r2[0], digits) +" "+ toString(r2[1], digits)+" "+ toString(r2[2], digits)+" "+ toString(r2[3], digits) +" |");
		System.out.println("|"+ toString(r3[0], digits) +" "+ toString(r3[1], digits)+" "+ toString(r3[2], digits)+" "+ toString(r3[3], digits) +" |");
		System.out.println("|"+ toString(r4[0], digits) +" "+ toString(r4[1], digits)+" "+ toString(r4[2], digits)+" "+ toString(r4[3], digits) +" |");
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
	
	/**
	 * the radian constant. useful for rotations and such
	 */
	private static final float radian = (float) (Math.PI/180d);
	
	/**
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2] + a[3]*b[3];
	}
}
