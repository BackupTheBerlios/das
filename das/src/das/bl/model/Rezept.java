package das.bl.model;
import das.dao.RezeptDao;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.*;
import das.bl.service.ZutatenService;

import das.bl.model.Rezept;
import das.DasException;
import das.bl.model.Allergie;
import das.bl.model.Kategorie;
import das.bl.model.Zutat;
import das.dao.DbUtil;
import das.dao.AllergieDao;
import das.dao.KategorieDao;
import das.dao.RezeptDao;
import das.util.Query;
import das.util.QueryExpr;
import das.util.ResultType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * Domain klasse Rezept.
 */
public class Rezept {
    
    private Long id;
    private String name;
    private String anleitung;
    private String benutzer;
    private Float avgRating;
    public Map<Long,Long> zutaten; //zutat_id,wert
    
    public Rezept(){
        zutaten = new HashMap<Long,Long>();
    }
    
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
    
    public String getBenutzer(){
        return benutzer;
    }
    
    public void setBenutzer(String id) {
        this.benutzer = id;
    }
    
    public String getAnleitung() {
        return anleitung;
    }
    
    public void setAnleitung(String anleitung) {
        this.anleitung = anleitung;
    }
    
    public Float getAvgRating() {
        Connection con = null;
        try {
            con = DbUtil.getConnection();
            return RezeptDao.getBewertung(this,con);
        } catch(SQLException ex){
            throw new DasException("Average Rating konnte nicht geladen werden", ex);
        } finally {
            DbUtil.close(con);
        }
    }
    
    
}
