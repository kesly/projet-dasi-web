package fr.insalyon.dasi.project.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.td1.metier.modele.ProfilAstral;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ObtenirPredictionSerialisation extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {


        List<String> predictions = (List<String>)request.getAttribute("predictions");

        JsonObject container = new JsonObject();


        if (predictions != null) {
            JsonArray jsonElements = new JsonArray();

            for (String prediction: predictions) {
                jsonElements.add(prediction);
            }
            container.add("predictions", jsonElements);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
