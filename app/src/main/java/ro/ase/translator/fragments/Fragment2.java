package ro.ase.translator.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import ro.ase.translator.R;


public class Fragment2 extends Fragment {


    EditText et1Fr2 = null;
    EditText et2Fr2 = null;

    TranslatorOptions options = new TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ROMANIAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build();
    final Translator engRoTranslator = Translation.getClient(options);

    DownloadConditions conditions = new DownloadConditions.Builder()
            .requireWifi()
            .build();

    public void callEngRoTranslator(String str){

        engRoTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
        engRoTranslator.translate(str)
                .addOnSuccessListener(
                        new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                et2Fr2.setText(o.toString());
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity().getApplicationContext(), "n am tradus nimic", Toast.LENGTH_LONG).show();
                            }
                        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_2, container, false);


        Bundle bundle = this.getArguments();
        if(bundle != null){
            String data = bundle.getString("key");
            et1Fr2.setText(data);
        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et1Fr2 = getActivity().findViewById(R.id.editText1_fr2);
        et2Fr2 = getActivity().findViewById(R.id.editText2_fr2);

        et1Fr2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.equals("")){
                    callEngRoTranslator(et1Fr2.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public String getTextTradus(){
        String textTradus = et2Fr2.getText().toString();
        return textTradus;
    }

    public String getTextIntrodus(){
        String textIntrodus = et1Fr2.getText().toString();
        return textIntrodus;
    }

    public void setTextIntrodus(String text){
        et1Fr2.setText(text);
    }
}