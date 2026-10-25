package com.yinzcam.fnc314.hiring.core.data

import kotlinx.serialization.Serializable

@Serializable
public enum class NFLTeamsTriCode(
  public val conference: Conference,
  public val division: Division
) {
  // AFC North
  BAL(Conference.AFC, Division.NORTH),
  CIN(Conference.AFC, Division.NORTH),
  CLE(Conference.AFC, Division.NORTH),
  PIT(Conference.AFC, Division.NORTH),

  // AFC East
  BUF(Conference.AFC, Division.EAST),
  MIA(Conference.AFC, Division.EAST),
  NE(Conference.AFC, Division.EAST),
  NYJ(Conference.AFC, Division.EAST),

  // AFC South
  HOU(Conference.AFC, Division.SOUTH),
  IND(Conference.AFC, Division.SOUTH),
  JAX(Conference.AFC, Division.SOUTH),
  TEN(Conference.AFC, Division.SOUTH),

  // AFC West
  DEN(Conference.AFC, Division.WEST),
  KC(Conference.AFC, Division.WEST),
  LV(Conference.AFC, Division.WEST),
  LAC(Conference.AFC, Division.WEST),

  // NFC North
  CHI(Conference.NFC, Division.NORTH),
  DET(Conference.NFC, Division.NORTH),
  GB(Conference.NFC, Division.NORTH),
  MIN(Conference.NFC, Division.NORTH),

  // NFC East
  DAL(Conference.NFC, Division.EAST),
  NYG(Conference.NFC, Division.EAST),
  PHI(Conference.NFC, Division.EAST),
  WAS(Conference.NFC, Division.EAST),

  // NFC South
  ATL(Conference.NFC, Division.SOUTH),
  CAR(Conference.NFC, Division.SOUTH),
  NO(Conference.NFC, Division.SOUTH),
  TB(Conference.NFC, Division.SOUTH),

  // NFC West
  ARI(Conference.NFC, Division.WEST),
  LAR(Conference.NFC, Division.WEST),
  SF(Conference.NFC, Division.WEST),
  SEA(Conference.NFC, Division.WEST);

  public val triCode: String get() = name
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