package com.btpj.wanandroid.data.http

import com.btpj.lib_base.bean.ApiResponse
import com.btpj.wanandroid.data.bean.Article
import com.btpj.wanandroid.data.bean.Banner
import com.btpj.lib_base.bean.PageResponse
import com.btpj.wanandroid.data.bean.ProjectTitle
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Http接口，Retrofit的请求Service
 *
 * @author LTP  2022/3/21
 */
interface Api {

    /** 获取首页banner数据 */
    @GET("banner/json")
    suspend fun getBanner(): ApiResponse<List<Banner>>

    /** 获取置顶文章集合数据 */
    @GET("article/top/json")
    suspend fun getArticleTopList(): ApiResponse<List<Article>>

    /** 获取首页文章数据 */
    @GET("article/list/{pageNo}/json")
    suspend fun getArticlePageList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int
    ): ApiResponse<PageResponse<Article>>

    /** 获取项目分类数据 */
    @GET("project/tree/json")
    suspend fun getProjectTitleList(): ApiResponse<List<ProjectTitle>>

    /** 获取最新项目列表分页数据 */
    @GET("article/listproject/{pageNo}/json")
    suspend fun getNewProjectPageList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int
    ): ApiResponse<PageResponse<Article>>

    /** 获取项目列表分页数据 */
    @GET("project/list/{pageNo}/json")
    suspend fun getProjectPageList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int,
        @Query("cid") categoryId: Int
    ): ApiResponse<PageResponse<Article>>

    /** 获取体系数据 */
    @GET("tree/json")
    suspend fun getTreeList(): ApiResponse<List<String>>
}