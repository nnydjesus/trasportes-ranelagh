package ar.com.nny.base.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import ar.com.nny.base.dao.GenericDao;
import ar.com.nny.base.exception.NonBusinessException;
import ar.com.nny.base.persistence.PersistentObject;
import ar.com.nny.base.utils.IdentificablePersistentObject;

@Entity
public class IdGenerator extends IdentificablePersistentObject implements Serializable{

    @Transient
    private static  IdGenerator INSTANCE = null;
//    
    @Id
    private String id="00001";

    @Transient
    private static  GenericDao<PersistentObject> dao;
    
    @Basic
    private Double current = (double)1/10000;
    
    public static synchronized IdGenerator getInstance(){
        dao = new GenericDao<PersistentObject>(IdGenerator.class);
        IdGenerator INSTANCE = null;
        IdGenerator byId;
        if(INSTANCE == null){
            try {
                byId = (IdGenerator) dao.getById("00001");                
            } catch (NonBusinessException e) {
                byId = new IdGenerator();
                dao.save(byId);
            }
            INSTANCE = byId;
        }
        return INSTANCE;
        
    }
    
    
    public String nextId() {
        current+= (double)0.0001;
        dao.update(this);
        return current.toString();
    }


    @Override
    public String[] atributos() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void main(String[] args) {
//        System.out.println((double)1/1000);
        System.out.println(IdGenerator.getInstance().nextId());
    }
    

}
