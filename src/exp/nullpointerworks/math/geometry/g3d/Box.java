package exp.nullpointerworks.math.geometry.g3d;

import com.nullpointerworks.math.vector.Vector3;

public class Box extends Geometry3D
{
	public Tetra t1,t2,t3,t4,t5,t6;
	public float x=0,y=0,z=0,w=0,h=0,d=0;
	
	public Box() {}
	
	public Box(float x, float y, float z, float w, float h, float d)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.w=w;
		this.h=h;
		this.d=d;
		recalculate();
	}

	@Override
	public Type type() 
	{
		return Type.Box;
	}

	@Override
	public boolean isInside(float px, float py, float pz)
	{
		if (t1.isInside(px, py, pz)) return true;
		if (t2.isInside(px, py, pz)) return true;
		if (t3.isInside(px, py, pz)) return true;
		if (t4.isInside(px, py, pz)) return true;
		if (t5.isInside(px, py, pz)) return true;
		if (t6.isInside(px, py, pz)) return true;
		return false;
	}
	
	@Override
	public float[] center()
	{
		return new float[] {x+(w*0.5f), y+(h*0.5f), z+(d*0.5f)};
	}
	
	@Override
	public float volume()
	{
		return w*h*d;
	}
	
	@Override
	public void offset(float x, float y, float z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
		recalculate();
	}
	
	@Override
	public Geometry3D copy()
	{
		return new Box(x,y,z,w,h,d);
	}
	
	@Override
	public Box getBoundingBox() 
	{
		return this;
	}
	
	// ==========================================
	
	private void recalculate()
	{
		float[] v0 = Vector3.New(x, y, z);
		float[] v1 = Vector3.New(x+w, y, z);
		float[] v2 = Vector3.New(x, y+h, z);
		float[] v3 = Vector3.New(x, y, z+d);
		
		t1 = new Tetra(v0,v1,v2,v3);
		
		
	}
}
