/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.random;

/**
 * An interface for a custom random number generator. Can be set in the Random class for later use.
 * @since 1.0.0
 */
public interface Randomizer
{
	/**
	 * Returns the seed this generator is using to generate the current series of numbers.
	 * @since 1.0.0
	 */
	long seed();
	
	/**
	 * Some random number generators use a seeding system to approximate the effect of randomness, but can be reset to re-generate the previous series of numbers. For a hardware generator this would probably of no use.
	 * @since 1.0.0
	 */
	void seed(long s);
	
	/**
	 * Returns a newly generated double from the {@code Randomizer}.
	 * @return a newly generated double from the {@code Randomizer}
	 * @since 1.0.0
	 */
	double _double();
	
	/**
	 * Returns a newly generated float from the {@code Randomizer}.
	 * @return a newly generated float from the {@code Randomizer}
	 * @since 1.0.0
	 */
	float _float();
	
	/**
	 * Returns a newly generated boolean from the {@code Randomizer}.
	 * @return a newly generated boolean from the {@code Randomizer}
	 * @since 1.0.0
	 */
	boolean _boolean();
	
	/**
	 * Returns a double within the range of the lower and upper limit.
	 * @param low - the lower limit
	 * @param high - the upper limit
	 * @return a double within the range of the lower and upper limit
	 * @since 1.0.0
	 */
	double _double(double low, double high);
	
	/**
	 * Returns a float within the range of the lower and upper limit.
	 * @param low - the lower limit
	 * @param high - the upper limit
	 * @return a float within the range of the lower and upper limit
	 * @since 1.0.0
	 */
	float _float(float low, float high);
	
	/**
	 * Returns an integer within the range of the lower and upper limit.
	 * @param low - the lower limit
	 * @param high - the upper limit
	 * @return an integer within the range of the lower and upper limit
	 * @since 1.0.0
	 */
	int _integer(int low, int high);
}