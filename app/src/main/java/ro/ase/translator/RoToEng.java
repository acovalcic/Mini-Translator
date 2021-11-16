package ro.ase.translator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ro.ase.translator.fragments.Fragment1;
import ro.ase.translator.fragments.Fragment2;
import ro.ase.translator.model.Traducere;

public class RoToEng extends AppCompatActivity {

    public static final String ADD_TRADUCERE = "adaugaTraducere";

    private static final int SETARI = 0;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ro_to_eng);

        Button buttonSalvare = findViewById(R.id.buttonSalvare1);
        buttonSalvare.setEnabled(false);
        buttonSalvare.setVisibility(View.INVISIBLE);
        Fragment1 f1 = new Fragment1();
        Fragment2 f2 = new Fragment2();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame1, f2).commit();
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame1, f1).commit();
                    buttonSalvare.setEnabled(true);
                    buttonSalvare.setVisibility(View.VISIBLE);
                }
                else if(!isChecked){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame1, f2).commit();
                    buttonSalvare.setEnabled(false);
                    buttonSalvare.setVisibility(View.INVISIBLE);
                    String transferText2 = f2.getTextTradus().toString();
                    f1.setTextIntrodus(transferText2);
                }

            }
        });

        final Intent intent = getIntent();

        database = FirebaseDatabase.getInstance();

        buttonSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f1.getTextIntrodus().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Introduceti textul de tradus!",Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        String textTradus = f1.getTextTradus();
                        Traducere traducere = new Traducere(textTradus);

                        writeTraducereinFirebase(traducere);

                        intent.putExtra(ADD_TRADUCERE, traducere);
                        setResult(RESULT_OK, intent);
                        finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void writeTraducereinFirebase(final Traducere tr){

        final DatabaseReference myRef = database.getReference("translatorproiectdam2021-default-rtdb");
        myRef.keepSynced(true);

        myRef.child("translatorproiectdam2021-default-rtdb").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tr.setUid(myRef.child("translatorproiectdam2021-default-rtdb").push().getKey());
                myRef.child("translatorproiectdam2021-default-rtdb").child(tr.getUid()).setValue(tr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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