package com.example.artistdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.artistdata.adapters.TrackDetailsRecyclerViewAdapter;
import com.example.artistdata.clients.RetrofitClient;
import com.example.artistdata.interfaces.Api;
import com.example.artistdata.models.ArtistDataModel;
import com.example.artistdata.models.ArtistTrackDetailsModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ArtistTrackDetailsModel> trackDetailsList = new ArrayList<>();
    private ArtistDataModel artistData;
    private Button artistDataButton;
    private EditText editTextArtist;
    private ProgressBar progressBar;
    private RecyclerView trackRecycler;
    private TextView resultCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trackRecycler = findViewById(R.id.idTrackRecyclerView);
        artistDataButton = findViewById(R.id.EnterArtist);

        //disable search button until edittext is not empty
        artistDataButton.setEnabled(false);
        editTextArtist = findViewById(R.id.editTextArtist);
        progressBar = findViewById(R.id.indeterminateBar);
        resultCount = findViewById(R.id.idResultCount);
        resultCount.setVisibility(View.GONE);

        artistDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss keyboard after search button is clicked
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager. hideSoftInputFromWindow(v. getApplicationWindowToken(),0);

                searchArtist(editTextArtist.getText().toString());
            }
        });

        //listen for edittext changes to inform when the search button should be enabled
        editTextArtist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                artistDataButton.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void searchArtist(String currentSearch) {
        //display loading spinner and hide recycler and result while search occurs
        trackRecycler.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        resultCount.setVisibility(View.GONE);

        artistData = new ArtistDataModel();

        //clear list before each new search
        trackDetailsList.clear();

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("term", currentSearch);
        //restrict search to music tracks to remove music video results
        queryMap.put("entity", "musicTrack");

        Api api = RetrofitClient.getClient().create(Api.class);
        api.getArtistData(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArtistDataModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(@NotNull ArtistDataModel artistDataModel) {
                        artistData = artistDataModel;

                        if (artistData.getResultCount() > 0) {
                            for (ArtistTrackDetailsModel artistTrackDetailsModel : artistData.getTrackDetails()) {

                                //only add tracks by the searched artist to remove songs with the artists name in the title
                                if (artistTrackDetailsModel.getArtistName().equalsIgnoreCase(currentSearch)) {
                                    trackDetailsList.add(artistTrackDetailsModel);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.d("error", String.valueOf(e));
                    }

                    @Override
                    public void onComplete() {
                        //hide loading spinner
                        progressBar.setVisibility(View.GONE);

                        //set and display result count
                        String results = "0 - " + trackDetailsList.size() + " results";
                        resultCount.setText(results);
                        resultCount.setVisibility(View.VISIBLE);

                        //only show list if it contains results
                        if (artistData.getResultCount() > 0) {

                            trackRecycler.setVisibility(View.VISIBLE);
                        }

                        //initialize adapter for the recycler view
                        TrackDetailsRecyclerViewAdapter trackAdapter = new TrackDetailsRecyclerViewAdapter(getApplicationContext(), trackDetailsList);

                        //set orientation for the list
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

                        //set the layout manager and adapter for the recycler view.
                        trackRecycler.setLayoutManager(linearLayoutManager);
                        trackRecycler.setAdapter(trackAdapter);
                    }
                });
    }
}