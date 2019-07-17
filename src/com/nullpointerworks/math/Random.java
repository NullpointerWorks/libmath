/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

import com.nullpointerworks.math.random.generator.LinearCongruential;
import com.nullpointerworks.math.random.generator.MersenneTwister;
import com.nullpointerworks.math.random.generator.Randomizer;
import com.nullpointerworks.math.random.generator.SystemRandomizer;

/**
 * This randomizer class allow for different types of randomization algorithms to be used aside from the JVM's system randomizer. In this version of the math library there are three randomizer available: A Linear Congruent number generator, a Mersenne Twister and the regular System generator. By default, the System randomizer is installed, but can be overridden by using the {@code setRandomizer(Randomizer)} method.
 * @since 1.0.0
 */
public class Random
{
	/**
	 * 
	 * @since 1.0.0
	 */
	public static Randomizer LinearCongruent()
	{
		return new LinearCongruential();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static Randomizer MersenneTwister()
	{
		return new MersenneTwister();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static Randomizer SystemRandomizer()
	{
		return new SystemRandomizer();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	private static Randomizer instance = SystemRandomizer();
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static Randomizer getRandomizer() {return instance;}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void setRandomizer( Randomizer r) {instance = r;}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static double Double() 
	{
		return instance._double();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static float Float() 
	{
		return instance._float();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static boolean Boolean() 
	{
		return instance._boolean();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static double Double(double low, double high) 
	{
		return instance._double(low, high);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static float Float(float low, float high) 
	{
		return instance._float(low, high);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static int Integer(int low, int high) 
	{
		return instance._integer(low, high);
	}
}
