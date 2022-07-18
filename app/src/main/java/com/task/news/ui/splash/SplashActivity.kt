package com.task.news.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.task.news.databinding.ActivitySplashBinding
import com.task.news.ui.dashaboard.DashboardActivity
import com.task.news.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, ISplashVM>(), Animator.AnimatorListener {
    override val viewModel: ISplashVM by viewModels<SplashVM>()
    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.lottieAnimation.addAnimatorListener(this)
    }

    override fun onAnimationStart(p0: Animator?) = Unit

    override fun onAnimationEnd(p0: Animator?) {
        startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
        finish()
    }

    override fun onAnimationCancel(p0: Animator?) = Unit

    override fun onAnimationRepeat(p0: Animator?) = Unit
}