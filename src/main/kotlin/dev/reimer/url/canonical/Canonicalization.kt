package dev.reimer.url.canonical

import org.jsoup.Connection
import org.jsoup.helper.HttpConnection
import org.jsoup.nodes.Document
import java.net.URL

fun URL.canonicalize(resolveFrom: URL = this): URL {
    val connection: Connection = HttpConnection.connect(this)
    val document: Document = try {
        connection.get()
    } catch (e: Exception) {
        return this
    }
    val canonicalLink = document
            .head()
            .select("""link[rel="canonical"][href]""")
            .firstOrNull()
            ?.attr("href")
            ?: return this
    return URL(resolveFrom, canonicalLink)
}

fun URL.isCanonical() = this == canonicalize()

