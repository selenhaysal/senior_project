package com.info.eventoutfyp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment {

    private ListView listView;
    private String venueID;

    DatabaseReference databaseReference;
    List<Event> eventList;
    private FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_list, container, false);

        listView = (ListView) view.findViewById(R.id.list_view);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventsFragment eventsFragment = new EventsFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, eventsFragment, eventsFragment.getTag()).commit();
                Toast.makeText(getContext(), "AFTER 0: " + venueID,
                        Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("events");
        eventList = new ArrayList<>();

        venueID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot uniqueKeySnapshot : dataSnapshot.getChildren()) {
                    Event event = uniqueKeySnapshot.getValue(Event.class);
//                    eventList.add(event);
//                    String test = event.getVenueID();
                    if (event.getVenueID().equals(venueID))
                        eventList.add(event);
                }

                if(getActivity()!= null) {
                    EventInfoAdapter eventInfoAdapter = new EventInfoAdapter(EventListFragment.this.getActivity(), eventList);
                    listView.setAdapter(eventInfoAdapter);
                }
            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



//    @Override
//    public void onStart() {
//        super.onStart();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
//
//                    Event event = eventSnapshot.getValue(Event.class);
//                    eventList.add(event);
//
//                }
//
//                EventInfoAdapter eventInfoAdapter = new EventInfoAdapter(EventListFragment.this.getActivity(), eventList);
//                listView.setAdapter(eventInfoAdapter);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
