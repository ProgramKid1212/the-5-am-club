package com.etienne.the5amclub.screens


//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.RowScopeInstance.align


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.etienne.the5amclub.R
import com.etienne.the5amclub.ui.theme.AppTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

data class Event(
    val name: String,
    val color: Color,
    val start: LocalDateTime,
    val end: LocalDateTime,
    val description: String? = null,
)
@Composable
fun HomeScreen(
    ) {
    val brush2 = Brush.linearGradient(listOf(Color.DarkGray, Color.Black))


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush2),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {

            Text(
                text = "Quote",
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.White,

                )

            Row()
            {


            }
            Row()
            {

            }
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "null",


            // crop the image if it's not a square
            modifier = Modifier

                .size(90.dp) // clip to the circle shape
                .border(10.dp, Color.Gray)
                .offset(x = 0.dp, y = 0.dp)// add a border (optional)
                .padding(bottom = 0.dp)
                .align(Alignment.TopEnd)
        )



        val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")

        @Composable
        fun BasicEvent(
            event: Event,
            modifier: Modifier = Modifier,
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(end = 2.dp, bottom = 2.dp)
                    .background(event.color, shape = RoundedCornerShape(4.dp))
                    .padding(4.dp)
            ) {
                Text(
                    text = "${event.start.format(EventTimeFormatter)} - ${event.end.format(EventTimeFormatter)}",
                    style = MaterialTheme.typography.caption,
                )

                Text(
                    text = event.name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold,
                )

                if (event.description != null) {
                    Text(
                        text = event.description,
                        style = MaterialTheme.typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }

        val sampleEvents = listOf(
            Event(
                name = "Google I/O Keynote",
                color = Color(0xFFAFBBF2),
                start = LocalDateTime.parse("2021-05-18T09:00:00"),
                end = LocalDateTime.parse("2021-05-18T11:00:00"),
                description = "Tune in to find out about how we're furthering our mission to organize the world’s information and make it universally accessible and useful.",
            ),
            Event(
                name = "Developer Keynote",
                color = Color(0xFFAFBBF2),
                start = LocalDateTime.parse("2021-05-18T11:15:00"),
                end = LocalDateTime.parse("2021-05-18T12:15:00"),
                description = "Learn about the latest updates to our developer products and platforms from Google Developers.",
            ),
            Event(
                name = "What's new in Android",
                color = Color(0xFF1B998B),
                start = LocalDateTime.parse("2021-05-18T12:30:00"),
                end = LocalDateTime.parse("2021-05-18T15:00:00"),
                description = "In this Keynote, Chet Haase, Dan Sandler, and Romain Guy discuss the latest Android features and enhancements for developers.",
            ),
            Event(
                name = "What's new in Machine Learning",
                color = Color(0xFFF4BFDB),
                start = LocalDateTime.parse("2021-05-19T09:30:00"),
                end = LocalDateTime.parse("2021-05-19T11:00:00"),
                description = "Learn about the latest and greatest in ML from Google. We’ll cover what’s available to developers when it comes to creating, understanding, and deploying models for a variety of different applications.",
            ),
            Event(
                name = "What's new in Material Design",
                color = Color(0xFF6DD3CE),
                start = LocalDateTime.parse("2021-05-19T11:00:00"),
                end = LocalDateTime.parse("2021-05-19T12:15:00"),
                description = "Learn about the latest design improvements to help you build personal dynamic experiences with Material Design.",
            ),
            Event(
                name = "Why Compose Basics",
                color = Color(0xFF1B998B),
                start = LocalDateTime.parse("2021-05-20T12:00:00"),
                end = LocalDateTime.parse("2021-05-20T13:00:00"),
                description = "This Workshop will take you through the basics of building your first app with Jetpack Compose, Android's new modern UI toolkit that simplifies and accelerates UI development on Android.",
            ),
            Event(
                name = "Not Compose Basics",
                color = Color(0xFF1B998B),
                start = LocalDateTime.parse("2021-05-21T12:00:00"),
                end = LocalDateTime.parse("2021-05-21T13:00:00"),
                description = "This Workshop will take you through the basics of building your first app with Jetpack Compose, Android's new modern UI toolkit that simplifies and accelerates UI development on Android.",
            ),
        )

        class EventsProvider : PreviewParameterProvider<Event> {
            override val values = sampleEvents.asSequence()
        }

        @Composable
        fun EventPreview(
            @PreviewParameter(EventsProvider::class) event: Event,
        ) {
            AppTheme {
                BasicEvent(event, modifier = Modifier.sizeIn(maxHeight = 64.dp))
            }
        }

        class EventDataModifier(
            val event: Event,
        ) : ParentDataModifier {
            override fun Density.modifyParentData(parentData: Any?) = event
        }

        fun Modifier.eventData(event: Event) = this.then(EventDataModifier(event))

        val DayFormatter = DateTimeFormatter.ofPattern("EE, MMM d")

        @Composable
        fun BasicDayHeader(
            day: LocalDate,
            modifier: Modifier = Modifier,
        ) {
            Text(
                text = day.format(DayFormatter),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }

        @Composable
        fun BasicDayHeaderPreview() {
            AppTheme {
                BasicDayHeader(day = LocalDate.now())
            }
        }

        @Composable
        fun ScheduleHeader(
            minDate: LocalDate,
            maxDate: LocalDate,
            dayWidth: Dp,
            modifier: Modifier = Modifier,
            dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
        ) {
            Row(modifier = modifier) {
                val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
                repeat(numDays) { i ->
                    Box(modifier = Modifier.width(dayWidth)) {
                        dayHeader(minDate.plusDays(i.toLong()))
                    }
                }
            }
        }

        @Composable
        fun ScheduleHeaderPreview() {
            AppTheme {
                ScheduleHeader(
                    minDate = LocalDate.now(),
                    maxDate = LocalDate.now().plusDays(5),
                    dayWidth = 256.dp,
                )
            }
        }

        val HourFormatter = DateTimeFormatter.ofPattern("h a")

        @Composable
        fun BasicSidebarLabel(
            time: LocalTime,
            modifier: Modifier = Modifier,
        ) {
            Text(
                text = time.format(HourFormatter),
                modifier = modifier
                    .fillMaxHeight()
                    .padding(4.dp)
            )
        }

        @Composable
        fun BasicSidebarLabelPreview() {
            AppTheme {
                BasicSidebarLabel(time = LocalTime.NOON, Modifier.sizeIn(maxHeight = 64.dp))
            }
        }

        @Composable
        fun ScheduleSidebar(
            hourHeight: Dp,
            modifier: Modifier = Modifier,
            label: @Composable (time: LocalTime) -> Unit = { BasicSidebarLabel(time = it) },
        ) {
            Column(modifier = modifier) {
                val startTime = LocalTime.MIN
                repeat(24) { i ->
                    Box(modifier = Modifier.height(hourHeight)) {
                        label(startTime.plusHours(i.toLong()))
                    }
                }
            }
        }
        @Composable
        fun ScheduleSidebarPreview() {
            AppTheme {
                ScheduleSidebar(hourHeight = 64.dp)
            }
        }

        @Composable
        fun Schedule(
            events: List<Event> = sampleEvents,
            modifier: Modifier = Modifier,
            eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
            dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
            minDate: LocalDate = events.minByOrNull(Event::start)!!.start.toLocalDate(),
            maxDate: LocalDate = events.maxByOrNull(Event::end)!!.end.toLocalDate(),
        ) {
            val dayWidth = 256.dp
            val hourHeight = 64.dp
            val verticalScrollState = rememberScrollState()
            val horizontalScrollState = rememberScrollState()
            var sidebarWidth by remember { mutableStateOf(0) }
            Column(modifier = modifier) {
                ScheduleHeader(
                    minDate = minDate,
                    maxDate = maxDate,
                    dayWidth = dayWidth,
                    dayHeader = dayHeader,
                    modifier = Modifier
                        .padding(start = with(LocalDensity.current) { sidebarWidth.toDp() })
                        .horizontalScroll(horizontalScrollState)
                )
                Row(modifier = Modifier.weight(1f)) {
                    ScheduleSidebar(
                        hourHeight = hourHeight,
                        modifier = Modifier
                            .verticalScroll(verticalScrollState)
                            .onGloballyPositioned { sidebarWidth = it.size.width }
                    )
                    BasicSchedule(
                        events = events,
                        eventContent = eventContent,
                        minDate = minDate,
                        maxDate = maxDate,
                        dayWidth = dayWidth,
                        hourHeight = hourHeight,
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(verticalScrollState)
                            .horizontalScroll(horizontalScrollState)
                    )
                }
            }
        }

        @Composable
        fun BasicSchedule(
            events: List<Event>,
            modifier: Modifier = Modifier,
            eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
            minDate: LocalDate = events.minByOrNull(Event::start)!!.start.toLocalDate(),
            maxDate: LocalDate = events.maxByOrNull(Event::end)!!.end.toLocalDate(),
            dayWidth: Dp,
            hourHeight: Dp,
        ) {
            val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 4
            val dividerColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray
            Layout(
                content = {
                    events.sortedBy(Event::start).forEach { event ->
                        Box(modifier = Modifier.eventData(event)) {
                            eventContent(event)
                        }
                    }
                },
                modifier = modifier
                    .drawBehind {
                        repeat(23) {
                            drawLine(
                                dividerColor,
                                start = Offset(0f, (it + 1) * hourHeight.toPx()),
                                end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                                strokeWidth = 1.dp.toPx()
                            )
                        }
                        repeat(numDays - 1) {
                            drawLine(
                                dividerColor,
                                start = Offset((it + 1) * dayWidth.toPx(), 0f),
                                end = Offset((it + 1) * dayWidth.toPx(), size.height),
                                strokeWidth = 1.dp.toPx()
                            )
                        }
                    }
            ) { measureables, constraints ->
                val height = hourHeight.roundToPx() * 24
                val width = dayWidth.roundToPx() * numDays
                val placeablesWithEvents = measureables.map { measurable ->
                    val event = measurable.parentData as Event
                    val eventDurationMinutes = ChronoUnit.MINUTES.between(event.start, event.end)
                    val eventHeight = ((eventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()
                    val placeable = measurable.measure(constraints.copy(minWidth = dayWidth.roundToPx(), maxWidth = dayWidth.roundToPx(), minHeight = eventHeight, maxHeight = eventHeight))
                    Pair(placeable, event)
                }
                layout(width, height) {
                    placeablesWithEvents.forEach { (placeable, event) ->
                        val eventOffsetMinutes =
                            ChronoUnit.MINUTES.between(LocalTime.MIN, event.start.toLocalTime())
                        val eventY = ((eventOffsetMinutes / 60f) * hourHeight.toPx()).roundToInt()
                        val eventOffsetDays =
                            ChronoUnit.DAYS.between(minDate, event.start.toLocalDate()).toInt()
                        val eventX = eventOffsetDays * dayWidth.roundToPx()
                        placeable.place(eventX, eventY)
                    }
                }
            }
        }


    }
    Schedule(events = sampleEvents)
}

@Composable
@Preview
fun HomeScreenPreview() {


    HomeScreen()
}