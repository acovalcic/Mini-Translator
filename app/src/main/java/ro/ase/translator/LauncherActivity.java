package ro.ase.translator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String pornire = prefs.getString("startup", null);
        if(prefs.contains("startup")){
            switch (pornire){
                case "op":
                    i = new Intent(LauncherActivity.this, TraducereActivity.class);
                    startActivity(i);
                    break;
                case "en":
                    i = new Intent(LauncherActivity.this, ViewRoToEng.class);
                    startActivity(i);
                    break;
                case "fr":
                    i = new Intent(LauncherActivity.this, RoToFr.class);
                    startActivity(i);
                    break;
                case "ger":
                    i = new Intent(LauncherActivity.this, RoToGer.class);
                    startActivity(i);
        }
        }
        else{
            i = new Intent(LauncherActivity.this, TraducereActivity.class);
            startActivity(i);
        }

        finish();
    }
}