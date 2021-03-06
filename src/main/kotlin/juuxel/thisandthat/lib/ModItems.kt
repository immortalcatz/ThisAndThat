/* This file is a part of the This & That project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/ThisAndThat
 */
package juuxel.thisandthat.lib

import juuxel.thisandthat.item.*
import juuxel.thisandthat.util.ModContent
import net.fabricmc.fabric.api.registry.FuelRegistry
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

object ModItems : ModRegistry() {
    lateinit var ryeSeeds: ModContent<Item> private set
    lateinit var rye: ModContent<Item> private set
    lateinit var enderFeather: ModContent<Item> private set
    lateinit var prismarineCoal: ModContent<Item> private set

    fun init() {
        register(Registry.ITEM, StoneRodItem())
        ryeSeeds = register(Registry.ITEM, RyeSeedsItem())
        rye = register(Registry.ITEM, RyeItem())
        register(Registry.ITEM, RyeFlourItem())
        prismarineCoal = register(Registry.ITEM, PrismarineCoalItem())
        register(Registry.ITEM, ModItem("logo", Item.Settings()))
        register(Registry.ITEM, SawItem())

        if (ModConfig.getInstance().items.enderFeathers)
            enderFeather = register(Registry.ITEM, EnderFeatherItem())

        FuelRegistry.INSTANCE.add(prismarineCoal.unwrap(), 2000)
    }
}
