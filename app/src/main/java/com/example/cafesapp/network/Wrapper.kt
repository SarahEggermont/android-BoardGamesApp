package com.example.cafesapp.network

import kotlinx.serialization.Serializable

/**
 * Data class for the wrapper.
 * @property total_count the total number of results.
 * @property results the results.
 */
@Serializable
data class Wrapper(
    val total_count: Int,
    val results: List<ApiCafe>
)
