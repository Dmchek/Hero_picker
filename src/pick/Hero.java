package pick;

import javafx.scene.layout.AnchorPane;

public class Hero {
    private int id;
    private String name;
    private AnchorPane pane;
    private String style;

    public Hero(int id, String name, AnchorPane pane, String style) {
        this.id = id;
        this.name = name;
        this.pane = pane;
        this.style = style;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
