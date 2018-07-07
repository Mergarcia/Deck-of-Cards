/*************************************
 Name: Eric Ybarra, Mercedes Garcia
 Thomas Krause, William Barajas
 Module 3
 CST 338 - "Deck of Cards"
 This program simulates a card game
 with the use of three classes: Card,
 Hand, & Deck. We use inheritance to
 incorporate all the classes into
 one program.
 
 *************************************/
public class Hand
{
    public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES};
    private char value;
    private Suit suit;
    boolean errorFlag;
    
    //The constructor should call the proper mutator(s).  Overload this to cope with a client that wants to
    //instantiate without parameters and use 'A' and 'spades' as the default value and suit when not supplied.
    //Provide at least two constructors -- no parameters and all parameters -- or more if you wish.
    //Because we have the errorFlag member, the constructor (via the mutator), can set that member when
    //it gets bad data; it does not have to assign default values upon receipt of bad data.
    //This is a new technique for us.  Again, default card (no parameters passed) is the ('A', spades).
    
    //Default Constructor
    public void Card()
    {
        value = value;
        suit = suit;
    }
    //Constructor with ALL parameters
    public void Card (char value, Suit suit)
    {
        value = 'A';
        suit = suit.SPADES;
        
    }
    //Mutators:
    public boolean set(char value, Suit suit)
    {
        if (errorFlag = isValid(value, suit))
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        return errorFlag;
    }
    //Accessors
    public char getValue()
    {
        return value;
    }
    //
    public Suit getSuit()
    {
        return suit;
    }
    //
    public boolean getErrorFlag()
    {
        return errorFlag;
    }
    //a stringizer that the client can use prior to displaying the card.
    //It provides a clean representation of the card.  If errorFlag == true,
    //it should return correspondingly reasonable reflection of this fact
    //(something like "[ invalid ]" rather than a suit and value).
    public String toString()
    {
        String displayCard = "Value: "+ value + ", Suit: " + suit;
        if(errorFlag == true)
        {
            return "[Invalid]";
        }
        else
        {
            return displayCard;
        }
    }
    //returns true if all the fields (members) are identical and false, otherwise.
    public boolean equals(Card card)
    {
        
    }
    //
    public boolean isValid(char value, Suit suit)
    {
        char[] allValues  =  {'A','1','2','3','4','5','6',
            '7','8','9','T','J','Q','K'};
        for(int i = 0; i< allValues.length;i++)
        {
            if(value != allValues[i])
            {
                System.out.println("Invalid");
            }
            else
                return true;
            for(int j = 0; j<allValues.length;j++)
            {
                if(suit != suit.values())
                    }
            
        }
        
    }
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        
    }
    
}



/*
public class Card {
   public enum Suit {Clubs, Diamonds, Hearts, Spades}

   private Suit suit;
   char value;
   boolean errorFlag;

   public Card(char value, Suit suit) {
      this.value = value;
      this.suit = suit;
   }

   public Card(boolean errorFlag) {
      this.errorFlag = errorFlag;
   }

   public Suit getSuit() {
      return suit;
   }

   public void setSuit(Suit suit) {
      this.suit = suit;
   }

   public char getValue() {
      return value;
   }

   public void setValue(char value) {
      this.value = value;
   }

   public boolean isErrorFlag() {
      return errorFlag;
   }

   public void setErrorFlag(boolean errorFlag) {
      this.errorFlag = errorFlag;
   }

   @Override
   public String toString() {
      return String.format("%s of %s", value, suit);
   }
}
*/
