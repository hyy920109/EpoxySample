package com.hyy.epoxysample.holders

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.*
import com.hyy.epoxysample.R
import com.hyy.epoxysample.databinding.ItemCustomViewLayoutBinding

/**
 * Create by hyy on 2021/2/26
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CustomViewItem @JvmOverloads constructor(
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

    //页面退出的时候也是会回调这个方法的  注意不要内存泄漏
    @OnVisibilityStateChanged
    fun onVisibilityStateChanged(
        @VisibilityState.Visibility visibilityState: Int
    ) {
        when (visibilityState) {
            //可见 可以认为是刚刚可见就被调用了
            VisibilityState.VISIBLE -> {
                Log.d(TAG, "$title Visible")
            }
            //完全不可见
            VisibilityState.INVISIBLE -> {
                Log.d(TAG, "$title Invisible")
            }
            //这个回调在两种情形下调用
            //① 这个item可见部分大于一半的试图（recyclerView 整体高度（竖直滑动情况下））
            //② 这个item小于一半视图高度  这个item全部可见时会被调用（和FULL_IMPRESSION_VISIBLE 同事调用）
            VisibilityState.FOCUSED_VISIBLE -> {
                Log.d(TAG, "$title FocusedVisible")
            }
            //可见部分不足百分白 但超过Threshold(75%)
            VisibilityState.UNFOCUSED_VISIBLE -> {
                Log.d(TAG, "$title UnfocusedVisible")
            }
            //可见部分超过Threshold75%
            VisibilityState.PARTIAL_IMPRESSION_VISIBLE -> {
                Log.d(TAG, "$title PartialImpressionVisible")
            }
            //可见部分已不足Threshold75%
            VisibilityState.PARTIAL_IMPRESSION_INVISIBLE -> {
                Log.d(TAG, "$title PartialImpressionInVisible")
            }
            //全部可见
            VisibilityState.FULL_IMPRESSION_VISIBLE -> {
                Log.d(TAG, "$title FullImpressionVisible")
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
        Log.d(TAG, "$title clear: -->")
        //onVisibilityEventDrawable.reset()
    }

    companion object {
        const val TAG = "CustomViewItem"
    }
}