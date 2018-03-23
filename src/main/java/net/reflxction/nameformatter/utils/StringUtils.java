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
import me.kbrewster.hypixelapi.guild.Member;
import me.kbrewster.mojangapi.MojangAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * A utility class which provides useful methods to manage strings
 */
public class StringUtils {

    private static String[] ranks = {"[VIP]", "[VIP+]", "[MVP]", "[MVP+]", "[MVP++]"};

    static String modifyRank(String rank, String plusColor) {
        if (rank.equals("NONE")) return "";
        if (rank.equals("VIP")) return ChatColor.GREEN + "[VIP]";
        if (rank.equals("VIP_PLUS")) return ChatColor.translateAlternateColorCodes("&a[VIP&6+&a]");
        if (rank.equals("MVP")) return ChatColor.AQUA + "[MVP]";
        if (rank.equals("MVP_PLUS"))
            return ChatColor.translateAlternateColorCodes("&b[MVP" + (ChatColor.fromName(plusColor) == null ? "" : ChatColor.fromName(plusColor)) + "+&b]");
        if (rank.equals("SUPERSTAR"))
            return ChatColor.translateAlternateColorCodes("&6[MVP" + (ChatColor.fromName(plusColor) == null ? "" : ChatColor.fromName(plusColor)) + "++&6]");
        return "";
    }

    public static String[] listToArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static ArrayList<String> arrayToList(String[] array) {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(array));
        return list;
    }

    static String getName(Member m) {
        String name = "";
        try {
            name = MojangAPI.getName(UUID.fromString(MojangAPI.addDashes(m.getUuid())));
        } catch (IOException | APIException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * A method which returns the name of the player without the rank
     *
     * @param name Player name which contains the rank prefix, e.g [VIP+] Reflxction
     * @return Name without the rank, e.g "Reflxction"
     */
    public static String stripRank(String name) {
        String nameWithoutColors = ChatColor.stripColor(name);
        for (String rank : ranks) {
            if (nameWithoutColors.contains(rank)) {
                return nameWithoutColors.replace(rank, "").trim();
            }
        }
        return nameWithoutColors.trim();
    }

}
