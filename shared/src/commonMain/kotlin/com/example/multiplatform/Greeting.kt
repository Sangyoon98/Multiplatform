package com.example.multiplatform

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, This is ${platform.name}!"
    }

    fun title(): String {
        return "toss"
    }
}