<h1 align="center">
     PokédexApp
</h1>

## Description
Dive into the enchanting world of Pokémon with PokédexApp. It is an Android application developed using the Kotlin programming language. This app retrieves Pokémon data from the popular Pokémon API service, [PokéAPI](https://pokeapi.co/), and presents it to users.

## Screenshots
| Main Screen | Main Screen | Main Screen | Main Screen |
| ------------- | ------------- | ------------- | ------------- |
| ![Main Screen](https://github.com/esravsar/PokedexApp/assets/50179587/83d81a50-a176-44ae-acda-fb98a02e0faa) | ![Main Screen](https://github.com/esravsar/PokedexApp/assets/50179587/c4093870-8790-4471-bd7c-7dd3b4073206) | ![Main Screen](https://github.com/esravsar/PokedexApp/assets/50179587/7a74ef97-7f6a-4b45-80dc-5ba874eb7ae3) | ![Main Screen](https://github.com/esravsar/PokedexApp/assets/50179587/2ebd2531-28e9-4664-ba9a-554c60a3fb6b) |

| Main Screen | Detail Screen | Detail Screen | Detail Screen |
| ------------- | ------------- | ------------- | ------------- |
| ![Main Screen](https://github.com/esravsar/PokedexApp/assets/50179587/7600719a-7eff-4111-a6fb-0a7f986b94ec) | ![Detail Screen](https://github.com/esravsar/PokedexApp/assets/50179587/28db2c9f-f09f-410b-a2ab-b8aac580b66a) | ![Detail Screen](https://github.com/esravsar/PokedexApp/assets/50179587/ae863074-5454-4106-b6fd-06debe1375f8) | ![Detail Screen](https://github.com/esravsar/PokedexApp/assets/50179587/aa593dc4-fbd4-4cdf-ac16-06d1c5bbd217) |

## Features
**Home Screen:**
   - View a list of Pokémon.
   - Search for Pokémon by name and ID.
   
**Detail Screen:**
   - View detailed information about the selected Pokémon.
   - Navigate between different Pokémon.

## Tech Stack & Libraries
* 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) and [Flow](https://developer.android.com/kotlin/flow).
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  -  A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage fragment operations.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer. 
  - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
- [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open source media management and image loading framework for Android.
