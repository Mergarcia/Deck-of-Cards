import java.util.Random;
import java.util.Scanner;

/*************************************
 Name: Eric Ybarra, Mercedes Garcia
 Thomas Krause, William Barajas
 Module 3
 CST 338 - "Deck of Cards"
 This program simulates a card game
 with the use of three classes: Card,
 Hand, & Deck.

 *************************************/
public class Assig3 {
   public static void main(String[] args) {
      // Here we use some helper methods to test
      // each of the different sections of the program.
      testCard();
      testHand();
      testDeck();

      testIntegration();
   }

   /**
    * Tests the integration between Deck and Hand
    */
   private static void testIntegration() {
      System.out.println("\n/* ---------- TEST INTEGRATION ---------- */");
      Scanner input = new Scanner(System.in);

      // Ensure the user enters a valid number
      int count;
      do {
         System.out.print("How many hands? (1 - 10, please): ");
         count = input.nextInt();
      } while(count < 1 || count > 10);

      // Create the hands and deck
      Deck deck = new Deck();
      Hand hands[] = new Hand[count];

      for (int i = 0; i < count; i++) {
         hands[i] = new Hand();
      }

      // Use the helper to deal to each of the hands
      dealToHands(deck, hands);

      // Output unshuffled
      System.out.println("Here are our hands, from unshuffled deck:");
      for (Hand hand : hands) {
         System.out.println(hand + "\n");
      }

      // Reset the variables
      deck.init();
      deck.shuffle();
      for (Hand hand : hands) {
         hand.resetHand();
      }

      // Deal from the shuffled deck this time
      dealToHands(deck, hands);

      // Output shuffled
      System.out.println("Here are our hands, from SHUFFLED deck:");
      for (Hand hand : hands) {
         System.out.println(hand + "\n");
      }
   }

   /**
    * Deals evenly to an array of hands until the deck is empty.
    *
    * @param deck deck
    * @param hands hands
    */
   private static void dealToHands(Deck deck, Hand[] hands) {
      // Deal until we are told to leave
      while (true) {
         // Fill up each hand
         for (Hand hand : hands) {
            // If there was an error stop dealing
            if (! hand.takeCard(deck.dealCard())) {
               return;
            }
         }
      }
   }

   /**
    * Tests the card class
    */
   private static void testCard() {
      System.out.println("/* ---------- TEST CARD ---------- */");

      Card card1 = new Card('A', Card.Suit.spades);
      // One illegal card
      Card card2 = new Card('H', Card.Suit.hearts);
      Card card3 = new Card('J', Card.Suit.clubs);

      System.out.println(card1);
      System.out.println(card2);
      System.out.println(card3 + "\n");

      // Make first card illegal and illegal card legal
      card1.set('G', Card.Suit.diamonds);
      card2.set('Q', Card.Suit.spades);

      System.out.println(card1);
      System.out.println(card2);
      System.out.println(card3);
   }

   /**
    * Tests the hand class
    */
   private static void testHand() {
      System.out.println("\n/* ---------- TEST HAND ---------- */");
      Hand hand = new Hand();
      Card[] templates = {
         new Card('A', Card.Suit.spades),
         new Card('9', Card.Suit.hearts),
         new Card('2', Card.Suit.clubs),
         new Card('6', Card.Suit.diamonds),
         new Card('T', Card.Suit.spades),
      };

      // Add cards from the template until error
      boolean run = true;
      while (run) {
         for (Card card : templates) {
            if (! (run = hand.takeCard(card)))
               break;
         }
      }

      System.out.println("Hand full\nAfter deal");
      System.out.println(hand);

      System.out.println("\nTesting inspectCard()");
      // Legal
      System.out.println(hand.inspectCard(5));
      // Illegal
      System.out.println(hand.inspectCard(500));

      // Testing playCard
      System.out.println("\nTesting playCard()");
      Card card;

      // Play cards until we run out
      do {
         card = hand.playCard();
         if (! card.getErrorFlag())
            System.out.println("Playing " + card);
      }
      while (! card.getErrorFlag());

      System.out.println("\nAfter playing all cards");
      System.out.println(hand);
   }

   /**
    * Tests the deck class
    */
   private static void testDeck() {
      System.out.println("\n/* ---------- TEST DECK ---------- */");
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
      while (!(card = deck.dealCard()).getErrorFlag()) {
         System.out.print(card + " / ");
      }

      System.out.println("\n");
   }
}

/**
 * Card
 * CST 338 - M3 Deck of Cards
 *
 * @author Thomas Krause, Mercedes Garcia
 * @version 1.2
 */
class Card {
   private static final char[] validCardValues = {
      'A', '1', '2', '3', '4', '5', '6',
      '7', '8', '9', 'T', 'J', 'Q', 'K'
   };

   public enum Suit { clubs, diamonds, hearts, spades }

   private char value;
   private Suit suit;
   private boolean errorFlag;

   /**
    * Default constructor for the card class
    */
   public Card() {
      this('A', Suit.spades);
   }

   /**
    * Constructor with all parameters.
    *
    * @param value
    * @param suit
    */
   public Card(char value, Suit suit) {
      this.set(value, suit);
   }

   /**
    * Invalid card setter.
    *
    * @param errorFlag
    */
   public Card(boolean errorFlag) {
      this.errorFlag = errorFlag;
   }

   /**
    * Copy constructor for card.
    *
    * @param card
    */
   public Card(Card card) {
      this.set(card.getValue(), card.getSuit());
   }

   //region Mutators

   /**
    * Set the card to a value and suit.
    *
    * @param value value
    * @param suit suit
    * @return error
    */
   public boolean set(char value, Suit suit) {
      // If there is no error, set the values
      errorFlag = ! isValid(value, suit);
      if (! errorFlag) {
         this.value = value;
         this.suit = suit;
      }

      return errorFlag;
   }
   //endregion

   //region Accessors

   /**
    * Get the value of the card.
    *
    * @return value
    */
   public char getValue() {
      return value;
   }

   /**
    * Get the suit of the card.
    *
    * @return suit
    */
   public Suit getSuit() {
      return suit;
   }

   /**
    * Determine if the card has an error.
    *
    * @return error
    */
   public boolean getErrorFlag() {
      return errorFlag;
   }

   /**
    * Determines if the card is valid.
    *
    * @param value
    * @param suit
    * @return valid
    */
   public boolean isValid(char value, Suit suit) {
      for (char c : validCardValues) {
         if (value == c)
            return true;
      }

      return false;
   }
   //endregion

   /**
    * Outputs the value of the card in string form.
    *
    * @return string
    */
   public String toString() {
      if (errorFlag)
         return "** illegal **";

      return String.format("%s of %s", value, suit);
   }

   /**
    * Determines if two cards are equal.
    *
    * @param card otherCard
    * @return equal
    */
   public boolean equals(Card card) {
      return this.value == card.getValue()
         && this.suit == card.getSuit()
         && this.getErrorFlag() == card.getErrorFlag();
   }
}

/**
 * Card Deck
 * CST 338 - M3 Deck of Cards
 *
 * @author Thomas Krause
 * @version 1.0
 */
class Deck {
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
         for (Card card : masterPack) {
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

/**
 * Hand
 * CST 338 - M3 Deck of Cards
 *
 * @author Eric Ybarra, Willan Barajas, Thomas Krause
 * @version 1.1
 */
class Hand
{
   // Constants
   public static final int MAX_CARDS = 50;

   // Private Data Members
   private Card[] myCards;
   private int numCards;

   /**
    * Default constructor.
    */
   public Hand()
   {
      resetHand();
   }

   /**
    * Resets the hand removing all cards.
    */
   public void resetHand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   /**
    * Add a card to the hand.
    *
    * @param card card
    * @return error
    */
   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS || card.getErrorFlag())
         return false;

      myCards[numCards++] = new Card(card);
      return true;
   }

   /**
    * Return and remove the top card
    *
    * @return card
    */
   public Card playCard()
   {
      // Get the last card or error card.
      Card card = inspectCard(--numCards);
      if (numCards >= 0)
         myCards[numCards] = null;

      return card;
   }

   /**
    * Gets the number of cards in the hand.
    *
    * @return numCards
    */
   public int getNumCards()
   {
      return numCards;
   }

   /**
    * Get a specified card from the hand.
    * @param k index
    * @return card
    */
   public Card inspectCard(int k)
   {
      // If out of bounds, return error card.
      if (k > numCards || k < 0)
         return new Card(true);

      return myCards[k];
   }

   /**
    * Return the text representation of the hand.
    *
    * @return string
    */
   @Override
   public String toString() {
      String output = "Hand = ( ";

      for (int i = 0; i < numCards; i++) {
         output += String.format("%s of %s, ", myCards[i].getValue(), myCards[i].getSuit());
      }

      return output + ")";
   }
}
