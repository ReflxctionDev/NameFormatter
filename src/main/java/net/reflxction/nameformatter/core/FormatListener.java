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

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.nameformatter.utils.ChatColor;
import net.reflxction.nameformatter.utils.StringUtils;

/**
 * Listener class which formats player names
 */
public class FormatListener {

    private NameFormatter main = new NameFormatter();

    @SubscribeEvent
    public void onPlayerNameFormat(PlayerEvent.NameFormat event) {
        System.out.println(event.displayname + " <- DISPLAYNAME");
        System.out.println(event.username + " <- USERNAME");

        if (NameFormatter.isEnabled()) {
            try {
                event.setCanceled(true);
            } catch (IllegalArgumentException ignored) {
            }
            if (main.getMembersCache() != null) {
                main.getMembersCache().stream()
                        .filter(m -> StringUtils.stripRank(m).equals(event.username))
                        .forEach(m -> event.displayname = ChatColor.translateAlternateColorCodes(main.getFormat() + StringUtils.stripRank(m)));
            }
        }
    }
}
