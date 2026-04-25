@file:YDRevisionIn(implementedAt = "2026-04-25", revisionAfterInDays = 365)

package de.yanosdev.styleguide.theme.components.atoms.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import de.yanosdev.annotation.YDRevisionIn

/**
 * Geometric monoline icon set with a sharp, technical aesthetic.
 *
 * Icons use a 24×24 dp canvas with 1.5 dp strokes, round caps, and miter joins —
 * producing a precise, circuit-board / terminal visual language.
 * Every icon is lazily initialised and safe to use from any thread.
 *
 * Usage:
 * ```
 * YDIcon(imageVector = YDIcons.Terminal, contentDescription = "Open terminal")
 * ```
 */
object YDIcons {

    // ── Navigation ────────────────────────────────────────────────────────────

    val ArrowLeft: ImageVector by lazy {
        ydIcon("ArrowLeft") {
            ydPath {
                moveTo(15f, 6f)
                lineTo(9f, 12f)
                lineTo(15f, 18f)
            }
        }
    }

    val ArrowRight: ImageVector by lazy {
        ydIcon("ArrowRight") {
            ydPath {
                moveTo(9f, 6f)
                lineTo(15f, 12f)
                lineTo(9f, 18f)
            }
        }
    }

    val ArrowUp: ImageVector by lazy {
        ydIcon("ArrowUp") {
            ydPath {
                moveTo(6f, 15f)
                lineTo(12f, 9f)
                lineTo(18f, 15f)
            }
        }
    }

    val ArrowDown: ImageVector by lazy {
        ydIcon("ArrowDown") {
            ydPath {
                moveTo(6f, 9f)
                lineTo(12f, 15f)
                lineTo(18f, 9f)
            }
        }
    }

    /** House outline with roof chevron, walls, and door cutout. */
    val Home: ImageVector by lazy {
        ydIcon("Home") {
            ydPath {
                // Roof
                moveTo(3f, 12f)
                lineTo(12f, 3f)
                lineTo(21f, 12f)
                // Left wall
                moveTo(5f, 12f)
                verticalLineTo(21f)
                // Right wall
                moveTo(19f, 12f)
                verticalLineTo(21f)
                // Floor
                moveTo(5f, 21f)
                horizontalLineTo(19f)
                // Door
                moveTo(10f, 21f)
                verticalLineTo(15f)
                horizontalLineTo(14f)
                verticalLineTo(21f)
            }
        }
    }

    val Close: ImageVector by lazy {
        ydIcon("Close") {
            ydPath {
                moveTo(5f, 5f)
                lineTo(19f, 19f)
                moveTo(19f, 5f)
                lineTo(5f, 19f)
            }
        }
    }

    /** Three-line hamburger menu. */
    val Menu: ImageVector by lazy {
        ydIcon("Menu") {
            ydPath {
                moveTo(3f, 7f); horizontalLineTo(21f)
                moveTo(3f, 12f); horizontalLineTo(21f)
                moveTo(3f, 17f); horizontalLineTo(21f)
            }
        }
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    val Add: ImageVector by lazy {
        ydIcon("Add") {
            ydPath {
                moveTo(12f, 5f); verticalLineTo(19f)
                moveTo(5f, 12f); horizontalLineTo(19f)
            }
        }
    }

    val Minus: ImageVector by lazy {
        ydIcon("Minus") {
            ydPath {
                moveTo(5f, 12f)
                horizontalLineTo(19f)
            }
        }
    }

    val Check: ImageVector by lazy {
        ydIcon("Check") {
            ydPath {
                moveTo(4f, 12f)
                lineTo(9f, 17f)
                lineTo(20f, 6f)
            }
        }
    }

    /** Magnifying glass: circle with a handle. */
    val Search: ImageVector by lazy {
        ydIcon("Search") {
            ydPath {
                // Circle (center 10.5,10.5 radius 6)
                moveTo(16.5f, 10.5f)
                arcTo(6f, 6f, 0f, false, true, 4.5f, 10.5f)
                arcTo(6f, 6f, 0f, false, true, 16.5f, 10.5f)
                close()
                // Handle
                moveTo(15.5f, 15.5f)
                lineTo(21f, 21f)
            }
        }
    }

    /** Diagonal pencil / edit icon. */
    val Edit: ImageVector by lazy {
        ydIcon("Edit") {
            ydPath {
                // Pencil body parallelogram
                moveTo(3.5f, 17.5f)
                lineTo(17f, 4f)
                lineTo(21f, 8f)
                lineTo(7.5f, 21.5f)
                close()
                // Tip
                moveTo(3.5f, 17.5f)
                lineTo(3f, 21f)
                lineTo(7.5f, 21.5f) // join to body bottom corner
                // Edge highlight
                moveTo(14f, 5.5f)
                lineTo(18.5f, 10f)
            }
        }
    }

    /** Trash bin with lid, body outline, and two vertical lines. */
    val Trash: ImageVector by lazy {
        ydIcon("Trash") {
            ydPath {
                // Lid
                moveTo(3f, 7f); horizontalLineTo(21f)
                // Handle notch above lid
                moveTo(9f, 7f); verticalLineTo(4f); horizontalLineTo(15f); verticalLineTo(7f)
                // Body outline
                moveTo(5f, 7f); verticalLineTo(20f); horizontalLineTo(19f); verticalLineTo(7f)
                // Interior lines
                moveTo(10f, 10f); verticalLineTo(17f)
                moveTo(14f, 10f); verticalLineTo(17f)
            }
        }
    }

    /** Two offset rectangles (back-left, front-right). */
    val Copy: ImageVector by lazy {
        ydIcon("Copy") {
            ydPath {
                // Back rect
                moveTo(4f, 4f); horizontalLineTo(15f); verticalLineTo(15f); horizontalLineTo(4f); close()
                // Front rect
                moveTo(9f, 9f); horizontalLineTo(20f); verticalLineTo(20f); horizontalLineTo(9f); close()
            }
        }
    }

    /** Three diamond nodes connected by lines (share / network). */
    val Share: ImageVector by lazy {
        ydIcon("Share") {
            ydPath {
                // Top-right node
                moveTo(18f, 3f); lineTo(20f, 5f); lineTo(18f, 7f); lineTo(16f, 5f); close()
                // Left node
                moveTo(3f, 11f); lineTo(5f, 13f); lineTo(3f, 15f); lineTo(1f, 13f); close()
                // Bottom-right node
                moveTo(18f, 17f); lineTo(20f, 19f); lineTo(18f, 21f); lineTo(16f, 19f); close()
                // Connecting lines
                moveTo(5f, 12f); lineTo(16f, 5f)
                moveTo(5f, 14f); lineTo(16f, 19f)
            }
        }
    }

    val Download: ImageVector by lazy {
        ydIcon("Download") {
            ydPath {
                moveTo(12f, 4f); verticalLineTo(15f)
                moveTo(8f, 11f); lineTo(12f, 15f); lineTo(16f, 11f)
                moveTo(4f, 20f); horizontalLineTo(20f)
            }
        }
    }

    val Upload: ImageVector by lazy {
        ydIcon("Upload") {
            ydPath {
                moveTo(12f, 16f); verticalLineTo(5f)
                moveTo(8f, 9f); lineTo(12f, 5f); lineTo(16f, 9f)
                moveTo(4f, 20f); horizontalLineTo(20f)
            }
        }
    }

    /** Two opposing semicircular arrows forming a circle. */
    val Refresh: ImageVector by lazy {
        ydIcon("Refresh") {
            ydPath {
                // Top semicircle (3 o'clock → 9 o'clock, going clockwise over the top)
                moveTo(3f, 12f)
                arcTo(9f, 9f, 0f, false, true, 21f, 12f)
                // Arrowhead at right (21,12) pointing downward
                moveTo(19f, 10f); lineTo(21f, 12f); lineTo(23f, 10f)
                // Bottom semicircle (9 o'clock → 3 o'clock, going clockwise under the bottom)
                moveTo(21f, 12f)
                arcTo(9f, 9f, 0f, false, true, 3f, 12f)
                // Arrowhead at left (3,12) pointing upward
                moveTo(1f, 14f); lineTo(3f, 12f); lineTo(5f, 14f)
            }
        }
    }

    /** Four decreasing horizontal lines (funnel filter). */
    val Filter: ImageVector by lazy {
        ydIcon("Filter") {
            ydPath {
                moveTo(3f, 5f); horizontalLineTo(21f)
                moveTo(6f, 10f); horizontalLineTo(18f)
                moveTo(9f, 15f); horizontalLineTo(15f)
                moveTo(11f, 20f); horizontalLineTo(13f)
            }
        }
    }

    /** Box with an up-right arrow escaping its top-right corner. */
    val ExternalLink: ImageVector by lazy {
        ydIcon("ExternalLink") {
            ydPath {
                // Arrow stem
                moveTo(20f, 4f)
                lineTo(11f, 13f)
                // Arrow head
                moveTo(14f, 4f); horizontalLineTo(20f); verticalLineTo(10f)
                // Box (three sides, open at top-right)
                moveTo(20f, 14f); verticalLineTo(20f); horizontalLineTo(4f); verticalLineTo(4f); horizontalLineTo(10f)
            }
        }
    }

    val Eye: ImageVector by lazy {
        ydIcon("Eye") {
            ydPath {
                // Eye outline
                moveTo(2f, 12f)
                curveTo(2f, 12f, 6f, 5f, 12f, 5f)
                curveTo(18f, 5f, 22f, 12f, 22f, 12f)
                curveTo(22f, 12f, 18f, 19f, 12f, 19f)
                curveTo(6f, 19f, 2f, 12f, 2f, 12f)
                close()
                // Pupil
                moveTo(14f, 12f)
                arcTo(2f, 2f, 0f, false, true, 10f, 12f)
                arcTo(2f, 2f, 0f, false, true, 14f, 12f)
                close()
            }
        }
    }

    // ── Status ────────────────────────────────────────────────────────────────

    /** Letter `i` framed between two bracket lines. */
    val Info: ImageVector by lazy {
        ydIcon("Info") {
            ydPath {
                // Left bracket
                moveTo(7f, 3f); horizontalLineTo(5f); verticalLineTo(21f); horizontalLineTo(7f)
                // Right bracket
                moveTo(17f, 3f); horizontalLineTo(19f); verticalLineTo(21f); horizontalLineTo(17f)
                // Dot
                moveTo(11f, 7f); horizontalLineTo(13f); verticalLineTo(9f); horizontalLineTo(11f); close()
                // Bar
                moveTo(11f, 11f); horizontalLineTo(13f); verticalLineTo(17f); horizontalLineTo(11f); close()
            }
        }
    }

    /** Equilateral triangle with an exclamation mark inside. */
    val Warning: ImageVector by lazy {
        ydIcon("Warning") {
            ydPath {
                moveTo(12f, 2f); lineTo(22f, 20f); horizontalLineTo(2f); close()
                // Exclamation bar
                moveTo(12f, 8.5f); verticalLineTo(13.5f)
                // Exclamation dot
                moveTo(11.5f, 16f); horizontalLineTo(12.5f); verticalLineTo(17f); horizontalLineTo(11.5f); close()
            }
        }
    }

    /** Bell shape with stem and clapper. */
    val Bell: ImageVector by lazy {
        ydIcon("Bell") {
            ydPath {
                // Stem at top
                moveTo(12f, 2f); verticalLineTo(5f)
                // Bell body
                moveTo(6f, 18f)
                verticalLineTo(11f)
                curveTo(6f, 7.13f, 8.69f, 5f, 12f, 5f)
                curveTo(15.31f, 5f, 18f, 7.13f, 18f, 11f)
                verticalLineTo(18f)
                // Bottom rim
                moveTo(5f, 18f); horizontalLineTo(19f)
                // Clapper arc
                moveTo(10.5f, 18f)
                curveTo(10.5f, 20.49f, 13.5f, 20.49f, 13.5f, 18f)
            }
        }
    }

    // ── Content / Settings ────────────────────────────────────────────────────

    /** Circle head with V-shaped angular shoulders. */
    val User: ImageVector by lazy {
        ydIcon("User") {
            ydPath {
                // Head circle (center 12,8 radius 4)
                moveTo(16f, 8f)
                arcTo(4f, 4f, 0f, false, true, 8f, 8f)
                arcTo(4f, 4f, 0f, false, true, 16f, 8f)
                close()
                // Shoulders
                moveTo(4f, 21f)
                lineTo(7f, 15f)
                lineTo(12f, 13f)
                lineTo(17f, 15f)
                lineTo(20f, 21f)
            }
        }
    }

    /** Five-pointed star. */
    val Star: ImageVector by lazy {
        ydIcon("Star") {
            ydPath {
                moveTo(12f, 2.5f)
                lineTo(14.5f, 9.3f)
                lineTo(21.5f, 9.3f)
                lineTo(15.9f, 13.5f)
                lineTo(18f, 20.5f)
                lineTo(12f, 16.3f)
                lineTo(6f, 20.5f)
                lineTo(8.1f, 13.5f)
                lineTo(2.5f, 9.3f)
                lineTo(9.5f, 9.3f)
                close()
            }
        }
    }

    /** Geometric angular heart. */
    val Heart: ImageVector by lazy {
        ydIcon("Heart") {
            ydPath {
                moveTo(12f, 20f)
                lineTo(4f, 12f)
                lineTo(4f, 7f)
                lineTo(7f, 4f)
                lineTo(9f, 4f)
                lineTo(12f, 7f)
                lineTo(15f, 4f)
                lineTo(17f, 4f)
                lineTo(20f, 7f)
                lineTo(20f, 12f)
                close()
            }
        }
    }

    /** Rectangular padlock with angular shackle. */
    val Lock: ImageVector by lazy {
        ydIcon("Lock") {
            ydPath {
                // Lock body
                moveTo(3f, 12f); horizontalLineTo(21f); verticalLineTo(21f); horizontalLineTo(3f); close()
                // Shackle (angular arch)
                moveTo(8f, 12f); verticalLineTo(8f); horizontalLineTo(16f); verticalLineTo(12f)
                // Keyhole slot
                moveTo(11f, 15f); horizontalLineTo(13f); verticalLineTo(19f); horizontalLineTo(11f); close()
            }
        }
    }

    /** Calendar rectangle with day-binding tabs and grid lines. */
    val Calendar: ImageVector by lazy {
        ydIcon("Calendar") {
            ydPath {
                // Outer box
                moveTo(3f, 6f); horizontalLineTo(21f); verticalLineTo(21f); horizontalLineTo(3f); close()
                // Header separator
                moveTo(3f, 10f); horizontalLineTo(21f)
                // Binding tabs
                moveTo(8f, 3f); verticalLineTo(7f)
                moveTo(16f, 3f); verticalLineTo(7f)
                // Vertical grid
                moveTo(9f, 10f); verticalLineTo(21f)
                moveTo(15f, 10f); verticalLineTo(21f)
                // Horizontal grid
                moveTo(3f, 15f); horizontalLineTo(21f)
            }
        }
    }

    /** Circle clock face with hour and minute hands. */
    val Clock: ImageVector by lazy {
        ydIcon("Clock") {
            ydPath {
                // Face
                moveTo(21f, 12f)
                arcTo(9f, 9f, 0f, false, true, 3f, 12f)
                arcTo(9f, 9f, 0f, false, true, 21f, 12f)
                close()
                // Minute hand (12 o'clock)
                moveTo(12f, 12f); verticalLineTo(5f)
                // Hour hand (~10 o'clock)
                moveTo(12f, 12f); lineTo(7.5f, 8.5f)
                // Center dot
                moveTo(12.5f, 12f)
                arcTo(0.5f, 0.5f, 0f, false, true, 11.5f, 12f)
                arcTo(0.5f, 0.5f, 0f, false, true, 12.5f, 12f)
                close()
            }
        }
    }

    /** Three horizontal slider lines with square track handles. */
    val Settings: ImageVector by lazy {
        ydIcon("Settings") {
            ydPath {
                // Line 1
                moveTo(3f, 7f); horizontalLineTo(21f)
                // Handle 1 at x=15 (square 14–16, y=6–8)
                moveTo(14f, 5f); horizontalLineTo(17f); verticalLineTo(9f); horizontalLineTo(14f); close()

                // Line 2
                moveTo(3f, 12f); horizontalLineTo(21f)
                // Handle 2 at x=8 (square 7–9, y=11–13)
                moveTo(7f, 10f); horizontalLineTo(10f); verticalLineTo(14f); horizontalLineTo(7f); close()

                // Line 3
                moveTo(3f, 17f); horizontalLineTo(21f)
                // Handle 3 at x=17 (square 16–18, y=16–18)
                moveTo(16f, 15f); horizontalLineTo(19f); verticalLineTo(19f); horizontalLineTo(16f); close()
            }
        }
    }

    // ── Techie ────────────────────────────────────────────────────────────────

    /** `>_` terminal / command-line prompt. */
    val Terminal: ImageVector by lazy {
        ydIcon("Terminal") {
            ydPath {
                // Outer bracket
                moveTo(2f, 4f); horizontalLineTo(22f); verticalLineTo(20f); horizontalLineTo(2f); close()
                // > chevron prompt
                moveTo(6f, 9f); lineTo(11f, 12f); lineTo(6f, 15f)
                // _ cursor
                moveTo(13f, 15f); horizontalLineTo(18f)
            }
        }
    }

    /** `</>` code / HTML-style tag icon. */
    val Code: ImageVector by lazy {
        ydIcon("Code") {
            ydPath {
                // Left bracket <
                moveTo(8.5f, 6f); lineTo(3f, 12f); lineTo(8.5f, 18f)
                // Slash /
                moveTo(14f, 5f); lineTo(10f, 19f)
                // Right bracket >
                moveTo(15.5f, 6f); lineTo(21f, 12f); lineTo(15.5f, 18f)
            }
        }
    }

    /** Three stacked offset rectangles representing layers / library. */
    val Layers: ImageVector by lazy {
        ydIcon("Layers") {
            ydPath {
                // Bottom layer
                moveTo(2f, 16f); horizontalLineTo(16f); verticalLineTo(19f); horizontalLineTo(2f); close()
                // Middle layer
                moveTo(5f, 11f); horizontalLineTo(19f); verticalLineTo(14f); horizontalLineTo(5f); close()
                // Top layer
                moveTo(8f, 6f); horizontalLineTo(22f); verticalLineTo(9f); horizontalLineTo(8f); close()
            }
        }
    }

    /** Lightning bolt — performance / speed. */
    val Bolt: ImageVector by lazy {
        ydIcon("Bolt") {
            ydPath {
                moveTo(13f, 2f)
                lineTo(5f, 14f)
                horizontalLineTo(11f)
                lineTo(11f, 22f)
                lineTo(19f, 10f)
                horizontalLineTo(13f)
                close()
            }
        }
    }

    /** Cylindrical database stack. */
    val Database: ImageVector by lazy {
        ydIcon("Database") {
            ydPath {
                // Top ellipse
                moveTo(20f, 7f)
                arcTo(8f, 3f, 0f, false, true, 4f, 7f)
                arcTo(8f, 3f, 0f, false, true, 20f, 7f)
                close()
                // Left wall
                moveTo(4f, 7f); verticalLineTo(17f)
                // Right wall
                moveTo(20f, 7f); verticalLineTo(17f)
                // Bottom ellipse
                moveTo(20f, 17f)
                arcTo(8f, 3f, 0f, false, true, 4f, 17f)
                arcTo(8f, 3f, 0f, false, true, 20f, 17f)
                close()
                // Middle ring
                moveTo(4f, 12f)
                arcTo(8f, 3f, 0f, false, false, 20f, 12f)
            }
        }
    }
}

// ── Private helpers ───────────────────────────────────────────────────────────

private fun ydIcon(name: String, block: ImageVector.Builder.() -> Unit): ImageVector =
    ImageVector.Builder(
        name = "YDIcons.$name",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
    ).apply(block).build()

private fun ImageVector.Builder.ydPath(pathBuilder: PathBuilder.() -> Unit) = path(
    stroke = SolidColor(Color.Black),
    strokeLineWidth = 1.5f,
    strokeLineCap = StrokeCap.Round,
    strokeLineJoin = StrokeJoin.Miter,
    pathBuilder = pathBuilder,
)
