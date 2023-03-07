package com.example.demoapppodlodka

actual fun getPlatform(): Platform = object : Platform {
    override val name: String = "Desktop version ..."
}