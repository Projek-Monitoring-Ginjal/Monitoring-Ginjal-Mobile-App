package com.neotelemetrixgdscunand.monitoringginjalapp.domain.common

data class Response <T>(
    val error: Boolean,
    val message: String,
    val data: T?
)