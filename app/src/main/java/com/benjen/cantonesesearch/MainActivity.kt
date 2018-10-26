package com.benjen.cantonesesearch

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

//主页
class MainActivity : FragmentActivity() {

    lateinit var ignoreToneFragment: IgnoreToneFragment
    lateinit var specailWordFragment: SpecailWordFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //强制竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        initView()
        initListener()
    }

    private fun initListener() {
        //切换到"同音字"
        tv_similar_words.setOnClickListener {
            tv_similar_words.isActivated = true
            tv_special_word.isActivated = false
            switchFragment(ignoreToneFragment, specailWordFragment)
        }
        //切换到特定字
        tv_special_word.setOnClickListener {
            tv_similar_words.isActivated = false
            tv_special_word.isActivated = true
            switchFragment(specailWordFragment, ignoreToneFragment)
        }
    }

    private fun initView() {
        //切换的button
        tv_similar_words.isActivated = true
        tv_special_word.isActivated = false
        //初始化fragment
        ignoreToneFragment = IgnoreToneFragment.newInstance()
        specailWordFragment = SpecailWordFragment.newInstance()
        addFragment(R.id.fl_container, ignoreToneFragment)
        addFragment(R.id.fl_container, specailWordFragment)
        switchFragment(ignoreToneFragment, specailWordFragment)
    }

    /**
     * 添加fragment
     */
    fun addFragment(containerId: Int, fragment: Fragment): Unit {
        var ft = supportFragmentManager.beginTransaction()
        ft.add(containerId, fragment).commit()
    }

    /**
     * 切换fragment, 不重新初始化
     */
    fun switchFragment(showFragment: Fragment, hideFragment: Fragment) {
        var ft = supportFragmentManager.beginTransaction()
        ft.hide(hideFragment).show(showFragment).commit()
    }
}
