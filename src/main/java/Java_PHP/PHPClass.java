package Java_PHP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PHPClass {

    /**
     *
     * @param user Instancia de la clase User con la información del usuario que
     * se desea agregar a la base de datos
     * @return TRUE si se almacena la información correctamente, FALSE en el
     * caso contrario
     */
    public boolean saveInfo(User user) {
        try {
            URL url = new URL("http://localhost/java_post_examples/src/main/java/Java_PHP/saveInfo.php");
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            ps.print("&table_name=users_list");
            ps.print("&name=" + user.getName());
            ps.print("&last_name=" + user.getLastName());
            ps.print("&birthdate=" + user.getBirthdate());
            ps.print("&gender=" + user.getGender());
            con.getInputStream();
            InputStream is = con.getInputStream();
            while (is.available() > 0) {
                // System.out.print((char) is.read());
                is.read();
            }
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @return Lista de objetos de la clase User con todos los usuarios
     * almacenados en la base de datos
     */
    public List<User> getInfo() {
        List<User> users = new ArrayList<>();
        String json = getJSON();
        Object jsonObject = JSONValue.parse(json.toString());
        JSONArray array = (JSONArray) jsonObject;

        for (int i = 0; i < array.size(); i++) {
            JSONObject row = (JSONObject) array.get(i);
            users.add(new User(
                    Integer.parseInt(row.get("id").toString()),
                    row.get("name").toString(),
                    row.get("last_name").toString(),
                    row.get("birthdate").toString(),
                    row.get("gender").toString()));
        }
        return users;
    }

    /**
     * Método que invoca el script getInfo.php para extraer la información de la
     * base de datos y empaquetarla en un JSON
     *
     * @return Un JSON con la información extraida de la base de datos
     */
    private String getJSON() {
        StringBuffer response = null;
        try {
            String url = "";
            url = "http://localhost/java_post_examples/src/main/java/Java_PHP/getInfo.php";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.toString().contains("Warning")) {
            int i = 0;
            String msg = "";
            while (true) {
                if (response.charAt(i) == '[') {
                    break;
                } else {
                    msg += response.charAt(i);
                    i++;
                }
                if (i > 1000) {
                    break;
                }
            }
            return response.toString().replace(msg, "");
        } else {
            return response.toString();
        }

    }
}
