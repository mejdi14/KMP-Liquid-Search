

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

``` java
                            var controller: AnimationController? by remember { mutableStateOf(null) }
                            VanishComposable(
                                Modifier,
                                VanishOptions = VanishOptions(),
                                effect = AnimationEffect.SHATTER,
                                onControllerReady = {
                                    controller = it
                                }
                            ) {
                                // Your Composable
                                ContentComposable()
                            }
```

Animation Listener
-----

``` java
// Start animation
controller?.triggerVanish() {
                        // Do something when animation finishes
                    }
                    
// Reverse animation                    
controller?.reset()
```

Animation types
-----

``` java
    PIXELATE,
    SWIRL,
    SCATTER,
    SHATTER,
    WAVE,
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT,
    UP,
    DOWN,
    DISSOLVE,
    EXPLODE
```

Hold animation duration after separation
-----

``` java
 .timeBetweenAnimations
```

Configuration options
-----

``` java
  pixelSize: size of each pixel or dot 
  pixelSpacing: space between pixels when they are separated
  pixelVelocity: velocity of the pixels
  animationDuration: duration of the animation from start to finish
  triggerFinishAt: use this if you want to trigger the end of animation a bit earlier (1f: wait to end, 0f: don't wait)
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
