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

package net.reflxction.nameformatter.utils;

import me.kbrewster.exceptions.APIException;
import me.kbrewster.hypixelapi.HypixelAPI;
import me.kbrewster.hypixelapi.guild.Guild;
import me.kbrewster.hypixelapi.player.HypixelPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class, made to cache the guild players and their ranks to avoid making too many API requests
 */
public class LocalCache {

    private static String key = "7ab406d7-9776-4fa8-b03e-d8866c1d148b";
    private static final HypixelAPI api = new HypixelAPI(key);


    public static List<String> updateCache() {
        List<String> players = null;
        try {
            players = new ArrayList<>();
            try {
                String guildID = null;
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
                List<String> finalPlayers = players;
                guild.getMembers().forEach(m -> {
                    try {
                        HypixelPlayer player = api.getPlayer(StringUtils.getName(m));
                        String rank = player.getCurrentRank();
                        String plusColor = player.getRankPlusColor();
                        finalPlayers.add(StringUtils.modifyRank(rank, plusColor) + " " + StringUtils.getName(m));
                    } catch (IOException | APIException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception ignored) {
            }
        } catch (Exception ignored) {
        }
        return players;
    }

    /**
     * A method which handles the key used to cache data
     *
     * @param s Key which the cache key should be set to
     */
    public static void setCacheKey(String s) {
        key = s;
    }

    public static HypixelAPI getApiInstance() {
        return api;
    }

}


