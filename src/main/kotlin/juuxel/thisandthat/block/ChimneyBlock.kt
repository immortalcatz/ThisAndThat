/* This file is a part of the This & That project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/ThisAndThat
 */
package juuxel.thisandthat.block

import juuxel.thisandthat.util.ModBlock
import juuxel.watereddown.api.Dualloggable
import juuxel.watereddown.api.Lavaloggable
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.state.StateFactory
import net.minecraft.state.property.Properties
import net.minecraft.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.ViewableWorld
import net.minecraft.world.World
import java.util.*

class ChimneyBlock : Block(Settings.copy(Blocks.BRICKS)), ModBlock, Dualloggable {
    override val name = "chimney"
    override val itemSettings = Item.Settings().itemGroup(ItemGroup.DECORATIONS)
    override val hasDescription = true

    @Environment(EnvType.CLIENT)
    override fun randomDisplayTick(state: BlockState?, world: World, pos: BlockPos, random: Random) {
        val x = pos.x + 0.5
        val y = pos.y + 0.9
        val z = pos.z + 0.5

        for (i in 1..3) {
            world.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0.0, 0.0, 0.0)
        }
    }

    override fun hasRandomTicks(p0: BlockState?) = true
    override fun getTickRate(p0: ViewableWorld?) = 3
    override fun getBoundingShape(p0: BlockState?, p1: BlockView?, p2: BlockPos?) = shape

    override fun getPlacementState(context: ItemPlacementContext): BlockState? {
        val state = context.world.getFluidState(context.pos)
        return this.defaultState.with(
            Properties.WATERLOGGED,
            state.matches(FluidTags.WATER)
        ).with(
            Lavaloggable.LAVALOGGED,
            state.matches(FluidTags.LAVA)
        )
    }

    override fun appendProperties(p0: StateFactory.Builder<Block, BlockState>) {
        p0.with(Properties.WATERLOGGED).with(Lavaloggable.LAVALOGGED)
    }

    companion object {
        private val shape = Block.createCubeShape(4.0, 0.0, 4.0, 12.0, 12.0, 12.0)
    }
}