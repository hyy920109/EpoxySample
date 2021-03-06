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
        //???????????????????????? ??????????????????????????????????????????????????????????????? ??????????????????????????????
        "Custom $title".also { binding.name.text = it }
        binding.name.setOnClickListener(listener)
    }

    //???????????????????????????????????????????????????  ????????????????????????
    @OnVisibilityStateChanged
    fun onVisibilityStateChanged(
        @VisibilityState.Visibility visibilityState: Int
    ) {
        when (visibilityState) {
            //?????? ??????????????????????????????????????????
            VisibilityState.VISIBLE -> {
                Log.d(TAG, "$title Visible")
            }
            //???????????????
            VisibilityState.INVISIBLE -> {
                Log.d(TAG, "$title Invisible")
            }
            //????????????????????????????????????
            //??? ??????item????????????????????????????????????recyclerView ??????????????????????????????????????????
            //??? ??????item????????????????????????  ??????item?????????????????????????????????FULL_IMPRESSION_VISIBLE ???????????????
            VisibilityState.FOCUSED_VISIBLE -> {
                Log.d(TAG, "$title FocusedVisible")
            }
            //??????????????????????????? ?????????Threshold(75%)
            VisibilityState.UNFOCUSED_VISIBLE -> {
                Log.d(TAG, "$title UnfocusedVisible")
            }
            //??????????????????Threshold75%
            VisibilityState.PARTIAL_IMPRESSION_VISIBLE -> {
                Log.d(TAG, "$title PartialImpressionVisible")
            }
            //?????????????????????Threshold75%
            VisibilityState.PARTIAL_IMPRESSION_INVISIBLE -> {
                Log.d(TAG, "$title PartialImpressionInVisible")
            }
            //????????????
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