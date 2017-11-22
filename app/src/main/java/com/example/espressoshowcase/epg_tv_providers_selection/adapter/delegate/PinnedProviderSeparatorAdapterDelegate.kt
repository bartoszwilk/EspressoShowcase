package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.espressoshowcase.R
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.EpgTvProviderListItem
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.PINNED_PROVIDER_SEPARATOR_ITEM_TYPE
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.viewholder.PinnedProviderSeparatorViewHolder

class PinnedProviderSeparatorAdapterDelegate : AdapterDelegate<List<EpgTvProviderListItem>>() {

    override fun isForViewType(items: List<EpgTvProviderListItem>, position: Int): Boolean =
            items[position].type == PINNED_PROVIDER_SEPARATOR_ITEM_TYPE

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            PinnedProviderSeparatorViewHolder(
                    LayoutInflater
                            .from(parent.context).inflate(R.layout.viewholder_provider_selection_separator, parent, false))

    @Suppress("EmptyFunctionBlock")
    override fun onBindViewHolder(items: List<EpgTvProviderListItem>, position: Int,
                                  holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        //do nuffin
    }
}