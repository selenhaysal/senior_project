package com.info.eventoutfyp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.text.InputType;
import android.content.DialogInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.location.places.ui.PlacePicker;



public class ProfileFragment extends Fragment {

    private LinearLayout addressLay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragments_profile,container,false);


        addressLay = (LinearLayout) view.findViewById(R.id.setAddressLayout);


        addressLay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    // building a place picker for owner to set venue address

                    int PLACE_PICKER_REQUEST = 1;
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);

                }catch(Exception e)
                {

                }



            }

        });

        return view;
    }




}
