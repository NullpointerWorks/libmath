/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.matrix;

/**
 * Interface for standardizing square matrix operations. Some implementations may have extra method definitions that are intended to be utilized for that range of dimensions.
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
	 * @param m - the matrix to transpose
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
	 * Multiplies two matrices and returns the result.
	 * @param m1 - the base matrix
	 * @param m2 - the matrix to multiply with
	 * @return the multiplied matrix
	 * @since 1.0.0
	 */
	float[][] mul(float[][] m1, float[][] m2);
	
	/**
	 * Multiplies a list matrices and returns the result.
	 * @param m - a list of matrices
	 * @return the multiplied matrix
	 * @since 1.0.0
	 */
	float[][] mul(float[][]... m);
	
	/**
	 * Returns the determinate of the given matrix.
	 * @param m - a matrix
	 * @return the determinate
	 * @since 1.0.0
	 */
	float det(float[][] m);
	
	/**
	 * Returns the adjugate of the given matrix. The adjugate matrix is the transpose of the cofactor matrix.
	 * @param m - a matrix
	 * @return the adjugate matrix
	 * @since 1.0.0
	 */
	float[][] adjugate(float[][] m);
	
	/**
	 * Returns the inverse of the given matrix.
	 * @param m - a matrix
	 * @return the inverse matrix
	 * @since 1.0.0
	 */
	float[][] inverse(float[][] m);
}
