package com.hyy.epoxysample.holders

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.card.MaterialCardView
import com.hyy.epoxysample.R
import com.hyy.epoxysample.model_helpers.KotlinEpoxyHolder

/**
 *Create by hyy on 2021/2/26
 */
@EpoxyModelClass(layout = R.layout.item_student_two_linear_layout)
abstract class ItemModelWithViewHolder : EpoxyModelWithHolder<ItemViewHolder>() {

    @EpoxyAttribute
    lateinit var listener: (v: View) -> Unit
    @EpoxyAttribute
    lateinit var name: String

    override fun bind(holder: ItemViewHolder) {
        "ItemModelWithViewHolder ${name}".also {
            holder.name.text = it
        }
        holder.container.setOnClickListener {
            listener(it)
        }
    }
}


class ItemViewHolder : KotlinEpoxyHolder() {
    val name by bind<AppCompatTextView>(R.id.name)
    val container by bind<MaterialCardView>(R.id.cv_container)
}