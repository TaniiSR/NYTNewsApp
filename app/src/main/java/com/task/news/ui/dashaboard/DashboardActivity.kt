package com.task.news.ui.dashaboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.task.news.R
import com.task.news.databinding.ActivityDashboardBinding
import com.task.news.utils.base.BaseNavActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseNavActivity<ActivityDashboardBinding, IDashboardVM>() {
    override val viewModel: IDashboardVM by viewModels<DashboardVM>()
    override fun getViewBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)

    override val navigationGraphId: Int
        get() = R.navigation.news_navigation

    override fun onDestinationChanged(
        controller: NavController?,
        destination: NavDestination?,
        arguments: Bundle?
    ) = Unit

}
