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

package net.reflxction.nameformatter;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.reflxction.nameformatter.gui.MainGUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CommandHandler implements ICommand {

    @Override
    public String getCommandName() {
        return "nameformatter";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "nameformatter <key | toggle>";
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
        } else {
            if (args[0].equalsIgnoreCase("key")) {
                NameFormatter.setApiKey(args[1]);
            }
            if (args[0].equalsIgnoreCase("toggle")) {
                NameFormatter.setEnabled(!NameFormatter.isEnabled());
            }
            if (args[0].equalsIgnoreCase("format")) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Minecraft.getMinecraft().displayGuiScreen(new MainGUI());
                    }
                }, 50);
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
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColor.translateAlternateColorCodes(message)));
        }

    }
}
