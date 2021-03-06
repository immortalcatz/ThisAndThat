/* This file is a part of the This & That project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/ThisAndThat
 */
package juuxel.thisandthat.block

import juuxel.thisandthat.util.BlockVariant
import juuxel.thisandthat.util.ModBlock
import juuxel.watereddown.api.FluidProperty
import juuxel.watereddown.api.Fluidloggable
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.VerticalEntityPosition
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateFactory
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class PostBlock(variant: BlockVariant) : Block(variant.settings), ModBlock, Fluidloggable {
    override val name = "${variant.contentName}_post"
    override val itemSettings = Item.Settings().itemGroup(ItemGroup.DECORATIONS)
    override val hasDescription = true
    override val descriptionKey = "block.thisandthat.post.desc"

    init {
        defaultState = stateFactory.defaultState.with(FluidProperty.FLUID, FluidProperty.EMPTY)
    }

    @Suppress("OverridingDeprecatedMember")
    override fun getOutlineShape(state: BlockState?, view: BlockView?, pos: BlockPos?, vep: VerticalEntityPosition?): VoxelShape =
        PlatformBlock.postShape

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        return Fluidloggable.onGetPlacementState(context, defaultState)
    }

    override fun appendProperties(p0: StateFactory.Builder<Block, BlockState>) {
        Fluidloggable.onAppendProperties(p0)
    }
}
