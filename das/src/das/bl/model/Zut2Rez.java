package das.bl.model;


/**
 * Domain klasse Zut2Rez
 */
public class Zut2Rez {
	
	private Long zutatId;
	private Long rezeptId;
	private Double menge;

	public Long getZutatId() {
		return zutatId;
	}

	public void setZutatId(Long zutatId) {
		this.zutatId = zutatId;
	}

	public Long getRezeptId() {
		return rezeptId;
	}

	public void setRezeptId(Long rezeptId) {
		this.rezeptId = rezeptId;
	}

	public Double getMenge() {
		return menge;
	}

	public void setMenge(Double menge) {
		this.menge = menge;
	}
	
}
