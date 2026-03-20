package gui;

import domein.DomeinController;
import dto.ContainerDTO;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Collection;

public class OverzichtsScherm extends BorderPane {
    private static final int SERIENUMMER = 0;
    private static final int EIGENAAR = 1;
    private static final int MASSA = 2;
    private static final int VOLUME = 3;
    private final DomeinController dc;
    private GridPane overzichtsPane;
    private Menu sorteerMenu;
    private int gekozenSortering = -1;

    public OverzichtsScherm(DomeinController dc) {
        this.dc = dc;
        buildGui();
    }

    private void buildGui() {
        MenuBar menuBar = buildMenuBar();
        setTop(menuBar);
        overzichtsPane = buildGridPane();
        setCenter(overzichtsPane);
        toonContainers(-1);
    }

    private GridPane buildGridPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(3);
        grid.setAlignment(Pos.TOP_CENTER);
        ColumnConstraints fixedSizeConstraint = new ColumnConstraints(100);
        grid.getColumnConstraints().addAll(fixedSizeConstraint, fixedSizeConstraint, fixedSizeConstraint,
                fixedSizeConstraint);
        Button[] buttons = {new Button("Serienummer"), new Button("Eigenaar"), new Button("Massa"),
                new Button("Volume")};
        int col = 0;
        for (Button button : buttons) {
            grid.add(button, col, 0);
            button.getStyleClass().add("headerButton");
            GridPane.setHalignment(button, HPos.CENTER);
            final int sorteerSleutel = col;
            button.setOnAction(evt -> toonContainers(sorteerSleutel));
            button.setPrefWidth(100);
            col++;
        }
        return grid;
    }

    private MenuBar buildMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Containers");
        MenuItem overzichtMenuItem = new MenuItem("Overzicht");
        MenuItem toevoegMenuItem = new MenuItem("Toevoegen");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+x"));
        fileMenu.getItems().addAll(overzichtMenuItem, toevoegMenuItem, new SeparatorMenuItem(), exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        sorteerMenu = new Menu("Sorteren");
        MenuItem[] menuItems = {new MenuItem("Op serienummer"), new MenuItem("Op eigenaar"), new MenuItem("Op massa"),
                new MenuItem("Op volume")};
        int position = 0;
        for (MenuItem menuItem : menuItems) {
            final int sorteerSleutel = position;
            sorteerMenu.getItems().add(menuItem);
            menuItem.setOnAction(evt -> toonContainers(sorteerSleutel));
            position++;
        }
        menuBar.getMenus().add(sorteerMenu);
        overzichtMenuItem.setOnAction(evt -> show());
        toevoegMenuItem.setOnAction(evt -> voegContainerToe());
        exitMenuItem.setOnAction(evt -> Platform.exit());
        return menuBar;
    }

    public void show() {
        sorteerMenu.setDisable(false);
        this.setCenter(overzichtsPane);
        toonContainers(gekozenSortering);
    }

    private void voegContainerToe() {
        sorteerMenu.setDisable(true);
        // De ToevoegView is met SceneBuilder gemaakt
        // We gebruiken een FXMLLoader om de instantie te maken
        this.setCenter(maakToevoegController());
    }

    private void toonContainers(int sorteerSleutel) {
        gekozenSortering = sorteerSleutel;
        Collection<ContainerDTO> containers = switch (sorteerSleutel) {
            case SERIENUMMER -> dc.geefAlleContainersGesorteerdOpSerienummer();
            case EIGENAAR -> dc.geefAlleContainersGesorteerdOpEigenaar();
            case MASSA -> dc.geefAlleContainersGesorteerdOpMassa();
            case VOLUME -> dc.geefAlleContainersGesorteerdOpVolumeEnEigenaar();
            default -> dc.geefAlleContainers();
        };
        int rij = 1;
        for (ContainerDTO container : containers) {
            TextField txfSerienummer = maakUneditableTextField(Integer.toString(container.serienummer()));
            overzichtsPane.add(txfSerienummer, 0, rij);
            TextField txfEigenaar = maakUneditableTextField(container.eigenaar());
            overzichtsPane.add(txfEigenaar, 1, rij);
            TextField txfMassa = maakUneditableTextField(Integer.toString(container.massa()));
            overzichtsPane.add(txfMassa, 2, rij);
            TextField txfVolume = maakUneditableTextField(Integer.toString(container.volume()));
            overzichtsPane.add(txfVolume, 3, rij);
            rij++;
        }
    }

    private TextField maakUneditableTextField(String tekst) {
        TextField txfField = new TextField(tekst);
        txfField.setEditable(false);
        return txfField;
    }

    private Parent maakToevoegController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ToevoegScherm.fxml"));
            loader.setControllerFactory(type -> {
                try {
                    if (type == ToevoegController.class) {
                        return new ToevoegController(dc, this);
                    }
                    return type.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            return loader.load();
        } catch (IOException ex) {
            throw new IllegalStateException("Kon ToevoegController niet laden", ex);
        }
    }
}