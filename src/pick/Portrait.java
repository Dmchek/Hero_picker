package pick;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Portrait {

    private AnchorPane portraitPane;
    private ImageView selected;
    private Text name;


    public void portraitHover(AnchorPane portraitPane, ImageView selected,Text name){
        portraitPane.setOnMouseEntered(event -> portraitPane.toFront());
        portraitPane.setOnMouseExited(event -> portraitPane.toBack());
        portraitPane.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> {
            selected.getStyleClass().clear();
            selected.getStyleClass().add(portraitPane.getId());
            name.setText(portraitPane.getId());
        });
    }
}

