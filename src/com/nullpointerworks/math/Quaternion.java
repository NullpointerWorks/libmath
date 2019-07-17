/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

import com.nullpointerworks.math.vector.Vector3;
import com.nullpointerworks.math.vector.Vector4;

/**
 * Provides static quaternion mathematics operations. Quaternions are a number system that extends complex numbers. All operations provide a new {@code float[4]} object and do not modify the content of the input. Quaternions are represented in the format described below.
 * <pre>
 * i^2 = j^2 = k^2 = ijk = -1
 * 
 * q = r + bi + cj + dk
 * q = float[4] {real, i, j, k}</pre>
 * @since 1.0.0
 */
public class Quaternion 
{
	/**
	 * Returns a new quaternion.
	 * @param r - the real component
	 * @param i - the i unit
	 * @param j - the j unit
	 * @param k - the k unit
	 * @return a new quaternion
	 * @since 1.0.0
	 */
	public static float[] New(float r, float i, float j, float k)
	{
		return new float[] {r,i,j,k};
	}
	
	/**
	 * Returns the quaternion identity.
	 * @return the quaternion identity
	 * @since 1.0.0
	 */
	public static float[] identity()
	{
		return new float[] {1f,0f,0f,0f};
	}
	
	/**
	 * Returns the quaternionic i identity.
	 * @return the quaternionic i identity
	 * @since 1.0.0
	 */
	public static float[] identity_i()
	{
		return new float[] {0f,1f,0f,0f};
	}
	
	/**
	 * Returns the quaternionic j identity.
	 * @return the quaternionic j identity
	 * @since 1.0.0
	 */
	public static float[] identity_j()
	{
		return new float[] {0f,0f,1f,0f};
	}
	
	/**
	 * Returns the quaternionic k identity.
	 * @return the quaternionic k identity
	 * @since 1.0.0
	 */
	public static float[] identity_k()
	{
		return new float[] {0f,0f,0f,1f};
	}
	
	/**
	 * Returns the quaternion identity on a scale.
	 * @param s - the quaternion scale
	 * @return the quaternion identity on a scale
	 * @since 1.0.0
	 */
	public static float[] scalar(float s)
	{
		return new float[] {s,0f,0f,0f};
	}
	
	/**
	 * Adds the components of the two given quaternions.
	 * @param a - a quaternion
	 * @param b - another quaternion
	 * @return a quaternion with its components the sum of {@code a} and {@code b}
	 * @since 1.0.0
	 */
	public static float[] add(float[] a, float[] b)
	{
		return new float[]{	a[0]+b[0],
							a[1]+b[1],
							a[2]+b[2],
							a[3]+b[3]};
	}
	
	/**
	 * Multiplies two quaternions.
	 * @param q - the base quaternion
	 * @param r - the multiplier quaternion
	 * @return the result of the multiplication
	 * @since 1.0.0
	 * non-commutative
	 */
	public static float[] mul(float[] q, float[] r)
	{
		float a = q[0],b = q[1],c = q[2],d = q[3];
		float e = r[0],f = r[1],g = r[2],h = r[3];
		
		float s = a*e - b*f - c*g - d*h;
		float i = a*f + b*e + c*h - d*g;
		float j = a*g - b*h + c*e + d*f;
		float k = a*h + b*g - c*f + d*e;
		
		return new float[] {s,i,j,k};
	}
	
	/**
	 * Convert a quaternion into a 4-by-4 matrix.
	 * @param q - the quaternion to convert
	 * @return the quaternion as a 4-by-4 matrix
	 * @since 1.0.0
	 */
	public static float[][] matrix(float[] q)
	{
		float w=q[0], x=q[1], y=q[2], z=q[3];
		
		float xx2 = 2f*x*x;
		float xy2 = 2f*x*y;
		float xz2 = 2f*x*z;
		float yy2 = 2f*y*y;
		float yz2 = 2f*y*z;
		float zz2 = 2f*z*z;
		float wx2 = 2f*w*x;
		float wy2 = 2f*w*y;
		float wz2 = 2f*w*z;
		
		float m11 = 1f - yy2 - zz2;
		float m12 = 	 xy2 - wz2;
		float m13 = 	 xz2 + wy2;
		
		float m21 = 	 xy2 + wz2;
		float m22 = 1f - xx2 - zz2;
		float m23 = 	 yz2 - wx2;
		
		float m31 = 	 xz2 - wy2;
		float m32 = 	 yz2 + wx2;
		float m33 = 1f - xx2 - yy2;
		
		return new float[][] 
		{
			{m11,m12,m13, 0f},
			{m21,m22,m23, 0f},
			{m31,m32,m33, 0f},
			{ 0f, 0f, 0f, 1f}
		};
	}
	
	/**
	 * Returns the inverse/conjugate of the given quaternion.
	 * @param q - the quaternion to invert
	 * @return the inverse/conjugate of the given quaternion
	 * @since 1.0.0
	 */
	public static float[] conjugate(float[] q)
	{
		return new float[] {q[0],-q[1],-q[2],-q[3]};
	}
	
	/**
	 * Normalize a quaternion.
	 * @param q - the quaternion to normalize
	 * @return the normalized quaternion
	 * @since 1.0.0
	 */
	public static float[] normalize(float[] q)
	{
		float x = q[0];
		float y = q[1];
		float z = q[2];
		float w = q[3];
		float m = Vector4.magnitude(q);
		float invm = 1f / m;
		return new float[]{x*invm, y*invm, z*invm, w*invm};
	}
	
	/**
	 * Construct a rotation quaternion around the given vector3 axes at the specified angle in radians.
	 * @param N - the rotation axes
	 * @param angle - the amount of radians to rotate around
	 * @return a quaternion that represents the rotation around the given axes
	 * @since 1.0.0
	 */
	public static float[] rotation(float[] N, float angle) 
	{
		float sin = (float)Approximate.sin(angle*0.5f);
		float cos = (float)Approximate.cos(angle*0.5f);
		return fromVector3(cos, Vector3.mul(N,sin) );
	}
	
	/**
	 * Rotate a quaternion {@code q} around perpendicular quaternion {@code N} with the given angle in radians.
	 * @param q - 
	 * @param N - 
	 * @param angle - 
	 * @return 
	 * @since 1.0.0
	 */
	public static float[] rotate(float[] q, float[] N, float angle) 
	{
		float[] cos = scalar((float)Approximate.cos(angle));
		float[] sin = scalar((float)Approximate.sin(angle));
		float[] r = add(cos, mul(sin,N) );
		return mul(r,q);
	}
	
	/**
	 * Returns a copy of the given quaternion.
	 * @param z - the quaternion to copy
	 * @return a copy of the given quaternion
	 * @since 1.0.0
	 */
	public static float[] copy(float[] q) 
	{
		return new float[] {q[0],q[1],q[2],q[3]};
	}
	
	/**
	 * Returns a string that represents the given quaternion.
	 * @param q - the quaternion to print
	 * @return a String with the content of {@code q} 
	 * @since 1.0.0
	 */
	public static String toString(float[] q)
	{
		return "r:"+q[0]+" i:"+q[1]+" j:"+q[2]+" k:"+q[3];
	}
	
	/*
	 * construct a quaternion with a given scalar value from a vector3
	 */
	private static float[] fromVector3(float s, float[] v)
	{
		return new float[] {s,v[0],v[1],v[2]};
	}
}
