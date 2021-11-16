package ro.ase.translator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import ro.ase.translator.fragments.Fragment5;
import ro.ase.translator.fragments.Fragment6;

public class RoToGer extends AppCompatActivity {

    private static final int SETARI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ro_to_ger);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame3, new Fragment6()).commit();
        Switch switch3 = (Switch) findViewById(R.id.switch3);
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame3, new Fragment5()).commit();
                }
                else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame3, new Fragment6()).commit();
                }

            }
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
                break;
            case android.R.id.home:
                finish();

                return true;
        }
        return false;
    }
}