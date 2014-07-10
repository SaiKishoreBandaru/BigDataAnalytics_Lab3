package com.example.crudlab3;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			//getFragmentManager().beginTransaction()
				//	.add(R.id.container, new PlaceholderFragment()).commit();
				
			
		}
		
		Button b1,b2,b3,b4;
		b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WebView webview = new WebView(MainActivity.this);
				 setContentView(webview);
				 webview.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseCreate/CRUD/coordinates:accelerometer:humidity:temperature:Date");


			}
		});
		
		b2 = (Button)findViewById(R.id.button2);
		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WebView webview = new WebView(MainActivity.this);
				 setContentView(webview);
				 webview.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseInsert/CRUD/-home-group11-group11-sensor_sai1.txt/coordinates:accelerometer:humidity:temperature:Date");


			}
		});
		
		b3 = (Button)findViewById(R.id.button3);
		b3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WebView webview = new WebView(MainActivity.this);
				 setContentView(webview);
				 webview.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseRetrieveAll/CRUD");


			}
		});
		
		b2 = (Button)findViewById(R.id.button4);
		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				WebView webview = new WebView(MainActivity.this);
				 setContentView(webview);
				 webview.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbasedeletel/CRUD");


			}
		});
		
		b3 = (Button)findViewById(R.id.button5);
		b3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*WebView webview = new WebView(MainActivity.this);
				 setContentView(webview);
				 webview.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseRetrieveAll/CRUD");
*/
				HttpClient dClientRequest = new DefaultHttpClient();
				
				InputStream resultStream = null;
				String sWeatherResult = "";
				String s="";
				//String URL=arg0[0];
				//String URL="http://api.openweathermap.org/data/2.5/weather?q="+rEditText1.getText();
				
				try {
					
					HttpGet hpURL = new HttpGet("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseRetrieveAll/CRUD");
				   HttpResponse hrWebResponse = dClientRequest.execute(hpURL);
				    StatusLine statusLine = hrWebResponse.getStatusLine();
				    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
				    {
				    HttpEntity heWebEntity = hrWebResponse.getEntity();
				    resultStream = heWebEntity.getContent();
				    BufferedReader bReader = new BufferedReader(new InputStreamReader(resultStream, "UTF-8"), 8);
				    StringBuilder sb = new StringBuilder();
				    
				    while((s = bReader.readLine()) != null)
				    {
				        sb.append(s + "\n");
				        //rEditText.setText(s);
				        
				        
				        
				    }
				    sWeatherResult = sb.toString();	
				    }
				    
				    File sdCard = Environment.getExternalStorageDirectory();
			        File directory = new File (sdCard.getAbsolutePath() + "/Data");
			        if(!directory.exists())
			        directory.mkdirs();
			        String fname = "Retreival.txt";
			        File file = new File (directory, fname);
			        
			        try {
			            if(!file.exists())
			                file.createNewFile();
			               FileOutputStream out = new FileOutputStream(file,true);
			               out.write(sWeatherResult.getBytes());
			               out.flush();
			               out.close();

			        } catch (Exception e) {
			               e.printStackTrace();
			        }
				        
				    
				} catch (Exception e) { 
					e.printStackTrace();
				
				}
				
				

			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
