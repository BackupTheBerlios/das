package das.bl.model;

/**
 * Domain klasse Zutat.
 */
public class Zutat {
	
	private Long id;
	private String name;
	private String einheit;
	private Float kalorien;
	private Float fett;
	private Float zucker;
	private Long katId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEinheit() {
		return einheit;
	}

	public void setEinheit(String einheit) {
		this.einheit = einheit;
	}

	public Float getKalorien() {
		return kalorien;
	}

	public void setKalorien(Float kalorien) {
		this.kalorien = kalorien;
	}

	public Float getFett() {
		return fett;
	}

	public void setFett(Float fett) {
		this.fett = fett;
	}

	public Float getZucker() {
		return zucker;
	}

	public void setZucker(Float zucker) {
		this.zucker = zucker;
	}
	
	public Long getKatId(){
		return katId;
	}
	
	public void setKatId(Long katId){
		this.katId = katId;
	}
}
