package org.dam;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EquipoDeserializer implements JsonDeserializer<List<Equipo>> {
    @Override
    public List<Equipo> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray equipoArray = jsonObject.getAsJsonArray("Equipo");
        List<Equipo> equipos = new ArrayList<>();
        for (JsonElement element : equipoArray){
            JsonObject equipoObject = element.getAsJsonObject();
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(equipoObject.get("idEquipo").getAsLong());
            equipo.setNombre(equipoObject.get("nombre").getAsString());
            equipo.setCiudad(equipoObject.get("ciudad").getAsString());
            equipo.setConferencia(Conferencia.valueOf(equipoObject.get("conferencia").getAsString()));
            equipo.setDivision(Division.valueOf(equipoObject.get("division").getAsString()));
            equipo.setNombreCompleto(equipoObject.get("nombreCompleto").getAsString());
            equipo.setAbreviatura(equipoObject.get("abreviatura").getAsString());
            equipos.add(equipo);
        }
        return equipos;
    }
}
