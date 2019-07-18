/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.matrix;

/**
 * 
 * @since 1.0.0
 */
public interface Matrix 
{
	/**
	 * Creates a new zero matrix.
	 * @return a new zero matrix
	 * @since 1.0.0
	 */
	float[][] zero();
	
	/**
	 * Creates a new identity matrix.
	 * @return a new identity matrix
	 * @since 1.0.0
	 */
	float[][] identity();
	
	/**
	 * Returns the transpose of the given matrix.
	 * @param 
	 * @return the transpose of the given matrix
	 * @since 1.0.0
	 */
	float[][] transpose(float[][] m);
	
	/**
	 * Multiplies all elements of a matrix with a factor.
	 * @param m - a matrix
	 * @param f - the multiplication factor
	 * @return a new matrix {@code m} with its components scaled by {@code f}
	 * @since 1.0.0
	 */
	float[][] mul(float[][] m, float f);
	
	/**
	 * @since 1.0.0
	 */
	float[][] mul(float[][] m1, float[][] m2);
	
	/**
	 * @since 1.0.0
	 */
	float[][] mul(float[][]... m);
	
	/**
	 * @since 1.0.0
	 */
	float det(float[][] m);
	
	/**
	 * @since 1.0.0
	 */
	float[][] adjugate(float[][] m);
	
	/**
	 * @since 1.0.0
	 */
	float[][] inverse(float[][] m);
}
