package DomainObject;

public class Hole {
	
	/**
	 * Hole object represents each data record in the input file
	 */

	public String id;
	public float top;
	public float base;
	
	public Hole(String id, float top, float base) {
		super();
		this.id = id;
		this.base = base;
		this.top = top;
		
	}


	@Override
	public String toString() {
		return "Hole [id=" + id + ", top=" + top + ", base=" + base +"]";
	}
	
	
}
