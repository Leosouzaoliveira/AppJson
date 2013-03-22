package br.com.k19.android.cap06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.widget.TextView;
public class LIstCompany extends ListActivity {
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

	        
	        ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	        
	        String response = makeRequest("http://api.crunchbase.com/v/1/search.js?query=sony&api_key=xkbgxdv3ayxggp2m54ba65u7");
	        
	        try{
	        	
	        JSONObject json = new JSONObject(response);
	        JSONArray jsonA = json.getJSONArray(("results"));
	        for(int i = 0; i <= jsonA.length(); i++){
	        	JSONObject name = jsonA.getJSONObject(i);
	        	JSONObject permalink = jsonA.getJSONObject(i);
	        	JSONObject overview = jsonA.getJSONObject(i);
	        	JSONObject img = jsonA.getJSONObject(i).getJSONObject("image");
	        	
	        	//nameText.setText(name.getString("name"));
	        	//permalinkText.setText(getString(R.string.permalink_label,permalink.getString("permalink")));
	        	//overviewText.setText(getString(R.string.overview_label, overview.getString("overview")));
	        	
	        }
	        
	        
	        
	        }catch (JSONException e){
	        	e.printStackTrace();
	        
	        }
	    }


	    private String makeRequest(String urlAddress) {
			HttpURLConnection con = null;
			URL url = null;
			String response = null;
			try {
				url = new URL(urlAddress);
				con = (HttpURLConnection) url.openConnection();
				response = readStream(con.getInputStream());
				
			}catch (Exception e){
				e.printStackTrace();
			}finally {
				con.disconnect();
			}
			return response;
	    }
			private String readStream(InputStream in) {
				BufferedReader reader = null;
				StringBuilder builder = new StringBuilder();
				
				try{
					reader = new BufferedReader(new InputStreamReader(in));
					String line = null;
					while ((line = reader.readLine()) != null) {
						builder.append(line + "\n");
					}
				}catch (IOException e){
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						}catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return builder.toString();
			}


}
