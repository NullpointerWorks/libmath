/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

/**
 * Provides fast approximation mathematics for when accuracy isn't critical, but performance is. <br>
 * <br>
 * Contains the following static methods:
 * <pre>
 * exp(x)
 * pow(n,m)
 * sin(x)
 * asin(x)
 * sinc(x)
 * cos(x)
 * acos(x)
 * atan(x)
 * atan2(y,x)</pre>
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
	private static final double PI025 = 0.25*PI;
	private static final double PI15 = 3.0*PI05;
	private static final double bhaskara(double x) 
	{
		double r = x*(PI - x);
		double n = 4.0*r;
		double d = 12.3370057 - r;
		return n/d;
	}
	
	/**
	 * Fast arc sinusoidal approximation using atan. Error deviation from {@code Math.asin(x)} goes up to approximately {@code 1.14%}.
	 * <pre>                     x
	 * asin(x) = atan( ---------- )
	 *                 sqrt(1-x^2)
	 * </pre>
	 * @param x - ratio to compute
	 * @return the approximate value of {@code arcsin(x)}
	 * @since 1.0.0
	 */
	public static final double asin(double x) 
	{
		if (x < -1.0) x = -1.0;
		if (x > 1.0) x = 1.0;
		double sqrt = Math.sqrt( 1.0 - x*x );
		if (sqrt == 0.0) return Double.MAX_VALUE;
		return atan( x / sqrt );
	}
	
	/**
	 * Fast approximation of a cardinal sine function. Uses an adaptation of Bhaskara I's sine approximation. Error deviation from {@code Math.sin(x)} goes up to approximately {@code 1.86%}.
	 * <pre>
	 * x = sin(x) / x,  where x != 0
	 * x = 1,           where x == 0</pre>
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
		if (x < PI15)
		{
			return -bhaskara(x-PI05);
		}
		return bhaskara(x-PI15);
	}
	
	/**
	 * Fast approximation of the arc cosine function. Error deviation from {@code Math.acos(x)} goes up to approximately {@code 0.514%}.
	 * @param x - ratio to compute
	 * @return the approximate value of {@code arccos(x)}
	 * @since 1.0.0
	 */
	public static final double acos(double x)
	{
		double r, s, t, u;
	    t = (x < 0) ? (-x) : x;
	    u = 1.0f - t;
	    s = Math.sqrt(u + u);
	    r = (0.10501094)*u*s + s;
	    if (x < 0) r = PI - r;
	    return r;
	}
	
	/*
	 * a set of magic numbers to compute the arc-tangent Taylor-series
	 */
	private final static double A = (-0.27);
	private final static double B = (0.077);
	private final static double C = (-0.0102);
	private final static double X = (PI025 - A - B - C);
	
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
			return -atan(1.0/x) + s*PI05;
		}
	}
	
	/**
	 * A fast approximation of {@code atan2(y,x)} function. Error deviation from {@code Math.atan2(y,x)} goes up to approximately {@code 0.148%}.
	 * @param y - input value
	 * @param x - input value
	 * @return the approximate value of {@code arctan2(y,x)}
	 * @since 1.0.0
	 */
	public static double atan2(double y, double x)
	{
	    if (x != 0.0f)
	    {
	        if ( ((x<0.0)?-x:x) > ((y<0.0)?-y:y)) // check abs(x) > abs(y)
	        {
	        	double z = y / x;
	            if (x > 0.0)
	            {
	                // atan2(y,x) = atan(y/x) if x > 0
	                return atan(z);
	            }
	            else if (y >= 0.0)
	            {
	                // atan2(y,x) = atan(y/x) + PI if x < 0, y >= 0
	                return atan(z) + PI;
	            }
	            else
	            {
	                // atan2(y,x) = atan(y/x) - PI if x < 0, y < 0
	                return atan(z) - PI;
	            }
	        }
	        else // Use property atan(y/x) = PI/2 - atan(x/y) if |y/x| > 1.
	        {
	        	double z = x / y;
	            if (y > 0.0)
	            {
	                // atan2(y,x) = PI/2 - atan(x/y) if |y/x| > 1, y > 0
	                return -atan(z) + PI05;
	            }
	            else
	            {
	                // atan2(y,x) = -PI/2 - atan(x/y) if |y/x| > 1, y < 0
	                return -atan(z) - PI05;
	            }
	        }
	    }
	    else
	    {
	        if (y > 0.0) // x = 0, y > 0
	        {
	            return PI05;
	        }
	        else 
	        if (y < 0.0) // x = 0, y < 0
	        {
	            return -PI05;
	        }
	    }
	    return 0.0; // x,y = 0. Could return NaN instead.
	}
}
