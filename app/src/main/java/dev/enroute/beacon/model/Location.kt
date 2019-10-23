package dev.enroute.beacon.model

import java.sql.Timestamp

data class Location (val timestamp: Timestamp, val coords: Coords)