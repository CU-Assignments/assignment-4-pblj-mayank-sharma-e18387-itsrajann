import java.util.*;

public class CardDeckManager {
    private static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] VALUES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static Map<String, List<String>> deck = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeDeck();
        
        System.out.print("Enter the suit (e.g., Hearts, Diamonds, Clubs, Spades): ");
        String suit = scanner.nextLine().trim();

        displayCardsBySuit(suit);
    }

    private static void initializeDeck() {
        for (String suit : SUITS) {
            List<String> cards = new ArrayList<>();
            for (String value : VALUES) {
                cards.add(value + " of " + suit);
            }
            deck.put(suit, cards);
        }
    }

    private static void displayCardsBySuit(String suit) {
        if (deck.containsKey(suit)) {
            System.out.println("Cards in " + suit + ": " + String.join(", ", deck.get(suit)));
        } else {
            System.out.println("Invalid suit! Please enter one of: " + String.join(", ", SUITS));
        }
    }
}
