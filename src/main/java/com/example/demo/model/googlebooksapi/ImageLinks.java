
package com.example.demo.model.googlebooksapi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Generated("jsonschema2pojo")
@Data
public class ImageLinks {

    @SerializedName("smallThumbnail")
    @Expose
    public String smallThumbnail;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

}
