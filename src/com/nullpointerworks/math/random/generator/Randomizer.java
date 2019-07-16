package com.nullpointerworks.math.random.generator;

public interface Randomizer
{
	double _double();

	float _float();

	boolean _boolean();

	double _double(double low, double high);

	float _float(float low, float high);

	int _integer(int low, int high);

	long seed();

	void seed(long s);

}