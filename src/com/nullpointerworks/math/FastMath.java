/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

public class FastMath
{
	/**
	 * e^x exponential approximation.
	 */
	public static final double exp(double val) 
	{
	    final long tmp = (long) (1512775 * val + 1072632447);
	    return Double.longBitsToDouble(tmp << 32);
	}
	
	/**
	 * fast low values exp() approximation. good accuracy where, x < 2
	 */
	public static final double l_exp(double x) 
	{
		  x = 1d + x / 256d;
		  x *= x; x *= x; x *= x; x *= x;
		  x *= x; x *= x; x *= x; x *= x;
		  return x;
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
	 * fast float absolute function
	 */
	public static final float abs(float n)
	{
		return Float.intBitsToFloat( 0x7fffffff & Float.floatToIntBits(n) );
	}

	/*
	 * optimized integer absolute finder. fails when MIN_VALUE is used
	 */
	public static final int intABS(int n)
	{
		return (n + (n >> 31)) ^ (n >> 31);
	}
	
	private final static float M_PI_4 = (float)(3.1415926535897932384626433832795 * 0.25);
	private final static float A = 0.0776509570923569f;
	private final static float B = -0.287434475393028f;
	private final static float C = (M_PI_4 - A - B);
	
	/*
	 * 
	 */
	public static final double atan(double x) 
	{
	  double xx = x * x;
	  return ((A*xx + B)*xx + C)*x;
	}
	
	/**
	 * fast 32bit ceiling function
	 */
	public static final float ceil(float n)
	{
		return ((float)(int)(n + ((n>0.0f)?1:0) ));
	}
	
	/**
	 * fast sinusoidal wave approximation<br>
	 * modeled after the Taylor-series
	 */
	public static final double sin(double n) 
	{
		double x = n%6.283185307;
		if (x<0.0d) return -sin(-n);
		double mod = -1d;
		if (x>3.1416d) 
		{
			mod = 1d;
			x -= 3.141592654d;
		}
		x = -x * .63661977237;
		double x2 = x * x;
		return mod*( ( ( (.00015148419 * x2 
						- .00467376557) * x2 
						+ .07968967928) * x2 
						- .64596371106) * x2 
						+ 1.57079631847) * x;
	}
	
	/**
	 * fast co-sinusoidal wave approximation<br>
	 * a modified sinewave, modeled after the Taylor-series
	 */
	public static final double cos(double n) 
	{
		double x = (n + 1.5707963267d)%6.283185307d;
		if (x<0.0d) return cos(-n);
		double mod = -1d;
		if (x>3.1416d) 
		{
			mod = 1d;
			x -= 3.141592654d;
		}
		x = -x * .63661977237;
		double x2 = x * x;
		return mod*( ( ( (.00015148419 * x2 
						- .00467376557) * x2 
						+ .07968967928) * x2 
						- .64596371106) * x2 
						+ 1.57079631847) * x;
	}
	
	/**
	 * Sine cardinal - sampling function
	 */
	public static final double sinc(double n) 
	{
		if (n==0.0) return 1d;
		return sin(n)/n;
	}
	
	// Icecore's blazing fast atan2  - needs to be tested
	
	private static final int Size_Ac = 100000;
    private static final int Size_Ar = Size_Ac + 1;
    private static final float Pi = (float) Math.PI;
    private static final float Pi_H = Pi / 2;

    private static final float Atan2[] = new float[Size_Ar];
    private static final float Atan2_PM[] = new float[Size_Ar];
    private static final float Atan2_MP[] = new float[Size_Ar];
    private static final float Atan2_MM[] = new float[Size_Ar];

    private static final float Atan2_R[] = new float[Size_Ar];
    private static final float Atan2_RPM[] = new float[Size_Ar];
    private static final float Atan2_RMP[] = new float[Size_Ar];
    private static final float Atan2_RMM[] = new float[Size_Ar];

    static 
    {
        for (int i = 0; i <= Size_Ac; i++) 
        {
            double d = (double) i / Size_Ac;
            double x = 1;
            double y = x * d;
            float v = (float) Math.atan2(y, x);
            Atan2[i] = v;
            Atan2_PM[i] = Pi - v;
            Atan2_MP[i] = -v;
            Atan2_MM[i] = -Pi + v;
            Atan2_R[i] = Pi_H - v;
            Atan2_RPM[i] = Pi_H + v;
            Atan2_RMP[i] = -Pi_H + v;
            Atan2_RMM[i] = -Pi_H - v;
        }
    }
    
    /**
     * very fast atan2 function<br>
     * returns a value in the range (-pi , pi]<br>
     */
    public static final float atan2(float y, float x) 
    {
        if (y < 0) 
        {
            if (x < 0) 
            {
                //(y < x) because == (-y > -x)
                if (y < x) 
                {
                    return Atan2_RMM[(int) (x / y * Size_Ac)];
                } 
                else 
                {
                    return Atan2_MM[(int) (y / x * Size_Ac)];
                }
            } 
            else 
            {
                y = -y;
                if (y > x) 
                {
                    return Atan2_RMP[(int) (x / y * Size_Ac)];
                } 
                else 
                {
                    return Atan2_MP[(int) (y / x * Size_Ac)];
                }
            }
        } 
        else 
        {
            if (x < 0) 
            {
                x = -x;
                if (y > x) 
                {
                    return Atan2_RPM[(int) (x / y * Size_Ac)];
                } 
                else 
                {
                    return Atan2_PM[(int) (y / x * Size_Ac)];
                }
            } 
            else 
            {
                if (y > x) 
                {
                    return Atan2_R[(int) (x / y * Size_Ac)];
                } 
                else 
                {
                    return Atan2[(int) (y / x * Size_Ac)];
                }
            }
        }
    }
}
