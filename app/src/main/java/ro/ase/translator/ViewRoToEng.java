package ro.ase.translator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ro.ase.translator.adapter.TraducereAdapter;
import ro.ase.translator.model.Traducere;

public class ViewRoToEng extends AppCompatActivity {

    private static final int SETARI = 0;

    private Button button;

    private Intent intent;

    private ListView listView;
    List<Traducere> traducereList = new ArrayList<Traducere>();

    public static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ro_to_eng);

        button = findViewById(R.id.buttonNew1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), RoToEng.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        listView = findViewById(R.id.listView1);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Traducere traducere = traducereList.get(position);

                final TraducereAdapter traducereAdapter = (TraducereAdapter) listView.getAdapter();
                traducereAdapter.notifyDataSetChanged();

                AlertDialog dialog = new AlertDialog.Builder(ViewRoToEng.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Doriti sa stergeti aceasta traducere?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), "Nu am sters nimic! ", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int poz = traducereList.indexOf(traducere);
                                traducereList.remove(traducere);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("translatorproiectdam2021-default-rtdb");
                                myRef.child("translatorproiectdam2021-default-rtdb").child(traducere.getUid()).removeValue();

                                traducereAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "S-a sters traducerea selectata! ", Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

                dialog.show();
                return true;
            }
        });
    }

    private View.OnClickListener chartEventListener() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("translatorproiectdam2021-default-rtdb");
        myRef.keepSynced(true);

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    traducereList.clear();
                    for(DataSnapshot dn: dataSnapshot.getChildren()){
                        Traducere traducere = dn.getValue(Traducere.class);
                        traducereList.add(traducere);
                    }
                }

                TraducereAdapter adapter = new TraducereAdapter(getApplicationContext(), R.layout.elemlistview, traducereList, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        return view;
                    }
                };

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myRef.child("translatorproiectdam2021-default-rtdb").addValueEventListener(listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            Traducere traducere = (Traducere) data.getSerializableExtra(RoToEng.ADD_TRADUCERE);
            if(traducere != null){
                traducereList.add(traducere);

                TraducereAdapter adapter = new TraducereAdapter(getApplicationContext(), R.layout.elemlistview, traducereList, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        return view;
                    }
                };

                listView.setAdapter(adapter);
            }
        }
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