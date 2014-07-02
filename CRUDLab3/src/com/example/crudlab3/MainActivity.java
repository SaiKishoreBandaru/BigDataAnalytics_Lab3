package com.example.crudlab3;



import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
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
