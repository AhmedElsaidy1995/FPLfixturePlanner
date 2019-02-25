package com.example.sasadoe.fplfixtureplanner.Model

import android.os.Parcel
import android.os.Parcelable

//class Player(var name: TextView, var team: String,var teamShirt: ImageView, var opponent: TextView ,val index: Int){
class Player(var name: String, var team: String, var opponent: String ,val index: Int):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(team)
        parcel.writeString(opponent)
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }


}