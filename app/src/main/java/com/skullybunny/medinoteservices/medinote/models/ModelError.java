package com.skullybunny.medinoteservices.medinote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin on 4/27/2017.
 */

public class ModelError {

    @SerializedName("Message")
    private String message;

    @SerializedName("ModelState")
    private String modelState;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getModelState() {
        return modelState;
    }

    public void setModelState(String modelState) {
        this.modelState = modelState;
    }
}
