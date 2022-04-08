package com.btpj.wanandroid.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.btpj.lib_base.base.BaseViewModel
import com.btpj.lib_base.bean.ApiResponse
import com.btpj.lib_base.bean.PageResponse
import com.btpj.lib_base.ext.handleResponse
import com.btpj.lib_base.ext.launch
import com.btpj.lib_base.utils.LogUtil
import com.btpj.wanandroid.data.DataRepository
import com.btpj.wanandroid.data.bean.Article
import com.btpj.wanandroid.data.bean.Banner
import kotlinx.coroutines.async

/**
 * 首页ViewModel
 *
 * @author LTP 2022/3/23
 */
class HomeViewModel : BaseViewModel() {

    companion object {
        /** 每页显示的条目大小 */
        const val PAGE_SIZE = 10
    }

    /** Banner列表 */
    val bannerListLiveData = MutableLiveData<List<Banner>>()

    /** 文章列表 */
    val articlePageListLiveData = MutableLiveData<PageResponse<Article>>()

    override fun start() {}

    /** 请求首页轮播图 */
    fun fetchBanners() {
        launch({
            val response = DataRepository.getBanner()
            handleResponse(response, {
                bannerListLiveData.value = response.data
            })
        })
    }

    /**
     * 请求文章列表，第一页包括一个请求置顶的接口和一个文章分页列表的接口
     *
     * @param pageNo 页码（0表示请求第1页）
     */
    fun fetchArticlePageList(pageNo: Int = 0) {
        launch({
            if (pageNo == 0) {
                // 第一页会同时请求置顶文章列表接口和分页文章列表的接口，使用async进行并行请求速度更快（默认是串行的）
                val response1 = async { DataRepository.getArticlePageList(pageNo, PAGE_SIZE) }
                val response2 = async { DataRepository.getArticleTopList() }

                handleResponse(response1.await(), {
                    val list = response1.await()
                    handleResponse(response2.await(), {
                        (list.data.datas as ArrayList<Article>).addAll(0, response2.await().data)
                        articlePageListLiveData.value = list.data
                    })
                })
            } else {
                val response = DataRepository.getArticlePageList(pageNo, PAGE_SIZE)
                handleResponse(response, { articlePageListLiveData.value = response.data })
            }
        })
    }


}