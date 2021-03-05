package com.hyy.epoxysample.holders

import android.view.View
import com.airbnb.epoxy.*
import com.hyy.epoxysample.R

/**
 * Create by hyy on 2021/2/26
 */
@EpoxyModelClass(layout = R.layout.item_header_layout)
abstract class HeaderItem : EpoxyModel<View>()