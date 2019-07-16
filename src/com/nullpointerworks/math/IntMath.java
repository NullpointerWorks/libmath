/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math;

public class IntMath 
{
	
	/*
	 * returns the exponential value of a base number with the given power
	 */
	public static int pow(int n, int pow)
	{
		switch(pow)
		{
		case 0: return 1;
		case 1: return n;
		default: return n * pow(n,pow-1);
		}
	}
	
	/*
	 * copy an array without using clone()
	 */
	public static int[] copy(int[] a)
	{
		int i=0,l=a.length;
		int[] res = new int[l];
		for(;i<l;i++) res[i] = a[i];
		return res;
	}
	
	/*
	 * lerp two integers
	 */
	public static int lerp(int c1, int c2, float alpha)
	{
		return (int)( (float)c1*(1f-alpha) + (float)c2*(alpha) );
	}
	
	/*
	 * lerp two integers using lerp range [0-2^pow]
	 */
	public static int intLerp(int A, int B, int F, int pow)
	{
		return (A*((1<<pow)-F) + B * F) >> pow;
	}
	
	/*
	 * lerp two integers using lerp range [0-256]
	 */
	public static int intLerp256(int A, int B, int F)
	{
		return (A*(256-F) + B * F) >> 8;
	}
	
	/*
	 * lerp two integers using lerp range [0-512]
	 */
	public static int int512(int A, int B, int F)
	{
		return (A*(512-F) + B * F) >> 9;
	}

	/*
	 * lerp two integers using lerp range [0-1024]
	 */
	public static int intLerp1024(int A, int B, int F)
	{
		return (A*(1024-F) + B * F) >> 10;
	}
	
	
	/**
	 * returns the ceiling of a number as an integer
	 */
	public static int ceil(float x)
	{
		return (int)Math.ceil(x);
	}
	
	/**
	 * return absolute of an integer
	 */
	public static int abs(int x)
	{
		return ((x >= 0) ? x : -x);
	}
	
	/**
	 * clamp a given value between two values.
	 */
	public static int clamp(int lower, int x, int upper)
	{
		int x1 = (x<lower)?lower:x;
		return (x1<upper)?x1:upper;
	}

	/**
	 * find the smallest number of three numbers
	 */
	public static int min(int x1,int x2,int x3)
	{
		int x = (x1<x2)?x1:x2;
		return (x<x3)?x:x3;
	}
	
	/*
	 * test if a value is strictly between the interval
	 */
	public static boolean between(int x1, int v, int x2)
	{
		return (x1 < v && v < x2);
	}

	/**
	 * find the smallest number of three numbers
	 */
	public static int min(int x1,int x2)
	{
		return (x1>x2)?x2:x1;
	}
	
	/**
	 * find the middle number of three numbers
	 */
	public static int mid(int a,int b,int c)
	{
		return a>b ? (c>a ? a : (b>c ? b:c) ) : ( c>b ? b : (a>c ? a:c) );
	}
	
	/**
	 * find the largest number of three numbers
	 */
	public static int max(int x1,int x2,int x3)
	{
		int x = (x1<x2)?x2:x1;
		return (x<x3)?x3:x;
	}
	
	/**
	 * find the largest number of three numbers
	 */
	public static int max(int x1,int x2)
	{
		 return (x1<x2)?x2:x1;
	}
	
	/**
	 * round a float to an integer
	 */
	public static int round(float x)
	{
		return (int)(x + 0.5f);
	}
	
	/**
	 * round a float to an integer
	 */
	public static int round(double x)
	{
		return (int)(x + 0.5f);
	}
}
