package com.fed.pitarestesttask.model


import com.google.gson.annotations.SerializedName

data class Multimedia(
    @SerializedName("src") val src: String = "",
    @SerializedName("width") val width: Int = 0,
    @SerializedName("type") val type: String = "",
    @SerializedName("height") val height: Int = 0
)


data class ResultsItem(
    @SerializedName("multimedia") val multimedia: Multimedia?,
    @SerializedName("date_updated") val dateUpdated: String = "",
    @SerializedName("display_title") val displayTitle: String = "",
    @SerializedName("link") val link: Link,
    @SerializedName("publication_date") val publicationDate: String = "",
    @SerializedName("summary_short") val summaryShort: String = "",
    @SerializedName("critics_pick") val criticsPick: Int = 0,
    @SerializedName("byline") val byline: String = "",
    @SerializedName("headline") val headline: String = "",
    @SerializedName("mpaa_rating") val mpaaRating: String = "",
    @SerializedName("opening_date") val openingDate: String = ""
)


data class Link(
    @SerializedName("suggested_link_text") val suggestedLinkText: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("url") val url: String = ""
)


data class ApiResponse(
    @SerializedName("copyright") val copyright: String = "",
    @SerializedName("has_more") val hasMore: Boolean = false,
    @SerializedName("results") val results: List<ResultsItem>?,
    @SerializedName("num_results") val numResults: Int = 0,
    @SerializedName("status") val status: String = ""
)


