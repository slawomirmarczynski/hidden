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
 * ROT13を使用し、ラテン文字をアルファベット上で13文字分ずらして暗号化します。
 *
 * <p>大文字と小文字は別々に処理し、元の大文字・小文字を保ちます。
 * 空白、句読点、数字、ASCII範囲外の文字など、ASCII英字以外の文字は変更せずにコピーします。
 * ROT13は対称であり、2回適用すると元のテキストに戻るため、暗号化結果の復号にも使えます。
 * これは符号化方式であり、安全な暗号方式ではありません。</p>
 */
public class Rot13Cipher extends Cipher {
    /**
     * 入力内の各ASCII英字にROT13を適用します。
     *
     * @param text 変換するテキスト。{@code null}は指定できません
     * @return ASCII範囲外の英字やその他の文字を保持した変換後のテキスト
     * @throws NullPointerException {@code text}が{@code null}の場合
     */
    @Override
    public String encrypt(String text) {
        // 不正な入力に対して誤解を招く結果を返さず、明示的に失敗させます。
        Objects.requireNonNull(text, "text");

        // ROT13ではUTF-16コード単位数が変わらないため、入力と同じ容量で初期化できます。
        StringBuilder encrypted = new StringBuilder(text.length());
        // 文字を1つずつ変換します。ASCII範囲外の英字はそのまま通過します。
        for (char character : text.toCharArray()) {
            // 大文字をアルファベットの0始まりの位置に変換して回転し、'A'の位置を戻します。
            if (character >= 'A' && character <= 'Z') {
                encrypted.append((char) ('A' + (character - 'A' + 13) % 26));
            // 小文字にも同じ計算を適用し、小文字のまま保持します。
            } else if (character >= 'a' && character <= 'z') {
                encrypted.append((char) ('a' + (character - 'a' + 13) % 26));
            } else {
                // 空白、句読点、数字など、その他の文字は入力されたまま保持します。
                encrypted.append(character);
            }
        }
        // 呼び出し元の入力を変更せず、新しい文字列を返します。
        return encrypted.toString();
    }
}
