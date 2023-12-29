package com.example.cafesapp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class for the wrapper.
 *
 * @property totalCount the total number of results.
 * @property results the results.
 */
@Serializable
data class Wrapper(
    @SerialName("total_count") val totalCount: Int,
    val results: List<ApiCafe>,
)
