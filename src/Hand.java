/**
 * Hand
 * CST 338 - M3 Deck of Cards
 *
 * @author Eric Ybarra, Willan Barajas, Thomas Krause
 * @version 1.1
 */
public class Hand
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
