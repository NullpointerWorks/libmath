package com.nullpointerworks.math.random.generator;

import com.nullpointerworks.math.random.generator.abstracts.AbstractRandomizer;

/**
 *  Linear Congruential Generator
 *	a seeded Pseudo Random Number Generator<br>
 *	LCG - linear congruent generator<br>
 *	x(i+1) = (a * x(i) + b) % m<br>
 *	<br>
 *	use seed(long) to set a new seed for the sequence<br>
 *	use seed() to get the current seed in the sequence<br>
 *	use random() to get a new random number<br>
 */
public class LinearCongruential extends AbstractRandomizer
{
	/*
	 * a = multiplier
	 * i = incrementor
	 * m = modulus
	 * x = current seed
	 * 
	 * Using the recipe from Numerical Values
	 */
	private final long a = 1664525;
	private final long i = 1013904223;
	private final long m = pow(2,32);
    private long x = 0;
    
    /**
     * recursive power function
     */
	private final long pow(long n, int pow)
	{
		switch(pow)
		{
		case 0: return 1;
		case 1: return n;
		default: return n * pow(n,pow-1);
		}
	}
    
    /**
     * set the seed for the lcg algorithm.
     */
    public final void seed(long seed) 
    {
    	x = seed; // set seed
    }
    
    /**
     * returns the current seed.
     */
    public final long seed() 
    {
    	return x;
    }
    
    /**
     * get next random number in the sequence ranging [0,1)
     */
    public final double _double() 
    {
        x = (a * x + i) % m;
        return (double)x / m;
    }
    
    /**
     * get next random number in the sequence ranging [0,1)
     */
    public final float _float() 
    {
        x = (a * x + i) % m;
        return (float)x / m;
    }
    
	public boolean _boolean()
	{
		double r = _double();
		return r < 0.5;
	}
    
    /**
     * get next random number in the sequence.<br>
     * parameters low and high indicate the desired range of the random value
     */
    public final double _double(double low, double high) 
    {
        double r = _double();
        return low + r*(high-low);
    }
    
    /**
     * get next random float in the sequence.<br>
     * parameters low and high indicate the desired range of the random value
     */
    public final float _float(float low, float high) 
    {
    	float r = (float)_double();
        return low + r*(high-low);
    }
	
    /**
     * get next random integer in the sequence.<br>
     * parameters low and high indicate the desired range of the random value
     */
	public int _integer(int low, int high)
	{
		double r = _double();
        return low + (int)( r*(high-low) + 0.5);
	}
}