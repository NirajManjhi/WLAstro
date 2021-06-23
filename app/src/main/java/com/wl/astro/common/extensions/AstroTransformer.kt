package com.wl.astro.common.extensions

import com.wl.astro.common.database.AstroTable
import com.wl.astro.common.model.AstroResponse
import com.wl.astro.common.model.HomeModel

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
fun AstroResponse.transformToModel(): HomeModel {
    return HomeModel(
        date = this.date, title = this.title, desc = this.explanation, mediaType = this.mediaType, url = this.url
    )
}

fun AstroTable.transformToModel(): HomeModel {
    return HomeModel(
        date = this.date, title = this.title, desc = this.desc, mediaType = this.mediaType, url = this.url
    )
}

fun HomeModel.transformToTable(): AstroTable {
    return AstroTable(
        date = this.date,
        title = this.title,
        desc = this.desc,
        mediaType = this.mediaType,
        url = this.url
    )
}
