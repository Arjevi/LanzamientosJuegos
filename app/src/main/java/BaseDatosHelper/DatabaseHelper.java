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


	private static final String DATABASE_NAME = "MisJuegos.db";
	public static final	String NOMBRE = "nombre";
	public static final	String PLATAFORMA = "plataforma";
	public static final	String GENERO = "genero";
	public static final String FINALIZADO="finalizado";

	public DatabaseHelper (Context context)
	{ super(context,DATABASE_NAME, null,1);
	}



	public void onCreate( SQLiteDatabase db)
	{
		db.execSQL (" CREATE TABLE juegos (id INTEGER PRIMARY KEY, nombre TEXT, plataforma TEXT, genero TEXT, finalizado INTEGER);");
	}


	public void onUpgrade ( SQLiteDatabase db, int oldVersion, int newVersion)
	{// Borrado y creación de la bd y tabla si se cambia el 1 del constructor, que indará nueva versión
		android.util.Log.w("juegos","Upgrading  database, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS juegos");
		onCreate(db);

	}
}
