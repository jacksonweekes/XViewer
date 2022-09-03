package io.github.jacksonweekes.xviewer.upcominglaunches.data
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class Launch(
    @SerialName("date_local")
    val dateLocal: String,
    @SerialName("date_precision")
    val datePrecision: String,
    @SerialName("date_unix")
    val dateUnix: Int,
    @SerialName("date_utc")
    val dateUtc: String,
    @SerialName("links")
    val links: Links?,
    @SerialName("name")
    val name: String
)

@Serializable
data class Links(
    @SerialName("patch")
    val patch: Patch?,
)

@Serializable
data class Patch(
    @SerialName("large")
    val large: String?,
    @SerialName("small")
    val small: String?
)
