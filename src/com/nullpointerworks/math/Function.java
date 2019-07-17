/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

/**
 * An interface for reference bases math objects.
 * @since 1.0.0
 */
public interface Function 
{
	/**
	 * Compute an output for the given input {@code x}.
	 * @param x - input value
	 * @return the result
	 * @since 1.0.0
	 */
	public float calculate(float x);
}
