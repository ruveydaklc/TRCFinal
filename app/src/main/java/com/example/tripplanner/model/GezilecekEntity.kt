package com.example.tripplanner.model

import kotlin.properties.Delegates

class GezilecekEntity(var Id: Int?) {

   // var Id by Delegates.notNull<Int>()
    var Adi: String? = null
    var Tanim: String? = null
    var Aciklama: String? = null
    var Oncelik:String? = null

}