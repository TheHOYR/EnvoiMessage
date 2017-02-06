package thomas.chanet.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.util.ArrayList;

/**
 * Created by chanett on 27/01/2017.
 */
public class ArrayAdapterChannel extends ArrayAdapter<JsonChannel> {
    private final Context context;
    private final ArrayList<JsonChannel> values;
    public ArrayAdapterChannel(Context context, ArrayList<JsonChannel> values) {
        super(context, R.layout.activity_channel_layout, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_channel_layout, parent, false);
        TextView textViewChannel1 = (TextView) rowView.findViewById(R.id.textViewChannel1);
        TextView textViewChannel2 = (TextView) rowView.findViewById(R.id.textViewChannel2);
        textViewChannel1.setText(getItem(position).getName()+" : "+getItem(position).getChannelID());
        textViewChannel2.setText("Nombre d'utilisateur connectés : "+getItem(position).getConnectedusers());

        return rowView;
    }
}