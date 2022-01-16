package ru.geekbrains.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "GitHubRepoTable")
data class GitHubRepo(

    @PrimaryKey
    @ColumnInfo
    @SerializedName("id")
    val id: String,

    @ColumnInfo
    @SerializedName("full_name")
    val full_name: String? = null,

    @ColumnInfo
    @SerializedName("size")
    val url: Long? = null
) : Parcelable