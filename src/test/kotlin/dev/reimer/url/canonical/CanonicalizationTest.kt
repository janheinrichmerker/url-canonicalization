package dev.reimer.url.canonical

import dev.reimer.kotlin.jvm.ktx.toURL
import org.junit.jupiter.api.Test

class CanonicalizationTest {

    private companion object {
       private val DOG_BREEDS = "https://en.m.wikipedia.org/wiki/Dog_breeds".toURL()
       private val LIST_OF_DOG_BREEDS = "https://en.m.wikipedia.org/wiki/List_of_dog_breeds".toURL()
    }

    @Test
    fun `Can canonicalize Dog Breeds`() {
        assert(DOG_BREEDS.canonicalize() == LIST_OF_DOG_BREEDS)
    }

    @Test
    fun `Dog breed is canonical`() {
        assert(LIST_OF_DOG_BREEDS.isCanonical())
    }

    @Test
    fun `Dog breeds is not canonical`() {
        assert(!DOG_BREEDS.isCanonical())
    }
}
