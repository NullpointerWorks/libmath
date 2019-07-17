/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.random;

/**
 * The {@code SystemRandomizer} is an implementation of the JVM's {@code StrictMath.random()} randomizer. This is a hardware randomizer that does not allow for seeding.
 * @since 1.0.0
 */
public class SystemRandomizer extends AbstractRandomizer
{
	public final double _double() 
	{
		return StrictMath.random();
	}
	
	@Override
	public final double _double(double low, double high) 
    {
        double r = StrictMath.random();
        return low + r*(high-low);
    }

	@Override
	public final float _float() 
	{
		return (float)StrictMath.random();
	}

	@Override
	public final float _float(float low, float high) 
    {
		float r = (float)StrictMath.random();
        return low + r*(high-low);
    }

	@Override
	public boolean _boolean()
	{
		double r = StrictMath.random();
		return r < 0.5;
	}

	@Override
	public int _integer(int low, int high)
	{
		double r = StrictMath.random();
        return low + (int)( r*(high-low) + 0.5);
	}
	
	@Override
	public long seed() 
	{
		return -1;
	}

	@Override
	public void seed(long s) 
	{
		
	}
}
