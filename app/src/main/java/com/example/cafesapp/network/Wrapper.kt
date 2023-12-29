package com.example.cafesapp.network

import kotlinx.serialization.Serializable

/**
 * Data class for the wrapper.
 *
 * @property totalCount the total number of results.
 * @property results the results.
 */
@Serializable
data class Wrapper(
    val totalCount: Int,
    val results: List<ApiCafe>,
)
