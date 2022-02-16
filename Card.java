public class Card {
    private String number;
    private String suit;
    private int value;
    private int inDeck;
    public Card(int n){

        //Checking if it is a face card or ace
        if (n>10||n==0||n==1){
            number = faceCard(n);
        }
        else {
            number = String.format("%d",n);
            value = n;
        }
        inDeck = 0;
    }
    public String toString(){
        return number+" of "+suit;
    }
    public String getSuit() {
        return suit;
    }
    public String setSuit(int s){
        //Setting the suit based on the number
        if (s%52<13){
            suit = "Diamonds";
        }
        else if (s%52<26){
            suit = "Hearts";
        }
        else if (s%52<39){
            suit = "Clubs";
        }
        else{
            suit = "Spades";
        }
        return suit;
    }
    public String faceCard(int n){

        //Assigning the type of card to the card
        if (n == 11){
            number = "Jack";
            value = 10;
        }
        else if (n == 12){
            number = "Queen";
            value = 10;
        }
        else if (n == 0){
            number = "King";
            value = 10;
        }
        else{
            number = "Ace";
            value = 11;
        }
        return number;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int a){
        value = a;
    }
    public int getInDeck() {
        return inDeck;
    }
    public void setInDeck(int a){
        inDeck = a;
    }
    public String getNumber(){ return number;}
}
