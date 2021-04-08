/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math;

import com.nullpointerworks.math.random.LinearCongruential;
import com.nullpointerworks.math.random.MersenneTwister;
import com.nullpointerworks.math.random.Randomizer;
import com.nullpointerworks.math.random.SystemRandomizer;

/**
 * This randomizer class allow for different types of randomization algorithms to be used aside from the JVM's system randomizer. In this version of the math library there are three randomizer available: A Linear Congruent number generator, a Mersenne Twister and the regular System generator. By default, the System randomizer is installed, but can be overridden by using the {@code setRandomizer(Randomizer)} method.
 * @since 1.0.0
 */
public class Random
{
	/**
	 * Returns a new instance of a linear congruent number generator.
	 * @return a new instance of a linear congruent number generator
	 * @since 1.0.0
	 */
	public static Randomizer LinearCongruent()
	{
		return new LinearCongruential();
	}
	
	/**
	 * Returns a new instance of a Mersenne twister.
	 * @return a new instance of a Mersenne twister
	 * @since 1.0.0
	 */
	public static Randomizer MersenneTwister()
	{
		return new MersenneTwister();
	}
	
	/**
	 * Returns a new instance of a system randomizer.
	 * @return a new instance of a system randomizer
	 * @since 1.0.0
	 */
	public static Randomizer SystemRandomizer()
	{
		return new SystemRandomizer();
	}
	
	private static Randomizer instance = SystemRandomizer();
	
	/**
	 * Returns the currently installed randomizer.
	 * @return the currently installed randomizer
	 * @since 1.0.0
	 */
	public static final Randomizer getRandomizer() {return instance;}
	
	/**
	 * Set the randomizer to be used when calling values from this class.
	 * @param r - an instance of {@code Randomizer}
	 * @since 1.0.0
	 */
	public static final void setRandomizer(Randomizer r) {instance = r;}
	
	/**
	 * Returns a newly generated double from the {@code Randomizer}.
	 * @return a newly generated double from the {@code Randomizer}
	 * @since 1.0.0
	 */
	public static double Double() 
	{
		return instance._double();
	}
	
	/**
	 * Returns a newly generated float from the {@code Randomizer}.
	 * @return a newly generated float from the {@code Randomizer}
	 * @since 1.0.0
	 */
	public static float Float() 
	{
		return instance._float();
	}
	
	/**
	 * Returns a newly generated boolean from the {@code Randomizer}.
	 * @return a newly generated boolean from the {@code Randomizer}
	 * @since 1.0.0
	 */
	public static boolean Boolean() 
	{
		return instance._boolean();
	}
	
	/**
	 * Returns a double within the range of the lower and upper limit.
	 * @param low - the lower limit
	 * @param high - the upper limit
	 * @return a double within the range of the lower and upper limit
	 * @since 1.0.0
	 */
	public static double Double(double low, double high) 
	{
		return instance._double(low, high);
	}
	
	/**
	 * Returns a float within the range of the lower and upper limit.
	 * @param low - the lower limit
	 * @param high - the upper limit
	 * @return a float within the range of the lower and upper limit
	 * @since 1.0.0
	 */
	public static float Float(float low, float high) 
	{
		return instance._float(low, high);
	}
	
	/**
	 * Returns an integer within the range of the lower and upper limit.
	 * @param low - the lower limit
	 * @param high - the upper limit
	 * @return an integer within the range of the lower and upper limit
	 * @since 1.0.0
	 */
	public static int Integer(int low, int high) 
	{
		return instance._integer(low, high);
	}
}
