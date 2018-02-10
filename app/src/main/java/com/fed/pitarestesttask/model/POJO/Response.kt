package com.fed.pitarestesttask.model.POJO

/**
 * created by Fedor SURIN on 10.02.2018.
 */
data class Response(val status: String,
                    val copyright: String,
                    val hasMore: Boolean,
                    val numResults: Int,
                    val results: List<Result>
)