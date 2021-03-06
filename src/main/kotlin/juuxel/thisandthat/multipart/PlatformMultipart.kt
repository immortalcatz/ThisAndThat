/* This file is a part of the This & That project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/ThisAndThat
 */
package juuxel.thisandthat.multipart

/*import juuxel.thisandthat.util.*
import net.minecraft.block.Block
import net.minecraft.block.enums.BlockHalf
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.state.StateFactory
import net.minecraft.state.property.EnumProperty
import net.minecraft.util.StringRepresentable
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.ViewableWorld
import net.shadowfacts.simplemultipart.container.MultipartContainer
import net.shadowfacts.simplemultipart.multipart.Multipart
import net.shadowfacts.simplemultipart.multipart.MultipartState
import net.shadowfacts.simplemultipart.multipart.MultipartView
import net.shadowfacts.simplemultipart.util.MultipartHelper
import net.shadowfacts.simplemultipart.util.MultipartPlacementContext
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.math.roundToInt

class PlatformMultipart(variant: BlockVariant) : Multipart(), ModMultipart {
    override val name = "${variant.contentName}_platform_multipart"
    override val hasDescription = true
    override val descriptionKey = "multipart.thisandthat.platform.desc"
    override val itemSettings = Item.Settings().itemGroup(ItemGroup.DECORATIONS)

    init {
        defaultState = defaultState.with(location, Location.XN_ZN).with(half, BlockHalf.BOTTOM)
    }

    override fun getPlacementState(context: MultipartPlacementContext): MultipartState {
        val x: Int
        val z: Int
        val hitSide = context.facing
        val hitX = context.hitX
        val hitZ = context.hitZ

        when {
            hitSide.axis.isHorizontal -> {
                val xOffset = when (hitSide) {
                    Direction.WEST -> -0.25f
                    Direction.EAST -> 0.25f
                    else -> 0f
                }

                val zOffset = when (hitSide) {
                    Direction.NORTH -> -0.25f
                    Direction.SOUTH -> 0.25f
                    else -> 0f
                }

                x = (hitX + xOffset).roundToInt() % 2
                z = (hitZ + zOffset).roundToInt() % 2
            }

            else -> {
                x = hitX.roundToInt() % 2
                z = hitZ.roundToInt() % 2
            }
        }

        val l = (Location.values().firstOrNull { it.x == x && it.z == z } ?: Location.XN_ZN).let {
            if (!hitSide.axis.isHorizontal) return@let it

            if (context.isOffset) it.oppositeOn(hitSide.axis)
            else it
        }

        val h = MultipartUtils.getHalf(context).let {
            if (!context.isOffset && context.hitY >= 1f)
                if (it == BlockHalf.TOP) BlockHalf.BOTTOM else BlockHalf.TOP
            else it
        }.let {
            if (context.facing == Direction.UP && it == BlockHalf.TOP)
                flipIfPlacingOnPost(context, l, it)
            else it
        }

        return defaultState.with(half, h).with(location, l)
    }

    private fun flipIfPlacingOnPost(context: MultipartPlacementContext, l: Location, h: BlockHalf): BlockHalf {
        val rayTrace = MultipartHelper.rayTrace(context.container, context.world, context.pos, context.player)

        return if (rayTrace?.view?.multipart is PostMultipart && rayTrace.view.state[PostMultipart.half] != h && context.container.parts.none { p ->
                p.multipart is PlatformMultipart && p.state[location] == l && p.state[half] == h
            }
        ) opposite(h)
        else h
    }

    override fun getBoundingShape(state: MultipartState, view: MultipartView?) =
        shapes.getOrPut(state) {
            val x = state[location].x * 8
            val y = if (state[half] == BlockHalf.TOP) 8 else 0
            val z = state[location].z * 8

            Block.createCubeShape(0.0 + x, 6.0 + y, 0.0 + z, 8.0 + x, 8.0 + y, 8.0 + z)
        }

    override fun appendProperties(builder: StateFactory.Builder<Multipart, MultipartState>) {
        builder.with(location, half)
    }

    override fun canIntersectWith(self: MultipartState, other: MultipartState) =
        other.multipart is PostMultipart

    override fun canSupportTorches(state: MultipartState, world: ViewableWorld, pos: BlockPos) =
        state[half] == BlockHalf.TOP && Location.values().map { l ->
            (world.getBlockEntity(pos) as? MultipartContainer ?: return false).parts.any {
                it.multipart is PlatformMultipart &&
                        it.state[half] == BlockHalf.TOP &&
                        it.state[location] == l
            }
        }.all { it }

    private fun opposite(half: BlockHalf) = when (half) {
        BlockHalf.TOP -> BlockHalf.BOTTOM
        BlockHalf.BOTTOM -> BlockHalf.TOP
    }

    companion object {
        val location = EnumProperty.create("location", Location::class.java)
        val half = EnumProperty.create("half", BlockHalf::class.java)
        private val shapes = HashMap<MultipartState, VoxelShape>()
    }

    enum class Location(val x: Int, val z: Int) : StringRepresentable {
        XP_ZP(1, 1),
        XP_ZN(1, 0),
        XN_ZP(0, 1),
        XN_ZN(0, 0);

        override fun asString() = name.toLowerCase(Locale.ROOT)

        fun oppositeOn(axis: Direction.Axis): Location = when (axis) {
            Direction.Axis.X -> {
                when (this) {
                    XP_ZP -> XN_ZP
                    XP_ZN -> XN_ZN
                    XN_ZP -> XP_ZP
                    XN_ZN -> XP_ZN
                }
            }
            Direction.Axis.Z -> {
                when (this) {
                    XP_ZP -> XP_ZN
                    XP_ZN -> XP_ZP
                    XN_ZP -> XN_ZN
                    XN_ZN -> XN_ZP
                }
            }
            Direction.Axis.Y -> throw IllegalArgumentException("no y axis pls")
        }
    }
}*/
