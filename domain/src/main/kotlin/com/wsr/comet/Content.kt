package com.wsr.comet

sealed interface Content {
    data class Text(val value: String) : Content
}