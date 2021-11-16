package ro.ase.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import ro.ase.translator.fragments.Fragment3;
import ro.ase.translator.fragments.Fragment4;

public class RoToFr extends AppCompatActivity {

    private static final int SETARI = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ro_to_fr);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame2, new Fragment4()).commit();
        Switch switch2 = (Switch) findViewById(R.id.switch2);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame2, new Fragment3()).commit();
                }
                else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame2, new Fragment4()).commit();
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
                return true;
        }
        return false;
    }
}