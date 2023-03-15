package conexionAPI;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexion {
    public static double obtenerCotizacion(String monedaOrigen, String monedaDestino, double monto) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=" + monedaDestino + "&from=" + monedaOrigen + "&amount=" + monto)
                .addHeader("apikey", "HpDx3XEj5EluB2BU0DFuLBpfIaHWqA5V")
                .method("GET", null)
                .build();

        try {
        	 Response response = client.newCall(request).execute();
             String jsonResponse = new String(response.body().bytes());
             JSONObject jsonObject = new JSONObject(jsonResponse);
             return jsonObject.getDouble("result");
         } catch (IOException | JSONException e) {
             System.out.println("Error: " + e.getMessage());
         }

         return 0;
     }

        
    


	public static JSONObject obtenerDivisasDisponibles() throws Exception {
		// Crea una URL para la API
		URL url = new URL("https://openexchangerates.org/api/currencies.json");

		// Abre una conexión HTTP a la URL
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// Establece el método HTTP GET
		con.setRequestMethod("GET");

		// Lee la respuesta de la API
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();

		// Convierte la respuesta de la API de JSON a un objeto Java utilizando la clase
		// JSONObject
		JSONObject jsonObject = new JSONObject(content.toString());
		return jsonObject;

//        // Itera sobre las claves de objeto JSON y muestra los nombres de las divisas disponibles
//        Iterator<String> keys = jsonObject.keys();
//        while(keys.hasNext()) {
//            String key = keys.next();
//            String value = jsonObject.getString(key);
//            System.out.println(key + " - " + value);
//            */
	}
}