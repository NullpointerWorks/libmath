/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.random;

/**
 * Linear Congruential Generator(a.k.a. LCG) is a seeded pseudo random number generator that follows a simple equation accompanied with a few magic numbers. There are various recipes available that produce varying results. Some produce very little randomization before repeating the series, others can produce millions of seemingly random numbers before any pattern appears.<br><br>
 * The general equation for a LGC is:
 * <pre>x(i+1) = (a * x(i) + b) % m</pre>
 * where,
 * <pre>
 * a = multiplier
 * i = incrementor
 * m = modulus
 * x = current seed</pre>
 * This LGC uses the following values as its recipe:
 * <pre>
 * a = 1664525
 * i = 1013904223
 * m = 2^32</pre>
 * @since 1.0.0
 */
public class LinearCongruential extends AbstractRandomizer
{
	/*
	 * Using the recipe from Numerical Values
	 */
	private final long a = 1664525;
	private final long i = 1013904223;
	private final long m = pow(2,32);
    private long x = 0;
    private long pow(long n, long pow)
	{
		if (pow == 0) return 1;
		if (pow == 1) return n;
		return n * pow(n,pow-1);
	}
    
    @Override
    public final void seed(long seed) 
    {
    	x = seed;
    }

    @Override
    public final long seed() 
    {
    	return x;
    }
    
    @Override
    public final double _double() 
    {
        x = (a * x + i) % m;
        return (double)x / m;
    }
    

    @Override
    public final float _float() 
    {
        return (float)_double();
    }

    @Override
	public boolean _boolean()
	{
		double r = _double();
		return r < 0.5;
	}
    

    @Override
    public final double _double(double low, double high) 
    {
        double r = _double();
        return low + r*(high-low);
    }
    

    @Override
    public final float _float(float low, float high) 
    {
    	float r = (float)_double();
        return low + r*(high-low);
    }
	

    @Override
	public int _integer(int low, int high)
	{
		double r = _double();
        return low + (int)( r*(high-low) + 0.5);
	}
}