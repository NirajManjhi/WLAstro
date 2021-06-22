package com.wl.astro

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
data class AstroResponse(
    @SerializedName("date") @Expose val date: String,
    @SerializedName("explanation") @Expose val explanation: String,
    @SerializedName("hdurl") @Expose val hdUrl: String,
    @SerializedName("media_type") @Expose val mediaType: String,
    @SerializedName("service_version") @Expose val serviceVersion: String,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("url") @Expose val url: String
)