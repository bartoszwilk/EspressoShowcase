package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.espressoshowcase.R
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.viewholder_provider_selection.view.*
import pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item.StandardProviderItem

class StandardProviderViewHolder(private val standardProviderClicks: PublishSubject<StandardProviderItem>,
                                 itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var clicksDisposable: Disposable? = null

    fun bind(item: StandardProviderItem) {
        itemView.txtName.text = "Standard provider"
        itemView.txtChannelsCountInfo.text = "Providers channels"
        changeCellSelection(item.isSelected)
        clicksDisposable = itemView.clicks()
                .subscribeBy(onNext = { standardProviderClicks.onNext(item) })
    }

    fun unbind() = clicksDisposable?.dispose()

    private fun changeCellSelection(isSelected: Boolean) {
        itemView.check.setImageResource(if (isSelected) R.drawable.ic_checkbox_checked else R.drawable.ic_checkbox_unchecked)
        itemView.setBackgroundResource(if (isSelected) R.drawable.cell_epg_active else R.drawable.cell_epg_normal)
    }

}