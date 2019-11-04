/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.velsoft.mvn2.dao;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SGEN0290
 */
public class EntityManagerHelper implements Serializable {

    private static final long serialVersionUID = -8759151984927361294L;

    /* esta cadena tiene que coincidir con la propiedad name de la persistence unit en el persistence.xml */
    private static final String ENTITYMANAGER = "eg_JPA_MYSQL";
    //private static final String ENTITYMANAGER = "eg_JPA_SQLSERVER";
    private static final EntityManagerFactory EMF;

    /* esto es para implementar singleton */
    static {
        try {

            EMF = Persistence.createEntityManagerFactory(ENTITYMANAGER);
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    private EntityManagerHelper() {
        super();
    }

    public static EntityManagerFactory getEMF() {
        return EMF;
    }
}
