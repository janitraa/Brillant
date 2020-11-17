package id.ac.ui.cs.mobileprogramming.janitra.brillant.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_details")
data class Profile (
    @ColumnInfo(name = "firstName") var firstName: String,
    @ColumnInfo(name = "lastName") var lastName: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "dob") var dob: String,
    @ColumnInfo(name = "goals") var goals: String,
    @ColumnInfo(name = "dreamJob") var dreamJob: String,
    @ColumnInfo(name = "image") var image: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 1)