package com.yinzcam.fnc314.hiring.core.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.builtins.InstantComponentSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import java.time.LocalDate
import kotlin.time.Clock
import kotlin.time.Instant

@Serializable
data class NFLTeamsPage(
  @SerialName("DefaultGameId")
  val defaultGameID: Long,

  @SerialName("Filters")
  val filters: List<Filter>,

  @SerialName("GameSection")
  val gameSection: List<GameSection>,

  @SerialName("Team")
  val team: Team,

  @SerialName("YinzNode")
  val yinzNode: YinzNode
)


@Serializable
data class Filter (
  @SerialName("Current")
  val current: String,

  @SerialName("Items")
  val filterItems: List<FilterItem>,

  @SerialName("Name")
  val name: String,

  @SerialName("Placeholder")
  val placeholder: String,

  @SerialName("QueryParameter")
  val queryParameter: String,

  @SerialName("Type")
  val type: String
)

@Serializable
data class FilterItem (
  @SerialName("Id")
  val id: Int,

  @SerialName("Name")
  val name: String
)

@Serializable
data class GameSection (
  @SerialName("Game")
  val game: List<Game>,

  @SerialName("Heading")
  val heading: String
)

@Serializable
public data class Game (
  @SerialName("AwayScore")
  val awayScore: String? = null,

  @SerialName("Buttons")
  val buttons: List<Button> = emptyList(),

  @SerialName("CardData")
  val cardData: CardData,

  @SerialName("Clock")
  val clock: String? = null,

  @SerialName("Date")
  val date: Date = Date("", "", LocalDate.now(), "", "", Clock.System.now()),

  @SerialName("Down")
  val down: String? = null,

  @SerialName("GameState")
  val gameState: String? = null,

  @SerialName("Home")
  val home: Boolean,

  @SerialName("HomeScore")
  val homeScore: String? = null,

  @SerialName("Id")
  val id: Long = Long.MIN_VALUE,

  @SerialName("IsHome")
  val isHome: Boolean,

  @SerialName("IsSuperStadium")
  val isSuperStadium: Boolean,

  @SerialName("Label")
  val label: String,

  @SerialName("Opponent")
  val opponent: Opponent? = null,

  @SerialName("Quarter")
  val quarter: String? = null,

  @SerialName("QuarterShorthand")
  val quarterShorthand: String? = null,

  @SerialName("Radio")
  val radio: String,

  @SerialName("Result")
  val result: String,

  @SerialName("ScheduleHeader")
  val scheduleHeader: String,

  @SerialName("SecondaryButtons")
  val secondaryButtons: JsonArray = JsonArray(emptyList()),

  @SerialName("Tickets")
  val tickets: Tickets? = null,

  @SerialName("TV")
  val tv: String,

  @SerialName("Type")
  val type: String,

  @SerialName("Venue")
  val venue: String? = null,

  @SerialName("Week")
  val week: String,

  @SerialName("WLT")
  val wlt: String? = null,
)

@Serializable
public data class Button (
  @SerialName("Title")
  val title: String,

  @SerialName("URL")
  val url: String
)

@Serializable
public data class CardData (
  @SerialName("ClickthroughURL")
  val clickthroughURL: String,

  @SerialName("IsDefault")
  val isDefault: String = "False"
)

@Serializable
public data class Date (
  @SerialName("IsTBA")
  val isTBA: String,

  @SerialName("IsTba")
  val isTba: String,

  @SerialName("Numeric")
  @Serializable(
    with = LocalDateSerializer::class,
  )
  val numeric: LocalDate,

  @SerialName("Text")
  val text: String,

  @SerialName("Time")
  val time: String,

  @SerialName("Timestamp")
  val timestamp: Instant
) {
}

internal class LocalDateSerializer : KSerializer<LocalDate> {
  override val descriptor: SerialDescriptor
    get() = SerialDescriptor("LocalDate", InstantComponentSerializer.descriptor)

  override fun serialize(
    encoder: Encoder,
    value: LocalDate,
  ) {
    encoder.encodeString(
      value.toString()
    )
  }

  override fun deserialize(decoder: Decoder): LocalDate =
    decoder.decodeString().let {
      LocalDate.parse(it)
    }
}

@Serializable
public data class Opponent (
  @SerialName("City")
  val city: String,

  @SerialName("FullName")
  val fullName: String,

  @SerialName("Name")
  val name: String,

  @SerialName("Record")
  val record: String,

  @SerialName("TriCode")
  val triCode: String
)

@Serializable
public data class Tickets (
  @SerialName("HasLink")
  val hasLink: Boolean,

  @SerialName("Label")
  val label: String,

  @SerialName("Link")
  val link: String
)

@Serializable
public data class Team (
  @SerialName("City")
  val city: String,

  @SerialName("FullName")
  val fullName: String,

  @SerialName("Losses")
  val losses: String,

  @SerialName("Name")
  val name: String,

  @SerialName("PrimaryColor")
  val primaryColor: String,

  @SerialName("Record")
  val record: String,

  @SerialName("TriCode")
  val triCode: String,

  @SerialName("WinPercentage")
  val winPercentage: String,

  @SerialName("Wins")
  val wins: String
)

@Serializable
public data class YinzNode (
  @SerialName("Ads")
  val ads: String,

  @SerialName("Carrier")
  val carrier: String,

  @SerialName("Generated")
  val generated: String,

  @SerialName("InVenue")
  val inVenue: Boolean,

  @SerialName("VenueStatus")
  val venueStatus: String
)