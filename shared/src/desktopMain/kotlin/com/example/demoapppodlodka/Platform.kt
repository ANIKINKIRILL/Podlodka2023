package com.example.demoapppodlodka

class DesktopPlatform : Platform {
    override val name: String = "Desktop Version!"
}

actual fun getPlatform(): Platform = DesktopPlatform()