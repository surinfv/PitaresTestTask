package com.fed.pitarestesttask.model.POJO

import com.google.gson.annotations.SerializedName

/**
 * created by Fedor SURIN on 10.02.2018.
 */
data class Result(@SerializedName("display_title")
                  val displayTitle: String,
                  @SerializedName("mpaa_rating")
                  val mpaaRating: String,
                  @SerializedName("critics_pick")
                  val criticsPick: Int,
                  val byline: String,
                  val headline: String,
                  @SerializedName("summary_short")
                  val summaryShort: String,
                  @SerializedName("publication_date")
                  val publicationDate: String,
                  @SerializedName("opening_date")
                  val openingDate: String,
                  @SerializedName("date_updated")
                  val dateUpdated: String,
                  val link: Link,
                  val multimedia: Multimedia)