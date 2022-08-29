package com.craftschemers.hub.managers

import assertk.assertThat
import assertk.assertions.*
import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import com.craftschemers.hub.BukkitTestCase
import com.craftschemers.hub.Hub
import com.craftschemers.hub.HubPlayer
import com.craftschemers.hub.minigame.GameType
import com.craftschemers.hub.minigame.Minigame
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HubPlayerManagerTest : BukkitTestCase() {

    @Test
    fun `verify player is added when addPlayer is called`() {
        val player = server.addPlayer()
        val hubPlayerManager = plugin.playerManger


        player.inventory.setItemInMainHand(ItemStack(Material.ACACIA_BOAT))
        player.gameMode = GameMode.SURVIVAL

        hubPlayerManager.addPlayer(player)

        assertThat(hubPlayerManager.hasPlayer(player.uniqueId)).isTrue()
        assertThat(player.gameMode).isEqualTo(GameMode.ADVENTURE)
        assertThat(player.inventory.contents).containsOnly(null)
    }

    @Test
    fun `verify player actually leaves game`() {
        val player = server.addPlayer()
        val hubPlayerManager = plugin.playerManger
        hubPlayerManager.addPlayer(player)

        val hubPlayer = hubPlayerManager.getPlayer(player.uniqueId)

        assertThat(hubPlayer).isNotNull()

        val hubMinigameManager = plugin.gameManager
        if (hubPlayer != null) {
            hubMinigameManager.addPlayerToMinigame(hubPlayer, GameType.ONE_IN_THE_QUIVER)
            hubMinigameManager.removePlayerFromMinigame(hubPlayer)
            assertThat(hubPlayer.game).isNull()
            assertThat(hubMinigameManager.minigameServers.values).each { servers ->
                servers.each {
                    it.prop(Minigame::players).doesNotContain(hubPlayer)
                }
            }
        }
    }

}
