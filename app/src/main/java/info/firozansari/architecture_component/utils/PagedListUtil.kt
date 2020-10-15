package info.firozansari.architecture_component.utils

/**
 * Created by Firoz Ansari on 15/10/2020.
 */
import androidx.paging.PagedList

fun pagedListConfig() = PagedList.Config.Builder()
    .setInitialLoadSizeHint(10)
    .setEnablePlaceholders(false)
    .setPageSize(10)
    .build()