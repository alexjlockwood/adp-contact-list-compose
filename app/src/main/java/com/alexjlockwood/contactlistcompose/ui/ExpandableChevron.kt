package com.alexjlockwood.contactlistcompose.ui

import androidx.compose.animation.animatedFloat
import androidx.compose.foundation.Icon
import androidx.compose.foundation.contentColor
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp

/**
 * An expandable chevron icon that animates between an expanded and collapsed state.
 */
@Composable
fun ExpandableChevron(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    color: Color = contentColor(),
) {
    // Create an AnimatedFloat with an initial value of 0f.
    val animatedProgress = animatedFloat(0f)

    onCommit(isExpanded) {
        // Animate the state change each time isExpanded has changed.
        animatedProgress.animateTo(targetValue = if (isExpanded) 1f else 0f)
    }

    // The SVG path data strings for the collapsed and expanded chevron icon respectively.
    val collapsedPathData = "M 12 13.17 L 7.41 8.59 L 6 10 L 12 16 L 18 10 L 16.59 8.59 L 12 13.17"
    val expandedPathData = "M 12 8 L 6 14 L 7.41 15.41 L 12 10.83 L 16.59 15.41 L 18 14 L 12 8"

    // Convert each path data string into a List<PathNode>. We remember this state to avoid
    // the unnecessary cost of having to parse SVG path data strings on each animation frame.
    val collapsedPathNodes = remember { addPathNodes(collapsedPathData) }
    val expandedPathNodes = remember { addPathNodes(expandedPathData) }

    // Create the morphed list of path nodes based on the current animated
    // progress t in the interval [0,1].
    val t = animatedProgress.value
    val morphedPathNodes = lerp(collapsedPathNodes, expandedPathNodes, t)

    // Draw the chevron icon using the morphed path nodes.
    Icon(
        painter = VectorPainter(
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ) { _, _ ->
            Path(
                pathData = morphedPathNodes,
                fill = SolidColor(color),
            )
        },
        modifier = modifier,
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
