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

import me.kbrewster.exceptions.APIException;
import me.kbrewster.hypixelapi.HypixelAPI;
import me.kbrewster.hypixelapi.guild.Guild;
import me.kbrewster.mojangapi.MojangAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class LocalCache {

    static List<String> cacheGuildPlayers(final String key) {
        List<String> players = new ArrayList<>();
        try {
            HypixelAPI api = new HypixelAPI(key);

            String guildID = null; // Gets Guilds Identifier
            try {
                guildID = api.getGuildID("Reflxction");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Guild guild = null;
            try {
                guild = api.getGuild(guildID);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert guild != null;
            guild.getMembers().forEach(m -> {
                try {
                    players.add(MojangAPI.getName(UUID.fromString(MojangAPI.addDashes(m.getUuid()))));
                } catch (IOException | APIException e) {
                    e.printStackTrace();
                }
            });
            return players;
        } catch (Exception ignored) {
        }
        return null;
    }

}


