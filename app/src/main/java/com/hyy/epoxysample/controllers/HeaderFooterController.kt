package com.hyy.epoxysample.controllers

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.hyy.epoxysample.R
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.holders.*

/**
 *Create by hyy on 2021/2/25
 */
class HeaderFooterController : TypedEpoxyController<List<Student>>() {

    override fun buildModels(students: List<Student>) {
        headerItem {
            id("headerView")
        }
        students.forEachIndexed { index, student ->

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
        footerItem {
            id("FooterView")
        }
    }

}