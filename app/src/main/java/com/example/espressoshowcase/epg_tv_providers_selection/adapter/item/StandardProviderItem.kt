package pl.wp.videostar.viper.epg_tv_providers_selection.adapter.item

val STANDARD_PROVIDER_ITEM_TYPE = StandardProviderItem::class.java.hashCode()

data class StandardProviderItem(override var isSelected: Boolean = false)
    : ProviderItem(isSelected) {

    override val type = STANDARD_PROVIDER_ITEM_TYPE
}