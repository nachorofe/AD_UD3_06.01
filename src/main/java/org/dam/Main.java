package org.dam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(new TypeToken<List<Equipo>>(){}.getType(),new EquipoDeserializer())
//                .create();
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:\\Grado Superior DAM\\AD\\Proyectos\\AD_UD3_06.01\\src\\main\\resources\\jsonEquipos.json"))){
//            List<Equipo> equipos = gson.fromJson(br,new TypeToken<List<Equipo>>(){}.getType());
//
//            for (Equipo e : equipos){
//                System.out.println(e);
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }

        System.out.println("Creando bdd vacía");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("equipo");
        EntityManager em = emf.createEntityManager();
        System.out.println("bdd creada exitosamente");
        em.close();
        emf.close();

    }
}