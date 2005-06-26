package das.bl.model;

import das.util.ObjName;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Domain klasse User
 *
 * @author Mario; k
 */
public class User {
	
	private Long id;
	private String login;
	private String name;
	private String passwort;
	private Long gruId;
	private String email;
	//private Map<Long,Bewertung> bewertungen = new LinkedHashMap<Long,Bewertung>();
	private Set<ObjName> allergien = new TreeSet<ObjName>();

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGruId(){
		return gruId;
	}
	
	public void setGruId(Long gruId){
		this.gruId = gruId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	public void addBewertung(Bewertung b){
		bewertungen.put(b.getId(), b);
	}
	
	public void removeBewertung(Long bewertungId){
		bewertungen.remove(bewertungId);
	}
	
	public Collection<Bewertung> getBewertungen(){
		return bewertungen.values();
	}
	 */
	
	public Set<ObjName> getAllergien(){
		return allergien;
	}
	
	public void setAllergien(Set<ObjName> allergien){
		this.allergien = allergien;
	}
}
