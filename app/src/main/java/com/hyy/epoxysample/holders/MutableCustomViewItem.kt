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
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.databinding.ItemCustomViewLayoutBinding

/**
 * Create by hyy on 2021/2/26
 */
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MutableCustomViewItem @JvmOverloads constructor(
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

    // Or if you need to store data in properties there are two options

    // 1.You can make it nullable like this and annotate the setter
    var listener: View.OnClickListener? = null
        @CallbackProp set

    // 2. Or you can use lateinit
    @ModelProp
    lateinit var student: Student

    @AfterPropsSet
    fun useProps() {
        // This is optional, and is called after the annotated properties above are set.
        // This is useful for using several properties in one method to guarantee they are all set first.
        //这个方法是可选的 这个方法会被调用（在上面的注解属性被设置后 这个方法就会被调用）
        "Custom ${student.name}".also { binding.name.text = it }
        binding.name.setOnClickListener(listener)
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