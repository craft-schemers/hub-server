package com.craftschemers.hub.managers

import assertk.all
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HubPlayerManagerTest {

    private lateinit var server: ServerMock

    @BeforeEach
    fun setUp() {
        server = MockBukkit.mock()
    }

    @Test
    fun `verify player is added when addPlayer is called`() {
        val player = server.addPlayer()
        val hubPlayerManager = HubPlayerManager()


        player.inventory.setItemInMainHand(ItemStack(Material.ACACIA_BOAT))
        player.gameMode = GameMode.SURVIVAL

        hubPlayerManager.addPlayer(player)

        assertThat(hubPlayerManager.hasPlayer(player.uniqueId)).isTrue()
        assertThat(player.gameMode).isEqualTo(GameMode.ADVENTURE)
        assertThat(player.inventory.contents).containsOnly(null)
    }

}
