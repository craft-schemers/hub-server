package com.craftschemers.hub

import be.seeseemelk.mockbukkit.MockBukkit
import be.seeseemelk.mockbukkit.ServerMock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BukkitTestCase {

    lateinit var server: ServerMock
    lateinit var plugin: Hub

    @BeforeEach
    fun setUp() {
        server = MockBukkit.mock()
        plugin = MockBukkit.load(Hub::class.java)
        plugin.onEnable()
    }

    @AfterEach
    fun tearDown() {
        MockBukkit.unmock()
    }

}
