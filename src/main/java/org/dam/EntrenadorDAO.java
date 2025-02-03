package org.dam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class EntrenadorDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public boolean anadirEntrenador(Entrenador e){
        em = JpaNbaManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        return true;
    }

    public Entrenador obtenerEntrenador(Long idEntrenador){
        em = JpaNbaManager.getEntityManager();
        em.getTransaction().begin();
        Entrenador entrenador = em.find(Entrenador.class,idEntrenador);
        em.getTransaction().commit();
        return entrenador;
    }



}
