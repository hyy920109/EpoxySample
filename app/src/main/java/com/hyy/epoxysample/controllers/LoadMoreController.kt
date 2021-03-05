package com.hyy.epoxysample.controllers

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.TypedEpoxyController
import com.hyy.epoxysample.R
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.holders.ItemDataHolder
import com.hyy.epoxysample.holders.customViewItemTwo
import com.hyy.epoxysample.holders.loadMoreViewItem

/**
 *Create by hyy on 2021/2/25
 */
class LoadMoreController : TypedEpoxyController<List<Student>>() {

    private var showLoadMore = false
    private val totalData = mutableListOf<Student>()
    override fun buildModels(students: List<Student>) {
        students.forEachIndexed { index, student ->

            if (index % 7 == 0) {
                ItemDataHolder(student).apply {
                    spanSizeOverride(EpoxyModel.SpanSizeOverrideCallback { totalSpanCount, position, itemCount ->
                        println("GridController--> $totalSpanCount")
                        return@SpanSizeOverrideCallback 3
                    })
                    id("student ${student.id}")
                    addTo(this@LoadMoreController)
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
        if (showLoadMore) loadMoreViewItem {
            id("loadMore")
            //override spanSize
            spanSizeOverride(EpoxyModel.SpanSizeOverrideCallback { totalSpanCount, position, itemCount ->
                println("GridController--> $totalSpanCount")
                return@SpanSizeOverrideCallback 3
            })
        }

    }

    fun setList(data: List<Student>) {
        showLoadMore = true
        totalData.clear()
        totalData.addAll(data)
        setData(totalData)
    }

    fun showLoadMore() {
        showLoadMore = true
        setData(totalData)
    }

    fun addData(data: List<Student>) {
        showLoadMore = true
        totalData.addAll(data)
        setData(totalData)
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