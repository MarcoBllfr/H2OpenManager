package dev.marcobf.h2openmanager.presentation.maintenance

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.marcobf.h2openmanager.domain.model.MaintenanceTask
import dev.marcobf.h2openmanager.domain.utils.isOverdue
import dev.marcobf.h2openmanager.presentation.utils.formatEpochDays

@Composable
fun TaskRow(
    task: MaintenanceTask,
    onToggle: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val isDone = task.isCompleted
    val overdue = !isDone && isOverdue(task.dueDate)

    val completedColor = MaterialTheme.colorScheme.primary
    val overdueColor = MaterialTheme.colorScheme.error

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onToggle() },
                onLongClick = { onEdit() }
            ),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isDone,
                onCheckedChange = { onToggle() },
                modifier = Modifier.padding(end = 8.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.type.label,
                    style = MaterialTheme.typography.titleSmall,
                    color = when {
                        isDone -> completedColor
                        else -> MaterialTheme.colorScheme.onSurface
                    },
                    textDecoration = if (overdue) {
                        TextDecoration.LineThrough
                    } else {
                        null
                    }
                )
                if (task.description.isNotBlank()) {
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(Modifier.height(4.dp))
                val intervalText = task.intervalDays?.let { " · 🔁 ogni $it giorni" } ?: ""
                Text(
                    text = "📅 ${formatEpochDays(task.dueDate)}$intervalText",
                    style = MaterialTheme.typography.labelMedium,
                    color = when {
                        overdue -> overdueColor
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
            TextButton(
                onClick = onDelete,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("✕")
            }
        }
    }
}