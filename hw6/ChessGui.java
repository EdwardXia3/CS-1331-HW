import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Creates a GUI table holding the metadata of chessgames.
 *
 * @author exia3
 * @version 1.0
 */
public class ChessGui extends Application {
    @Override public void start(Stage stage) {
        ChessDb chessDb = new ChessDb();
        ObservableList<ChessGame> games =
            FXCollections.observableArrayList(chessDb.getGames());
        TableView<ChessGame> table = createTable(games);

        Button viewButton = new Button("View Game");
        viewButton.setOnAction(e -> {
                ChessGame game = table.getSelectionModel().getSelectedItem();
                viewDialog(game);
            });
        viewButton.disableProperty()
            .bind(Bindings.isNull(
                table.getSelectionModel().selectedItemProperty()));

        Button dismissButton = new Button("Dismiss");
        dismissButton.setOnAction(e -> Platform.exit());

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(viewButton, dismissButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, buttonBox);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("ChessGUI");
        stage.setWidth(1050);
        stage.setHeight(450);
        stage.show();
    }

    private TableView<ChessGame> createTable(ObservableList<ChessGame> games) {
        TableView<ChessGame> table = new TableView<ChessGame>();
        table.setItems(games);

        TableColumn<ChessGame, String> eventCol =
            new TableColumn<ChessGame, String>("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory("Event"));

        TableColumn<ChessGame, String> siteCol =
            new TableColumn<ChessGame, String>("Site");
        siteCol.setCellValueFactory(new PropertyValueFactory("Site"));

        TableColumn<ChessGame, String> dateCol =
            new TableColumn<ChessGame, String>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("Date"));

        TableColumn<ChessGame, String> whiteCol =
            new TableColumn<ChessGame, String>("White");
        whiteCol.setCellValueFactory(new PropertyValueFactory("White"));

        TableColumn<ChessGame, String> blackCol =
            new TableColumn<ChessGame, String>("Black");
        blackCol.setCellValueFactory(new PropertyValueFactory("Black"));

        TableColumn<ChessGame, String> resultCol =
            new TableColumn<ChessGame, String>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory("Result"));

        TableColumn<ChessGame, String> openingCol =
            new TableColumn<ChessGame, String>("Opening");
        openingCol.setCellValueFactory(new PropertyValueFactory("Opening"));

        table.getColumns().setAll(eventCol, siteCol, dateCol, whiteCol,
            blackCol, resultCol, openingCol);
        return table;
    }

    private void viewDialog(ChessGame game) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(game.getEvent());
        alert.setHeaderText(String.format("Event: %s%nSite: %s%nDate: %s%n"
            + "White: %s%nBlack: %s%nResult: %s%nOpening:%s",
            game.getEvent(), game.getSite(), game.getDate(), game.getWhite(),
            game.getBlack(), game.getResult(), "a"));
        String moves = "";
        try {
            for (int i = 1; i < 5000; i++) {
                moves = moves + i + ". " + game.getMove(i) + ' ';
            }
        } catch (IndexOutOfBoundsException e) {
            alert.setContentText(moves);
        }
        alert.showAndWait();
    }
}