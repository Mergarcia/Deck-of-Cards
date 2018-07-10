/**
 * Card
 * CST 338 - M3 Deck of Cards
 *
 * @author Thomas Krause, Mercedes Garcia
 * @version 1.1
 */
public class Card {
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
         && this.suit == card.getSuit();
   }
}
