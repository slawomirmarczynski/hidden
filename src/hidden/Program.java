package hidden;

public class Program implements Runnable {
    @Override
    public void run() {
        Input input = new ConsoleInput();
        Output output = new ConsoleOutput();
        Cipher cipher = new Rot13Cipher();

        String plain = input.read();
        String encrypted = cipher.encrypt(plain);
        output.write(encrypted);
    }

    static void main() {
        Program program = new Program();
        program.run();
    }
}
