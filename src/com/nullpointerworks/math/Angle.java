package com.nullpointerworks.math;

public class Angle extends java.lang.Number
{
	private static final long serialVersionUID = -4886086630493221581L;

	public static final int RADIAN = 0;
	public static final int ARCSEC = 1;
	public static final int ARCMIN = 2;
	public static final int DEGREE = 3;
	
	// =========================================================
	
	private double origin = 0.0;
	private int o_unit = DEGREE;
	private double value = 0.0;
	private int unit = DEGREE;
	public Angle() {}
	public Angle(double v, int un) 
	{
		setValue(v,un);
	}

	private final double deg = 180.0 / Math.PI;
	private final double rad = 1.0 / deg;
	private final double radm = 1.0 / (60.0 * deg);
	private final double rads = 1.0 / (3600.0 * deg);
	
	public Angle setValue(double v, final int nu)
	{
		origin = value = v;
		o_unit = unit = nu;
		return this;
	}
	
	public Angle setUnit(final int nu)
	{
		if (nu == unit) return this;
		double base = 1.0;
		
		// convert current value to radians
		switch(o_unit)
		{
		case DEGREE:
			base = rad;
			break;
		case ARCMIN:
			base = radm;
			break;
		case ARCSEC:
			base = rads;
			break;
		default: break;
		}
		
		// convert to new unit
		switch(nu)
		{
		case DEGREE:
			base *= deg;
			break;
		case ARCMIN:
			base *= 60.0 * deg;
			break;
		case ARCSEC:
			base *= 3600.0 * deg;
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
