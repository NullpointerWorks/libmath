package exp.nullpointerworks.math.geometry.g3d;

import com.nullpointerworks.math.vector.Vector3;

public class Tetra extends Geometry3D
{
	private final Vector3 V3 = new Vector3();
	
	// location vertices
	private float[] a;
	private float[] b;
	private float[] c;
	private float[] d;
	
	// plane normals
	private float[] na;
	private float[] nb;
	private float[] nc;
	private float[] nd;
	
	public Tetra(float[] v0, float[] v1, float[] v2, float[] v3)
	{
		this.a=v0;
		this.b=v1;
		this.c=v2;
		this.d=v3;
		
		
	}
	
	/**
	 * <pre>
	 *     | (a-d)*((b-d)x(c-d)) |
	 * V = -----------------------
	 *                6
	 * </pre>
	 */
	@Override
	public float volume()
	{
		float[] f1 = V3.sub(a, d);
		float[] f2 = V3.sub(b, d);
		float[] f3 = V3.sub(c, d);
		float[] cross = V3.cross(f2, f3);
		float dot = Math.abs( V3.dot(f1, cross) );
		return dot / 6f;
	}

	@Override
	public void offset(float x, float y, float z)
	{
		
	}
	
	@Override
	public float[] center()
	{
		float x = a[0] + b[0] + c[0] + d[0];
		float y = a[1] + b[1] + c[1] + d[1];
		float z = a[2] + b[2] + c[2] + d[2];
		return new float[] {x*0.25f, y*0.25f, z*0.25f};
	}
	
	@Override
	public Box getBoundingBox()
	{
		return null;
	}
	
	@Override
	public Geometry3D copy()
	{
		return new Tetra(a,b,c,d);
	}
	
	@Override
	public Type type()
	{
		return Type.Tetra;
	}
	
	@Override
	public boolean isInside(float px, float py, float pz)
	{
		return false;
	}

}
