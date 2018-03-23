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

package net.reflxction.nameformatter.core;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.reflxction.nameformatter.Reference;
import net.reflxction.nameformatter.command.CommandHandler;
import net.reflxction.nameformatter.utils.ChatColor;
import net.reflxction.nameformatter.utils.LocalCache;

import java.io.File;
import java.util.List;
import java.util.UUID;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class NameFormatter {

    private static String API_KEY = "7ab406d7-9776-4fa8-b03e-d8866c1d148b";
    private static String format = ChatColor.translateAlternateColorCodes("&e&l[Impurity]");

    private static Configuration config;

    private static boolean isEnabled = true;

    private List<String> members = LocalCache.updateCache();

    public static boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean b) {
        isEnabled = b;
        config.get("Enabled", "Enabled", true).set(b);
        config.save();
    }

    private static String getApiKey() {
        return API_KEY;
    }

    public void setApiKey(String key) {
        API_KEY = key;
        config.get("Key", "Key", "pls dont hack me").set(UUID.fromString(key).toString());
        config.save();
    }

    String getFormat() {
        return format;
    }

    public void setFormat(String newFormat) {
        format = newFormat;
        config.get("Format", "Format", format).set(newFormat);
        config.save();
    }

    /**
     * Initialize variables here
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(new File("config/name-formatter.cfg"));
        config.load();
        isEnabled = config.get("Enabled", "Enabled", true).getBoolean();
        setApiKey(config.get("Key", "Key", "7ab406d7-9776-4fa8-b03e-d8866c1d148b").getString());
        LocalCache.setCacheKey(getApiKey());
        format = config.get("Format", "Format", format).getString();
        config.save();
    }

    /**
     * Register events here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new FormatListener());
        ClientCommandHandler.instance.registerCommand(new CommandHandler(this));
    }

    /**
     * Register commands here
     */
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandHandler(this));
    }

    List<String> getMembersCache() {
        return members;
    }
}


