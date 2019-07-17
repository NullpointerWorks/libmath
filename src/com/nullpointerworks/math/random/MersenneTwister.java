/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.random;

/**
 * The Mersenne twister is a pseudo random number generator that uses a table of bit-states that help shuffle the seed when generating the next random number. 
 * 
 * @since 1.0.0
 * @see https://en.wikipedia.org/wiki/Mersenne_Twister
 */
public class MersenneTwister extends AbstractRandomizer
{
	private int next			= 0;
	private int seed 			= 5489;
	private final int m 		= 397;
	private final int size 		= 624;
	private final int offset 	= size - m;
	private int[] bitstate		= new int[size];
	private final double invMAX = 1.0 / (double)Integer.MAX_VALUE;
	
	/**
	 * Creates a new Mersenne twister object and sets the initial state.
	 * @since 1.0.0
	 */
	public MersenneTwister()
	{
		seed(seed);
	}

    @Override
	public void seed(long seed) 
    {
		this.seed = (int)seed;
		bitstate[0] = this.seed;
		
		for (int i = 1; i < size; i++)    
			bitstate[i] = 1812433253 * (bitstate[i-1] ^ (bitstate[i-1] >> 30)) + i;
		
		twist();
    }

    @Override
    public long seed() 
    {
    	return seed;
    }

    @Override
    public double _double() 
    {
    	if (next >= size) twist();
    	
    	int x = bitstate[next++];   
    	x ^= (x >> 11);
    	x ^= (x <<  7) & 0x9d2c5680;
    	x ^= (x << 15) & 0xefc60000;
    	x ^= (x >> 18);
    	
    	return (double)x * invMAX;
    }

    @Override
    public double _double(double low, double high) 
    {
        double r = _double();
        return low + r*(high-low);
    }

    @Override
    public float _float() 
    {
    	if (next >= size) twist();
    	int x = bitstate[next++];   
    	x ^= (x >> 11);
    	x ^= (x <<  7) & 0x9d2c5680;
    	x ^= (x << 15) & 0xefc60000;
    	x ^= (x >> 18);
    	return (float)(x * invMAX);
    }

    @Override
	public boolean _boolean()
	{
		double r = _double();
		return r < 0.5;
	}

    @Override
    public float _float(float low, float high) 
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
	
    /*
     * recompute the state of the twister
     */
    private void twist()
    {
    	int i; 
    	
    	for (i = 0; i<offset; i++)   
    	{     
    		int bits = (bitstate[i] & 0x80000000) | (bitstate[i + 1] & 0x7fffffff);     
    		bitstate[i] = bitstate[i + m] ^ (bits >> 1) ^ ((bits & 1) * 0x9908b0df);   
    	}   
    	
    	for (int l = size-1; i<l; i++)   
    	{     
    		int bits = (bitstate[i] & 0x80000000) | (bitstate[i + 1] & 0x7fffffff);     
    		bitstate[i] = bitstate[i - offset] ^ (bits >> 1) ^ ((bits & 1) * 0x9908b0df);   
    	}
    	
    	int bits = (bitstate[i] & 0x80000000) | (bitstate[0] & 0x7fffffff);   
    	bitstate[i] = bitstate[m - 1] ^ (bits >> 1) ^ ((bits & 1) * 0x9908b0df);   
    	
    	next = 0;
    }
}