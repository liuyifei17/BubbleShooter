package View;

import javafx.scene.layout.Pane;

/**
 * The menu class.
 */
public abstract class Menu {

    private View view;
    private Pane menuPane;

    /**
     * the menu constructor.
     * @param view the view to set.
     * @param menuPane the menu pane to set.
     */
    public Menu(View view, Pane menuPane) {
        this.view = view;
        this.menuPane = menuPane;
    }

    /**
     * Creates the menu.
     */
    public abstract void drawMenu();

    /**
     * @return the menu pane.
     */
    public Pane getMenuPane() {
        return menuPane;
    }

    /**
     * @return the view.
     */
    public View getView() {
        return view;
    }

    /**
     * @param menuPane sets the menu pane.
     */
    public void setMenuPane(Pane menuPane) {
        this.menuPane = menuPane;
    }

}
