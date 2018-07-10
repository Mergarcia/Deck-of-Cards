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
      testCard();
      testHand();
      testDeck();
   }

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
