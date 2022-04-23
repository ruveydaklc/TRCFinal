package com.example.tripplanner.model

import kotlin.properties.Delegates

class YerEntity {

    var Id by Delegates.notNull<Int>()
    var YerAdi: String? = null
    var KisaTanim: String? = null
    var KisaAciklama: String? = null

}