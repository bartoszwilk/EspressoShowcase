package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.espressoshowcase.R
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import io.reactivex.subjects.PublishSubject

import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.EpgTvProviderListItem
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.STANDARD_PROVIDER_ITEM_TYPE
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.StandardProviderItem
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.viewholder.StandardProviderViewHolder

class StandardProviderDelegate(private val standardProviderClicks: PublishSubject<StandardProviderItem>)
    : AdapterDelegate<List<EpgTvProviderListItem>>() {

    override fun isForViewType(items: List<EpgTvProviderListItem>, position: Int): Boolean =
            items[position].type == STANDARD_PROVIDER_ITEM_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            StandardProviderViewHolder(
                    standardProviderClicks = standardProviderClicks,
                    itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_provider_selection, parent, false))

    override fun onBindViewHolder(itemGuides: List<EpgTvProviderListItem>, position: Int,
                                  holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) =
            (holder as StandardProviderViewHolder).bind(itemGuides[position] as StandardProviderItem)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as StandardProviderViewHolder).unbind()
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        (holder as StandardProviderViewHolder).unbind()
        return super.onFailedToRecycleView(holder)
    }
}