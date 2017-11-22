package pl.wp.videostar.viper.epg_tv_providers_selection.adapter

import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.delegate.PinnedProviderDelegate
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.delegate.PinnedProviderSeparatorAdapterDelegate
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.delegate.StandardProviderDelegate
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.*
import kotlin.reflect.KClass

class EpgTvProviderSelectionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @VisibleForTesting
    val standardProviderClicks = PublishSubject.create<StandardProviderItem>()!!
    private val delegatesManager = AdapterDelegatesManager<List<EpgTvProviderListItem>>()
            .addDelegate(PINNED_PROVIDER_ITEM_TYPE, PinnedProviderDelegate())
            .addDelegate(STANDARD_PROVIDER_ITEM_TYPE, StandardProviderDelegate(standardProviderClicks))
            .addDelegate(PINNED_PROVIDER_SEPARATOR_ITEM_TYPE, PinnedProviderSeparatorAdapterDelegate())


    var listingItems: List<EpgTvProviderListItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
            selectPinnedProviderIfExists()
        }

    init {
        standardProviderClicks
                .subscribeBy(
                        onNext = { clickedStandardProviderItem ->
                            unselectPreviouslySelectedStandardProvider()
                            selectClickedStandardProvider(clickedStandardProviderItem)
                        })
    }

    override fun getItemViewType(position: Int) =
            delegatesManager.getItemViewType(listingItems, position)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            delegatesManager.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            delegatesManager.onBindViewHolder(listingItems, position, holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) =
            delegatesManager.onViewRecycled(holder)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder) =
            delegatesManager.onFailedToRecycleView(holder)

    override fun getItemCount() = listingItems.size

    private fun selectClickedStandardProvider(selectedItem: StandardProviderItem) {
//        listingItems
//                .firstOrNull { it is StandardProviderItem && it.provider == selectedItem.provider }
//                ?.cast(StandardProviderItem::class)
//                ?.let {
//                    it.isSelected = true
//                    notifyItemChanged(listingItems.indexOf(it))
//                }
    }

    private fun unselectPreviouslySelectedStandardProvider() {
//        listingItems
//                .firstOrNull { it is StandardProviderItem && it.isSelected }
//                ?.cast(StandardProviderItem::class)
//                ?.let {
//                    it.isSelected = false
//                    notifyItemChanged(listingItems.indexOf(it))
//                }
    }

    private fun selectPinnedProviderIfExists() {
        listingItems
                .firstOrNull { it is PinnedProviderItem }
                ?.cast(PinnedProviderItem::class)
                ?.apply { isSelected = true }
    }

    fun <T : Any> Any.cast(clazz: KClass<T>) = clazz.java.cast(this)!!
}