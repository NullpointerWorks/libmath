package com.nullpointerworks.math.complex;

import com.nullpointerworks.math.FastMath;
import com.nullpointerworks.math.vector.Vector3;
import com.nullpointerworks.math.vector.Vector4;

/**
 * quaternion math class<br>
 * a + bi + cj + dk <br>
 * i^2 = j^2 = k^2 = ijk = -1
 */
public class Quaternion 
{
	/**
	 * returns the quaternion identity
	 */
	public static float[] New(float s, float i, float j, float k)
	{
		return new float[] {s,i,j,k};
	}
	
	/**
	 * returns the quaternion identity
	 */
	public static float[] identity()
	{
		return new float[] {1f,0f,0f,0f};
	}
	
	/**
	 * returns the quaternionic i identity
	 */
	public static float[] identity_i()
	{
		return new float[] {0f,1f,0f,0f};
	}
	
	/**
	 * returns the quaternionic j identity
	 */
	public static float[] identity_j()
	{
		return new float[] {0f,0f,1f,0f};
	}
	
	/**
	 * returns the quaternionic k identity
	 */
	public static float[] identity_k()
	{
		return new float[] {0f,0f,0f,1f};
	}
	
	/**
	 * returns the quaternion scaled identity
	 */
	public static float[] scalar(float s)
	{
		return new float[] {s,0f,0f,0f};
	}
	
	/**
	 * construct a quaternion with a given scalar value from a vector3
	 */
	public static float[] fromVector3(float s, float[] v)
	{
		return new float[] {s,v[0],v[1],v[2]};
	}
	
	/**
	 * construct a vector3 from the given quaternion
	 */
	public static float[] toVector3(float[] q)
	{
		return new float[] {q[1],q[2],q[3]};
	}
	
	/**
	 * add two quaternions
	 */
	public static float[] add(float[] a, float[] b)
	{
		return new float[]{	a[0]+b[0],
							a[1]+b[1],
							a[2]+b[2],
							a[3]+b[3]};
	}
	
	/**
	 * multiply two quaternions<br>
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
	 * convert quaternion into a matrix
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
	 * returns the inverse/conjugate of the given quaternion
	 */
	public static float[] conjugate(float[] q)
	{
		return new float[] {q[0],-q[1],-q[2],-q[3]};
	}
	
	/**
	 * normalize a quaternion<br>
	 */
	public static float[] normalize(float[] a)
	{
		float x = a[0];
		float y = a[1];
		float z = a[2];
		float w = a[3];
		float m = Vector4.magnitude(a);
		float invm = 1f / m;
		return new float[]{x*invm, y*invm, z*invm, w*invm};
	}
	
	/**
	 * construct a rotation quaternion around the given axes(Vector3) with a given angle
	 * for x axis -> (1,0,0) etc.
	 */
	public static float[] rotation(float[] N, float angle) 
	{
		float sin = (float)FastMath.sin(angle*0.5f);
		float cos = (float)FastMath.cos(angle*0.5f);
		return fromVector3(cos, Vector3.mul(N,sin) );
	}
	
	/**
	 * rotate a quaternion q around perpendicular quaternion N with the given angle
	 */
	public static float[] rotate(float[] q, float[] N, float angle) 
	{
		float[] cos = scalar((float)FastMath.cos(angle));
		float[] sin = scalar((float)FastMath.sin(angle));
		float[] r = add(cos, mul(sin,N) );
		return mul(r,q);
	}
	
	/**
	 * returns a copy of the given quaternion
	 */
	public static float[] copy(float[] q) 
	{
		return new float[] {q[0],q[1],q[2],q[3]};
	}
	
	/**
	 * returns a string that represents the given quaternion
	 */
	public static String toString(float[] q)
	{
		return "a:"+q[0]+" i:"+q[1]+" j:"+q[2]+" k:"+q[3];
	}
}
