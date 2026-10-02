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
 * Connects the console input/output implementations with the ROT13 cipher.
 *
 * <p>The program depends on the abstract {@link Input}, {@link Output}, and
 * {@link Cipher} types. The concrete implementations are selected here, so
 * alternative implementations can be introduced without changing the
 * processing sequence.</p>
 */
public class Program implements Runnable {
    /**
     * Reads one line, encrypts it, and writes the result.
     *
     * <p>Keeping these operations in sequence makes the data flow explicit:
     * input text is passed to the cipher, and only the encrypted text is sent
     * to the output.</p>
     */
    @Override
    public void run() {
        // Select the console adapters and the desired encryption algorithm.
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        Cipher cipher = new Rot13Cipher();

        // Keep each stage separate so another input, cipher, or output can be substituted.
        String plain = input.read();
        String encrypted = cipher.encrypt(plain);
        output.write(encrypted);
    }

    /**
     * Creates and runs the program.
     *
     * <p>This entry point has package visibility to match the original
     * application structure.</p>
     */
    static void main() {
        // Start the application through the Runnable implementation.
        Program program = new Program();
        program.run();
    }
}
