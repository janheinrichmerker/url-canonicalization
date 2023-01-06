[![GitHub Actions](https://img.shields.io/github/actions/workflow/status/heinrichreimer/url-canonicalization/ci.yml?branch=master&style=flat-square)](https://github.com/heinrichreimer/url-canonicalization/actions/workflows/ci.yml)
[![JitPack](https://img.shields.io/jitpack/v/github/heinrichreimer/url-canonicalization?style=flat-square)](https://jitpack.io/#dev.reimer/url-canonicalization)

# 🔗 url-canonicalization<sup>[α](#status-α)</sup>

Canonicalize URLs based on HTML [canonical link relations](https://en.wikipedia.org/wiki/Canonical_link_element).

## Gradle Dependency

This library is available on [**jitpack.io**](https://jitpack.io/#dev.reimer/url-canonicalization).  
Add this in your `build.gradle.kts` or `build.gradle` file:

<details open><summary>Kotlin</summary>

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("dev.reimer:url-canonicalization:<version>")
}
```

</details>

<details><summary>Groovy</summary>

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'dev.reimer:url-canonicalization:<version>'
}
```

</details>

## Status α

⚠️ _Warning:_ This project is in an experimental alpha stage:
- The API may be changed at any time without further notice.
- Development still happens on `master`.
- Pull Requests are highly appreciated!
