package ro.ase.translator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ro.ase.translator.R;
import ro.ase.translator.model.Traducere;

public class TraducereAdapter extends ArrayAdapter<Traducere> {

    private Context context;
    private int resource;
    private List<Traducere> traducereList;
    private LayoutInflater layoutInflater;

    public TraducereAdapter(@NonNull Context context, int resource, List<Traducere> traducereList, LayoutInflater layoutInflater) {
        super(context, resource, traducereList);
        this.context = context;
        this.resource = resource;
        this.traducereList = traducereList;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Traducere traducere = traducereList.get(position);

        if(traducere != null){
            TextView tv = view.findViewById(R.id.traducere);
            tv.setText(traducere.getTextTradus());
        }

        return view;
    }
}
