package com.hyy.epoxysample.holders

import com.hyy.epoxysample.R
import com.hyy.epoxysample.Student
import com.hyy.epoxysample.databinding.ItemStudentLinearLayoutBinding
import com.hyy.epoxysample.model_helpers.ViewBindingKotlinModel

/**
 *Create by hyy on 2021/2/26
 */
// This does not require annotations or annotation processing.
// The data class is required to generated equals/hashcode which Epoxy needs for diffing.
// Views are easily declared via property delegates
// 这种方式不需要注解或者时注解处理
// 但是出入的dataClass需要自定义equals和hashCode方法  方便epoxy框架diffing
// view 的id就可以直接声明view
class ItemViewBindingDataClass(val student: Student) :
    ViewBindingKotlinModel<ItemStudentLinearLayoutBinding>(R.layout.item_student_linear_layout) {
    override fun ItemStudentLinearLayoutBinding.bind() {
        "ViewBindingHolder ${student.name}".also {
            name.text = it
        }
    }
}