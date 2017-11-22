package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item

val PINNED_PROVIDER_ITEM_TYPE = PinnedProviderItem::class.java.hashCode()

data class PinnedProviderItem(override var isSelected: Boolean = false)
    : ProviderItem(isSelected) {

    override val type = PINNED_PROVIDER_ITEM_TYPE
}