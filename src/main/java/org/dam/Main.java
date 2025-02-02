package org.dam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void anadirEquipo(){
        Equipo equipo = new Equipo();
        try {
            System.out.println("AÑADIR EQUIPO");
            System.out.print("Introduce nombre: ");
            equipo.setNombre(br.readLine());
            System.out.print("Introduce ciudad: ");
            equipo.setCiudad(br.readLine());
            String conferenciaStr;
            do {
                System.out.print("Introduce conferencia (E-Este, O-Oeste): ");
                conferenciaStr = br.readLine();
                if (!conferenciaStr.equalsIgnoreCase("E") && !conferenciaStr.equalsIgnoreCase("O")) System.out.println("Introduce un valor válido");
            } while ((!conferenciaStr.equalsIgnoreCase("E") && !conferenciaStr.equalsIgnoreCase("O")));
            switch (conferenciaStr){
                case "e","E" -> { equipo.setConferencia(Conferencia.ESTE); }
                case "o","O" -> { equipo.setConferencia(Conferencia.OESTE); }
                default -> { System.out.println("La opción de conferencia es incorrecta"); }
            }
            String divisionStr;
            do {
                System.out.println("Introduce División (ATLANTIC, CENTRAL, SOUTHEAST, NORTHWEST, PACIFIC, SOUTHWEST");
                divisionStr = br.readLine();
                if ((!divisionStr.equals("ATLANTIC")) && (!divisionStr.equals("CENTRAL")) && (!divisionStr.equals("SOUTHEAST")) && (!divisionStr.equals("NORTHWEST")) && (!divisionStr.equals("PACIFIC")) && (!divisionStr.equals("SOUTHWEST")))
                    System.out.print("Introduce un valor válido");
            } while ((!divisionStr.equalsIgnoreCase("ATLANTIC")) && (!divisionStr.equalsIgnoreCase("CENTRAL")) && (!divisionStr.equalsIgnoreCase("SOUTHEAST")) && (!divisionStr.equalsIgnoreCase("NORTHWEST")) && (!divisionStr.equalsIgnoreCase("PACIFIC")) && (!divisionStr.equalsIgnoreCase("SOUTHWEST")));
            equipo.setDivision(Division.fromDivision(divisionStr));
            System.out.print("Introduce nombre completo: ");
            equipo.setNombreCompleto(br.readLine());
            System.out.print("Introduce abreviatura: ");
            equipo.setAbreviatura(br.readLine());

            EquipoDAO equipoDAO = new EquipoDAO();
            if (equipoDAO.anadirEquipo(equipo)) {
                System.out.println("Equipo añadido correctamente");
            } else {
                System.out.println("Ha habido un error al añadir equipo");
            }

        } catch (IOException e){
            System.out.println("Error IO: " + e.getMessage());
        }

    }

    public static void pruebaAnadirEquipo(){
        Equipo equipo = new Equipo("SANTIAGO BASKET","SANTIAGO",Conferencia.ESTE,Division.CENTRAL,"Santiago de Compostela Basket","SCB");
        EquipoDAO equipoDAO = new EquipoDAO();
        if (equipoDAO.anadirEquipo(equipo)) {
            System.out.println("Equipo añadido correctamente");
        } else {
            System.out.println("Error al añadir equipo");
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        do {
            System.out.println("GESTIÓN DE EQUIPOS DE BALONCESTO");
            System.out.println("1 - Añadir un equipo");
            System.out.println("2 - Prueba manual");
            System.out.println("S - Salir");

            try {
                opcion = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (opcion){
                case "1" -> { anadirEquipo(); }
                case "2" -> { pruebaAnadirEquipo(); }
                case "s","S" -> { System.out.println("Saliendo del programa"); }
            }
        } while (!opcion.equalsIgnoreCase("s"));



//        Código para guardar los datos del gson en la base de datos
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(new TypeToken<List<Equipo>>(){}.getType(),new EquipoDeserializer())
//                .create();
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:\\Grado Superior DAM\\AD\\Proyectos\\AD_UD3_06.01\\src\\main\\resources\\jsonEquipos.json"))){
//            List<Equipo> equipos = gson.fromJson(br,new TypeToken<List<Equipo>>(){}.getType());
//
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("equipos");
//            EntityManager em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//            for (Equipo e : equipos){
//                System.out.println(e);
//                em.persist(e);
//            }
//            em.getTransaction().commit();
//            em.close();
//            emf.close();
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }

//        Código para crear la base de datos
//        System.out.println("Creando bdd vacía");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("equipos");
//        EntityManager em = emf.createEntityManager();
//        System.out.println("bdd creada exitosamente");
//        em.close();
//        emf.close();




    }
}