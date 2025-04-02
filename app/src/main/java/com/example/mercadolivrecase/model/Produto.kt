package com.example.mercadolivrecase

import android.os.Parcel
import android.os.Parcelable

data class Produto(
    val imagem: Int,
    val preco: String,
    val descricao: String?,
    val frete: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imagem)
        parcel.writeString(preco)
        parcel.writeString(descricao)
        parcel.writeString(frete)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Produto> {
        override fun createFromParcel(parcel: Parcel): Produto {
            return Produto(parcel)
        }

        override fun newArray(size: Int): Array<Produto?> {
            return arrayOfNulls(size)
        }
    }
}