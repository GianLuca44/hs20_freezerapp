package fhnw.emoba.modules.module04.weather_solution.ui.theme


import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import fhnw.emoba.R

private val Lato = fontFamily(
        font(R.font.lato_extra_light, FontWeight.ExtraLight),
        font(R.font.lato_hai, FontWeight.Thin),
        font(R.font.lato_lig, FontWeight.Light),
        font(R.font.lato_reg, FontWeight.Normal),
        font(R.font.lato_bol, FontWeight.Bold),
        font(R.font.lato_bla, FontWeight.Black)
)

val typography = Typography(
        defaultFontFamily = Lato,
)