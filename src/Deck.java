import java.util.Random;

/**
 * Card Deck
 *
 * CST 338 - M3 Deck of Cards
 * @author Thomas Krause
 * @version 1.0
 */
public class Deck {
    // Allow the deck to have up to 6 packs of cards
    public static final int MAX_CARDS = 52 * 6;
    private static Card[] masterPack;

    private Card cards[] = new Card[MAX_CARDS];
    private int topCard = 0;
    private int numPacks;

    /**
     * Default constructor that creates one pack.
     */
    public Deck() {
        this(1);
    }

    /**
     * Creates a deck with up to 6 packs.
     *
     * @param numPacks number of packs
     */
    public Deck(int numPacks) {
        this.init(numPacks);
    }

    /**
     * Mutator that sets the number of packs.
     * If not in the range, we'll force it into the range.
     *
     * @param numPacks number of packs
     */
    private void setNumPacks(int numPacks) {
        if (numPacks > 6)
            numPacks = 6;

        if (numPacks < 0)
            numPacks = 1;

        this.numPacks = numPacks;
    }

    /**
     * Setup the master pack so that we can copy from it.
     */
    private static void allocateMasterPack() {
        if (masterPack != null)
            return;

        masterPack = new Card[52];
        char[] map = {
            'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'
        };

        // For each suit, generate the cards
        int i = 0;
        for (Card.Suit suit : Card.Suit.values()) {
            // Thirteen cards in each suit
            for (char value : map) {
                // Assign the card and move to the new card
                masterPack[i++] = new Card(value, suit);
            }
        }
    }

    /**
     * Initialize the deck with x packs.
     *
     * @param numPacks number of packs
     */
    public void init(int numPacks) {
        this.setNumPacks(numPacks);
        this.init();
    }

    /**
     * Initialize the deck with one pack.
     */
    public void init() {
        Deck.allocateMasterPack();

        topCard = 0;
        cards = new Card[MAX_CARDS];

        // Clone the cards from the masterPack
        int c = 0;
        for (int j = 0; j < numPacks; j++) {
            for (Card card: masterPack) {
                cards[c++] = card;
            }
        }
    }

    /**
     * Shuffle the deck.
     */
    public void shuffle() {
        Random rand = new Random();

        int numCards = numPacks * 52;
        for (int i = 0; i < numCards; i++) {
            // Random for remaining positions.
            int r = i + rand.nextInt(numCards - i);

            // Swap the elements
            Card temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;

        }
    }

    /**
     * Deal a card from the deck
     *
     * @return card
     */
    public Card dealCard() {
        Card card = inspectCard(topCard);
        topCard++;

        return card;
    }

    /**
     * Retrieve the next card from the deck.
     *
     * @param k index
     * @return card
     */
    public Card inspectCard(int k) {
        // Are we out of index?
        if (k >= numPacks * 52)
            return new Card(true);

        // Has this card already been removed?
        Card card = cards[topCard];
        if (card == null)
            return new Card(true);

        return card;
    }

    /**
     * Get the index of the next card.
     *
     * @return topCard
     */
    public int getTopCard() {
        return topCard;
    }
}
