package com.dxdx.randomeats.models.response;
import java.util.List;

import com.dxdx.randomeats.models.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlacesResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    @SerializedName("results")
    @Expose
    private List<Result> results;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}