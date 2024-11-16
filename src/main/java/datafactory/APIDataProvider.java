package datafactory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class APIDataProvider implements DataProvider {

   
    private String API_URL;

	public APIDataProvider(String API_URL) {
        this.API_URL = API_URL;
    } 
    public Object[][] setDataProvider() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(API_URL);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("HTTP error code : " + statusCode);
        }
        String responseBody = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();
        JSONArray jsonArray = new JSONArray(responseBody);
        Object[][] data = new Object[jsonArray.length()][1]; 

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            data[i][0] = jsonObject.getString("name"); 
        }

        return data;
    }
}