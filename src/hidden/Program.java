package hidden;

public class Program implements Runnable {
    @Override
    public void run() {
        Input input = new Input();
        Output output = new Output();
        Cipher cipher = new Cipher();

        String plain = input.read();
        String encrypted = cipher.encrypt(plain);
        output.write(encrypted);
    }

    static void main() {
        Program program = new Program();
        program.run();
    }
}
