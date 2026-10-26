package com.yinzcam.fnc314.hiring.core.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class NFLTeamsTriCode(
  public val conference: Conference,
  public val division: Division,
  public val teamName: String,
  public val city: String,
) {
  // AFC North
  BAL(
    conference = Conference.AFC,
    division = Division.NORTH,
    teamName = "Ravens",
    city = "Baltimore"
  ),
  CIN(
    conference = Conference.AFC,
    division = Division.NORTH,
    teamName = "Bengals",
    city = "Cincinnati"
  ),
  CLE(
    conference = Conference.AFC,
    division = Division.NORTH,
    teamName = "Browns",
    city = "Cleveland"
  ),
  PIT(
    conference = Conference.AFC,
    division = Division.NORTH,
    teamName = "Steelers",
    city = "Pittsburgh"
  ),

  // AFC East
  BUF(
    conference = Conference.AFC,
    division = Division.EAST,
    teamName = "Bills",
    city = "Buffalo"
  ),
  MIA(
    conference = Conference.AFC,
    division = Division.EAST,
    teamName = "Dolphins",
    city = "Miami"
  ),
  NE(
    conference = Conference.AFC,
    division = Division.EAST,
    teamName = "Patriots",
    city = "New England"
  ),
  NYJ(
    conference = Conference.AFC,
    division = Division.EAST,
    teamName = "Jets",
    city = "New York"
  ),

  // AFC South
  HOU(
    conference = Conference.AFC,
    division = Division.SOUTH,
    teamName = "Texans",
    city = "Houston"
  ),
  @SerialName("INC")
  IND(
    conference = Conference.AFC,
    division = Division.SOUTH,
    teamName = "Colts",
    city = "Indianapolis"
  ),
  JAX(
    conference = Conference.AFC,
    division = Division.SOUTH,
    teamName = "Jaguars",
    city = "Jacksonville"
  ),
  TEN(
    conference = Conference.AFC,
    division = Division.SOUTH,
    teamName = "Titans",
    city = "Tennessee"
  ),

  // AFC West
  DEN(
    conference = Conference.AFC,
    division = Division.WEST,
    teamName = "Broncos",
    city = "Denver"
  ),
  KC(
    conference = Conference.AFC,
    division = Division.WEST,
    teamName = "Chiefs",
    city = "Kansas City"
  ),
  LV(
    conference = Conference.AFC,
    division = Division.WEST,
    teamName = "Raiders",
    city = "Las Vegas"
  ),
  LAC(
    conference = Conference.AFC,
    division = Division.WEST,
    teamName = "Chargers",
    city = "Los Angeles"
  ),

  // NFC North
  CHI(
    conference = Conference.NFC,
    division = Division.NORTH,
    teamName = "Bears",
    city = "Chicago"
  ),
  DET(
    conference = Conference.NFC,
    division = Division.NORTH,
    teamName = "Lions",
    city = "Detroit"
  ),
  GB(
    conference = Conference.NFC,
    division = Division.NORTH,
    teamName = "Packers",
    city = "Green Bay"
  ),
  MIN(
    conference = Conference.NFC,
    division = Division.NORTH,
    teamName = "Vikings",
    city = "Minnesota"
  ),

  // NFC East
  DAL(
    conference = Conference.NFC,
    division = Division.EAST,
    teamName = "Cowboys",
    city = "Dallas"
  ),
  NYG(
    conference = Conference.NFC,
    division = Division.EAST,
    teamName = "Giants",
    city = "New York"
  ),
  PHI(
    conference = Conference.NFC,
    division = Division.EAST,
    teamName = "Eagles",
    city = "Philadelphia"
  ),
  WAS(
    conference = Conference.NFC,
    division = Division.EAST,
    teamName = "Commanders",
    city = "Washington"
  ),

  // NFC South
  ATL(
    conference = Conference.NFC,
    division = Division.SOUTH,
    teamName = "Falcons",
    city = "Atlanta"
  ),
  CAR(
    conference = Conference.NFC,
    division = Division.SOUTH,
    teamName = "Panthers",
    city = "Carolina"
  ),
  NO(
    conference = Conference.NFC,
    division = Division.SOUTH,
    teamName = "Saints",
    city = "New Orleans"
  ),
  TB(
    conference = Conference.NFC,
    division = Division.SOUTH,
    teamName = "Buccaneers",
    city = "Tampa Bay"
  ),

  // NFC West
  ARI(
    conference = Conference.NFC,
    division = Division.WEST,
    teamName = "Cardinals",
    city = "Arizona"
  ),
  LAR(
    conference = Conference.NFC,
    division = Division.WEST,
    teamName = "Rams",
    city = "Los Angeles"
  ),
  SF(
    conference = Conference.NFC,
    division = Division.WEST,
    teamName = "49ers",
    city = "San Francisco"
  ),
  SEA(
    conference = Conference.NFC,
    division = Division.WEST,
    teamName = "Seahawks",
    city = "Seattle"
  ),

  NONE(
    conference = Conference.AFC,
    division = Division.NORTH,
    teamName = "None",
    city = "None"
  );

  public val triCode: String get() = name
  public val fullName: String get() = "$city $teamName"
}

@Serializable
public enum class Conference {
  AFC,
  NFC,
}

@Serializable
public enum class Division {
  NORTH,
  EAST,
  SOUTH,
  WEST,
}