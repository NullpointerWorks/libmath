package com.nullpointerworks.math;

public class Numeral extends java.lang.Number
{
	private static final long serialVersionUID = 231247363245681850L;

	public static final int NULL = 0;
	public static final int METER = 1;
	public static final int DECIMETER = 2;
	public static final int CENTIMETER = 3;
	public static final int MILLIMETER = 4;
	public static final int MICROMETER = 5;
	public static final int NANOMETER = 6;
	
	// =========================================================
	
	private double origin = 0.0;
	private int o_unit = METER;
	
	private double value = 0.0;
	private int unit = METER;
	public Numeral() {}
	public Numeral(double v, final int un) 
	{
		origin = value = v;
		o_unit = unit = un;
	}

	public Numeral setValue(double v, final int nu)
	{
		origin = value = v;
		o_unit = unit = nu;
		return this;
	}
	
	public Numeral setUnit(int nu)
	{
		if (nu == unit) return this;
		double base = 1.0;
		
		// convert current value to meters
		switch(o_unit)
		{
		case NANOMETER:
			base = 0.000_000_001;
			break;
		case MICROMETER:
			base = 0.000_001;
			break;
		case MILLIMETER:
			base = 0.001;
			break;
		case CENTIMETER:
			base = 0.01;
			break;
		case DECIMETER:
			base = 0.1;
			break;
		default: break;
		}
		
		// convert to new unit
		switch(nu)
		{
		case NANOMETER:
			base *= 1000_000_000.0;
			break;
		case MICROMETER:
			base *= 1000_000.0;
			break;
		case MILLIMETER:
			base *= 1000.0;
			break;
		case CENTIMETER:
			base *= 100.0;
			break;
		case DECIMETER:
			base *= 10.0;
			break;
		default: break;
		}
		
		value = origin * base;
		unit = nu;
		return this;
	}
	
	public int getUnit() 
	{
		return unit;
	}
	
	@Override
	public double doubleValue() 
	{
		return value;
	}
	
	@Override
	public float floatValue() 
	{
		return (float)value;
	}
	
	@Override
	public int intValue() 
	{
		return (int)value;
	}
	
	@Override
	public long longValue() 
	{
		return (long)value;
	}
}
