package com.example.duan1_nhom3.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1_nhom3.R;

public class fragment_table extends Fragment {
    ImageButton imageButton;
    RecyclerView recy_table;
    TextView textView,textView2;
    ImageView imageView2;

    public fragment_table() {
    }

    public static fragment_table newInstance(String param1, String param2) {
        fragment_table fragment = new fragment_table();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        recy_table = view.findViewById(R.id.recy_table);
        imageButton = view.findViewById(R.id.imageButton);
        textView= view.findViewById(R.id.textView);
        imageView2= view.findViewById(R.id.imageView2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_themtable);

                Button button_luu = dialog.findViewById(R.id.button_dialogThemtable_luu);
                button_luu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button button_huy = dialog.findViewById(R.id.button_dialogThemtable_huy);
                button_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
        return view;
    }
}
