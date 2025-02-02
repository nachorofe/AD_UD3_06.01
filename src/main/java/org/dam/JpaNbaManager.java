package org.dam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.IOException;

public class JpaNbaManager {
    public static final String UNIT_NAME = "equipos";
    private static EntityManagerFactory emf;

    private JpaNbaManager() {
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        if (emf == null || !emf.isOpen()) {
            synchronized (JpaNbaManager.class){
                if (emf == null || !emf.isOpen()){
                    try {
                        emf = Persistence.createEntityManagerFactory(UNIT_NAME);
                    } catch (Exception e){
                        System.out.println("Error al crear la unidad de persistencia: " + e.getMessage());
                    }
                }
            }
        }
        return emf;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

}
