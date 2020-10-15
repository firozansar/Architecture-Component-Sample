package info.firozansari.architecture_component.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
data class Media (

    @SerializedName("id") val id : Int,
    @SerializedName("blogId") val blogId : Int,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("image") val image : String,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url : String
)