package sample;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    private ArrayList<Card> activeCards = new ArrayList<>();



    ArrayList<Integer> Rank = new ArrayList();

    public Deck() {
        for (int i = 1; i < 14; i++) {
            Rank.add(i);
        }
        cards = new ArrayList<>();
        for (Suit s : Suit.values()) {
            for (int r : Rank) {
                cards.add(new Card(r, s));
            }
        }
        Collections.shuffle(cards);

        printDeck();
    }


    public void openCard(){
        if (cards.size()==0){
            cards.addAll(activeCards);
            activeCards.clear();
        }
        else{
        activeCards.add(cards.get(cards.size()-1));
        cards.remove(cards.size()-1);}
    }
    public void removeTop() {
        activeCards.remove(activeCards.size() - 1);
    }

    public Card getTop() {
        if(activeCards.size()==0) return null;
        return activeCards.get(activeCards.size()-1);
    }



    public int getSize() {
        return activeCards.size();
    }

    public void printDeck() {
        System.out.println("============================");
        int i = 0;
        for (Card c : cards) {
            System.out.println(c.toString() + " Индекс = " + i);
            i++;
        }
        System.out.println("============================");
    }


}
