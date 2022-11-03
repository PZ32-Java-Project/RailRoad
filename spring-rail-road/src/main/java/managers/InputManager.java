package managers;

import java.util.Scanner;

// TODO: wrap inputs in try/catch blocks (when UI is ready)
public class InputManager {
    private Scanner scanner;

    public InputManager()
    {
        scanner = new Scanner(System.in);
    }

    public int getEntranceCount() {
        System.out.println("Enter quantity of entrances:");
        return scanner.nextInt();
    }

    public int getCashRegistriesCount() {
        System.out.println("Enter quantity of cash registries:");
        return scanner.nextInt();
    }

    public int getCashRegistryServeTime() {
        System.out.println("Enter cash registry serve time (in ms):");
        return scanner.nextInt();
    }
}