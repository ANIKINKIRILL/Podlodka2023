package com.example.demoapppodlodka

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {
    var tasks by remember {
        mutableStateOf(userTasks)
    }

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.White
            ) {
                Text(text = "Compose Demo")
            }
        },
        sheetContent = {
            Box(
                modifier = Modifier.fillMaxWidth().height(height = 250.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Your task is completed ❤️", fontSize = 25.sp)
            }
        },
        sheetBackgroundColor = Color.LightGray,
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetScaffoldState,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                this.itemsIndexed(tasks) { index, task ->
                    val topMargin = if (index == 0) 8.dp else 0.dp
                    val bottomMargin = if (index == tasks.lastIndex) 8.dp else 0.dp
                    TaskCard(
                        modifier = Modifier.padding(
                            top = topMargin,
                            bottom = bottomMargin
                        ),
                        task = task,
                    ) { checked ->
                        val currentTask = tasks[index]
                        val changedTask = currentTask.copy(
                            isCompleted = checked,
                            hoursToComplete = currentTask.hoursToComplete.takeIf {
                                checked.not()
                            }.orEmpty()
                        )
                        tasks = tasks.toMutableList().apply {
                            this[index] = changedTask
                        }

                        // открывать попап
                        coroutineScope.launch {
                            if (checked) {
                                // если таску выполнили - то открываем
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onCompleteSwitcherChange: (Boolean) -> Unit,
) {
    Card(modifier = modifier.fillMaxWidth().height(120.dp), elevation = 8.dp) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = task.hoursToComplete, fontSize = 14.sp, color = task.color)
        }

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = task.title, fontSize = 20.sp)
            }

            Row {
                Switch(
                    checked = task.isCompleted,
                    onCheckedChange = { checked ->
                        onCompleteSwitcherChange(checked)
                    }
                )
            }
        }
    }
}

data class Task(
    val title: String,
    val isCompleted: Boolean,
    val hoursToComplete: String,
    val color: Color,
)

val userTasks = listOf(
    Task(
        title = "Read book",
        isCompleted = false,
        hoursToComplete = "5 hours left",
        color = Color.Red,
    ),
    Task(
        title = "Watch movie",
        isCompleted = true,
        hoursToComplete = "",
        color = Color.Black,
    ),
    Task(
        title = "Meet friends",
        isCompleted = true,
        hoursToComplete = "",
        color = Color.Black,
    ),
    Task(
        title = "Do Kotlin",
        isCompleted = true,
        hoursToComplete = "",
        color = Color.Black,
    ),
    Task(
        title = "Play volleyball",
        isCompleted = false,
        hoursToComplete = "4 hours left",
        color = Color.Blue,
    ),
    Task(
        title = "Play guitar",
        isCompleted = true,
        hoursToComplete = "",
        color = Color.Black,
    ),
    Task(
        title = "Do exercise",
        isCompleted = true,
        hoursToComplete = "",
        color = Color.Black,
    ),
    Task(
        title = "Go to a cinema",
        isCompleted = false,
        hoursToComplete = "3 hours left",
        color = Color.DarkGray,
    ),
    Task(
        title = "Have a launch",
        isCompleted = true,
        hoursToComplete = "",
        color = Color.Black,
    ),
    Task(
        title = "Sleep",
        isCompleted = false,
        hoursToComplete = "8 hours left",
        color = Color.Cyan,
    )
)