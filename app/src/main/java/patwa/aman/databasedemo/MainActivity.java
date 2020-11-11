package patwa.aman.databasedemo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDatabaseHelper dbh;
    EditText name,surname,marks;
    Button add,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbh = new MyDatabaseHelper(this);
        name = (EditText) findViewById(R.id.etname);
        surname = (EditText) findViewById(R.id.etsname);
        marks = (EditText) findViewById(R.id.etmks);
        add = (Button) findViewById(R.id.b1);
        show = (Button) findViewById(R.id.b2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isinserted = dbh.insertData(name.getText().toString(), surname.getText().toString(), marks.getText().toString());
                if (isinserted == true)
                    System.out.println("Inserted");
                else
                    System.out.println("Not Inserted");
            }
        });
        AddData();
    }
        public void AddData() {
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor cs = dbh.getAllData();
                    if (cs.getCount() == 0) {
                        showMessage("Error", "No data found");
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (cs.moveToNext()) {
                        buffer.append("ID :" + cs.getString(0) + "\n");
                        buffer.append("Name :" + cs.getString(1) + "\n");
                        buffer.append("Surname :" + cs.getString(2) + "\n");
                        buffer.append("Marks :" + cs.getString(3) + "\n\n");


                    }
                    showMessage("Data", buffer.toString());
                }
            });
        }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title)
                .setCancelable(true)
                .setMessage(message)
                .show();
    }
}
