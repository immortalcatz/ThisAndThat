/* This file is a part of the This & That project
 * by Juuxel, licensed under the MIT license.
 * Full code and license: https://github.com/Juuxel/ThisAndThat
 */
package juuxel.thisandthat.lib

import juuxel.thisandthat.item.ModBlockItem
//import juuxel.thisandthat.item.ModMultipartItem
import juuxel.thisandthat.util.ModBlock
import juuxel.thisandthat.util.ModContent
import juuxel.thisandthat.util.ModMultipart
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
//import net.shadowfacts.simplemultipart.SimpleMultipart

abstract class ModRegistry {
    @Suppress("UNCHECKED_CAST")
    protected fun <R> register(registry: Registry<R>, content: ModContent<R>): ModContent<R> {
        Registry.register(registry, Identifier("thisandthat", content.name), content.unwrap())
        return content
    }

    protected fun <T : ModBlock> registerBlock(content: T): T {
        register(Registry.BLOCK, content)

        if (content.registerItem)
            Registry.register(Registry.ITEM, Identifier("thisandthat", content.name), ModBlockItem(content))
        if (content.blockEntityType != null)
            Registry.register(Registry.BLOCK_ENTITY, Identifier("thisandthat", content.name), content.blockEntityType)

        return content
    }

    protected fun <T : ModMultipart> registerMultipart(content: T): T {
//        register(SimpleMultipart.MULTIPART, content)
//
//        if (content.registerItem)
//            Registry.register(Registry.ITEM, Identifier("thisandthat", content.name), ModMultipartItem(content))
//
        return content
    }
}
