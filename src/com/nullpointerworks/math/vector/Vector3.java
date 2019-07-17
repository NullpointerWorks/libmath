/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.vector;

import com.nullpointerworks.math.Approximate;
import com.nullpointerworks.math.random.generator.Randomizer;

public class Vector3 
{
	private static final float MAX_VALUE = Float.MAX_VALUE;
	
	/**
	 * 
	 */
	public static float[] New(float x, float y, float z)
	{
		return new float[]{x,y,z};
	}
	
	/**
	 * add two vector3 arrays
	 */
	public static float[] add(float[] a, float[] b)
	{
		return new float[]{	a[0]+b[0],
							a[1]+b[1],
							a[2]+b[2]};
	}

	/**
	 * subtract two vector3 arrays
	 */
	public static float[] sub(float[] a, float[] b)
	{
		return new float[]{	a[0]-b[0],
							a[1]-b[1],
							a[2]-b[2]};
	}
	
	/**
	 * multiply a vector with a factor
	 */
	public static float[] mul(float[] a, float f)
	{
		return new float[]{	a[0]*f,
							a[1]*f,
							a[2]*f};
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
		return new float[]{-a[0], -a[1], -a[2]};
	}
	
	/**
	 * returns a new instance of the given float[3]
	 */
	public static float[] copy(float[] c) 
	{
		return new float[] {c[0],c[1],c[2]};
	}
	
	/**
	 * returns a string representation of a vector
	 */
	public static String toString(float[] v)
	{
		return "x:"+v[0]+" y:"+v[1]+" z:"+v[2];
	}
	
	// =========================================================
	
	/**
	 * add multiple vectors together
	 */
	public static float[] add(float[]... r) 
	{
		float[] in = {0f,0f,0f};
		for (float[] v : r)
			in = add(in,v);
		return in;
	}
	
	/***
	 * project a point from a vector with a given lambda value<br>
	 * P = A + a*lambda<br>
	 **/
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
	 * returns the dot product between two float[3] vectors
	 */
	public static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
	}
	
	/**
	 * returns a random vector with values [0,1]
	 */
	public static float[] random(Randomizer rng)
	{
		float x = (float)(rng._double()*2d - 1d);
		float y = (float)(rng._double()*2d - 1d);
		float z = (float)(rng._double()*2d - 1d);
		return new float[] {x, y, z};
	}
	
	/**
	 * interpolate between two vectors
	 */
	public static float[] lerp(float[] a, float[] b, float i)
	{
		float j = 1f-i;
		return new float[]{ a[0]*j + b[0]*i,
							a[1]*j + b[1]*i,
							a[2]*j + b[2]*i};
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
		float m = magnitude(a);
		float invm = 1f / m;
		return new float[]{x*invm, y*invm, z*invm};
	}
	
	/**
	 * vector plane lerp. Provide an origin, and two vectors that define the plane.<br>
	 * u,v are interpolation values to get any point on the plane.
	 */
	public static float[] plane(float[] O, float[] a, float[] b, float u, float v)
	{
		float x = O[0] + a[0]*u + b[0]*v;
		float y = O[1] + a[1]*u + b[1]*v;
		float z = O[2] + a[2]*u + b[2]*v;
		return new float[] {x,y,z};
	}
	
	/**
	 * returns the cross product between two float[3] vectors
	 */
	public static float[] cross(float[] a, float[] b)
	{
		return new float[]{	a[1]*b[2]-a[2]*b[1],
							a[2]*b[0]-a[0]*b[2],
							a[0]*b[1]-a[1]*b[0]};
	}
	
	/**
	 * returns the normal vector from three vertices
	 */
	public static float[] normal(float[] a, float[] b, float[] c)
	{
		float[] u = sub(b,a);
		float[] v = sub(c,a);
		return cross(u,v);
	}
	
	/**
	 * test to see where a vector V+v*u intersects the plane O+a*u+b*v<br>
	 * returns the lambda value of the intersection.
	 */
	public static float intersect(float[] V, float[] v, float[] O, float[] a, float[] b)
	{
		float[] N = cross(a,b);
		float num,denom,d = dot(N,O);
		
		denom = dot(N,v);
		if (denom == 0f) return MAX_VALUE;
		
		num = d - dot(N,V);
		return num/denom;
	}
	
	/** find the angle in radians between two vectors<br>
	 * u dot v<br>
	 * -------- = cos t<br>
	 * |u| * |v|<br>
	 */
	public static float angle(float[] u, float[] v)
	{
		float magU = magnitude(u);
		float magV = magnitude(v);
		float m = magU * magV;
		if (m==0.0f) return 0f;
		float s = dot(u, v);
		return (float)Math.acos(s / m);
	}
	
	/**
	 * returns a rotated normalized vector
	 */
	public static float[] rotation(float angle)
	{
		float cs = (float)Approximate.cos(angle);
		float sn = (float)Approximate.sin(angle);
		return new float[]{cs,sn,0f};
	}
	
	/**
	 * rotate a vector v around the given normal axes n
	 */
	public static float[] rotate(float[] v,float[] N, float angle)
	{
		float cos = (float)Approximate.cos(angle);
		float sin = (float)Approximate.sin(angle);
		float[] n = cross(N,v);
		float[] vcos = mul(v,cos);
		float[] vsin = mul(n,sin);
		return add(vcos,vsin);
	}
	
	/**
	 * TODO Vector3 : add reflection code
	 * returns the reflection vector from a given vector of incidence.
	 */
	public static float[] reflection(float[] A, float[] C, float[] nn)
	{
		return null;
	}
}
