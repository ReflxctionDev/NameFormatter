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

import me.kbrewster.hypixelapi.guild.Member;
import me.kbrewster.mojangapi.MojangAPI;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.List;
import java.util.UUID;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class NameFormatter {

    private static String API_KEY = "pls dont hax me";
    private static Configuration config;
    private static boolean isEnabled = true;
    private final List<Member> members = LocalCache.players();

    static String getApiKey() {
        return API_KEY;
    }

    static void setApiKey(String key) {
        API_KEY = key;
        config.get("Key", "Key", "").set(key);
        config.save();
    }

    static boolean isEnabled() {
        return isEnabled;
    }

    static void setEnabled(boolean b) {
        isEnabled = b;
        config.get("Enabled", "Enabled", true).set(b);
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
        API_KEY = config.get("Key", "Key", "").getString();
        config.save();
    }

    @SubscribeEvent
    public void onPlayerNameFormat(PlayerEvent.NameFormat event) {
        String name;
        for (Member member : members) {
            try {
                name = MojangAPI.getName(UUID.fromString(MojangAPI.addDashes(member.getUuid())));
                if (event.username.equals(name)) {
                    event.displayname = ChatColor.translateAlternateColorCodes("&l&e[Impurity] &r" + event.username);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Register events here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new CommandHandler());
    }

    /**
     * Register commands here
     */
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandHandler());
    }
}
