package com.example.popupmenu;

import java.lang.reflect.Field;
import java.util.Locale;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnMenuItemClickListener{

	String crntlang = "te";
	

	Configuration config;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PopupMenu menu = new PopupMenu(v.getContext(), v);
				menu.setOnMenuItemClickListener(MainActivity.this);
				menu.inflate(R.menu.menu);
				menu.show();
				
			}
		});
		
		try {			
			Typeface telugufont = Typeface.createFromAsset(getAssets(),
					"fonts/Vemana.ttf");
			final Field defaultFontTypeField = Typeface.class.getDeclaredField("SANS_SERIF");
			defaultFontTypeField.setAccessible(true);
			defaultFontTypeField.set(null, telugufont);
			
		} catch(Exception e) {
			Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
		}
		
		Locale locale = new Locale(crntlang);
		Locale.setDefault(locale);
		config = new Configuration();
		config.locale = locale;
		this.getResources()
				.updateConfiguration(
						config,
						this.getResources()
								.getDisplayMetrics());
	this.setTheme(R.style.astheme);
	}
	

	@Override
	public boolean onMenuItemClick(MenuItem arg0) {
		Toast.makeText(this, arg0.getTitle(), Toast.LENGTH_LONG).show();
		return false;
	}
}
