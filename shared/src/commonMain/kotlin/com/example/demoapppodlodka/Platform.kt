package com.example.demoapppodlodka

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform