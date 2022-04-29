package com.example.pokedex.util

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.example.pokedex.R
import java.math.RoundingMode
import java.text.DecimalFormat

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun formattedNumber(number: Int?): String {
    val formattedNumber = number.toString().padStart(3, '0')
    return "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
}

fun convertValue(convert: Int?): String {
    convert?.let {
        return try {
            val value = ((it.toDouble()) / 10)
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            df.format(value).replace(",", ".").toDouble().toString()
        } catch (e: Exception) {
            "0"
        }
    } ?: run {
        return "0"
    }
}

fun getTypePokemon(type: String): Int {
    return when (type) {
        "normal" -> R.drawable.type_normal
        "fighting" -> R.drawable.type_fighting
        "flying" -> R.drawable.type_flying
        "poison" -> R.drawable.type_poison
        "ground" -> R.drawable.type_ground
        "rock" -> R.drawable.type_rock
        "bug" -> R.drawable.type_bug
        "ghost" -> R.drawable.type_ghost
        "steel" -> R.drawable.type_steel
        "fire" -> R.drawable.type_fire
        "water" -> R.drawable.type_water
        "grass" -> R.drawable.type_grass
        "electric" -> R.drawable.type_electric
        "psychic" -> R.drawable.type_psychic
        "ice" -> R.drawable.type_ice
        "dragon" -> R.drawable.type_dragon
        "dark" -> R.drawable.type_dark
        "fairy" -> R.drawable.type_fairy
        "unknown" -> R.drawable.type_normal
        "shadow" -> R.drawable.type_ghost
        else -> R.drawable.type_normal
    }
}

fun getTypeColor(type: String?): Int {
    return when (type) {
        "fighting" -> R.color.fighting
        "flying" -> R.color.flying
        "poison" -> R.color.poison
        "ground" -> R.color.ground
        "rock" -> R.color.rock
        "bug" -> R.color.bug
        "ghost" -> R.color.ghost
        "steel" -> R.color.steel
        "fire" -> R.color.fire
        "water" -> R.color.water
        "grass" -> R.color.grass
        "electric" -> R.color.electric
        "psychic" -> R.color.psychic
        "ice" -> R.color.ice
        "dragon" -> R.color.dragon
        "fairy" -> R.color.fairy
        "dark" -> R.color.dark
        else -> R.color.gray_21
    }
}

fun Context.showConfirmationDialog(
    title: String,
    message: String,
    isCancelable: Boolean = true,
    onConfirm: (() -> Unit)? = null,
    onCancel: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
): AlertDialog {
    val dialogView = View.inflate(this, R.layout.dialog_confirmation, null)
    val dialog = AlertDialog.Builder(this)
        .setView(dialogView)
        .setCancelable(isCancelable)
        .setOnDismissListener {
            it.dismiss()
            onDismiss?.invoke()
        }
        .create()

    with(dialogView) {
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)
        findViewById<TextView>(R.id.textTittle).text = title
        findViewById<TextView>(R.id.textMessage).text = message
        findViewById<TextView>(R.id.buttonConfirm).setOnClickListener {
            dialog.dismiss()
            onConfirm?.invoke()
        }
        findViewById<TextView>(R.id.buttonCancel).setOnClickListener {
            dialog.dismiss()
            onCancel?.invoke()
        }
    }

    dialog.show()

    return dialog
}

fun Context.showErrorDetailsDaialog(
    message: String,
    isCancelable: Boolean = true,
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
): AlertDialog {
    val dialogView = View.inflate(this, R.layout.dialog_error_details, null)
    val dialog = AlertDialog.Builder(this)
        .setView(dialogView)
        .setCancelable(isCancelable)
        .setOnDismissListener {
            it.dismiss()
            onDismiss?.invoke()
        }
        .create()

    with(dialogView) {
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)
        findViewById<TextView>(R.id.textMessageErrorDetails).text = message
        findViewById<TextView>(R.id.buttonOk).setOnClickListener {
            dialog.dismiss()
            onConfirm?.invoke()
        }
    }

    dialog.show()

    return dialog
}