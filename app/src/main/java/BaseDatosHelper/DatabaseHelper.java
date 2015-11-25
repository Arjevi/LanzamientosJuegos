package BaseDatosHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static String DB_PATH = "/data/data/es.arjevi.applanzamientosjuego/databases/";
    private static String DB_NAME = "juegos.s3db";
    
	  static InputStream myInput;
	  boolean existe=false;
	  boolean crear=false;
	  String version="version 1";
	  Context conte;
	  String texto;
	  
	public DatabaseHelper (Context context)
	{ super(context,DB_NAME, null,1);
	conte=context;
	try {
		myInput=context.getAssets().open(DB_NAME);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void createDataBase() throws IOException 
	{
		InputStreamReader flujo=null;
		BufferedReader lector=null;
		try
		{
			flujo= new InputStreamReader(conte.getApplicationContext().openFileInput("prueba.txt"));
			lector= new BufferedReader(flujo);
		    texto = lector.readLine();
		    while(texto!=null)
		    {
		    	if(texto.equals(version))
		    	{
		    		existe=true;
		    	}
		    	else
		    	{
		    		crear=true;
		    	}
		    	texto=lector.readLine();
		    }
		}
		catch (Exception ex)
		{
		    Log.e("ivan", "Error al leer fichero desde memoria interna "+texto);
		    crear=true;
		}
		finally
		{
			try {
	    			if(flujo!=null)
	    				flujo.close();
				} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(crear)
		{
		try
	    {
	        OutputStreamWriter fout=new OutputStreamWriter(conte.getApplicationContext().openFileOutput("prueba.txt", Context.MODE_PRIVATE));
	     
	        fout.write(version);
	        fout.close();
	    }
	    catch (Exception ex)
	    {
	        Log.e("Ficheros", "Error al escribir fichero a memoria interna");
	    }
		}
	     	if(!existe){
	        	try {
	     			copyDataBase();
	     		} catch (IOException e) {
	         		throw new Error("Error copying database: " + e.getMessage());
	         	}
	    	}
	    }
	    
	    static public boolean checkDataBase(){
	    	 
	    	SQLiteDatabase checkDB = null;
	     	try{
	    		String myPath = DB_PATH + DB_NAME;
	    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	    	} catch(SQLiteException e){
	    	}
	 
	    	if(checkDB != null){
	     		checkDB.close();
	     	}
	 
	    	return checkDB != null ? true : false;
	    }
	    
	    static public void copyDataBase() throws IOException {
	    	
	    	File dir = new File(DB_PATH);
			if (!dir.exists()) dir.mkdir();
			
			//InputStream myInput = context.getAssets().open(DB_NAME);
	         
	        String outFileName = DB_PATH + DB_NAME;
	 
	        OutputStream myOutput = new FileOutputStream(outFileName);
	        
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = myInput.read(buffer))>0){
	            myOutput.write(buffer, 0, length);
	        }

	        myOutput.flush();
	        myOutput.close();
	        myInput.close();
	        
	    }

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
