/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
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
