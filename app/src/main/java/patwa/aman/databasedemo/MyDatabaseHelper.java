package patwa.aman.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by dell on 09-08-2018.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String Table_Name="Student_table";
    public static final String col_1="ID";
    public static final String col_2="NAME";
    public static final String col_3="SURNAME";
    public static final String col_4="MARKS";




    public MyDatabaseHelper(Context context) {
        super(context, "Student.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+Table_Name+"( ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_Name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String Surname,String marks)
    {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(col_2, name);
            contentValues.put(col_3, Surname);
            contentValues.put(col_4, marks);
            long result = sqLiteDatabase.insert(Table_Name, null, contentValues);
            return true;

    }

    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("Select * from "+Table_Name,null);
        return  res;
    }
}