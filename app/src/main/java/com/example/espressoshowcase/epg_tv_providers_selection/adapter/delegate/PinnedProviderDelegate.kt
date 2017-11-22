package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.espressoshowcase.R
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.EpgTvProviderListItem
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.PINNED_PROVIDER_ITEM_TYPE
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.PinnedProviderItem
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.viewholder.PinnedProviderViewHolder

class PinnedProviderDelegate
    : AdapterDelegate<List<EpgTvProviderListItem>>() {

    override fun isForViewType(items: List<EpgTvProviderListItem>, position: Int) =
            items[position].type == PINNED_PROVIDER_ITEM_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            PinnedProviderViewHolder(
                    itemView =
                    LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.viewholder_provider_selection, parent, false))

    override fun onBindViewHolder(items: List<EpgTvProviderListItem>, position: Int,
                                  holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) =
            (holder as PinnedProviderViewHolder).bind(items[position] as PinnedProviderItem)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as PinnedProviderViewHolder).unbind()
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        (holder as PinnedProviderViewHolder).unbind()
        return super.onFailedToRecycleView(holder)
    }
}