/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.vector;

import com.nullpointerworks.math.Approximate;
import com.nullpointerworks.math.random.Randomizer;

/**
 * a comprehensive 2d vector class.
 * @since 1.0.0
 */
public class Vector2
{
	private static final float MAX_VALUE = Float.MAX_VALUE;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static float[] New(float x, float y)
	{
		return new float[]{x,y};
	}
	
	/**
	 * add two vector2 arrays
	 * @since 1.0.0
	 */
	public static float[] add(float[] a, float[] b)
	{
		return new float[]{	a[0]+b[0],
							a[1]+b[1]};
	}

	/**
	 * subtract two vector2 arrays
	 * @since 1.0.0
	 */
	public static float[] sub(float[] a, float[] b)
	{
		return new float[]{	a[0]-b[0],
							a[1]-b[1]};
	}
	
	/**
	 * multiply a vector with a factor
	 * @since 1.0.0
	 */
	public static float[] mul(float[] a, float f)
	{
		return new float[]{	a[0]*f,
							a[1]*f};
	}
	
	/**
	 * scalar divide two vectors
	 * @since 1.0.0
	 */
	public static float div(float[] a, float[] b)
	{
		float aDot = dot(a,b);
		float bDot = dot(b,b);
		return aDot / bDot;
	}
	
	/**
	 * negate a vector
	 * @since 1.0.0
	 */
	public static float[] neg(float[] a)
	{
		return new float[]{-a[0], -a[1]};
	}
	
	/**
	 * returns a new instance of the given float[2]
	 * @since 1.0.0
	 */
	public static float[] copy(float[] c) 
	{
		return new float[] {c[0],c[1]};
	}
	
	/**
	 * returns a string representation of a vector
	 * @since 1.0.0
	 */
	public static String toString(float[] v)
	{
		return "x:"+v[0]+" y:"+v[1];
	}
	
	// =========================================================
	
	/**
	 * add multiple vectors together
	 * @since 1.0.0
	 */
	public static float[] add(float[]... r) 
	{
		float[] in = {0f,0f};
		for (float[] v : r)
			in = add(in,v);
		return in;
	}
	
	/**
	 * project a point from a vector with a given lambda value<br>
	 * P = A + a*lambda<br>
	 * @since 1.0.0
	 */
	public static float[] projection(float[] A, float[] a, float lambda)
	{
		return add(A, mul(a, lambda) );
	}
	
	/***
	 * project a vector Q onto another vector A and returns the lambda value along A<br>
	 * t = q*a / a*a
	 * @since 1.0.0
	 **/
	public static float project(float[] q, float[] a)
	{
		float qa = dot(q,a);
		float aa = dot(a,a);
		return qa/aa;
	}
	
	/**
	 * returns the dot product between two vectors
	 * @since 1.0.0
	 */
	public static float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1];
	}
	
	/**
	 * returns a random vector with values [0,1]
	 * @since 1.0.0
	 */
	public static float[] random(Randomizer rng)
	{
		float x = (float)(rng._double()*2d - 1d);
		float y = (float)(rng._double()*2d - 1d);
		return new float[] {x, y};
	}
	
	/**
	 * lerp between two vector2 arrays
	 * @since 1.0.0
	 */
	public static float[] lerp(float[] a, float[] b, float i)
	{
		float j = 1f-i;
		return new float[]{ a[0]*j + b[0]*i,
							a[1]*j + b[1]*i};
	}
	
	/**
	 * returns the magnitude of a vector
	 * @since 1.0.0
	 */
	public static float magnitude(float[] v)
	{
		return (float) StrictMath.sqrt( dot(v,v) );
	}
	
	/**
	 * returns a vector with a maximum magnitude of the given value
	 * @since 1.0.0
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
		}
		return c;
	}

	/**
	 * normalize a vector<br>
	 * @since 1.0.0
	 */
	public static float[] normalize(float[] a)
	{
		float x = a[0];
		float y = a[1];
		float m = magnitude(a);
		float invm = 1f / m;
		return new float[]{	x*invm, y*invm};
	}
	
	/**
	 * vector plane lerp. Provide an origin, and two vectors that define the plane.<br>
	 * u,v are interpolation values to get any point on the plane.
	 * @since 1.0.0
	 */
	public static float[] plane(float[] O, float[] a, float[] b, float u, float v)
	{
		float x = O[0] + a[0]*u + b[0]*v;
		float y = O[1] + a[1]*u + b[1]*v;
		return new float[] {x,y};
	}
	
	/**
	 * returns the depth from the cross of the given vectors. 
	 * @since 1.0.0
	 */
	public static float cross(float[] a, float[] b)
	{
		return a[0]*b[1]-a[1]*b[0];
	}
	
	/**
	 * returns the normal from a vector
	 * @since 1.0.0
	 */
	public static float[] normal(float[] a)
	{
		return new float[]{-a[1],a[0]};
	}
	
	/**
	 * returns the normal from a vector multiplied by a scalar
	 * @since 1.0.0
	 */
	public static float[] normal(float[] A, float a)
	{
		return new float[]{A[1]*-a, A[0]*a};
	}
	
	/**
	 * find the intersection of A+l*a onto line B+l*b<br>
	 * returns the lambda scalar<br>
	 * l = ( dot(n,B) - dot(n,A) ) / dot(n,a)<br>
	 * @since 1.0.0
	 */
	public static float intersect(float[] A, float[] a,
								  float[] B, float[] b)
	{
		float[] n 	= normal(b);
		float denom = dot(n,a);
		if (denom == 0.0f) return 0f;
		float numer = dot(n,B) - dot(n,A);
		float l 	= numer / denom;
		return l;
	}
	
	/**
	 * find the intersection of A+l*a onto line B+l*b<br>
	 * returns both lambda scalars in vector form<br>
	 * @since 1.0.0
	 */
	public static float[] intersect2(float[] A, float[] a,
				   		   			 float[] B, float[] b)
	{
		float x1 = A[0];
		float y1 = A[1];
		float dx1 = a[0];
		float dy1 = a[1];
		
		float x2 = B[0];
		float y2 = B[1];
		float dx2 = b[0];
		float dy2 = b[1];
		
		float t1,t2,det = (dy1 * dx2 - dx1 * dy2);
		if (det != 0f)
		t1 = ((x1 - x2) * dy2 + (y2 - y1) * dx2) / det;
		else t1 = Float.MAX_VALUE;
		
		det = (dy2 * dx1 - dx2 * dy1);
		if (det != 0f)
		t2 = ((x2 - x1) * dy1 + (y1 - y2) * dx1) / det;
		else t2 = Float.MAX_VALUE;
		
		return new float[] {t1, t2};
	}
	
	/** find the angle in radians between two vectors<br>
	 * u dot v<br>
	 * -------- = cos t<br>
	 * |u| * |v|<br>
	 * @since 1.0.0
	 */
	public static float angle(float[] u, float[] v)
	{
		float magU = (float) Math.sqrt( dot(u,u) );
		float magV = (float) Math.sqrt( dot(v,v) );
		float m = magU * magV;
		if (m == 0.0f) return MAX_VALUE;
		float s = dot(u, v);
		return (float)Math.acos(s / m);
	}
	
	/**
	 * returns a rotated normalized vector
	 * @since 1.0.0
	 */
	public static float[] rotation(float angle)
	{
		float cs = (float)Approximate.cos(angle);
		float sn = (float)Approximate.sin(angle);
		return new float[]{cs,sn};
	}
	
	/**
	 * rotate a vector with a given angle in radians
	 * @since 1.0.0
	 */
	public static float[] rotate(float[] v, float angle)
	{
		float cs = (float)Approximate.cos(angle);
		float sn = (float)Approximate.sin(angle);
		return new float[]{ cs*v[0] - sn*v[1],
							sn*v[0] + cs*v[1]};
	}
	
	/**
	 * returns the reflection vector from a given vector of incidence.<br>
	 * A  = position of vector of incidence<br>
	 * C  = line intersection position<br>
	 * nn = normalized normal from vector w<br>
	 * <br>
	 * example usage from a given two vectors A+a*l and B+b*l;<br>
	 * float l = Vector2.intersect(A, a, B, b);<br>
	 * float[] n = Vector2.normalize( Vector2.normal(b) );<br>
	 * float[] C = Vector2.projection(A, a, l);<br>
	 * float[] c = Vector2.reflection(A, C, n);<br>
	 * @since 1.0.0
	 */
	public static float[] reflection(float[] A, float[] C, float[] nn)
	{
		float u 	= dot(nn, sub(A, C));
		float[] P	= projection(C, nn, u+u);
		return sub(P, A);
	}
	
}
