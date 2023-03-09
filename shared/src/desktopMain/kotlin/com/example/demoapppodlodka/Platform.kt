package com.example.demoapppodlodka

class IOSPlatform: Platform {
    override val name: String = "Desktop Version"
}

actual fun getPlatform(): Platform = IOSPlatform()