/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.accounts;

import com.android.internal.util.CharSequences;

public class ProviderEntry implements Comparable<ProviderEntry> {
    private final CharSequence name;
    private final String type;

    ProviderEntry(CharSequence providerName, String accountType) {
        name = providerName;
        type = accountType;
    }

    public int compareTo(ProviderEntry another) {
        if (name == null) {
            return -1;
        }
        if (another.name == null) {
            return +1;
        }

        // both are murena accounts, compare them normally
        if (MurenaAccountHelper.isMurenaAccount(type) && MurenaAccountHelper.isMurenaAccount(another.type)) {
            return CharSequences.compareToIgnoreCase(name, another.name);
        }

        // if any one is Murena account, put it on top
        if (MurenaAccountHelper.isMurenaAccount(type)) {
            return -1;
        }

        if (MurenaAccountHelper.isMurenaAccount(another.type)) {
            return 1;
        }

        return CharSequences.compareToIgnoreCase(name, another.name);
    }

    public CharSequence getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}