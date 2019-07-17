/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.vector;

/**
 * A general purpose vector manipulation class. This class allows for appending and prefixing vectors with values, or converting vectors into different lengths.
 * @since 1.0.0
 */
public class Vector 
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
	
	/**
	 * Sets the values of vector {@code v} into a new {@code float[2]} vector. If the given vector has fewer than two elements, only the available elements are used. If the vector has more than two elements, only the first two are used.
	 * @param v - the vector to scan
	 * @return a new float[2] vector
	 * @since 1.0.0
	 */
	public static float[] toVec2(float[] v)
	{
		float[] r = {0f,0f};
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
	
	/**
	 * Sets the values of vector {@code v} into a new {@code float[3]} vector. If the given vector has fewer than three elements, only the available elements are used. If the vector has more than three elements, only the first three are used.
	 * @param v - the vector to scan
	 * @return a new float[3] vector
	 * @since 1.0.0
	 */
	public static float[] toVec3(float[] v)
	{
		float[] r = {0f,0f,0f};
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
	
	/**
	 * Sets the values of vector {@code v} into a new {@code float[4]} vector. If the given vector has fewer than four elements, only the available elements are used. If the vector has more than four elements, only the first four are used.
	 * @param v - the vector to scan
	 * @return a new float[4] vector
	 * @since 1.0.0
	 */
	public static float[] toVec4(float[] v)
	{
		float[] r = {0f,0f,0f,0f};
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
	
	/**
	 * Sets the values of vector {@code v} into a new {@code float[n]} vector. If the given vector has fewer than {@code n} elements, only the available elements are used. If the vector has more than {@code n} elements, only the first {@code n} are used.
	 * @param v - the vector to scan
	 * @param n - the length of the new vector
	 * @return a new float[n] vector
	 * @since 1.0.0
	 */
	public static float[] toVecN(float[] v, int n)
	{
		float[] r = new float[n];
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
}
