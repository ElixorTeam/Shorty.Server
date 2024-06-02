package ru.elixor.api.features.user.features.analytic.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.elixor.api.entities.redirect.RedirectRepository
import ru.elixor.api.enums.TimePeriod
import ru.elixor.api.features.user.features.analytic.common.AnalyticService
import ru.elixor.api.features.user.features.analytic.dto.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class AnalyticServiceImpl(
    private val redirectRepo: RedirectRepository,
    private val analyticHelper: AnalyticServiceHelper,
) : AnalyticService {

    override fun get(linkId: UUID, userUid: UUID, type: TimePeriod): AnalyticOutputDto {
        val link = analyticHelper.getLinkByIdAndUser(linkId, userUid)

        val now = LocalDate.now()
        val startDate: LocalDateTime = when (type) {
            TimePeriod.Week -> now.minusWeeks(1).atStartOfDay()
            TimePeriod.Month -> now.minusMonths(1).atStartOfDay()
            TimePeriod.Year -> now.minusYears(1).atStartOfDay()
        }

        val redirects = redirectRepo.findAllByLinkAndCreateDtAfter(link, startDate)

        val redirectsGroupedByOs = redirects.groupBy { it.os }
        val redirectsGroupedByDevice = redirects.groupBy { it.device }
        val redirectsGroupedByDay = redirects.groupBy { redirect ->
            redirect.createDt.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(DateTimeFormatter.ISO_LOCAL_DATE)
        }

        val statisticItem = StatisticItem(
            views = redirects.size,
            uniqueViews = redirects.distinctBy { it.clientKey }.size
        );

        val osSubItems: List<AnalyticSubItem> = redirectsGroupedByOs.map { (os, group) ->
            AnalyticSubItem(label = os, value = group.size)
        }

        val deviceSubItems: List<AnalyticSubItem> = redirectsGroupedByDevice.map { (device, group) ->
            AnalyticSubItem(label = device.toString(), value = group.size)
        }

        val redirectsSubItems: List<AnalyticSubItem> = redirectsGroupedByDay.map { (day, group) ->
            AnalyticSubItem(label = day, value = group.size)
        }

        val deviceItem = DeviceItem(
            os = osSubItems,
            device = deviceSubItems,
        )

        return AnalyticOutputDto(AnalyticItem(statisticItem, deviceItem, redirectsSubItems));
    }

    @Transactional
    override fun delete(linkId: UUID, userUid: UUID) {
        val link = analyticHelper.getLinkByIdAndUser(linkId, userUid)
        redirectRepo.deleteAllByLink(link)
    }

}