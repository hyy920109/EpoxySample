package com.hyy.epoxysample.controllers

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.hyy.epoxysample.R
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.holders.ItemDataHolder
import com.hyy.epoxysample.holders.customViewItemTwo
import com.hyy.epoxysample.holders.mutableCustomViewItem
import com.hyy.epoxysample.holders.updateItem

/**
 *Create by hyy on 2021/2/25
 */
open class UpdateController : TypedEpoxyController<List<Student>>() {

    override fun buildModels(data: List<Student>) {
        updateItem {
            id("updateItem")
            title("UpdateItem")
            listener { _ ->
                onUpdateItemClicked()
            }
        }
        data.forEachIndexed { index, student ->
            mutableCustomViewItem {
                id("custom  $index")
                student(student)
//                name(student.name)
//                title(student.name)
//                image(R.mipmap.ic_launcher)
                listener { _, parentView, clickedView, position ->

                }
                spanSizeOverride(EpoxyModel.SpanSizeOverrideCallback { totalSpanCount, position, itemCount ->
                    return@SpanSizeOverrideCallback 1
                })
            }

        }

    }

    open fun onUpdateItemClicked() {

    }

}