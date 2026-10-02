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

/**
 * コンソール入出力の実装とROT13暗号を組み合わせます。
 *
 * <p>このプログラムは抽象型の{@link Input}、{@link Output}、{@link Cipher}に依存します。
 * 具体的な実装はここで選択するため、処理の流れを変えずに別の実装へ置き換えられます。</p>
 */
public class Program implements Runnable {
    /**
     * 1行を読み込み、暗号化して、結果を書き出します。
     *
     * <p>処理を順番に分けることでデータの流れを明確にします。
     * 入力されたテキストを暗号器に渡し、暗号化されたテキストだけを出力します。</p>
     */
    @Override
    public void run() {
        // コンソール用の入出力と、使用する暗号アルゴリズムを選択します。
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        Cipher cipher = new Rot13Cipher();

        // 各段階を分離し、入力元・暗号器・出力先を個別に差し替えられるようにします。
        String plain = input.read();
        String encrypted = cipher.encrypt(plain);
        output.write(encrypted);
    }

    /**
     * プログラムを生成して実行します。
     *
     * <p>元のアプリケーション構成に合わせ、このエントリーポイントはパッケージ可視です。</p>
     */
    static void main() {
        // Runnableの実装を通じてアプリケーションを開始します。
        Program program = new Program();
        program.run();
    }
}
