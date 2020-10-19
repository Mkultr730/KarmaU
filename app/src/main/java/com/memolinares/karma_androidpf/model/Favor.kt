package com.memolinares.karma_androidpf.model

data class Favor (
    val deliver_place: String = "",
    val user_client: String = "",
    val user_employee: String = "",
    var stage: String = "",
    val type: String = "" ,
    val details: String = "",
    var key: String = "",
    val client_check: Boolean = false,
    val employee_check: Boolean = false
) {
}