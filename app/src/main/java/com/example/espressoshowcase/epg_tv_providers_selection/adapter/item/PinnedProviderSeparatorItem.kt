package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item

val PINNED_PROVIDER_SEPARATOR_ITEM_TYPE = PinnedProviderSeparatorItem::class.java.hashCode()

class PinnedProviderSeparatorItem : EpgTvProviderListItem {

    override val type = PINNED_PROVIDER_SEPARATOR_ITEM_TYPE

    override fun equals(other: Any?) = other is PinnedProviderSeparatorItem

    override fun hashCode() = PINNED_PROVIDER_SEPARATOR_ITEM_TYPE
}