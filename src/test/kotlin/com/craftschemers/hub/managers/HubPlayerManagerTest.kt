package com.craftschemers.hub.managers

import assertk.assertThat
import assertk.assertions.*
import com.craftschemers.hub.BukkitTestCase
import com.craftschemers.hub.minigame.AbstractGameData
import com.craftschemers.hub.minigame.GameType
import com.craftschemers.hub.minigame.Minigame
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
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
    fun `verify player actually joins minigame`() {
        val player = server.addPlayer()
        val hubPlayerManager = plugin.playerManger
        hubPlayerManager.addPlayer(player)

        val hubPlayer = hubPlayerManager.getPlayer(player.uniqueId)

        assertThat(hubPlayer).isNotNull()
        val hubMinigameManager = plugin.gameManager
        if (hubPlayer != null) {
            hubMinigameManager.addPlayerToMinigame(hubPlayer, GameType.ONE_IN_THE_QUIVER)
            assertThat(hubPlayer.game).isEqualTo(GameType.ONE_IN_THE_QUIVER)
            assertThat(hubMinigameManager.minigames[GameType.ONE_IN_THE_QUIVER]?.lobbies)
                .isNotNull()
                .exactly(1) { it.prop(AbstractGameData::players).containsOnly(hubPlayer) }
        }
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
            assertThat(hubMinigameManager.minigames.values).each { game ->
                game.prop(Minigame::lobbies)
                    .each { lobby -> lobby.prop(AbstractGameData::players).doesNotContain(hubPlayer) }
            }
        }
    }

}
