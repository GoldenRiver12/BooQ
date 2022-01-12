
package com.example.demo.model.googlebooksapi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Generated("jsonschema2pojo")
@Data
public class SearchInfo {

    @SerializedName("textSnippet")
    @Expose
    public String textSnippet;

}
