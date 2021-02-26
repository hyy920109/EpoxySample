package com.hyy.epoxysample

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyy.epoxysample.databinding.ActivityRecyclerViewBinding

private val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

/**
 *Create by hyy on 2021/2/25
 */
class LinearLayoutManagerActivity : AppCompatActivity() {

    private val rvController by lazy {
        LinearController()
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
            layoutManager = LinearLayoutManager(this@LinearLayoutManagerActivity)
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
        val data = mutableListOf<Student>()
        for (i in 0..100) {
            data.add(Student("Hyy $i", "$i"))
        }
        rvController.setData(data)

//        rvController.requestModelBuild()

    }
}