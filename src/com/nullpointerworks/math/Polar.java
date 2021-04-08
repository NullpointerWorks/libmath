/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math;

/**
 * Contains static members to help with polar coordinate translation to complex numbers.
 * <pre>
 * p = r (cos T + i sin T)
 * p = float[2] = {r,T}</pre>
 * @since 1.0.0
 */
public class Polar 
{
	/**
	 * Convert a polar form into a complex number.
	 * @param p - the polar coordinate
	 * @return a complex number from polar
	 * @since 1.0.0
	 */
	public static float[] toComplex(float[] p)
	{
		float r = p[0] * (float)Approximate.cos( p[1] );
		float i = p[0] * (float)Approximate.sin( p[1] );
		return new float[] {r,i};
	}
	
	/**
	 * Multiplies two polar form numbers with each other.
	 * @param p - a polar coordinate
	 * @param q - a polar coordinate
	 * @return the result of the multiplication
	 * @since 1.0.0
	 */
	public static float[] mul(float[] p, float[] q)
	{
		float r = p[0] * q[0];
		float t = p[1] + q[1];
		return new float[] {r,t};
	}
	
	/**
	 * Divide two polar form numbers from each other.
	 * @param p - a polar coordinate
	 * @param q - a polar coordinate
	 * @return the result of the division
	 * @since 1.0.0
	 */
	public static float[] div(float[] p, float[] q)
	{
		float r = p[0] / q[0];
		float t = p[1] - q[1];
		return new float[] {r,t};
	}
}
