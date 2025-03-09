

<h1 align="center">KMP Liquid Search</h1>

<p align="center">
  <a href="https://developer.android.com/guide/topics/manifest/uses-sdk-element">
    <img src="https://img.shields.io/badge/API-15%2B-blue.svg?style=flat" alt="Minimum API Level" />
  </a>
  <a href="https://maven-badges.herokuapp.com/maven-central/com.example/your-library">
    <img src="https://maven-badges.herokuapp.com/maven-central/com.example/your-library/badge.svg" alt="Maven Central" />
  </a>
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License: MIT" />
  </a>
  <a href="https://android-arsenal.com/">
    <img src="https://img.shields.io/badge/Android%20Arsenal-Liquid%20Slider-green.svg?style=flat" alt="Android Arsenal" />
  </a>
</p>

## ✨ Demo

<div style="display: flex; justify-content: center; align-items: center;">
  <img 
    src="https://raw.githubusercontent.com/mejdi14/KMP-Liquid-Search/main/demo/demo.gif"
    height="450"
    width="545"
    style="margin-right: 20px;"
  />
</div>

## :art: inspiration

This library was inspired by [Oleg Frolov](https://dribbble.com/shots/4605344-Search-icon-interaction) beautiful work.

## Installation

Add this to your module's `build.gradle` file:

```gradle
dependencies {
    ...
    implementation("io.github.mejdi14:KMP-Liquid-Search:0.2.3")}
```

## :fire:How to use


:fire: How to Use
The LiquidSearch component provides a beautiful animated search interface for your Jetpack Compose apps:
val isChecked = remember { mutableStateOf(false) }
val liquidSearchController = rememberLiquidSearchController()
LiquidSearch(
modifier = Modifier,
liquidSearchConfig = LiquidSearchConfig(),
isChecked = isChecked,
liquidSearchController = liquidSearchController
)
Using the Controller
The LiquidSearch component comes with a controller that allows you to programmatically reset the search:
// Reset the search field and state
liquidSearchController.resetSearch()
Customization Options
LiquidSearch is highly customizable with the LiquidSearchConfig class:
LiquidSearch(
modifier = Modifier.fillMaxWidth(),
liquidSearchConfig = LiquidSearchConfig(
height = 80.dp,                        // Height of the search bar
backgroundColor = Color(0xFF6147ff),   // Background color
shape = RoundedCornerShape(20.dp),     // Shape of the search bar
padding = PaddingValues(16.dp),        // Padding inside the search bar
startSpacing = 40f,                    // Text starting position
cancelIconSizeRatio = 4,               // Size ratio for the cancel icon
searchIconColor = Color.White,         // Color of the search icon
cancelIconColor = Color.White,         // Color of the cancel icon
iconActiveColor = Color.Transparent,   // Color when icon is active
iconInactiveColor = Color(0xFF6147ff), // Color when icon is inactive
searchIconElevation = 4.dp,            // Elevation of the search icon
clearSearchWhenUnFocus = true,         // Clear search on focus loss
liquidSearchIconPosition = LiquidSearchIconPosition.LEFT, // Icon position
),
isChecked = isChecked,
liquidSearchController = liquidSearchController
)
