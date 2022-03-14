/**
 * MIT License
 * <p>
 * Copyright (c) 2019-2021 Matt
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.triumphteam.cmd.core.flag.internal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Basically a holder that contains all the needed flags for the command.
 *
 * @param <S> The sender type.
 */
public final class FlagGroup<S> {

    private final Map<String, FlagOptions<S>> flags = new HashMap<>();
    private final Map<String, FlagOptions<S>> longFlags = new HashMap<>();

    public Map<String, FlagOptions<S>> getFlags() {
        return flags;
    }

    public Map<String, FlagOptions<S>> getLongFlags() {
        return longFlags;
    }

    /**
     * Adds a new flag to the group.
     *
     * @param flagOptions The {@link FlagOptions} that should be added to the lis.
     */
    public void addFlag(@NotNull final FlagOptions<S> flagOptions) {
        final String key = flagOptions.getKey();

        final String longFlag = flagOptions.getLongFlag();
        if (longFlag != null) {
            longFlags.put(longFlag, flagOptions);
        }

        flags.put(key, flagOptions);
    }

    /**
     * Checks if the flags are empty.
     *
     * @return Whether the flag lists are empty.
     */
    public boolean isEmpty() {
        return flags.isEmpty() && longFlags.isEmpty();
    }

    /**
     * Gets the flag that matches the current token.
     *
     * @param token    The current token, a flag name or not.
     * @return The flag if found or null if not a valid flag.
     */
    @Nullable
    public FlagOptions<S> getMatchingFlag(@NotNull final String token) {
        final String stripped = stripLeadingHyphens(token);

        final FlagOptions<S> flag = flags.get(stripped);
        return flag != null ? flag : longFlags.get(stripped);
    }

    /**
     * Strips the hyphens from the token.
     *
     * @param token The flag token.
     * @return The flag token without hyphens.
     */
    @NotNull
    private String stripLeadingHyphens(@NotNull final String token) {
        if (token.startsWith("--")) return token.substring(2);
        if (token.startsWith("-")) return token.substring(1);
        return token;
    }
}
