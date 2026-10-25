package com.yinzcam.fnc314.hiring.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
public val Typography: Typography =
  Typography(
    // BYE Week
    displayLarge = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.ExtraBold,
      fontSize = 40.sp,
      lineHeight = 52.sp,
      letterSpacing = 0.sp,
    ),
    // Scores
    displayMedium = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Bold,
      fontSize = 32.sp,
      lineHeight = 44.sp,
      letterSpacing = 0.5.sp,
    ),
    displaySmall = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Bold,
      fontSize = 24.sp,
      lineHeight = 34.sp,
      letterSpacing = 0.5.sp,
    ),
    headlineLarge = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Bold,
      fontSize = 26.sp,
      lineHeight = 36.sp,
      letterSpacing = 0.1.sp,
    ),
    // Team Names
    headlineMedium = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Bold,
      fontSize = 24.sp,
      lineHeight = 34.sp,
      letterSpacing = 0.1.sp,
    ),
    headlineSmall = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Bold,
      fontSize = 22.sp,
      lineHeight = 32.sp,
      letterSpacing = 0.1.sp,
    ),
    titleLarge = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Normal,
      fontSize = 22.sp,
      lineHeight = 30.sp,
      letterSpacing = 0.sp,
    ),
    // Regular Season Container text
    titleMedium = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Light,
      fontSize = 20.sp,
      lineHeight = 28.sp,
      letterSpacing = 0.5.sp,
    ),
    titleSmall = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Light,
      fontSize = 18.sp,
      lineHeight = 26.sp,
      letterSpacing = 0.5.sp,
    ),
    bodyLarge = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Normal,
      fontSize = 16.sp,
      lineHeight = 24.sp,
      letterSpacing = 0.5.sp,
    ),
    // Default Text Size
    bodyMedium = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Normal,
      fontSize = 14.sp,
      lineHeight = 20.sp,
      letterSpacing = 0.5.sp,
    ),
    bodySmall = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Normal,
      fontSize = 12.sp,
      lineHeight = 18.sp,
      letterSpacing = 0.5.sp,
    ),
    labelLarge = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Medium,
      fontSize = 14.sp,
      lineHeight = 16.sp,
      letterSpacing = 0.5.sp,
    ),
    labelMedium = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Medium,
      fontSize = 12.sp,
      lineHeight = 14.sp,
      letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Medium,
      fontSize = 10.sp,
      lineHeight = 12.sp,
      letterSpacing = 0.5.sp,
    ),
  )
