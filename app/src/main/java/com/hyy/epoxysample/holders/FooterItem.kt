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
import com.hyy.epoxysample.databinding.ItemLoadMoreLayoutBinding

/**
 * Create by hyy on 2021/2/26
 */
@EpoxyModelClass(layout = R.layout.item_footer_layout)
abstract class FooterItem : EpoxyModel<View>()