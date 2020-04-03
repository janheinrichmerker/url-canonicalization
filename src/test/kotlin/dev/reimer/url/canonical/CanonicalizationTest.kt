package dev.reimer.url.canonical

import dev.reimer.kotlin.jvm.ktx.toURL
import org.junit.jupiter.api.Test

class CanonicalizationTest {

    private companion object {
       private val DOG_BREEDS = "https://en.m.wikipedia.org/wiki/Dog_breeds".toURL()
       private val DOG_BREED = "https://en.m.wikipedia.org/wiki/Dog_breed".toURL()
    }

    @Test
    fun `Can canonicalize Dog Breeds`() {
        assert(DOG_BREEDS.canonicalize() == DOG_BREED)
    }

    @Test
    fun `Dog breed is canonical`() {
        assert(DOG_BREED.isCanonical())
    }

    @Test
    fun `Dog breeds is not canonical`() {
        assert(!DOG_BREEDS.isCanonical())
    }
}