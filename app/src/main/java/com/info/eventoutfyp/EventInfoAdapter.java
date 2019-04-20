package com.info.eventoutfyp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventInfoAdapter extends ArrayAdapter<Event> {

    private Activity context;
    private List<Event> eventList;

    public EventInfoAdapter(Activity context, List<Event> eventList)
    {
        super(context,R.layout.item_event_card,eventList);
        this.context = context;
        this.eventList = eventList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.item_event_card, null, true);

        TextView titleEditText  = (TextView) listView.findViewById(R.id.eventName);
        TextView descEdittext = (TextView) listView.findViewById(R.id.eventDescription);
        TextView durationEdittext = (TextView) listView.findViewById(R.id.eventDuration);


        Event event = eventList.get(position);
        titleEditText.setText(event.getEventTitle());
        descEdittext.setText(event.getEventDescription());
        durationEdittext.setText(event.getStartTime() + " - " + event.getEndTime());




        return listView;


    }
}