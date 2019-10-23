package com.example.enroute.beacon

import java.sql.Timestamp

data class Location (val timestamp: Timestamp, val coords: Coords)