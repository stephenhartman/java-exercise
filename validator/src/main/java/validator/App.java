package validator;

import validator.Input;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Input input = new Input(args[0]);
        System.out.println(input.process());

    }
}
