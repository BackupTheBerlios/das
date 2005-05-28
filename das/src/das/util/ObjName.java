package das.util;

public class ObjName implements Comparable<ObjName> {
	
	private Comparable id;
	private String name;
	
	public ObjName(Comparable id, String name){
		if (id == null)
			throw new NullPointerException("id");
		
		this.id = id;
		this.name = name;
	}

	public Comparable getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String toString(){
		return "[" + id + "] " + name;
	}
	
	public boolean equals(Object other){
		ObjName otherName = (ObjName)other;
		
		if (otherName == null)
			return false;
		
		return this.id.equals(otherName.id);
	}
	
	public int compareTo(ObjName other){
		return id.compareTo(other.id);
	}
}
