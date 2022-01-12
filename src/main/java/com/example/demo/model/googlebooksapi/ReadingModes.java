
package com.example.demo.model.googlebooksapi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Generated("jsonschema2pojo")
@Data
public class ReadingModes {

    @SerializedName("text")
    @Expose
    public Boolean text;
    @SerializedName("image")
    @Expose
    public Boolean image;

}
