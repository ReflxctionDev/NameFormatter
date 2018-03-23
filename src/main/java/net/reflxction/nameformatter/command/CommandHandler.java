/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.nameformatter.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.reflxction.nameformatter.Reference;
import net.reflxction.nameformatter.core.NameFormatter;
import net.reflxction.nameformatter.gui.MainGUI;
import net.reflxction.nameformatter.utils.ChatColor;
import net.reflxction.nameformatter.utils.LocalCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CommandHandler implements ICommand {

    private NameFormatter m;
    private LocalCache cache = new LocalCache();

    public CommandHandler(NameFormatter m) {
        this.m = m;
    }

    @Override
    public String getCommandName() {
        return "nameformatter";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "nameformatter <key | toggle | format>";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> list = new ArrayList<>();
        list.add("nf");
        return list;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Player p = new Player();
        if (args.length == 0) {
            p.sendMessage("&cIncorrect usage. Try /" + getCommandUsage(sender));
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("toggle")) {
                m.setEnabled(!NameFormatter.isEnabled());
                p.sendMessage(Reference.PREFIX + (net.reflxction.nameformatter.core.NameFormatter.isEnabled() ? net.reflxction.nameformatter.utils.ChatColor.GREEN + "Name Formatter has been enabled" : net.reflxction.nameformatter.utils.ChatColor.RED + "Name Formatter has been disabled"));
            }
            if (args[0].equalsIgnoreCase("format")) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Minecraft.getMinecraft().displayGuiScreen(new MainGUI());
                    }
                }, 50);
            }
            if (args[0].equalsIgnoreCase("key")) {
                p.sendMessage(Reference.PREFIX + ChatColor.RED + "Invalid usage. Try /nf key <key>");
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("key")) {
                m.setApiKey(args[1]);
                p.sendMessage(Reference.PREFIX + net.reflxction.nameformatter.utils.ChatColor.GREEN + "Your API key has been set to " + net.reflxction.nameformatter.utils.ChatColor.YELLOW + args[1] + net.reflxction.nameformatter.utils.ChatColor.GREEN + ".");
                LocalCache.setCacheKey(args[1]);
            }
        }
    }


    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    private class Player {

        void sendMessage(String message) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(net.reflxction.nameformatter.utils.ChatColor.translateAlternateColorCodes(message)));
        }

    }
}
