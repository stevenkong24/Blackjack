import java.util.ArrayList;
public class Deck {
    private ArrayList<Card> fullDeck;
    private ArrayList<Card> deck;
    ArrayList<Card> hand = new ArrayList<>();
    private int deckNum;
    private int cards;
    private int num;
    private int tHandValue;
    private int dHandValue;

    public Deck(int deckNum){
        fullDeck = new ArrayList<>();
        deck = new ArrayList<>();
        this.deckNum = deckNum;
        cards = deckNum*52;
        initializeDeck();
        tHandValue = 0;
        dHandValue = 0;
    }
    public ArrayList<Card> dealHand(){
        deck = fullDeck;
        //Dealing to the user
        for(int x = 0; x<2;x++){

            //Assigning a card number to the dealt card
            num = (int)(Math.random()*cards);
            Card dealt = new Card((num%52)%13);
            dealt.setSuit(num);
            //Adding to the hands and removing the cards from the deck
            if (x==0) {
                deck.get(num).setInDeck(-1);
                hand.add(dealt);
            }
            else{

                /*
                Checking if the card is in the deck and repeating if a card is
                not in the deck
                
                */
                if (deck.get(num).getInDeck()==0){
                    hand.add(dealt);
                    deck.get(num).setInDeck(-1);
                }
                else{
                    int count = 0;
                    while (count != 1){
                        num = (int)(Math.random()*cards);
                        dealt = new Card((num%52)%13);
                        dealt.setSuit(num);
                        if (deck.get(num).getInDeck()==0){
                            count = 1;
                            hand.add(dealt);
                            deck.get(num).setInDeck(-1);
                        }
                    }
                }
            }
        }

        //Assing to the total hand value
        for (Card c: hand){
            tHandValue+= c.getValue();
        }
        return hand;
    }
    public void initializeDeck(){

        //Adding each card to the deck
        for (int x = 0; x<cards; x++){
            Card add = new Card(x%13+1);
            add.setSuit(x);
            fullDeck.add(add);
        }
    }

    //The user chooses to hit
    public Card hit(){

        //Picking and initializing the card
        num = (int)(Math.random()*cards);
        Card dealt = new Card((num%52)%13);
        dealt.setSuit(num);

        //Checking if the card is still in the deck
        if (deck.get(num).getInDeck()==0){
            hand.add(dealt);
        }
        else{
            int count = 0;

            //Reapeating if it is already taken out
            while (count != 1){
                num = (int)(Math.random()*cards);
                dealt = new Card((num%52)%13);
                dealt.setSuit(num);
                if (deck.get(num).getInDeck()==0){
                    count = 1;
                    hand.add(dealt);
                    deck.get(num).setInDeck(-1);
                }
            }
        }
        return dealt;
    }

    //Dealing the dealer's hand
    public ArrayList<Card> dealDHand(ArrayList<Card> playerHand) {
        ArrayList<Card> dHand = new ArrayList<>();
        for (int x = 0; x < 2; x++) {
            if (x == 0) {

                //Dealing and initializing card
                num = (int) (Math.random() * cards);
                Card dealt = new Card((num % 52) % 13);
                dealt.setSuit(num);
                boolean inHand = false;

                for (Card c : playerHand) {
                    if (dealt.toString().equals(c.toString())) {
                        inHand = true;
                    }
                }

                //Checking if the card is in the hand and repating if it is
                if (!inHand) {
                    dHand.add(dealt);
                    dHandValue+=dealt.getValue();
                }
                else {
                    while (inHand) {
                        num = (int) (Math.random() * cards);
                        dealt = new Card((num % 52) % 13);
                        dealt.setSuit(num);
                        inHand = false;
                        for (Card c : playerHand) {
                            if (dealt.toString().equals(c.toString())) {
                                inHand = true;
                            }
                        }
                    }
                    dHand.add(dealt);
                    dHandValue += dealt.getValue();
                }
            }
            else{

                //Hitting until the hand value is too high
                while (dHandValue<17) {
                    num = (int) (Math.random() * cards);
                    Card dealt = new Card((num % 52) % 13);
                    dealt.setSuit(num);
                    boolean inDHand = false;
                    for (Card c: dHand){
                        if (dealt.toString().equals(c.toString())){
                            inDHand = true;
                        }
                    }

                    //Checking if it is in the dealer's hand
                    if (!inDHand) {
                        boolean inHand = false;
                        for (Card c : playerHand) {
                            if (c.toString().equals(dealt.toString())) {
                                inHand = true;
                            }
                        }
                        if (inHand) {

                            //Repeating, drawing new card
                            while (inHand) {
                                num = (int) (Math.random() * cards);
                                dealt = new Card((num % 52) % 13);
                                dealt.setSuit(num);
                                inHand = false;
                                for (Card c : playerHand) {
                                    if (dealt.toString().equals(c.toString())) {
                                        inHand = true;
                                    }
                                }
                                for (Card c: dHand) {
                                    if (dealt.toString().equals(c.toString())) {
                                        inHand = true;
                                    }
                                }
                            }
                        }
                        dHand.add(dealt);
                        dHandValue += dealt.getValue();
                    }
                }
            }
        }
            return dHand;
        }
        public int getTHandValue(){
            return tHandValue;
        }
    }
