package com.hyy.epoxysample.controllers

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.hyy.epoxysample.R
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.holders.ItemDataHolder
import com.hyy.epoxysample.holders.customViewItemTwo

/**
 *Create by hyy on 2021/2/25
 */
class GridController : TypedEpoxyController<List<Student>>() {

    override fun buildModels(data: List<Student>) {
        data.forEachIndexed { index, student ->

            if (index % 7 == 0) {
                ItemDataHolder(student).apply {
                    spanSizeOverride(EpoxyModel.SpanSizeOverrideCallback { totalSpanCount, position, itemCount ->
                        println("GridController--> $totalSpanCount")
                        return@SpanSizeOverrideCallback 3
                    })
                    id("student ${student.id}")
                    addTo(this@GridController)
                }

            } else {
                customViewItemTwo {
                    id("custom  $index")
                    name(student.name)
                    title(student.name)

                    image(R.mipmap.ic_launcher)
                    listener { _, parentView, clickedView, position ->

                    }
                    spanSizeOverride(EpoxyModel.SpanSizeOverrideCallback { totalSpanCount, position, itemCount ->
                        return@SpanSizeOverrideCallback 1
                    })

                }
            }


        }
    }


//    override fun getSpanSizeLookup(): SpanSizeLookup {
//
//        return object : SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return if (position % 5 == 0) {
//                    3
//                } else {
//                    1
//                }
//            }
//        }
//    }
}