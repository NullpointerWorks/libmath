/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

/**
 * p = r (cos T + i sin T) <br>
 * float[2] = {r,T}
 */
public class Polar 
{
	/**
	 * convert a polar form into a complex number
	 */
	public static float[] toComplex(float[] p)
	{
		float r = p[0] * (float)FastMath.cos( p[1] );
		float i = p[0] * (float)FastMath.sin( p[1] );
		return new float[] {r,i};
	}
	
	/**
	 * multiply two polar form numbers with each other
	 */
	public static float[] mul(float[] p, float[] q)
	{
		float r = p[0] * q[0];
		float t = p[1] + q[1];
		return new float[] {r,t};
	}
	
	/**
	 * divide two polar form numbers from each other
	 */
	public static float[] div(float[] p, float[] q)
	{
		float r = p[0] / q[0];
		float t = p[1] - q[1];
		return new float[] {r,t};
	}
	
}
