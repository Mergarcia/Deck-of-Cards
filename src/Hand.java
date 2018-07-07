/**
 * Hand
 * <p>
 * CST 338 - M3 Deck of Cards
 *
 * @author Eric Ybarra, Willan Barajas
 * @version 1.0
 */
public class Hand 
{
   // Constants
   public static final int MAX_CARDS = 50;
   
   // Private Data Members
   private Card[] myCards;
   private int numCards;
   
   // Default Constructor
   public Hand()
   {
      resetHand();
   }
   
   // Resets the hand 
   public void resetHand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }
   
   //Adds a card to the next available position in the 
   // myCards array
   public boolean takeCard(Card card)
   {
      if (numCards >= MAX_CARDS)
      {
         return false; 
      }
      
   }
   
   // Returns and removes the card in the top occupied
   // position of the array.
   public Card playCard()
   {
      if(numCards <= 0)
      {
         return new Card; 
      }
      Card card = new Card(my cards[--numCards]);
      myCards[numCards] = null;
      return card;
   }
   
   // Returns the current number of cards
   public int getNumCards()
   {
      return numCards;
   }
   
}
