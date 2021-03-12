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
import com.hyy.epoxysample.controllers.UpdateController
import com.hyy.epoxysample.databinding.ActivityRecyclerViewBinding
import com.hyy.epoxysample.holders.customViewItemTwo
import com.hyy.epoxysample.holders.footerItem
import com.hyy.epoxysample.holders.headerItem
import com.hyy.epoxysample.holders.updateItem
import java.util.*

/**
 *Create by hyy on 2021/2/25
 */
class UpdateItemActivity : AppCompatActivity() {

    private val rvController by lazy {
        object : UpdateController() {
            override fun onUpdateItemClicked() {
                shuffleData()
            }
        }
    }

    private fun shuffleData() {
        data[0] = data[0].copy(name = "哈哈哈哈")
        rvController.setData(data)
    }

    val data = mutableListOf<Student>()

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
            val gridLayoutManager = LinearLayoutManager(this@UpdateItemActivity)
            layoutManager = gridLayoutManager
            adapter = rvController.adapter
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

        }
    }

    private fun initData() {
        for (i in 0..5) {
            data.add(Student("Hyy $i", "$i"))
        }
        rvController.setData(data)
    }
}