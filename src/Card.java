public class Card {
    public enum Suit { Clubs, Diamonds, Hearts, Spades }

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
