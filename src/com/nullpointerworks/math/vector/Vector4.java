package com.nullpointerworks.math.vector;

import com.nullpointerworks.math.random.generator.Randomizer;

/**
 * float[4] vector math class<br>
 * <br>
 * [0] = x<br>
 * [1] = y<br>
 * [2] = z<br>
 * [3] = w<br>
 */
public class Vector4 
{
	/**
	 * 
	 */
	public static float[] New(float x, float y, float z, float w)
	{
		return new float[]{x,y,z, w};
	}
	
	/**
	 * add two float[4]
	 */
	public static float[] add(float[] a, float[] b)
	{
		return new float[]{	a[0]+b[0],
							a[1]+b[1],
							a[2]+b[2],
							a[3]+b[3]};
	}
	
	/**
	 * subtract two float[4]
	 */
	public static float[] sub(float[] a, float[] b)
	{
		return new float[]{	a[0]-b[0],
							a[1]-b[1],
							a[2]-b[2],
							a[3]-b[3]};
	}
	
	/**
	 * multiply a vector with a factor
	 */
	public static float[] mul(float[] a, float f)
	{
		return new float[]{	a[0]*f,
							a[1]*f,
							a[2]*f,
							a[3]*f};
	}
	
	/**
	 * scalar divide two vectors
	 */
	public static float div(float[] a, float[] b)
	{
		float aDot = dot(a,b);
		float bDot = dot(b,b);
		return aDot / bDot;
	}
	
	/**
	 * negate a vector
	 */
	public static float[] neg(float[] a)
	{
		return new float[]{-a[0], -a[1], -a[2], -a[3]};
	}
	
	/**
	 * copy over the values of the given vector.
	 */
	public static float[] copy(float[] v) 
	{
		return new float[] {v[0],v[1],v[2],v[3]};
	}
	
	/**
	 * returns a string representation of a vector
	 */
	public static String toString(float[] v)
	{
		return "x:"+v[0]+" y:"+v[1]+" z:"+v[2]+" w:"+v[3];
	}

	// =========================================================
	
	/**
	 * add multiple vectors together
	 */
	public static float[] add(float[]... r) 
	{
		float[] in = {0f,0f,0f,0f};
		for (float[] v : r)
			in = add(in,v);
		return in;
	}
	
	/**
	 * project a point from a vector with a given lambda value<br>
	 * P = A + a*lambda<br>
	 */
	public static float[] projection(float[] A, float[] a, float lambda)
	{
		return add(A, mul(a, lambda) );
	}
	
	/***
	 * project a vector Q onto another vector A and returns the lambda value along A<br>
	 * t = q*a / a*a
	 **/
	public static float project(float[] q, float[] a)
	{
		float qa = dot(q,a);
		float aa = dot(a,a);
		return qa/aa;
	}
	
	/**
	 * returns the dot product between two float[4] vectors
	 */
	public static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2] + a[3]*b[3];
	}
	
	/**
	 * returns a random vector with values [0,1]
	 */
	public static float[] random(Randomizer rng)
	{
		float x = (float)(rng._double()*2d - 1d);
		float y = (float)(rng._double()*2d - 1d);
		float z = (float)(rng._double()*2d - 1d);
		float w = (float)(rng._double()*2d - 1d);
		return new float[] {x, y, z, w};
	}
	
	/**
	 * lerp between two vector4 arrays
	 */
	public static float[] lerp(float[] a, float[] b, float i)
	{
		float j = 1f-i;
		return new float[]{ a[0]*j + b[0]*i,
							a[1]*j + b[1]*i,
							a[2]*j + b[2]*i,
							a[3]*j + b[3]*i};
	}
	
	/**
	 * returns the magnitude of the given vector
	 */
	public static float magnitude(float[] dir) 
	{
		return (float)Math.sqrt(dot(dir,dir));
	}
	
	/**
	 * returns a vector with a maximum magnitude of the given value
	 */
	public static float[] limit(float[] v, float f) 
	{
		float[] c = copy(v);
		float m = magnitude(c);
		if (m > f)
		{
			if (m == 0f) m = 0.0001f;
			float invm = f / m;
			c[0] = c[0]*invm;
			c[1] = c[1]*invm;
			c[2] = c[2]*invm;
			c[3] = c[3]*invm;
		}
		return c;
	}
	
	/**
	 * normalize a vector<br>
	 */
	public static float[] normalize(float[] a)
	{
		float x = a[0];
		float y = a[1];
		float z = a[2];
		float w = a[3];
		float m = magnitude(a);
		float invm = 1f / m;
		return new float[]{x*invm, y*invm, z*invm, w*invm};
	}
	
	/**
	 * vector plane lerp. Provide an origin, and two vectors that define the plane.<br>
	 * u,v are interpolation values to get any point on the plane.
	 */
	public static float[] plane(float[] o, float[] a, float[] b, float u, float v)
	{
		float x = o[0] + a[0]*u + b[0]*v;
		float y = o[1] + a[1]*u + b[1]*v;
		float z = o[2] + a[2]*u + b[2]*v;
		float w = o[3] + a[3]*u + b[3]*v;
		return new float[] {x,y,z,w};
	}
}
