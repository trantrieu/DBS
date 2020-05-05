package com.dbs.detail

import com.dbs.DBSAppComponent
import com.dbs.MainScope
import dagger.Component

@MainScope
@Component(dependencies = [DBSAppComponent::class])
internal interface DetailComponent {

    fun inject(detailActivity: DetailActivity)

}