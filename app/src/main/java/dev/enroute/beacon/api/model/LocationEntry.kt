package dev.enroute.beacon.api.model

import dev.enroute.beacon.model.Coords
import java.sql.Timestamp

data class LocationEntry (val timestamp: Timestamp, val coords: Coords)