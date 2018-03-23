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

import java.util.ArrayList;

/**
 * An abstract cache class, extended by <p>LocalCache</p>
 *
 * @see net.reflxction.nameformatter.utils.LocalCache
 */
public abstract class PlayersCache {

    private static ArrayList<String> players = new ArrayList<>();

    public static ArrayList<String> getPlayersList() {
        return players;
    }

    protected static void put(String player, String rank) {
        getPlayersList().add(rank + player);
    }
}
