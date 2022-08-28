package com.craftschemers.hub.minigame

import org.bukkit.ChatColor
import org.bukkit.command.CommandSender

fun CommandSender.sendErrorMessage(message: String) = sendMessage("${ChatColor.RED}$message")

fun CommandSender.sendSuccessMessage(message: String) = sendMessage("${ChatColor.GOLD}$message")
