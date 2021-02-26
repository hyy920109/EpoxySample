package com.hyy.epoxysample.holders

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.airbnb.epoxy.*
import com.hyy.epoxysample.R
import com.hyy.epoxysample.databinding.ItemCustomViewLayoutBinding

/**
 * Create by hyy on 2021/2/26
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CustomViewItemTwo @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        ItemCustomViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    // You can annotate your methods with @ModelProp
    // A default model property value can be set by using Kotlin default arguments, but you
    // must use JvmOverloads for Epoxy to handle it correctly.
    //
    @JvmOverloads
    @ModelProp
    fun name(name: String = "") {
        "Custom $name".also { binding.name.text = it }
    }

    // Or if you need to store data in properties there are two options

    // 1.You can make it nullable like this and annotate the setter
    var listener: View.OnClickListener? = null
        @CallbackProp set

    // 2. Or you can use lateinit
    @TextProp
    lateinit var title: CharSequence

    // You can annotate your methods with @ModelProp
    // A default model property value can be set by using Kotlin default arguments, but you
    // must use JvmOverloads for Epoxy to handle it correctly.
    //
    @JvmOverloads
    @ModelProp
    fun image(@DrawableRes res: Int = R.mipmap.ic_launcher) {
       binding.image.setImageResource(res)
    }

    @AfterPropsSet
    fun useProps() {
        // This is optional, and is called after the annotated properties above are set.
        // This is useful for using several properties in one method to guarantee they are all set first.
        //这个方法是可选的 这个方法会被调用（在上面的注解属性被设置后 这个方法就会被调用）
        "Custom $title".also { binding.name.text = it }
        binding.name.setOnClickListener(listener)
    }

    @OnVisibilityStateChanged
    fun onVisibilityStateChanged(
        @VisibilityState.Visibility visibilityState: Int
    ) {
        when (visibilityState) {
            VisibilityState.VISIBLE -> {
                Log.d(TAG, "$title Visible")
//                onVisibilityEventDrawable.visible = true
            }
            VisibilityState.INVISIBLE -> {
                Log.d(TAG, "$title Invisible")
//                onVisibilityEventDrawable.visible = false
            }
            VisibilityState.FOCUSED_VISIBLE -> {
                Log.d(TAG, "$title FocusedVisible")
//                onVisibilityEventDrawable.focusedVisible = true
            }
            VisibilityState.UNFOCUSED_VISIBLE -> {
                Log.d(TAG, "$title UnfocusedVisible")
//                onVisibilityEventDrawable.focusedVisible = false
            }
            VisibilityState.PARTIAL_IMPRESSION_VISIBLE -> {
                Log.d(TAG, "$title PartialImpressionVisible")
//                onVisibilityEventDrawable.partialImpression = true
            }
            VisibilityState.PARTIAL_IMPRESSION_INVISIBLE -> {
                Log.d(TAG, "$title PartialImpressionInVisible")
//                onVisibilityEventDrawable.partialImpression = false
            }
            VisibilityState.FULL_IMPRESSION_VISIBLE -> {
                Log.d(TAG, "$title FullImpressionVisible")
//                onVisibilityEventDrawable.fullImpression = true
            }
        }
    }

    @OnVisibilityChanged
    fun onVisibilityChanged(
        percentVisibleHeight: Float,
        percentVisibleWidth: Float,
        visibleHeight: Int,
        visibleWidth: Int
    ) {
        Log.d(
            TAG,
            "$title onChanged ${percentVisibleHeight.toInt()} ${percentVisibleWidth.toInt()} " +
                    "$visibleHeight $visibleWidth ${System.identityHashCode(
                        this
                    )}"
        )
    }

    @OnViewRecycled
    fun clear() {
        Log.d(TAG, "clear: -->")
        //onVisibilityEventDrawable.reset()
    }

    companion object {
        const val TAG = "CustomViewItem"
    }
}