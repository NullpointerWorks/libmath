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

public class Random
{
	public static Randomizer LinearCongruent()
	{
		return new LinearCongruential();
	}
	
	public static Randomizer MersenneTwister()
	{
		return new MersenneTwister();
	}
	
	public static Randomizer SystemRandomizer()
	{
		return new SystemRandomizer();
	}
	
	private static Randomizer instance = SystemRandomizer();
	public static Randomizer getRandomizer() {return instance;}
	public static void setRandomizer( Randomizer r) {instance = r;}
	
	public static double Double() 
	{
		return instance._double();
	}
	
	public static float Float() 
	{
		return instance._float();
	}
	
	public static boolean Boolean() 
	{
		return instance._boolean();
	}
	
	public static double Double(double low, double high) 
	{
		return instance._double(low, high);
	}
	
	public static float Float(float low, float high) 
	{
		return instance._float(low, high);
	}
	
	public static int Integer(int low, int high) 
	{
		return instance._integer(low, high);
	}
	
}
