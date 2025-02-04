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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static void crearBDD(){
        System.out.println("Creando bdd vacía");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("equipos");
        EntityManager em = emf.createEntityManager();
        System.out.println("bdd creada exitosamente");
        em.close();
        emf.close();
    }

    public static void anadirEntrenador(){
        System.out.println("AÑADIR ENTRENADOR");
        Entrenador entrenador = new Entrenador();
        System.out.print("Introduce nombre: ");
        try {
            entrenador.setNombre(br.readLine());
            System.out.print("Introduce fecha de nacimiento (dd/mm/aaaa): ");
            try {
                entrenador.setFechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(br.readLine()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            System.out.print("Introduce salario: ");
            entrenador.setSalario(Double.parseDouble(br.readLine()));
        } catch (IOException e){
            System.out.println("Error IO" + e.getMessage());
        }

        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        if (entrenadorDAO.anadirEntrenador(entrenador)){
            System.out.println("Entrenador agregado correctamente");
        } else {
            System.out.println("Error al agregar entrenador");
        }

    }

    /**
     * Pide al usuario el id del entrenador a asignar al equipo
     * Pide al usuario el id del equipo a asignar ese entrenador
     *
     */
    public static void asignarEntrenadorAEquipo(){
        System.out.println("ASIGNAR ENTRENADOR A EQUIPO");
        try {
            System.out.print("Introduce id del entrenador: ");
            Long idEntrenador = Long.parseLong(br.readLine());
            System.out.print("Introduce id del equipo: ");
            Long idEquipo = Long.parseLong(br.readLine());
            // Obtener objeto entrenador a partir de su id.
            EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
            Entrenador entrenador = entrenadorDAO.obtenerEntrenador(idEntrenador);
            EquipoDAO equipoDAO = new EquipoDAO();
            if (equipoDAO.asignarEntrenador(entrenador,idEquipo)) {
                System.out.println("Entrenador asignado a equipo " + idEquipo);
            } else {
                System.out.println("Error en la asignación de entrenador");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void asignarEquipoAEntrenador() {
        System.out.println("Pendiente de implementar");
        // SE crea un equipo

//        Equipo a = new Equipo()
//
//        Equipo oKc = em.find(Equipo.class,21L);
//
//        Entrenador entrenador = new Entrenador(...);
//                entrenador.setIdEntrenador(null);
//                var txxt = em.getTransaction();
//                txt.begin();
//                em.persist(entrenador);
//                txt.commit();


        // Se persiste para encontrarlo con find
        // Si tengo identity, primero se crea el entrenador sin id y después hay que poner setIdEntrenador(null)



    }

    /**
     * Muestra datos de un equipo y de su entrenador
     */
    public static void mostrarEquipo(){
        System.out.print("Introduce id de equipo: ");
        try {
            Long idEquipo = Long.parseLong(br.readLine());
            EquipoDAO equipoDAO = new EquipoDAO();
            equipoDAO.mostrarEquipoYEntrenador(idEquipo);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opcion;
        do {
            System.out.println("GESTIÓN DE EQUIPOS DE BALONCESTO");
            System.out.println("0 - Crear base de datos");
            System.out.println("1 - Añadir un equipo");
            System.out.println("2 - Test añadir equipo");
            System.out.println("3 - Añadir entrenador");
            System.out.println("4 - Asignar entrenador a equipo");
            System.out.println("5 - Asignar equipo a entrenador");
            System.out.println("6 - Mostrar equipo");
            System.out.println("S - Salir");

            try {
                opcion = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            switch (opcion){
                case "0" -> { crearBDD(); }
                case "1" -> { anadirEquipo(); }
                case "2" -> { pruebaAnadirEquipo(); }
                case "3" -> { anadirEntrenador(); }
                case "4" -> { asignarEntrenadorAEquipo(); }
                case "5" -> { asignarEquipoAEntrenador(); }
                case "6" -> { mostrarEquipo(); }
                case "s","S" -> { System.out.println("Saliendo del programa"); }
                default -> {
                    System.out.println("Introduce una opción correcta");
                }
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





    }
}