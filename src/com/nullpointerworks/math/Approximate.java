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
	 * A fast {@code pow(n,m)} function.
	 * @param n - base number
	 * @param m - the power to raise
	 * @return the approximate value of {@code n^m}
	 * @since 1.0.0
	 */
	public static final double pow(double n, double m) 
	{
	    long tmp = (long)(9076650*(n-1) / (n+1+4*(Math.sqrt(n)))*m + 1072632447);
	    return Double.longBitsToDouble(tmp << 32);
	}
	
	/**
	 * Fast sinusoidal wave approximation modeled after Bhaskara I's sine approximation formula. Error deviation from {@code Math.sin(x)} goes up to approximately {@code 1.86%}.
	 * @param x - angle in radians
	 * @return the approximate value of {@code sin(x)}
	 * @since 1.0.0
	 */
	public static final double sin(double x) 
	{
		if (x < 0.0)
		{
			return -sin(-x);
		}
		x %= TAU;
		if (x <= PI)
		{
			return bhaskara(x);
		}
		return -bhaskara(x-PI);
	}
	
	private static final double PI 	 = 3.141592654;
	private static final double TAU  = 2*PI;
	private static final double PI05 = 0.5*PI;
	private static final double PI35 = 3.0*PI05;
	private static final double bhaskara(double x) 
	{
		double r = x*(PI - x);
		double n = 4.0*r;
		double d = 12.3370057 - r;
		return n/d;
	}
	
	/**
	 * Fast approximation of a cardinal sine function. Uses an adaptation of Bhaskara I's sine approximation. Error deviation from {@code Math.sin(x)} goes up to approximately {@code 1.86%}.
	 * @param x - angle in radians
	 * @return the approximate value of {@code sinc(x)}
	 * @since 1.0.0
	 */
	public static final double sinc(double x) 
	{
		if (x==0.0) return 1.0;
		return sin(x)/x;
	}
	
	/**
	 * Fast cosinusoidal wave approximation modeled after Bhaskara I's sine approximation formula. Error deviation from {@code Math.cos(x)} goes up to approximately {@code 1.87%}.
	 * @param x - angle in radians
	 * @return the approximate value of {@code cos(x)}
	 * @since 1.0.0
	 */
	public static final double cos(double x) 
	{
		if (x < 0.0)
		{
			return cos(-x);
		}
		x %= TAU;
		if (x < PI05)
		{
			return bhaskara(x+PI05);
		}
		if (x < PI35)
		{
			return -bhaskara(x-PI05);
		}
		return bhaskara(x-PI35);
	}
	
	/*
	 * a set of magic numbers to compute the arc-tangent Taylor-series
	 */
	private final static float M = (float)(3.1415926535897932384626433832795 * 0.25);
	private final static float N = (float)(3.1415926535897932384626433832795 * 0.5);
	private final static float A = (float)(-0.27);
	private final static float B = (float)(0.077);
	private final static float C = (float)(-0.0102);
	private final static float X = (M - A - B - C);
	
	/**
	 * Very fast arc-tangent approximation inspired by the Taylor-series. Error deviation from {@code Math.atan(x)} goes up to approximately {@code 1.14%} for large input values.
	 * @param x - input value
	 * @return the approximate value of {@code arctan(x)}
	 * @since 1.0.0
	 */
	public static final double atan(double x) 
	{
		if (-1.0 <= x && x <= 1.0)
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
