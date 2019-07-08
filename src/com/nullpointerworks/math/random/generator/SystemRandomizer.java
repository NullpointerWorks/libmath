package com.nullpointerworks.math.random.generator;

import com.nullpointerworks.math.random.generator.abstracts.AbstractRandomizer;

public class SystemRandomizer extends AbstractRandomizer
{
	public final double _double() 
	{
		return StrictMath.random();
	}
	
	/*
	 * range [0,1)
	 */
	public final double _double(double low, double high) 
    {
        double r = StrictMath.random();
        return low + r*(high-low);
    }
	
	/*
	 * range [0,1)
	 */
	public final float _float() 
	{
		return (float)StrictMath.random();
	}
    
	public boolean _boolean()
	{
		double r = StrictMath.random();
		return r < 0.5;
	}
	
	public final float _float(float low, float high) 
    {
		float r = (float)StrictMath.random();
        return low + r*(high-low);
    }
	
	public int _integer(int low, int high)
	{
		double r = StrictMath.random();
        return low + (int)( r*(high-low) + 0.5);
	}

	public long seed() 
	{
		return -1;
	}
	
	public void seed(long s) 
	{
		
	}
}
