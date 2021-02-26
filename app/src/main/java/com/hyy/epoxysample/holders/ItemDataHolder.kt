package com.hyy.epoxysample.holders

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyModel
import com.google.android.material.button.MaterialButton
import com.hyy.epoxysample.R
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.model_helpers.KotlinModel

/**
 *Create by hyy on 2021/2/25
 */
//传统的通过传递LayoutId 并找到对应view
class ItemDataHolder(
    val student: Student
) : KotlinModel(R.layout.item_student_btn) {

    val name by bind<MaterialButton>(R.id.name)

    override fun bind() {
        name.text = student.name
    }

    override fun spanSizeOverride(spanSizeCallback: SpanSizeOverrideCallback?): EpoxyModel<View> {
        return super.spanSizeOverride(spanSizeCallback)
    }
}