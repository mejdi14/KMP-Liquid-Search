

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

## 🚀:Try it out:
Click [here](https://mejdi14.github.io/KMP-Liquid-Search/) to test the live version.

## :fire: How to Use

```kotlin

@Composable
fun SampleLiquidSearch() {
    val isActive = remember { mutableStateOf(false) }
    val controller = rememberLiquidSearchController()

    val searchConfig = LiquidSearchConfig(
        height = 60.dp,
        backgroundColor = Color(0xFF6147ff),
        cancelIconColor = Color.White,
        searchIconColor = Color.White,
        iconActiveColor = Color.Transparent,
        iconInactiveColor = Color(0xFF6147ff),
        liquidSearchActionListener = object : LiquidSearchActionListener() {
            override fun onTextChange(text: String) {
                // Handle text changes
            }
            override fun onCancelClicked() {
                // Handle cancel action
            }
            override fun onSearchIconClick() {
                // Handle search icon click
            }
        }
    )

    LiquidSearch(
        modifier = Modifier.fillMaxWidth(),
        liquidSearchConfig = searchConfig,
        isChecked = isActive,
        liquidSearchController = controller
    )
}
```

## LiquidSearchConfig Properties

The `LiquidSearchConfig` class allows you to customize the appearance and behavior of the liquid search. Below is a table of its properties:

| Field                        | Type                              | Default Value                      | Description                                                   |
|------------------------------|----------------------------------|------------------------------------|---------------------------------------------------------------|
| `height`                     | `Dp`                              | `60.dp`                            | The height of the search bar.                                |
| `backgroundColor`             | `Color`                           | `Color(0xFF6147ff)`               | Background color of the search bar.                          |
| `cancelIconColor`             | `Color`                           | `Color.White`                      | Color of the cancel (X) icon.                                |
| `searchIconColor`             | `Color`                           | `Color.White`                      | Color of the search icon.                                    |
| `iconActiveColor`             | `Color`                           | `Color.Transparent`                | Color when the search is active.                             |
| `iconInactiveColor`           | `Color`                           | `Color(0xFF6147ff)`               | Color when the search is inactive.                           |
| `searchIconAnimationDuration` | `Long`                            | `300`                              | Animation duration for the search icon.                      |
| `cancelIconSizeRatio`         | `Int`                             | `5`                                | The size ratio for the cancel icon.                          |
| `clearSearchWhenUnFocus`      | `Boolean`                         | `true`                             | Whether to clear search when it loses focus.                 |
| `liquidSearchActionListener`  | `LiquidSearchActionListener`      | `defaultLiquidSearchActionListener` | Listener for search interactions. |

## Use Custom Search Actions

You can provide custom behavior when a search action happens by overriding the `LiquidSearchActionListener`.

```kotlin
val searchConfig = LiquidSearchConfig(
    height = 60.dp,
    backgroundColor = Color(0xFF6147ff),
    liquidSearchActionListener = object : LiquidSearchActionListener() {
        override fun onTextChange(text: String) {
            // Handle text changes
        }
        override fun onCancelClicked() {
            // Handle cancel click
        }
        override fun onSearchIconClick() {
            // Handle search icon click
        }
    }
)
```

## 🤝 Contributing

Contributions, issues and feature requests are welcome.<br />
Feel free to check [issues page] if you want to contribute.<br />

## Author

👤 **Mejdi Hafiane**

- profile: [@MejdiHafiane](https://twitter.com/mejdi141)

## Show your support

Please ⭐️ this repository if this project helped you!

## 📝 License

Copyright © 2019 [Mejdi Hafiane](https://github.com/mejdi14).<br />
This project is [MIT](https://github.com/mejdi14/readme-md-generator/blob/master/LICENSE) licensed.
