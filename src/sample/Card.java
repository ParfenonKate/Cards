package sample;

import javafx.scene.image.Image;

import java.io.File;

enum Suit {hearts, diamonds, spades, clubs }


public class Card {
    int rank;
    Suit suit;
    Image img;
    public Card (int r, Suit s) {rank = r; suit = s;img = getImage(s,r);}


    public Image getImage(Suit s, int r) {
        Image result;
        File file = new File("resources/"+s+r+".png");
        result = new Image(file.toURI().toString());
        return result;
    }

    public int getRank(){return rank;}
    @Override
    public String toString() {
        return suit.toString() + rank;
    }
}