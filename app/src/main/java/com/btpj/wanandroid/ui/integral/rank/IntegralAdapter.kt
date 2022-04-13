package com.btpj.wanandroid.ui.integral.rank

import com.btpj.wanandroid.R
import com.btpj.wanandroid.data.bean.Integral
import com.btpj.wanandroid.databinding.ListItemIntegralBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

/**
 * 积分排行列表的Adapter
 *
 * @author LTP  2022/3/23
 */
class IntegralAdapter :
    BaseQuickAdapter<Integral, BaseDataBindingHolder<ListItemIntegralBinding>>(layoutResId = R.layout.list_item_integral),
    LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ListItemIntegralBinding>, item: Integral) {
        holder.dataBinding?.apply {
            integral = item
            executePendingBindings()
        }
    }
}