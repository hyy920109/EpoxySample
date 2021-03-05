package com.hyy.epoxysample

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyModel
import com.hyy.epoxysample.controllers.HeaderFooterController
import com.hyy.epoxysample.controllers.LoadMoreController
import com.hyy.epoxysample.databinding.ActivityRecyclerViewBinding
import com.hyy.epoxysample.holders.customViewItemTwo
import com.hyy.epoxysample.holders.footerItem
import com.hyy.epoxysample.holders.headerItem

/**
 *Create by hyy on 2021/2/25
 */
class HeaderFooterActivity : AppCompatActivity() {

    private val rvController by lazy {
        HeaderFooterController()
    }

    private lateinit var rootView: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootView = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(rootView.root)

        initView()
        initData()
    }

    private fun initView() {
        rootView.rvContent.apply {
            val gridLayoutManager = LinearLayoutManager(this@HeaderFooterActivity)
            layoutManager = gridLayoutManager
//            adapter = rvController.adapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    outRect.set(10.dp, 10.dp, 10.dp, 0.dp)
                }
            })

            //setItemSpacingDp()
        }
    }

    private fun initData(loadMore: Boolean = false) {
        val data = mutableListOf<Student>()
        for (i in 0..5) {
            data.add(Student("Hyy $i", "$i"))
        }

//        rvController.setData(data)

        rootView.rvContent.withModels {
            headerItem {
                id("headerView")
            }
            data.forEachIndexed { index, student ->

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
}