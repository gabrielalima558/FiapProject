package com.example.scaniaapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedback_table")
class Feedback
    (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "person_name")
    var personName: String = "",

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "person_identification")
    var personIdentification: String = "",

    @ColumnInfo(name = "vehicle_model")
    var vehicleModel: String = "",

    @ColumnInfo(name = "vehicle_fabrication_year")
    var vehicleFabricationYear: String = "",

    @ColumnInfo(name = "vehicle_miles_travled")
    var vehicleMilesTravled: String = "",

    @ColumnInfo(name = "description")
    var description: String = ""

)