/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.vector;

import com.nullpointerworks.math.random.Randomizer;

/**
 * Interface for standardizing vector operations. This interface does not guarantee that each implementation only contains the available methods. Some implementations have special methods that are intended for the use of the specified dimensions. Varying number of dimensions may permit or restrict some of the implemented methods.
 * @since 1.0.0
 */
public interface Vector 
{
	/**
	 * Create a new vector with the given list of values.
	 * @param v - a list of values to place in the vector elements
	 * @return a new vector with the given list of values
	 * @since 1.0.0
	 */
	float[] New(float... v);
	
	/**
	 * Add the respective components of two vectors together and return the result.
	 * @param a - a vector
	 * @param b - another vector
	 * @return the result of the addition
	 * @since 1.0.0
	 */
	float[] add(float[] a, float[] b);
	
	/**
	 * Add a list of vectors with their respective components together and return the result.
	 * @param v - a list of vectors
	 * @return the result of the addition
	 * @since 1.0.0
	 */
	float[] add(float[]... v);
	
	/**
	 * Subtract the respective components of two vectors from each other and return the result.
	 * @param a - a vector
	 * @param b - another vector
	 * @return the difference between two vectors
	 * @since 1.0.0
	 */
	float[] sub(float[] a, float[] b);
	
	/**
	 * Multiplies a vectors with a given scalar value.
	 * @param a - vector
	 * @param f - scaling factor
	 * @return the scaled vector
	 * @since 1.0.0
	 */
	float[] mul(float[] a, float f);
	
	/**
	 * A vector division projects vector {@code b} onto vector {@code a} and returns the lambda value along the magnitude of {@code a}.
	 * <pre>l = a*b / b*b</pre>
	 * @param a - base vector
	 * @param b - projection vector
	 * @return the lambda value along the magnitude of {@code a}
	 * @since 1.0.0
	 */
	float div(float[] a, float[] b);
	
	/**
	 * Returns the dot product of two vectors.
	 * @param a - a vector
	 * @param b - another vector
	 * @return the dot product of two vectors
	 * @since 1.0.0
	 */
	float dot(float[] a, float[] b);
	
	/**
	 * Returns the negation, or inversion, of the given vector.
	 * @param a - the vector to negate
	 * @return the negation of the given vector
	 * @since 1.0.0
	 */
	float[] neg(float[] a);
	
	/**
	 * Returns a reference free copy of the given vector.
	 * @param c - the vector to copy
	 * @return a reference free copy of the given vector
	 * @since 1.0.0
	 */
	float[] copy(float[] c);
	
	/**
	 * Returns a {@code String} with the content of the given vector.
	 * @param v - the vector to print
	 * @return a {@code String} with the content of the given vector
	 * @since 1.0.0
	 */
	String print(float[] v);
	
	/**
	 * Linearly interpolate between two vectors using an interpolation factor {@code i} ranging between {@code 0.0} and {@code 1.0}.
	 * @param a - base vector
	 * @param b - end vector
	 * @param i - the interpolation factor
	 * @return the interpolant vector
	 * @since 1.0.0
	 */
	float[] lerp(float[] a, float[] b, float i);
	
	/**
	 * Returns a vector the point away perpendicular from the plane the two input vectors define. The two input vectors are assumed to originate from the same source vector. Only in 3 and 7 dimensions do cross products have a meaningful answer. Note: this method may not behave consistent among different implementations of the {@code Vector} interface.
	 * @param a - a vector
	 * @param b - another vector
	 * @return the cross product vector
	 * @since 1.0.0
	 */
	float[] cross(float[] a, float[] b);
	
	/**
	 * Returns the length, or magnitude, of the provided vector.
	 * @param v - a vector
	 * @return the magnitude of the provided vector
	 * @since 1.0.0
	 */
	float magnitude(float[] v);
	
	/**
	 * 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] random(Randomizer rng);
	
	/**
	 * Limits the magnitude of a vector a the given factor {@code f}. If the magnitude is less than {@code f}, this method returns a copy of vector {@code v}.
	 * @param v - the vector to limit
	 * @param f - the magnitude maximum
	 * @return the limited copy of vector {@code v}
	 * @since 1.0.0
	 */
	float[] limit(float[] v, float f);
	
	/**
	 * Normalizes the magnitude of a vector to range from {@code 0} to {@code 1}. In other words, this method returns a unit vector in the same direction as the given vector.
	 * @param a - the vector to normalize
	 * @return the normalized vector
	 * @since 1.0.0
	 */
	float[] normalize(float[] a);

	/**
	 * 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] planar(float[] O, float[] a, float[] b, float u, float v);

	/**
	 * 
	 * @param 
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
	 */
	float[] project(float[] A, float[] a, float lambda);
	
}
