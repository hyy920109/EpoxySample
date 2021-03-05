package com.hyy.epoxysample

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyy.epoxysample.controllers.LoadMoreController
import com.hyy.epoxysample.databinding.ActivityRecyclerViewBinding

/**
 *Create by hyy on 2021/2/25
 */
class LoadMoreActivity : AppCompatActivity() {

    private val rvController by lazy {
        LoadMoreController()
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
            val gridLayoutManager = GridLayoutManager(this@LoadMoreActivity, 3).apply {
                spanSizeLookup = rvController.spanSizeLookup
            }
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

            val loadmoreListener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
                override fun onLoadMore(page: Int) {
                    println("loadMore-->")
                    rvController.showLoadMore()
                    this@LoadMoreActivity.rootView.rvContent.postDelayed({
                        initData(true)
                    }, 2000)
                }
            }
            addOnScrollListener(loadmoreListener)
        }

    }

    private fun initData(loadMore: Boolean = false) {
        val data = mutableListOf<Student>()
        for (i in 0..100) {
            data.add(Student("Hyy $i", "$i"))
        }

        if (loadMore) {
            rvController.addData(data)
        }else {
            rvController.setList(data)
        }
//        rvController.add()
//        rvController.requestModelBuild()

    }
}