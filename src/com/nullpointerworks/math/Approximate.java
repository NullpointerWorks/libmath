/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

/**
 * Provides fast approximation mathematics for when accuracy isn't critical, but performance is.
 * @since 1.0.0
 */
public class Approximate 
{
	/**
	 * Fast e^x exponential approximation.
	 * @param x - input value
	 * @return the approximate value of {@code e^x}
	 * @since 1.0.0
	 */
	public static final double exp(double x) 
	{
	    final long tmp = (long) (1512775 * x + 1072632447);
	    return Double.longBitsToDouble(tmp << 32);
	}
	
	/**
	 * fast pow(a,b) function
	 */
	public static final double pow(double a, double b) 
	{
	    long tmp = (long)(9076650*(a-1) / (a+1+4*(Math.sqrt(a)))*b + 1072632447);
	    return Double.longBitsToDouble(tmp << 32);
	}
	
	/**
	 * Fast approximation of a cardinal sine function.
	 * @param x - input value
	 * @return the approximate value of {@code sinc(x)}
	 * @since 1.0.0
	 */
	public static final double sinc(double x) 
	{
		if (x==0.0) return 1d;
		return sin(x)/x;
	}
	
	/**
	 * Fast sinusoidal wave approximation modeled after the Taylor-series.
	 * @param x - input value
	 * @return the approximate value of {@code sin(x)}
	 * @since 1.0.0
	 */
	public static final double sin(double x) 
	{
		double a = x%6.283185307;
		if (a<0.0d) return -sin(-x);
		double mod = -1.0;
		if (a>3.14159) 
		{
			mod = 1.0;
			a -= 3.141592654;
		}
		a = -a * .63661977237;
		double aa = a * a;
		return mod*( ( ( (.00015148419 * aa 
						- .00467376557) * aa 
						+ .07968967928) * aa 
						- .64596371106) * aa 
						+ 1.57079631847) * a;
	}
	
	/**
	 * Fast cosinusoidal wave approximation modeled after the Taylor-series.
	 * @param x - input value
	 * @return the approximate value of {@code cos(x)}
	 * @since 1.0.0
	 */
	public static final double cos(double x) 
	{
		double a = (x + 1.5707963267)%6.283185307;
		if (a<0.0d) return cos(-x);
		double mod = -1.0;
		if (a>3.14159) 
		{
			mod = 1.0;
			a -= 3.141592654;
		}
		a = -a * .63661977237;
		double aa = a * a;
		return mod*( ( ( (.00015148419 * aa 
						- .00467376557) * aa 
						+ .07968967928) * aa 
						- .64596371106) * aa 
						+ 1.57079631847) * a;
	}
	
	/*
	 * a set of magic numbers to compute the atan(x) Taylor-series
	 */
	private final static float M = (float)(3.1415926535897932384626433832795 * 0.25);
	private final static float N = (float)(3.1415926535897932384626433832795 * 0.5);
	private final static float A = (float)(-0.27);
	private final static float B = (float)(0.077);
	private final static float C = (float)(-0.0102);
	private final static float X = (M - A - B - C);
	
	/**
	 * Very fast arc-tangent approximation inspired by the Taylor-series.
	 * @param x - input value
	 * @return the approximate value of {@code arctan(x)}
	 * @since 1.0.0
	 */
	public static final double atan(double x) 
	{
		if (-1.0 < x && x < 1.0)
		{
			double xx = x * x;
			return (((C*xx + B)*xx + A)*xx + X)*x;
		}
		else
		{
			double s = (x<0.0)?-1.0:1.0;
			return -atan(1.0/x) + s*N;
		}
	}
}
