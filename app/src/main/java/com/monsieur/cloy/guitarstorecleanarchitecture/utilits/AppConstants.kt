package com.monsieur.cloy.guitarstorecleanarchitecture.utilits

import android.view.Menu
import com.monsieur.cloy.guitarstorecleanarchitecture.R
import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.MainActivity

lateinit var APP_ACTIVITY: MainActivity
val myExeptionsTag = "myExeptions"
val myInfoTag = "myInfo"
var toolbarMenu: Menu? = null

val bassGuitarImages_1 = listOf(R.drawable.b11, R.drawable.b12)
val bassGuitarImages_2 = listOf(R.drawable.b21, R.drawable.b22)
val bassGuitarImages_3 = listOf(R.drawable.b31, R.drawable.b32)
val bassGuitarImages_4 = listOf(R.drawable.b41, R.drawable.b42)

val electricGuitarImages_1 = listOf(R.drawable.e11, R.drawable.e12)
val electricGuitarImages_2 = listOf(R.drawable.e21, R.drawable.e22)
val electricGuitarImages_3 = listOf(R.drawable.e31, R.drawable.e32)
val electricGuitarImages_4 = listOf(R.drawable.e41, R.drawable.e42)
val electricGuitarImages_5 = listOf(R.drawable.e51, R.drawable.e52)

val arrayImages = listOf(
    bassGuitarImages_1,
    bassGuitarImages_2,
    bassGuitarImages_3,
    bassGuitarImages_4,
    electricGuitarImages_1,
    electricGuitarImages_2,
    electricGuitarImages_3,
    electricGuitarImages_4,
    electricGuitarImages_5
)