package org.dam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.sql.SQLOutput;

public class EquipoDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public boolean anadirEquipo(Equipo e){
        em = JpaNbaManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        return true;
    }

    /**
     * Asigna un entrenador a un equipo, enviándole el entrenador del que extrae el id y asignándoselo al campo idEntrenador de Equipo
     * @param entrenador
     * @param idEquipo
     * @return
     */
    public boolean asignarEntrenador(Entrenador entrenador, Long idEquipo){
        EntityManager em = JpaNbaManager.getEntityManager();
        Equipo equipo = em.find(Equipo.class,idEquipo);
        if (equipo != null){
            em.getTransaction().begin();
            equipo.setEntrenador(entrenador);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public boolean asignarEquipo(Equipo equipo, Long idEntrenador){
        EntityManager em = JpaNbaManager.getEntityManager();
        Entrenador entrenador = em.find(Entrenador.class,idEntrenador);
        if (entrenador != null){
            em.getTransaction().begin();
            entrenador.setEquipo(equipo);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    /**
     * Muestra datos del equipo con idEquipo pasado por parámetro
     * Obtiene idEntrenador de ese equipo
     * Muestra datos del entrenador a partir de ese idEntrenador
     * @param idEquipo
     */
    public void mostrarEquipoYEntrenador(Long idEquipo){
        EntityManager em = JpaNbaManager.getEntityManager();

        try {
            Equipo equipo = em.find(Equipo.class,idEquipo);
            if (equipo == null) {
                System.out.println("No se encontró equipo con id " + idEquipo);
                return;
            }

            System.out.println("EQUIPO:");
            System.out.println(equipo);

            Entrenador entrenador = equipo.getEntrenador();
            if (entrenador != null){
                System.out.println("ENTRENADOR:");
                System.out.println(entrenador);
            } else {
                System.out.println("Este equipo no tiene entrenador asignado");
            }

        } finally {
            em.close();
        }

    }

}
