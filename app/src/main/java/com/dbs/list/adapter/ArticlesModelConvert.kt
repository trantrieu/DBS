package com.dbs.list.adapter

import com.dbs.data.article.list.Article
import com.dbs.util.DateConverter

internal object ArticlesModelConvert {

    fun convertViewModel(articleList: List<Article>): List<ArticleModel> {
        return articleList.map {
            with(it) {
                ArticleModel(
                    id,
                    title,
                    DateConverter.convertTimeStampToDateString(lastUpdate),
                    avatar,
                    shortDescription
                )
            }
        }
    }

}