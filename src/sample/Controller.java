package sample;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;


import java.io.File;
import java.net.URL;

import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ImageView CardDeck;
    @FXML
    public ImageView Case1;
    @FXML
    public ImageView Case2;
    @FXML
    public ImageView Case3;
    @FXML
    public ImageView Case4;
    @FXML
    public ImageView buffer;

    File file = new File("resources/card_back.png");
    Image imageCardBack = new Image(file.toURI().toString());
    Deck deck = new Deck();
    Card card;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CardDeck.setImage(imageCardBack);

        buffer.setOnDragDetected(this::onImageViewDragDetected);
        CardDeck.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deck.openCard();
                card=deck.getTop();
                if(card!=null)
                    {

                        buffer.setUserData(card);
                        System.out.println("Карта активная "+buffer.getUserData().toString());
                        buffer.setImage(card.img);

                    }
                else buffer.setImage(null);

            }


        });


        Case1.setOnDragOver(this::onImageViewDragOver);
        Case2.setOnDragOver(this::onImageViewDragOver);
        Case3.setOnDragOver(this::onImageViewDragOver);
        Case4.setOnDragOver(this::onImageViewDragOver);

        Case1.setOnDragDropped(this::onImageViewDragDropped);
        Case2.setOnDragDropped(this::onImageViewDragDropped);
        Case3.setOnDragDropped(this::onImageViewDragDropped);
        Case4.setOnDragDropped(this::onImageViewDragDropped);

        Case1.setUserData(null);
        Case2.setUserData(null);
        Case3.setUserData(null);
        Case4.setUserData(null);

    }

    private void onImageViewDragDropped(DragEvent dragEvent) {

            ImageView targetImageView = (ImageView) dragEvent.getGestureTarget();

            System.out.println("Карта в кейсе была "  +targetImageView.getUserData());

            ImageView sourceImageView = (ImageView) dragEvent.getGestureSource();

            targetImageView.setUserData(sourceImageView.getUserData());


            targetImageView.setImage(sourceImageView.getImage());

            System.out.println("Карта в кейсе стала "  +targetImageView.getUserData());

            deck.removeTop();

            if (deck.getSize()!=0)
                {
                    card= deck.getTop();
                    buffer.setImage(card.img);
                    buffer.setUserData(card);

                }
            else {
                buffer.setImage(null);
                buffer.setUserData(null);
            }

        System.out.println("Подменили карту в колоде на "+buffer.getUserData());
    }


    private void onImageViewDragOver(DragEvent dragEvent) {

        ImageView source = (ImageView) dragEvent.getGestureSource();

        ImageView target = (ImageView) dragEvent.getSource();

        Card sourceCard = (Card) source.getUserData();
        Card targetCard = (Card) target.getUserData();


        if ( targetCard == null && sourceCard.rank == 1) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
        else if (sourceCard.rank == targetCard.rank - 1 && sourceCard.suit == targetCard.suit) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }


    }

    private void onImageViewDragDetected(MouseEvent mouseEvent)
    {
        if (deck.getSize()!=0) {
            Dragboard db = buffer.startDragAndDrop(TransferMode.ANY);

            ClipboardContent content = new ClipboardContent();

            content.putImage(buffer.getImage());

            db.setContent(content);

            mouseEvent.consume();
        }
    }


}
