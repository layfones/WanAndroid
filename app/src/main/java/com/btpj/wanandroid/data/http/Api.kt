package com.btpj.wanandroid.data.http

import com.btpj.lib_base.bean.ApiResponse
import com.btpj.lib_base.bean.PageResponse
import com.btpj.wanandroid.data.bean.*
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

    /** 获取广场列表分页数据 */
    @GET("user_article/list/{pageNo}/json")
    suspend fun getSquarePageList(
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int
    ): ApiResponse<PageResponse<Article>>

    /** 获取每日一问列表分页数据 */
    @GET("wenda/list/{pageNo}/json")
    suspend fun getAskPageList(@Path("pageNo") pageNo: Int): ApiResponse<PageResponse<Article>>

    /** 获取体系数据 */
    @GET("tree/json")
    suspend fun getTreeList(): ApiResponse<List<System>>

    /** 获取导航数据 */
    @GET("navi/json")
    suspend fun getNavigationList(): ApiResponse<List<Navigation>>

    /** 获取公众号作者列表 */
    @GET("wxarticle/chapters/json")
    suspend fun getAuthorTitleList(): ApiResponse<List<Classify>>

    /** 获取公众号作者文章分页列表 */
    @GET("wxarticle/list/{authorId}/{pageNo}/json")
    suspend fun getAuthorArticlePageList(
        @Path("authorId") authorId: Int,
        @Path("pageNo") pageNo: Int,
        @Query("page_size") pageSize: Int
    ): ApiResponse<PageResponse<Article>>
}