import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DetailsDialog extends Dialog<Void> {

    public DetailsDialog() {
        TableView<PlanetData> table = new TableView<>();

        table.setItems(FXCollections.observableArrayList(
            
            //Uses Other class in package to save planets
            
            new PlanetData("Mercury", "3.30 x 10^23", "57.9", "47.4"),
            new PlanetData("Venus",   "4.87 x 10^24", "108.2", "35.0"),
            new PlanetData("Earth",   "5.97 x 10^24", "149.6", "29.8"),
            new PlanetData("Mars",    "6.42 x 10^23", "228.0", "24.1"),
            new PlanetData("Jupiter", "1.898 x 10^27", "778.5", "13.1"),
            new PlanetData("Saturn",  "5.68 x 10^26", "1432.0", "9.7"),
            new PlanetData("Uranus",  "8.68 x 10^25", "2867.0", "6.8"),
            new PlanetData("Neptune", "1.02 x 10^26", "4515.0", "5.4")
        ));

        //Name Column Creation
        
        TableColumn<PlanetData, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
            nameCol.setPrefWidth(110);

        //Mass Column Creation
            
        TableColumn<PlanetData, String> massCol = new TableColumn<>("Mass (kg)");
            massCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getMass()));
            massCol.setPrefWidth(150);

        //Distance from Sun Column Creation
            
        TableColumn<PlanetData, String> distCol = new TableColumn<>("Distance from Sun (10^6 km)");
            distCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDistance()));
            distCol.setPrefWidth(200);

        //Orbital Speed Column Creation
         
        TableColumn<PlanetData, String> speedCol = new TableColumn<>("Orbital Speed (km/s)");
            speedCol.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getSpeed()));
            speedCol.setPrefWidth(160);

        //Add all the Columns
            
        table.getColumns().add(nameCol);
        table.getColumns().add(massCol);
        table.getColumns().add(distCol);
        table.getColumns().add(speedCol);
        table.setPrefSize(640, 300);

        setTitle("Planet Details");
        setHeaderText("Physical and orbital data for the eight planets");
        getDialogPane().setContent(table);
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        setResizable(true);
    }
}