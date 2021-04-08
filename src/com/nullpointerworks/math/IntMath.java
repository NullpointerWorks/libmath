/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math;

/**
 * Contains static member functions to allow for various integer operations. Like interpolation, min,mid,max functions etc.
 * @since 1.0.0
 */
public class IntMath 
{
	/**
	 * Returns a reference free copy of the given array.
	 * @param a - the integer array
	 * @return a reference free copy of the given array
	 * @since 1.0.0
	 */
	public static int[] copy(int[] a)
	{
		int i=0,l=a.length;
		int[] res = new int[l];
		for(;i<l;i++) res[i] = a[i];
		return res;
	}
	
	/**
	 * Returns the power of a base number with the given exponent.
	 * @param n - the base number
	 * @param pow - the exponent
	 * @return the power of a base number with the given exponent
	 * @since 1.0.0
	 */
	public static int pow(int n, int pow)
	{
		if (pow == 0) return 1;
		if (pow == 1) return n;
		return n * pow(n,pow-1);
	}
	
	/**
	 * Optimized integer absolute function. This fails when MIN_VALUE is entered.
	 * @param n - input value
	 * @return the absolute value of the given integer
	 * @since 1.0.0
	 * @since 1.0.0
	 */
	public static final int absx(int n)
	{
		return (n + (n >> 31)) ^ (n >> 31);
	}
	
	/**
	 * Returns the absolute value of the given integer.
	 * @param n - input value
	 * @return the absolute value of the given integer
	 * @since 1.0.0
	 */
	public static int abs(int x)
	{
		return ((x >= 0) ? x : -x);
	}
	
	/**
	 * Returns the ceiling of a number as an integer.
	 * @param x - the input value
	 * @return the ceiling of a number as an integer
	 * @since 1.0.0
	 */
	public static int ceil(float x)
	{
		return (int)Math.ceil(x);
	}
	
	/**
	 * Integer based linear interpolation using {@code 2^10} as the exponent to define a precision. The interpolation factor is a value that range from {@code [0 - 1023]}.
	 * @param A - the starting value
	 * @param B - the end value
	 * @param F - the interpolation factor in the range of {@code [0 - 1023]}
	 * @return the resulting interpolant as an integer
	 * @since 1.0.0
	 * @see intlerp
	 */
	public static int lerp(int A, int B, int F)
	{
		return (A*(1023-F) + B * F) >> 10;
	}
	
	/**
	 * Integer based linear interpolation uses an exponent to define the stepping precision. The interpolation factor is a value that range from {@code [0 - 2^pow]}. For instance, interpolation that can utilize low precision can use a low exponent. If an exponent of {@code 8} is used then the interpolation factor {@code F} needs to be within the range of {@code 0} to {@code 255}, which is the upper range of {@code 2^8 = 256}.
	 * @param A - the starting value
	 * @param B - the end value
	 * @param F - the interpolation factor in the range of {@code [0 - 2^pow]}
	 * @param pow - exponential precision
	 * @return the resulting interpolant as an integer
	 * @since 1.0.0
	 */
	public static int lerp(int A, int B, int F, int pow)
	{
		return (A*((1<<pow)-F) + B * F) >> pow;
	}
	
	/**
	 * Clamps the given value of {@code x} between an lower and upper limit.
	 * @param lower - the lower limit
	 * @param x - the value to clamp
	 * @param upper - the upper limit
	 * @since 1.0.0
	 */
	public static int clamp(int lower, int x, int upper)
	{
		int x1 = (x<lower)?lower:x;
		return (x1<upper)?x1:upper;
	}

	/**
	 * Returns the largest of two numbers. When the number are equal, this returns the value of {@code a}.
	 * @param a - a number
	 * @param b - another number
	 * @return the largest of two numbers
	 * @since 1.0.0
	 */
	public static int max(int a, int b) 
	{
		return (a<b)?b:a;
	}
	
	/**
	 * Returns the smallest of two numbers. When the number are equal, this returns the value of {@code a}.
	 * @param a - a number
	 * @param b - another number
	 * @return the smallest of two numbers
	 * @since 1.0.0
	 */
	public static int min(int a, int b) 
	{
		return (a<b)?a:b;
	}
	
	/**
	 * Returns the largest of three numbers.
	 * @param a - a number
	 * @param b - another number
	 * @param c - yet another number
	 * @return the largest of three numbers
	 * @since 1.0.0
	 */
	public static int max(int a,int b,int c)
	{
		int x = (a<b)?b:a;
		return (x<c)?c:x;
	}
	
	/**
	 * Returns the middle of three numbers.
	 * @param a - a number
	 * @param b - another number
	 * @param c - yet another number
	 * @return the middle of three numbers
	 * @since 1.0.0
	 */
	public static float mid(int a,int b,int c)
	{
		return a>b ? (c>a ? a : (b>c ? b:c) ) : ( c>b ? b : (a>c ? a:c) );
	}
	
	/**
	 * Returns the smallest of three numbers.
	 * @param a - a number
	 * @param b - another number
	 * @param c - yet another number
	 * @return the smallest of three numbers
	 * @since 1.0.0
	 */
	public static int min(int a,int b,int c)
	{
		int x = (a<b)?a:b;
		return (x<c)?x:c;
	}
	
	/**
	 * Round a float to an integer.
	 * @param x - input value
	 * @return a float rounded to an integer
	 * @since 1.0.0
	 */
	public static int round(float x)
	{
		return (int)(x + 0.5f);
	}
	
	/**
	 * Round a double to an integer.
	 * @param x - input value
	 * @return a double rounded to an integer
	 * @since 1.0.0
	 */
	public static int round(double x)
	{
		return (int)(x + 0.5);
	}
}
