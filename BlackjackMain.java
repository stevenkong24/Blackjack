//Make something say you win if hand value is 21
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class BlackjackMain {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        int deckNum = 1;
        int handValue = 0;
        int dHandValue = 0;
        String decision;
        boolean gameDone = false;

        //Initializing the deck
        Deck d = new Deck(deckNum);
        System.out.println("Your hand is:");

        //Dealing two cards to the player
        ArrayList<Card> hand = d.dealHand();
        for (Card c: hand){
            TimeUnit.MILLISECONDS.sleep(750);
            System.out.println("  "+c.toString());
            handValue+= c.getValue();
        }

        //Dealing for the dealer and showing the up card
        ArrayList <Card> dHand = d.dealDHand(hand);
        for (Card c: dHand){
            dHandValue += c.getValue();
        }
        TimeUnit.MILLISECONDS.sleep(750);
        System.out.println("The dealer's up card is "+dHand.get(0).toString());
        TimeUnit.MILLISECONDS.sleep(750);

        //Checking if the user got blackjack
        if (handValue == 21){
            System.out.println("You got Blackjack!");
        }

        /*
        While loop for the player choosing their decision and continuing
        until they lose or choose to stand
        */
        while (handValue != 21&& !gameDone) {
            System.out.println("The value of your hand is a " + handValue + ". Do you want to hit or stand?");
            decision = in.nextLine().toLowerCase();

            if (decision.equals("h")){
                Card newCard = d.hit();
                TimeUnit.MILLISECONDS.sleep(750);
                System.out.println(newCard.toString());
                handValue += newCard.getValue();
                boolean aceInHand = false;

                //Checking if the user got an ace
                for (Card c: hand){
                    if (c.getNumber().equals("Ace")){
                        aceInHand = true;
                    }
                }

                //The user loses
                if (handValue > 21 && !(aceInHand)){
                    int val = 0;
                    System.out.println("Sorry, you busted. The value of your hand is "+handValue+".");
                }
                //The user's hand value is reduced as they have an ace
                else if (handValue > 21 && aceInHand) {
                    for (Card c : hand) {
                        //Add to check if 2 aces
                        if (c.getNumber().equals("Ace")) {
                            handValue -= 10;
                        }
                    }
                }

                //The user has 21
                else{
                    System.out.println("The value of your hand is 21");
                }
            }

            //The user stands
            else if (decision.equals("s")){
                TimeUnit.MILLISECONDS.sleep(750);

                //Showing the dealer's hand
                System.out.println("The dealer's hand is:");
                for (Card c: dHand) {
                    TimeUnit.MILLISECONDS.sleep(750);
                    System.out.println("  "+c.toString());
                }
                TimeUnit.MILLISECONDS.sleep(750);

                //Different results that can happen
                if (dHandValue > 21){
                    System.out.println("You win! The dealer busted.");
                }
                else if (dHandValue<handValue){
                    System.out.println("You win! The dealer's hand is worth "+dHandValue+" and yours is worth "+handValue+".");
                }
                else if (dHandValue>handValue){
                    System.out.println("You lost. The dealer's hand is worth "+dHandValue+" and yours is worth "+handValue+".");
                }
                else{
                    System.out.println("You pushed. Both hands are worth "+handValue);
                }
                gameDone = true;
            }
        }
    }
}
