/*
 * BSD 2-Clause License
 *
 * Copyright (c) 2026, Slawek
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDER AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package hidden;

import java.util.Objects;

/**
 * Encrypts Latin letters using ROT13, rotating each letter by 13 positions.
 *
 * <p>Uppercase and lowercase letters are handled separately to preserve their
 * case. Characters outside the ASCII letter ranges—including spaces,
 * punctuation, digits, and non-ASCII characters—are copied unchanged. ROT13
 * is symmetric: applying it twice returns the original text, so it can also
 * be used to reverse its own output. It is an encoding, not secure
 * cryptography.</p>
 */
public class Rot13Cipher extends Cipher {
    /**
     * Applies ROT13 to each ASCII letter in the input.
     *
     * @param text text to transform; must not be {@code null}
     * @return transformed text with non-ASCII letters and other characters
     *         preserved
     * @throws NullPointerException if {@code text} is {@code null}
     */
    @Override
    public String encrypt(String text) {
        // Fail explicitly for invalid input instead of returning a misleading result.
        Objects.requireNonNull(text, "text");

        // ROT13 preserves the number of UTF-16 code units, so this is a suitable initial capacity.
        StringBuilder encrypted = new StringBuilder(text.length());
        // Transform each character independently; non-ASCII letters pass through unchanged.
        for (char character : text.toCharArray()) {
            // Convert an uppercase letter to a zero-based alphabet offset, rotate, then restore A's offset.
            if (character >= 'A' && character <= 'Z') {
                encrypted.append((char) ('A' + (character - 'A' + 13) % 26));
            // Apply the same arithmetic to lowercase letters, preserving their case.
            } else if (character >= 'a' && character <= 'z') {
                encrypted.append((char) ('a' + (character - 'a' + 13) % 26));
            } else {
                // Keep spaces, punctuation, digits, and all other characters exactly as provided.
                encrypted.append(character);
            }
        }
        // Return a new string; the caller's original input has not been modified.
        return encrypted.toString();
    }
}
