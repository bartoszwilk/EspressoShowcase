package com.example.espressoshowcase

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_provider_selection.*
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.EpgTvProviderSelectionAdapter
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.PinnedProviderItem
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.PinnedProviderSeparatorItem
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.StandardProviderItem

class MainActivity : AppCompatActivity() {

    private val adapter = EpgTvProviderSelectionAdapter()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_selection)
        initActionBar()
        initCollapsingToolbar()
        initRecyclerView()
        adapter.listingItems =
                listOf(PinnedProviderItem(),
                        PinnedProviderSeparatorItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem(),
                        StandardProviderItem())
    }

    private fun initActionBar() =
            supportActionBar?.apply {
                title = ""
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

    private fun initCollapsingToolbar() {
        appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            isToolbarMoreCollapsedThanHalfway(verticalOffset).let { toolbarMoreCollapsedThanHalfway ->
                setToolbarTextWrapperVisibility(visible = toolbarMoreCollapsedThanHalfway)
                setUpArrowGreyout(greyoutEnabled = toolbarMoreCollapsedThanHalfway)
            }
        }
    }

    private fun isToolbarMoreCollapsedThanHalfway(verticalOffset: Int) =
            Math.abs(verticalOffset) > appBarLayout.totalScrollRange / 2

    private fun setToolbarTextWrapperVisibility(visible: Boolean) =
            if (visible) txtToolbarTitle.visible() else txtToolbarTitle.gone()

    private fun setUpArrowGreyout(greyoutEnabled: Boolean) {
        supportActionBar?.setUpArrowColorResource(this, if (greyoutEnabled) R.color.text_grey else R.color.text_white)
    }

    fun ActionBar.setUpArrowColorResource(context: Context, @ColorRes colorRes: Int) =
            ContextCompat
                    .getDrawable(context, R.drawable.abc_ic_ab_back_material)
                    .apply { setColorFilter(ContextCompat.getColor(context, colorRes), PorterDuff.Mode.SRC_ATOP) }
                    .let { setHomeAsUpIndicator(it) }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun initRecyclerView() =
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = this@MainActivity.adapter
            }

    private fun View.visible() = let { visibility = View.VISIBLE }

    private fun View.gone() = let { visibility = View.GONE }
}

