/*************************************
 Name: Eric Ybarra, Mercedes Garcia
 Thomas Krause, William Barajas
 Module 3
 CST 338 - "Deck of Cards"
 This program simulates a card game
 with the use of three classes: Card,
 Hand, & Deck. We use inheritance to
 incorporate all these classes into
 one program.

 *************************************/
public class Main {
    public static void main(String[] args) {
        testDeck();
    }

    /**
     * Tests the deck class
     */
    private static void testDeck() {
        // Deck Class Test
        Deck deck = new Deck(2);
        dealDeck(deck);

        deck.init();
        deck.shuffle();
        dealDeck(deck);

        // We could create a new instance. But this is a good time
        // to test the init with resize.
        deck.init(1);
        dealDeck(deck);

        deck.init();
        deck.shuffle();
        dealDeck(deck);
    }

    /**
     * Helper method to deal and output all of the cards for a deck.
     *
     * @param deck
     */
    private static void dealDeck(Deck deck) {
        Card card;
        while (! (card = deck.dealCard()).isErrorFlag()) {
            System.out.print(card + " / ");
        }

        System.out.println("\n");
    }
}
