package com.hyy.epoxysample.controllers

import com.airbnb.epoxy.TypedEpoxyController
import com.hyy.epoxysample.R
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.holders.ItemDataHolder
import com.hyy.epoxysample.holders.ItemViewBindingDataClass
import com.hyy.epoxysample.holders.customViewItem
import com.hyy.epoxysample.holders.itemModelWithViewHolder

/**
 *Create by hyy on 2021/2/25
 */
class LinearController : TypedEpoxyController<List<Student>>() {

    override fun buildModels(data: List<Student>) {
        data.forEachIndexed { index, student ->

            itemModelWithViewHolder {
                id("viewHolder $index")
                name(student.name)
                listener {
                    println("Clicked Item")
                }
            }
            customViewItem {
                id("custom  $index")
                name(student.name)
                title(student.name)
                image(R.mipmap.ic_launcher)
                listener { _, parentView, clickedView, position ->

                }
                onVisibilityChanged { model, view, percentVisibleHeight, percentVisibleWidth, heightVisible, widthVisible ->
                    model.name()
                }
            }
            ItemDataHolder(student)
                .id("student ${student.id}")
                .addTo(this)

            ItemViewBindingDataClass(student)
                .id("student2 ${student.id}")
                .addTo(this)
        }
    }

}