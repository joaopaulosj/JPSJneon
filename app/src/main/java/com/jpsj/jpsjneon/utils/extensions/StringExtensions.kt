package com.jpsj.jpsjneon.utils.extensions

fun String.getInitialsLetters(): String? {
    val split = this.split(" ")
    var initials = ""
    split.forEach { initials = initials.plus(it[0]) }

    return try {
        if (initials.length > 1) "${initials[0]}${initials[1]}" else this.take(2)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}