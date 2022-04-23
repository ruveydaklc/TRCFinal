package com.example.tripplanner.model

import java.util.*
import kotlin.properties.Delegates

class GezdiklerimEntity {

    var Id by Delegates.notNull<Int>()
    var Tarih: Date? = null
    var Aciklama: String? = null

}