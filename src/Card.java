/**
 * Card
 * CST 338 - M3 Deck of Cards
 *
 * @author Thomas Krause, Mercedes Garcia
 * @version 1.1
 */
public class Card {
   public enum Suit { Clubs, Diamonds, Hearts, Spades };

   private char value;
   private Suit suit;
   private boolean errorFlag;

   /**
    * Default constructor for the card class
    */
   public Card() {
      this('A', Suit.Spades);
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
      if (! (errorFlag = isValid(value, suit))) {
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
   //endregion

   /**
    * Outputs the value of the card in string form.
    *
    * @return string
    */
   public String toString() {
      if (errorFlag)
         return "[Invalid]";

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
         && this.suit == card.suit;
   }

   /**
    * Determines if the card is valid.
    *
    * @param value
    * @param suit
    * @return valid
    */
   public boolean isValid(char value, Suit suit) {
      char[] values = {'A', '1', '2', '3', '4', '5', '6',
         '7', '8', '9', 'T', 'J', 'Q', 'K'};

      for (char c : values) {
         if (value == c)
            return true;
      }

      return false;
   }
}