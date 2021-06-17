package com.example.artistdata.interfaces;

import com.example.artistdata.models.ArtistDataModel;

import java.util.Map;
import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.QueryMap;

public interface Api {

    String BASE_URL = "https://itunes.apple.com/";
    @GET("search?")
    Observable<ArtistDataModel> getArtistData(@QueryMap Map<String, String> queryMap);
}
