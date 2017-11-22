package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.espressoshowcase.R
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.viewholder_provider_selection.view.*
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.PinnedProviderItem

class PinnedProviderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var clicksDisposable: Disposable? = null

    fun bind(item: PinnedProviderItem) {
        itemView.txtName.text = "Provider"
        itemView.txtChannelsCountInfo.text = "Provider's channels"
        setCellSelection(item.isSelected)
        clicksDisposable = itemView.clicks()
                .subscribeBy(
                        onNext = {
                            item.isSelected = item.isSelected.not()
                            setCellSelection(isSelected = item.isSelected)
                        })
    }

    fun unbind() = clicksDisposable?.dispose()

    private fun setCellSelection(isSelected: Boolean) {
        itemView.check.setImageResource(if (isSelected) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox_unchecked)
        itemView.setBackgroundResource(if (isSelected) R.drawable.cell_epg_active else R.drawable.cell_epg_normal)
    }
}