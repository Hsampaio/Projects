package Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Utilidade {

	@SuppressLint("NewApi")
	public static void SetPreferences(String sKey, String sValue, Context ctx) {
		SharedPreferences.Editor sharePrefmeuTime = ctx.getSharedPreferences(
				"com.hssports", 0).edit();
		sharePrefmeuTime.putString(sKey, sValue);
		sharePrefmeuTime.apply();
		sharePrefmeuTime.commit();
	}

	public static String GetPreference(String sKey, String sDefaultValue,
			Context ctx) {

		SharedPreferences sharedPrefMeuTime = ctx.getSharedPreferences(
				"com.hssports", 0);
		String restoredText = sharedPrefMeuTime.getString(sKey, sDefaultValue);

		return restoredText;

	}
	
	public static String getDataAtual() {
		long date = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
		return sdf.format(date);

	}
	
	public final static long SECOND_MILLIS = 1000;
	public final static long MINUTE_MILLIS = SECOND_MILLIS*60;
	public final static long HOUR_MILLIS = MINUTE_MILLIS*60;
	public final static long DAY_MILLIS = HOUR_MILLIS*24;
	
	public static int DiasEntre(String sDataMenor, String sDataMaior)
	{
		 
	
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");    
		
		Date dtFim = null;
		try {
			dtFim = new java.sql.Date(format.parse(sDataMaior).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		  
		Date dtIni = null;
		try {
			dtIni = new java.sql.Date(format.parse(sDataMenor).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
		
		return (int)((dtFim.getTime()/DAY_MILLIS) - (dtIni.getTime()/DAY_MILLIS)); 
		
		
			
			
	}

}
