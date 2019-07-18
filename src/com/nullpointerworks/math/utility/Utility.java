package com.nullpointerworks.math.utility;

/**
 * 
 * @since 1.0.0
 */
public interface Utility 
{
	/**
	 * 
	 * @since 1.0.0
	 */
	float[][] transform(float[][] m, float[][] v);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	float[] transform(float[][] m, float[] v);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	float[][] translate(float x,float y,float z);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	float[][] translate(float[] v);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	float[][] scale(float sx,float sy,float sz);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	float[][] scale(float[] s);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	float[][] correction(float width, float height);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	float[][] counter(float width, float height);
}
