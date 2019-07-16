/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.vector;

public class Vector 
{
	
	/**
	 * add an element to the given vector
	 */
	public static float[] merge(float[] v, float n)
	{
		int l=v.length;
		float[] r = new float[l+1];
		for (int i=0; i<l; i++) r[i] = v[i];
		r[l] = n;
		return r;
	}
	
	/**
	 * Returns a copy of a vector
	 * @param float[] vector
	 * @return a copy of the given vector
	 */
	public static float[] copy(float[] v)
	{
		int l = v.length;
		float[] c = new float[l];
		for (int i=0;i<l;i++) c[i] = v[i];
		return c;
	}
	
	// =============================================
	
	public static float[] toVec2(float[] v)
	{
		float[] r = {0f,0f};
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
	
	public static float[] toVec3(float[] v)
	{
		float[] r = {0f,0f,0f};
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
	
	public static float[] toVec4(float[] v)
	{
		float[] r = {0f,0f,0f,0f};
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
	
	public static float[] toVecN(float[] v, int n)
	{
		float[] r = new float[n];
		for (int i=0,l=v.length; i<l; i++) r[i] = v[i];
		return r;
	}
}
