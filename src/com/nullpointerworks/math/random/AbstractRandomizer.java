/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.random;

/**
 * An abstraction of a custom random number generator. Can be set in the Random class for later use.
 * @since 1.0.0
 */
abstract class AbstractRandomizer implements Randomizer 
{
	@Override
	public abstract double _double();
	
	@Override
	public abstract float _float();
	
	@Override
	public abstract boolean _boolean();
	
	@Override
	public abstract double _double(double low, double high);
	
	@Override
	public abstract float _float(float low, float high);
	
	@Override
	public abstract int _integer(int low, int high);
	
	@Override
	public long seed() {return 0;}
	
	@Override
	public void seed(long s) {}
}
