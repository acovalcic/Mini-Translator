package ro.ase.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class TraducereActivity extends AppCompatActivity {

    public static final int REQUEST_CODE1 = 100;
    public static final int REQUEST_CODE2 = 200;
    public static final int REQUEST_CODE3 = 300;
    private static final int SETARI = 0;

    private Button button1;
    private Button button2;
    private Button button3;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener((view) ->{
            intent = new Intent(getApplicationContext(), ViewRoToEng.class);
            startActivityForResult(intent, REQUEST_CODE1);
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener((view) ->{
            intent = new Intent(getApplicationContext(), RoToFr.class);
            startActivityForResult(intent, REQUEST_CODE2);
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener((view) ->{
            intent = new Intent(getApplicationContext(), RoToGer.class);
            startActivityForResult(intent, REQUEST_CODE3);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,SETARI,0,"Setări");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case SETARI:
                Intent intent1 = new Intent(getApplicationContext(), SetariActivity.class);
                startActivity(intent1);
                return true;
        }
        return false;
    }
}