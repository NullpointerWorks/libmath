/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.matrix;

import static com.nullpointerworks.math.FloatMath.RADIAN;

import com.nullpointerworks.math.Approximate;
import com.nullpointerworks.math.Quaternion;
import com.nullpointerworks.math.vector.Vector3;
import com.nullpointerworks.math.vector.Vector4;
import com.nullpointerworks.math.vector.VectorN;

/**
 * A 4 dimensional implementation of the {@code Matrix} interface. This class contains various 4-by-4 matrix operations including a set of special functions to help generate 3D world transformations.
 * @since 1.0.0
 */
public class Matrix4 implements Matrix 
{
	@Override
	public float[][] zero() 
	{
		return new float[][]{{0f,0f,0f,0f},
			 {0f,0f,0f,0f},
			 {0f,0f,0f,0f},
			 {0f,0f,0f,0f}};
	}

	@Override
	public float[][] identity() 
	{
		return new float[][]{{1f,0f,0f,0f},
							 {0f,1f,0f,0f},
							 {0f,0f,1f,0f},
							 {0f,0f,0f,1f}};
	}

	@Override
	public float[][] transpose(float[][] m) 
	{
		float[] r0 = {m[0][0],0f,0f,0f};
		float[] r1 = {0f,m[1][1],0f,0f};
		float[] r2 = {0f,0f,m[2][2],0f};
		float[] r3 = {0f,0f,0f,m[3][3]};
		
		r0[1] = m[1][0];
		r1[0] = m[0][1];
		
		r0[2] = m[2][0];
		r2[0] = m[0][2];
		
		r0[3] = m[3][0];
		r3[0] = m[0][3];
		
		r1[2] = m[2][1];
		r2[1] = m[1][2];
		
		r1[3] = m[3][1];
		r3[1] = m[1][3];
		
		r2[3] = m[3][2];
		r3[2] = m[2][3];
		return new float[][] {r0,r1,r2,r3};
	}

	@Override
	public float[][] mul(float[][] m, float f) 
	{
		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = m[0][0]*f;
		r0[1] = m[0][1]*f;
		r0[2] = m[0][2]*f;
		r0[3] = m[0][3]*f;

		r1[0] = m[1][0]*f;
		r1[1] = m[1][1]*f;
		r1[2] = m[1][2]*f;
		r1[3] = m[1][3]*f;

		r2[0] = m[2][0]*f;
		r2[1] = m[2][1]*f;
		r2[2] = m[2][2]*f;
		r2[3] = m[2][3]*f;

		r3[0] = m[3][0]*f;
		r3[1] = m[3][1]*f;
		r3[2] = m[3][2]*f;
		r3[3] = m[3][3]*f;
		return new float[][] {r0,r1,r2,r3};
	}

	@Override
	public float[][] mul(float[][] m1, float[][] m2) 
	{
		float[] col0 = column(m2,0);
		float[] col1 = column(m2,1);
		float[] col2 = column(m2,2);
		float[] col3 = column(m2,3);

		float[] row0 = m1[0];
		float[] row1 = m1[1];
		float[] row2 = m1[2];
		float[] row3 = m1[3];

		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = dot( row0 , col0 );
		r0[1] = dot( row0 , col1 );
		r0[2] = dot( row0 , col2 );
		r0[3] = dot( row0 , col3 );
		
		r1[0] = dot( row1 , col0 );
		r1[1] = dot( row1 , col1 );
		r1[2] = dot( row1 , col2 );
		r1[3] = dot( row1 , col3 );
		
		r2[0] = dot( row2 , col0 );
		r2[1] = dot( row2 , col1 );
		r2[2] = dot( row2 , col2 );
		r2[3] = dot( row2 , col3 );
		
		r3[0] = dot( row3 , col0 );
		r3[1] = dot( row3 , col1 );
		r3[2] = dot( row3 , col2 );
		r3[3] = dot( row3 , col3 );
		return new float[][] {r0,r1,r2,r3};
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
		float[] m0 = m[0];
		float[] m1 = m[1];
		float[] m2 = m[2];
		float[] m3 = m[3];
		
		float s0 = m0[0] * m1[1] - m1[0] * m0[1];
		float s1 = m0[0] * m1[2] - m1[0] * m0[2];
		float s2 = m0[0] * m1[3] - m1[0] * m0[3];
		float s3 = m0[1] * m1[2] - m1[1] * m0[2];
		float s4 = m0[1] * m1[3] - m1[1] * m0[2];
		float s5 = m0[2] * m1[3] - m1[2] * m0[3];

		float c5 = m2[2] * m3[3] - m3[2] * m2[3];
		float c4 = m2[1] * m3[3] - m3[1] * m2[3];
		float c3 = m2[1] * m3[2] - m3[1] * m2[2];
		float c2 = m2[0] * m3[3] - m3[0] * m2[3];
		float c1 = m2[0] * m3[2] - m3[0] * m2[2];
		float c0 = m2[0] * m3[1] - m3[0] * m2[1];
		return (s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);
	}

	@Override
	public float[][] adjugate(float[][] m) 
	{
		float[] m0 = m[0];
		float[] m1 = m[1];
		float[] m2 = m[2];
		float[] m3 = m[3];
		
		float s0 = m0[0] * m1[1] - m1[0] * m0[1];
		float s1 = m0[0] * m1[2] - m1[0] * m0[2];
		float s2 = m0[0] * m1[3] - m1[0] * m0[3];
		float s3 = m0[1] * m1[2] - m1[1] * m0[2];
		float s4 = m0[1] * m1[3] - m1[1] * m0[2];
		float s5 = m0[2] * m1[3] - m1[2] * m0[3];

		float c5 = m2[2] * m3[3] - m3[2] * m2[3];
		float c4 = m2[1] * m3[3] - m3[1] * m2[3];
		float c3 = m2[1] * m3[2] - m3[1] * m2[2];
		float c2 = m2[0] * m3[3] - m3[0] * m2[3];
		float c1 = m2[0] * m3[2] - m3[0] * m2[2];
		float c0 = m2[0] * m3[1] - m3[0] * m2[1];
		
		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = ( m1[1]*c5 - m1[2]*c4 + m1[3]*c3);
		r0[1] = (-m0[1]*c5 + m0[2]*c4 - m0[3]*c3);
		r0[2] = ( m3[1]*s5 - m3[2]*s4 + m3[3]*s3);
		r0[3] = (-m2[1]*s5 + m2[2]*s4 - m2[3]*s3);

		r1[0] = (-m1[0]*c5 + m1[2]*c2 - m1[3]*c1);
		r1[1] = ( m0[0]*c5 - m0[2]*c2 + m0[3]*c1);
		r1[2] = (-m3[0]*s5 + m3[2]*s2 - m3[3]*s1);
		r1[3] = ( m2[0]*s5 - m2[2]*s2 + m2[3]*s1);
		
		r2[0] = ( m1[0]*c4 - m1[1]*c2 + m1[3]*c0);
		r2[1] = (-m0[0]*c4 + m0[1]*c2 - m0[3]*c0);
		r2[2] = ( m3[0]*s4 - m3[1]*s2 + m3[3]*s0);
		r2[3] = (-m2[0]*s4 + m2[1]*s2 - m2[3]*s0);
		
		r3[0] = (-m1[0]*c3 + m1[1]*c1 - m1[2]*c0);
		r3[1] = ( m0[0]*c3 - m0[1]*c1 + m0[2]*c0);
		r3[2] = (-m3[0]*s3 + m3[1]*s1 - m3[2]*s0);
		r3[3] = ( m2[0]*s3 - m2[1]*s1 + m2[2]*s0);
		
		return new float[][] {r0,r1,r2,r3};
	}

	@Override
	public float[][] inverse(float[][] m) 
	{
		float[] m0 = m[0];
		float[] m1 = m[1];
		float[] m2 = m[2];
		float[] m3 = m[3];
		
		float s0 = m0[0] * m1[1] - m1[0] * m0[1];
		float s1 = m0[0] * m1[2] - m1[0] * m0[2];
		float s2 = m0[0] * m1[3] - m1[0] * m0[3];
		float s3 = m0[1] * m1[2] - m1[1] * m0[2];
		float s4 = m0[1] * m1[3] - m1[1] * m0[2];
		float s5 = m0[2] * m1[3] - m1[2] * m0[3];

		float c5 = m2[2] * m3[3] - m3[2] * m2[3];
		float c4 = m2[1] * m3[3] - m3[1] * m2[3];
		float c3 = m2[1] * m3[2] - m3[1] * m2[2];
		float c2 = m2[0] * m3[3] - m3[0] * m2[3];
		float c1 = m2[0] * m3[2] - m3[0] * m2[2];
		float c0 = m2[0] * m3[1] - m3[0] * m2[1];
		float inv_det = 1f / (s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0);
		
		float[] r0 = {0f,0f,0f,0f};
		float[] r1 = {0f,0f,0f,0f};
		float[] r2 = {0f,0f,0f,0f};
		float[] r3 = {0f,0f,0f,0f};
		
		r0[0] = ( m1[1]*c5 - m1[2]*c4 + m1[3]*c3) * inv_det;
		r0[1] = (-m0[1]*c5 + m0[2]*c4 - m0[3]*c3) * inv_det;
		r0[2] = ( m3[1]*s5 - m3[2]*s4 + m3[3]*s3) * inv_det;
		r0[3] = (-m2[1]*s5 + m2[2]*s4 - m2[3]*s3) * inv_det;

		r1[0] = (-m1[0]*c5 + m1[2]*c2 - m1[3]*c1) * inv_det;
		r1[1] = ( m0[0]*c5 - m0[2]*c2 + m0[3]*c1) * inv_det;
		r1[2] = (-m3[0]*s5 + m3[2]*s2 - m3[3]*s1) * inv_det;
		r1[3] = ( m2[0]*s5 - m2[2]*s2 + m2[3]*s1) * inv_det;
		
		r2[0] = ( m1[0]*c4 - m1[1]*c2 + m1[3]*c0) * inv_det;
		r2[1] = (-m0[0]*c4 + m0[1]*c2 - m0[3]*c0) * inv_det;
		r2[2] = ( m3[0]*s4 - m3[1]*s2 + m3[3]*s0) * inv_det;
		r2[3] = (-m2[0]*s4 + m2[1]*s2 - m2[3]*s0) * inv_det;
		
		r3[0] = (-m1[0]*c3 + m1[1]*c1 - m1[2]*c0) * inv_det;
		r3[1] = ( m0[0]*c3 - m0[1]*c1 + m0[2]*c0) * inv_det;
		r3[2] = (-m3[0]*s3 + m3[1]*s1 - m3[2]*s0) * inv_det;
		r3[3] = ( m2[0]*s3 - m2[1]*s1 + m2[2]*s0) * inv_det;
		
		return new float[][] {r0,r1,r2,r3};
	}
	
	/*
	 * returns a dot product of the given float[] arrays of any length.
	 * do not use with vectors
	 */
	private float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2] + a[3]*b[3];
	}
	
	/*
	 * get the column of a matrix by the given index
	 */
	private float[] column(float[][] matrix, int i)
	{
		return new float[] {matrix[0][i],
							matrix[1][i],
							matrix[2][i],
							matrix[3][i]};
	}

	// =========================================================
	
	private final Vector3 V3 = new Vector3();
	private final Vector4 V4 = new Vector4();
	
	/**
	 * Intended to be used for transforming a list of vertices {@code v} defined as an {@code float[n][4]} array.
	 * @param m - the transformation matrix
	 * @param v - the array of vertices
	 * @return a list of all transformed vertices
	 * @since 1.0.0
	 */
	public float[][] transform(float[][] m, float[][] v)
	{
		int l = v.length;
		float[][] res = new float[l][4];
		for (int i=0; i<l;i++)
		{
			res[i][0] = V4.dot(m[0], v[i]);
			res[i][1] = V4.dot(m[1], v[i]);
			res[i][2] = V4.dot(m[2], v[i]);
			res[i][3] = V4.dot(m[3], v[i]);
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
		float[] res = new float[4];
		res[0] = V4.dot(m[0], v);
		res[1] = V4.dot(m[1], v);
		res[2] = V4.dot(m[2], v);
		res[3] = V4.dot(m[3], v);
		return res;
	}
	
	/**
	 * Creates a translation matrix with space to transform into w.
	 * @param x - the Cartesian x location
	 * @param y - the Cartesian y location
	 * @param z - the Cartesian z location
	 * @return a translation matrix
	 * @since 1.0.0
	 */
	public float[][] translate(float x,float y,float z)
	{
		return new float[][]{{1f,0f,0f,x},
							 {0f,1f,0f,y},
							 {0f,0f,1f,z},
							 {0f,0f,0f,1f}};
	}
	
	/**
	 * Creates a translation matrix with the content of {@code float[3]} vector {@code v}. The fourth dimension is left to {@code 1.0} to account for depth transformations. 
	 * @param v - the vector with the Cartesian x, y and z location
	 * @return a translation matrix
	 * @since 1.0.0
	 */
	public float[][] translate(float[] v)
	{
		return new float[][]{{1f,0f,0f,v[0]},
							 {0f,1f,0f,v[1]},
							 {0f,0f,1f,v[2]},
							 {0f,0f,0f,1f}};
	}

	/**
	 * Creates a scaling matrix. The fourth dimension is left to {@code 1.0} to account for depth transformations. 
	 * @param sx - the scale on x
	 * @param sy - the scale on y
	 * @param sz - the scale on z
	 * @return a scaling matrix
	 * @since 1.0.0
	 */
	public float[][] scale(float sx,float sy,float sz)
	{
		return new float[][]{{sx,0f,0f,0f},
							 {0f,sy,0f,0f},
							 {0f,0f,sz,0f},
							 {0f,0f,0f,1f}};
	}

	/**
	 * Creates a scaling matrix with the content of {@code float[3]} vector {@code s}. The fourth dimension is left to {@code 1.0} to account for depth transformations.
	 * @param s - the vector with the x, y and z scales
	 * @return a scaling matrix
	 * @since 1.0.0
	 */
	public float[][] scale(float[] s)
	{
		return new float[][]{{s[0],0f,0f,0f},
							 {0f,s[1],0f,0f},
							 {0f,0f,s[2],0f},
							 {0f,0f,0f,1f}};
	}
	
	/**
	 * Creates a 3D screen-space correction matrix.
	 * @param width - the width of the screen
	 * @param height - the height of the screen
	 * @return a 2D screen-space correction matrix
	 * @since 1.0.0
	 */
	public float[][] correction(float width, float height)
	{
		float hw = width*0.5f;
		float hh = height*0.5f;
		float[][] sscm = {{hw, 0f, 0f, hw-0.5f},
						  {0f,-hh, 0f, hh-0.5f},
						  {0f, 0f, 1f, 0f},
						  {0f, 0f, 0f, 1f}};
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
		return new float[][]{{m00,  0f, 0f,-asp},
							 { 0f,-m00, 0f, 1f},
							 { 0f,  0f, 1f, 0f},
							 { 0f,  0f, 0f, 1f}};
	}
	
	/**
	 * Creates a rotation matrix computed with quaternions.
	 * @param axes - an axes defines by a {@code float[3]} vector
	 * @param angle - the angle in radians to rotate
	 * @return a rotation matrix
	 * @since 1.0.0
	 */
	public float[][] rotation(float[] axes, float angle)
	{
		float[] v3 = V3.normalize(axes);
		float[] dt = Quaternion.rotation(v3, angle);
		return Quaternion.matrix(dt);
	}
	
	/**
	 * Creates a rotation matrix by passing the angles for each axes. This method applies the Euler's method of combining three rotation matrices which makes it vulnerable to Gimbal lock. This method uses the approximation library for fast computation of sine and cosine.
	 * @param roll - rotation around the z axes, angle in radians
	 * @param pitch - rotation around the y axes, angle in radians
	 * @param yaw - rotation around the x axes, angle in radians
	 * @return a rotation matrix
	 * @since 1.0.0
	 * @see Approximate
	 */
	public float[][] rotation(float roll, float pitch, float yaw)
	{
		float cos = (float) Approximate.cos(roll);
		float sin = (float) Approximate.sin(roll);
		
		float[][] mRoll = new float[][]{{cos,-sin, 0f, 0f},
										{sin, cos, 0f, 0f},
										{ 0f,  0f, 1f, 0f},
										{ 0f,  0f, 0f, 1f}};
		
		cos = (float) Approximate.cos(yaw);
		sin = (float) Approximate.sin(yaw);
		float[][] mPitch = new float[][]{{ cos, 0f,sin, 0f},
										 {  0f, 1f, 0f, 0f},
										 {-sin, 0f,cos, 0f},
										 {  0f, 0f, 0f, 1f}};
		
		cos = (float) Approximate.cos(pitch);
		sin = (float) Approximate.sin(pitch);
		float[][] mYaw = new float[][]{ { 1f, 0f,  0f, 0f},
									 	{ 0f,cos,-sin, 0f},
									 	{ 0f,sin, cos, 0f},
									 	{ 0f, 0f,  0f, 1f}};
		
		return mul(mYaw, mPitch, mRoll);
	}
	
	/**
	 * Creates a "look-at" view matrix used for model- to world-space transformations. This matrix is primarily used for camera translation and rotation.
	 * @param eye - the location vertex of the camera
	 * @param target - the location vertex to look at
	 * @param up - the upwards camera vector
	 * @return a look-at matrix
	 * @since 1.0.0
	 */
	public float[][] lookAt(float[] eye, float[] target, float[] up)
	{
		float[] Z = V3.sub(eye, target);
		Z = V3.normalize(Z);
		
		float[] X = V3.cross(up, Z);
		float[] Y = V3.cross(Z, X);
		
		X = V3.normalize(X);
		Y = V3.normalize(Y);
		
		float Xd = -V3.dot(X, eye);
		float Yd = -V3.dot(Y, eye);
		float Zd = -V3.dot(Z, eye);
		
		X = VectorN.append(X, Xd);
		Y = VectorN.append(Y, Yd);
		Z = VectorN.append(Z, Zd);
		
		return new float[][]{X,Y,Z,{0f,0f,0f,1f}};
	}
	
	/**
	 * Creates a perspective transformation matrix which transforms vertices into homogeneous coordinates. 
	 * @param fov - field of view in degrees,
	 * @param aspect - the aspect ratio of the screen
	 * @param near - the distance to the frustum's near plane
	 * @param far - the distance to the frustum's far plane
	 * @return a perspective transformation matrix
	 * @since 1.0.0
	 */
	public float[][] perspective(float fov, float aspect, float near, float far)
	{
		float t = (float)Math.tan((double)(RADIAN * fov * 0.5f));
        float n11 = 1f / (t * aspect);
        float n22 = 1f / (t);
        float n33 = (far + near) / (far - near);
        float n34 = (- 2.0f * far * near) / (far - near);
        return new float[][] {	{n11,  0f,  0f,  0f}, 
					        	{ 0f, n22,  0f,  0f}, 
					        	{ 0f,  0f,-n33, n34}, 
					        	{ 0f,  0f, -1f,  0f}};
	}
	
	/**
	 * Creates an orthographic transformation matrix which transforms vertices into homogeneous coordinates. 
	 * @param fov - field of view in degrees,
	 * @param aspect - the aspect ratio of the screen
	 * @param near - the distance to the frustum's near plane
	 * @param far - the distance to the frustum's far plane
	 * @return an orthographic transformation matrix
	 * @since 1.0.0
	 */
	public float[][] orthographic(float fov, float aspect, float near, float far) 
	{
        float t = near * (float)Math.tan((double)(RADIAN * fov * 0.5f));
        float r = t * aspect;
        float s = 0.7f;
        float n11 = s / (r+r);
        float n22 = s / (t+t);
        float n33 = (-s) / (far - near);
        float n34 = far * near * n33;
        return new float[][]{	{n11,  0f,  0f,  0f}, 
					        	{ 0f, n22,  0f,  0f}, 
					        	{ 0f,  0f, n33, n34}, 
					        	{ 0f,  0f,  0f,  1f}};
    }
	
	/**
	 * Creates an orthographic lightbox transformation matrix. Primarily used for rendering a scene at low resolution to generate a depth map for global lighting effects in 3D simulations.
	 * @param width - the width of the box
	 * @param height - the height of the box
	 * @param length - the length of the box
	 * @return an orthographic lightbox transformation matrix
	 * @since 1.0.0
	 */
	public float[][] lightbox_orthographic(float width, float height, float length)
	{
		float n11 = 2f / width;
		float n22 = 2f / height;
		float n33 = 2f / length;
	    return new float[][]{{n11, 0f,  0f, 0f},
							 { 0f,n22,  0f, 0f},
							 { 0f, 0f,-n33, 0f},
							 { 0f, 0f,  0f, 1f}};
	}
}
