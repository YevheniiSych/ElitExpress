package elit.express.elitexpress;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendRequest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String[] params) {
        String responseFromServer = null;

        try {
            String url = params[0];


            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("Accept_Language", "en-US,en,q=0.5");

            String urlParameters = params[1];

            //запись информации в поток

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));

            writer.write(urlParameters);

            writer.close();

            wr.close();

            //получение информации из потока ответа

            int responseCode = con.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();

            responseFromServer = response.toString();

        } catch (Exception ex) {
            return ex.getMessage();
        }

        return responseFromServer;
    }

    // ответ сервера
    @Override
    protected void onPostExecute(String message) {
//        Toast.makeText(,message, Toast.LENGTH_LONG).show();
    }
}
