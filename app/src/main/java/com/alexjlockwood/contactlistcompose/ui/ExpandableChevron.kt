package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.animation.animatedFloat
import androidx.compose.foundation.Image
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp

@Composable
fun ExpandableChevron(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    color: Color = contentColor(),
) {
    val animatedProgress = animatedFloat(0f)
    onCommit(isExpanded) {
        animatedProgress.animateTo(targetValue = if (isExpanded) 1f else 0f)
    }

    val collapsedPathData = "M 12 13.17 L 7.41 8.59 L 6 10 L 12 16 L 18 10 L 16.59 8.59 L 12 13.17"
    val expandedPathData = "M 12 8 L 6 14 L 7.41 15.41 L 12 10.83 L 16.59 15.41 L 18 14 L 12 8"
    val collapsedPathNodes = remember { addPathNodes(collapsedPathData) }
    val expandedPathNodes = remember { addPathNodes(expandedPathData) }

    val t = animatedProgress.value
    val chevronPathNodes = lerp(collapsedPathNodes, expandedPathNodes, t)

    Image(
        painter = VectorPainter(
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ) { _, _ ->
            Path(
                pathData = chevronPathNodes,
                fill = SolidColor(color),
            )
        },
        modifier = modifier.preferredSize(48.dp),
        contentScale = ContentScale.Inside,
    )
}

/**
 * Linearly interpolates two lists of path nodes to simulate path morphing.
 */
private fun lerp(
    fromPathNodes: List<PathNode>,
    toPathNodes: List<PathNode>,
    t: Float,
): List<PathNode> {
    return fromPathNodes.mapIndexed { i, from ->
        val to = toPathNodes[i]
        if (from is PathNode.MoveTo && to is PathNode.MoveTo) {
            PathNode.MoveTo(
                lerp(from.x, to.x, t),
                lerp(from.y, to.y, t),
            )
        } else if (from is PathNode.LineTo && to is PathNode.LineTo) {
            PathNode.LineTo(
                lerp(from.x, to.x, t),
                lerp(from.y, to.y, t),
            )
        } else {
            // We only support MoveTo and LineTo commands in this demo for brevity.
            throw IllegalStateException("Unsupported SVG PathNode command")
        }
    }
}
