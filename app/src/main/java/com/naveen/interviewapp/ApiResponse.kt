package com.naveen.interviewapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.io.Serializable


class ApiResponse : Serializable {
    @SerializedName("questions")
    @Expose
    var questions: List<Question>? = null

}
