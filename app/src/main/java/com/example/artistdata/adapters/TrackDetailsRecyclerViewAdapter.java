package com.example.artistdata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.artistdata.R;
import com.example.artistdata.models.ArtistTrackDetailsModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TrackDetailsRecyclerViewAdapter extends RecyclerView.Adapter<TrackDetailsRecyclerViewAdapter.Viewholder> {

    private Context context;
    private ArrayList<ArtistTrackDetailsModel> trackDetailsArrayList;

    // Constructor
    public TrackDetailsRecyclerViewAdapter(Context context, ArrayList<ArtistTrackDetailsModel> trackDetailsArrayList) {
        this.context = context;
        this.trackDetailsArrayList = trackDetailsArrayList;
    }

    @NonNull
    @Override
    public TrackDetailsRecyclerViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflates the layout for each item in the recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_detail_card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackDetailsRecyclerViewAdapter.Viewholder holder, int position) {
        ArtistTrackDetailsModel model = trackDetailsArrayList.get(position);

        DecimalFormat dec = new DecimalFormat("$#.00");
        String price = dec.format(model.getTrackPrice());
        // set data to the textviews of the card
        holder.artistName.setText(model.getArtistName());
        holder.trackName.setText(model.getTrackName());
        holder.trackPrice.setText(price);
        holder.releaseDate.setText(model.getReleaseDate());
        holder.trackGenre.setText(model.getPrimaryGenreName());
    }

    @Override
    public int getItemCount() {
        // gets total card number
        return trackDetailsArrayList.size();
    }

    // Viewholder class used to initialize views
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView artistName, trackName, trackPrice, releaseDate, trackGenre;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.idArtistName);
            trackName = itemView.findViewById(R.id.idTrackName);
            trackPrice = itemView.findViewById(R.id.idTrackPrice);
            releaseDate = itemView.findViewById(R.id.idReleaseDate);
            trackGenre = itemView.findViewById(R.id.idPrimaryGenreName);
        }
    }
}
