package das.bl.model;
import das.util.ObjName;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Domain klasse Zutat.
 */
public class Zutat {
	
	private Long id;
	private String name;
	private String einheit;
	private Float kalorien;
	private Integer fett;
	private Integer zucker;
	private Long katId;
	private Set<ObjName> allergien = new TreeSet<ObjName>();

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

	public Integer getFett() {
		return fett;
	}

	public void setFett(Integer fett) {
		this.fett = fett;
	}

	public Integer getZucker() {
		return zucker;
	}

	public void setZucker(Integer zucker) {
		this.zucker = zucker;
	}
	
	public Long getKatId(){
		return katId;
	}
	
	public void setKatId(Long katId){
		this.katId = katId;
	}
	
	public Set<ObjName> getAllergien(){
		return allergien;
	}
	
	public void setAllergien(Set<ObjName> allergien){
		this.allergien = allergien;
	}
}
