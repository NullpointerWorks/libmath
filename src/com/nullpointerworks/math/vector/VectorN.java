/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.vector;

/**
 * A general purpose vector manipulation class. This class allows for appending and prefixing vectors with values.
 * @since 1.0.0
 */
public class VectorN 
{
	/**
	 * Add the value of {@code n} and the end of vector {@code v} increasing the element count by {@code 1}.
	 * @param v - the vector to extend
	 * @param n - the number to append
	 * @return the new vector with the content of {@code v} followed by {@code n}
	 * @since 1.0.0
	 */
	public static float[] append(float[] v, float n)
	{
		int l=v.length;
		float[] r = new float[l+1];
		for (int i=0; i<l; i++) r[i] = v[i];
		r[l] = n;
		return r;
	}
	
	/**
	 * Place a value {@code n} in front of the given vector {@code v}. This method extends the element count by {@code 1}.
	 * @param n - the number to prefix
	 * @param v - the vector to extend
	 * @return the new vector with the content of {@code n} followed by the content of vector {@code v}
	 * @since 1.0.0
	 */
	public static float[] prefix(float n, float[] v)
	{
		int l=v.length;
		float[] r = new float[l+1];
		r[0] = n;
		for (int i=0; i<l; i++) r[i+1] = v[i];
		return r;
	}
	
	/**
	 * Returns a copy of vector {@code v}.
	 * @param v - the vector to copy
	 * @return a copy of vector {@code v}
	 * @since 1.0.0
	 */
	public static float[] copy(float[] v)
	{
		int l = v.length;
		float[] c = new float[l];
		for (int i=0;i<l;i++) c[i] = v[i];
		return c;
	}
}
