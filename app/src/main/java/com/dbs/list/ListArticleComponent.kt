package com.dbs.list

import com.dbs.DBSAppComponent
import com.dbs.MainScope
import dagger.Component

@MainScope
@Component(dependencies = [DBSAppComponent::class], modules = [ListArticleModule::class])
internal interface ListArticleComponent {

    fun inject(listArticleActivity: ListArticleActivity)

}