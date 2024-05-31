package ru.elixor.api.features.user.features.analytic.dto

class AnalyticSubItem(
    val label: String,
    val value: Int,
)

class StatisticItem(
    val views: Int,
    val uniqueViews: Int
)

class DeviceItem(
    val os: List<AnalyticSubItem>,
    val device: List<AnalyticSubItem>,
)

class AnalyticItem(
    val statistics: StatisticItem,
    val devicesData: DeviceItem,
    val viewsData: List<AnalyticSubItem>
)

data class AnalyticOutputDto(
    val data: AnalyticItem
)