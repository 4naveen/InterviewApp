package com.naveen.interviewapp

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by RPS20 on 13-06-2018.
 */

class Question() :Parcelable{
    @SerializedName("question")
    @Expose
    var question: String? = null
    @SerializedName("Answer")
    @Expose
    var answer: String? = null

    constructor(parcel: Parcel) : this() {
        question = parcel.readString()
        answer = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeString(answer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}
