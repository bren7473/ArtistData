package com.example.artistdata.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ArtistDataModel {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<ArtistTrackDetailsModel> trackDetails = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<ArtistTrackDetailsModel> getTrackDetails() {
        return trackDetails;
    }

    public void setResults(List<ArtistTrackDetailsModel> results) {
        this.trackDetails = trackDetails;
    }

}
