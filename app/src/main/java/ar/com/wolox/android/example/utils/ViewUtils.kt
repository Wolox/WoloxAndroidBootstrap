package ar.com.wolox.android.example.utils

import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.GradientDrawable.Orientation
import android.graphics.drawable.GradientDrawable.Orientation.TL_BR
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

fun View.toggleVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.togglePresence(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.setClickEnabled(enabled: Boolean) {
    this.isClickable = enabled
    this.isFocusable = enabled
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

/**
 * Creates a [GradientDrawable] Drawable with the given parameters.
 * When specifying only [color] that color will be used as the background color.
 * If both [color] and [endColor] are provided, [color] will be used as the start color of the gradient.
 *
 * @param color Color of the drawable. In case [endColor] is provided, this'll act as starting color.
 * @param endColor End color of the gradient. By default is "-1".
 * @param cornerRadius Corner radius of the drawable **In DP**. By default is 4dp.
 * @param orientation Orientation of the gradient. By default is [Orientation.TL_BR].
 * @return [GradientDrawable] with the gradient
 */
fun Resources.createRoundDrawable(
    @ColorInt color: Int,
    @ColorInt endColor: Int = -1,
    cornerRadius: Float = 4f,
    orientation: Orientation = TL_BR
): GradientDrawable {

    val end = if (endColor == -1) color else endColor
    val pxRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, cornerRadius, displayMetrics)
    val gradient = GradientDrawable(orientation, intArrayOf(color, end))
    gradient.cornerRadius = pxRadius
    return gradient
}

/**
 * Adds new filters to the ones that the [EditText] already has.
 */
fun EditText.addInputFilter(vararg inputFilter: InputFilter) {
    val filters = mutableListOf<InputFilter>()
    this.filters?.forEach { filters.add(it) }
    inputFilter.forEach { filters.add(it) }
    this.filters = filters.toTypedArray()
}

/**
 * Sets the [text] to this [TextView]. If the text is null or empty the [TextView.getVisibility] will be set to GONE.
 * Otherwise it will be set to VISIBLE.
 */
fun TextView.setTextOrGone(text: String?) {
    this.togglePresence(!text.isNullOrEmpty())
    this.text = text
}

/**
 * Adds support for a empty argument click listener
 */
fun View.onClickListener(listener: () -> Unit) {
    this.setOnClickListener { listener() }
}

/**
 * Adds a simple lambda textWatcher to listen after the text changes.
 */
fun EditText.onTextChanged(listener: (Editable) -> Unit) {
    this.addTextChangedListener(object : SimpleTextWatcher() {
        override fun afterTextChanged(editable: Editable) { listener(editable) }
    })
}

/**
 * Simple text watcher with base implementations of [TextWatcher].
 */
open class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(editable: Editable) {}
    override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {}
}