import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class SolarSystem extends Application{

    //Sound Object
    private SoundBank sounds;
    
    //Planets Before Coloring
    private static final Color GREY = Color.web("#6E6E73");
    
    //Before Coloring Booleans
    private boolean mercuryPlaced = false;
    private boolean venusPlaced = false;
    private boolean earthPlaced = false;
    private boolean marsPlaced = false;
    private boolean jupiterPlaced = false;
    private boolean saturnPlaced = false;
    private boolean uranusPlaced = false;
    private boolean neptunePlaced = false;

    @Override
    public void start(Stage primaryStage){
        Pane myPane = new Pane();

        //Sun
        Circle sun = new Circle(400,225,20);
            sun.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/images/sun.png"))));

        //Sun Shadowing
        DropShadow sunGlow = new DropShadow();
            sunGlow.setRadius(30.0);             
            sunGlow.setOffsetX(0.0);             
            sunGlow.setOffsetY(0.0);
            sunGlow.setColor(Color.web("#FF8C00")); 
        sun.setEffect(sunGlow);
            
        //Orbitals
        Ellipse path1 = new Ellipse(400,225, 60, 24);
            path1.setFill(Color.TRANSPARENT);
            path1.setStroke(Color.GRAY);

        Ellipse path2 = new Ellipse(400,225, 100, 40);
            path2.setFill(Color.TRANSPARENT);
            path2.setStroke(Color.GRAY);

        Ellipse path3 = new Ellipse(400,225, 140,56);
            path3.setFill(Color.TRANSPARENT);
            path3.setStroke(Color.GRAY);

        Ellipse path4 = new Ellipse(400,225, 180,72);
            path4.setFill(Color.TRANSPARENT);
            path4.setStroke(Color.GRAY);

        Ellipse path5 = new Ellipse(400,225, 220,88);
            path5.setFill(Color.TRANSPARENT);
            path5.setStroke(Color.GRAY);

        Ellipse path6 = new Ellipse(400,225, 260, 104);
            path6.setFill(Color.TRANSPARENT);
            path6.setStroke(Color.GRAY);

        Ellipse path7 = new Ellipse(400,225, 300, 120);
            path7.setFill(Color.TRANSPARENT);
            path7.setStroke(Color.GRAY);

        Ellipse path8 = new Ellipse(400,225, 340, 136);
            path8.setFill(Color.TRANSPARENT);
            path8.setStroke(Color.GRAY);
            
        //Starry background
        myPane.setStyle("-fx-background-color: #0B0E1A;");
            Random rand = new Random();
        for(int i =0;i<150;i++){
            int x = (int) (rand.nextDouble()*800);
            int y = (int) (rand.nextDouble()*450);
            Circle star = new Circle(x,y,0.5+rand.nextDouble()*1.5);
            star.setFill(Color.WHITE);
            star.setMouseTransparent(true);
            myPane.getChildren().add(star);
        }

        //Planets being gray until their name is picked in the list
            Circle mercury = new Circle(460,225,5,GREY);
                Image mercuryImg = new Image(getClass().getResourceAsStream("/images/mercury.png"));

            Circle venus = new Circle(500,225,8,GREY);
                Image venusImg = new Image(getClass().getResourceAsStream("/images/venus.png"));

            Circle earth = new Circle(540,225,8.5,GREY);
                Image earthImg = new Image(getClass().getResourceAsStream("/images/earth.png"));

            Circle mars = new Circle(580,225,6.5,GREY);
                Image marsImg = new Image(getClass().getResourceAsStream("/images/mars.png"));

            Circle jupiter = new Circle(620,225,16,GREY);
                Image jupiterImg = new Image(getClass().getResourceAsStream("/images/jupiter.png"));

            Circle saturn = new Circle(660,225,14,GREY);
                Image saturnImg = new Image(getClass().getResourceAsStream("/images/saturn.png"));

            Circle uranus = new Circle(700,225,11,GREY);
                Image uranusImg = new Image(getClass().getResourceAsStream("/images/uranus.png"));

            Circle neptune = new Circle(740,225,10,GREY);
                Image neptuneImg = new Image(getClass().getResourceAsStream("/images/neptune.png"));

        //Saturn Ring
        Ellipse saturnRing = new Ellipse(660, 225, 24, 7);
            saturnRing.setFill(Color.TRANSPARENT);
            saturnRing.setStroke(Color.web("#D9C9A0"));
            saturnRing.setStrokeWidth(3);
            saturnRing.setMouseTransparent(true); //To allow animation to go through when ring is clicked
            saturnRing.setVisible(false);
            saturnRing.translateXProperty().bind(saturn.translateXProperty());
            saturnRing.translateYProperty().bind(saturn.translateYProperty());
            
        
        //Moon
        Ellipse moonPath = new Ellipse(540, 225, 22, 9);
            moonPath.setFill(Color.TRANSPARENT);
            moonPath.setStroke(Color.web("#4A5170"));
            moonPath.setStrokeWidth(0.8);

        Circle moon = new Circle(562, 225, 2.5, Color.web("#D3D6DB"));

        Group moonSystem = new Group(moonPath, moon);
            moonSystem.setVisible(false);
            moonSystem.setMouseTransparent(true);
            moonSystem.translateXProperty().bind(earth.translateXProperty());
            moonSystem.translateYProperty().bind(earth.translateYProperty());    
        
        PathTransition moonPt = new PathTransition(Duration.seconds(3), moonPath, moon);
            moonPt.setCycleCount(Animation.INDEFINITE);
            moonPt.setInterpolator(Interpolator.LINEAR);
            moonPt.setRate(-1);
                
        //Visibility for planets
        
        mercury.setVisible(false);
        venus.setVisible(false);
        earth.setVisible(false);
        mars.setVisible(false);
        jupiter.setVisible(false);
        saturn.setVisible(false);
        uranus.setVisible(false);
        neptune.setVisible(false);
        
        
        //Orbits
        
        PathTransition pt1 = new PathTransition(Duration.seconds(6), path1, mercury);
            pt1.setCycleCount(Animation.INDEFINITE);
            pt1.setInterpolator(Interpolator.LINEAR);
            pt1.setRate(-1);

        PathTransition pt2 = new PathTransition(Duration.seconds(9), path2, venus);
            pt2.setCycleCount(Animation.INDEFINITE);
            pt2.setInterpolator(Interpolator.LINEAR);
            pt2.setRate(-1);
            
        PathTransition pt3 = new PathTransition(Duration.seconds(12), path3, earth);
            pt3.setCycleCount(Animation.INDEFINITE);
            pt3.setInterpolator(Interpolator.LINEAR);
            pt3.setRate(-1);
            
        PathTransition pt4 = new PathTransition(Duration.seconds(16), path4, mars);
            pt4.setCycleCount(Animation.INDEFINITE);
            pt4.setInterpolator(Interpolator.LINEAR);
            pt4.setRate(-1);
        
        PathTransition pt5 = new PathTransition(Duration.seconds(24), path5, jupiter);
            pt5.setCycleCount(Animation.INDEFINITE);
            pt5.setInterpolator(Interpolator.LINEAR);
            pt5.setRate(-1);
         
        PathTransition pt6 = new PathTransition(Duration.seconds(32), path6, saturn);
            pt6.setCycleCount(Animation.INDEFINITE);
            pt6.setInterpolator(Interpolator.LINEAR);
            pt6.setRate(-1);
            
        PathTransition pt7 = new PathTransition(Duration.seconds(42), path7, uranus);
            pt7.setCycleCount(Animation.INDEFINITE);
            pt7.setInterpolator(Interpolator.LINEAR);
            pt7.setRate(-1);
            
        PathTransition pt8 = new PathTransition(Duration.seconds(52), path8, neptune);
            pt8.setCycleCount(Animation.INDEFINITE);
            pt8.setInterpolator(Interpolator.LINEAR);
            pt8.setRate(-1);

        //Click to Orbit
        
        mercury.setOnMouseClicked(e -> pt1.play());
        
        venus.setOnMouseClicked(e -> pt2.play());
        
        earth.setOnMouseClicked(e -> {
            pt3.play();
            moonPt.play();
        });
        
        mars.setOnMouseClicked(e -> pt4.play());
        
        jupiter.setOnMouseClicked(e -> pt5.play());
        
        saturn.setOnMouseClicked(e -> pt6.play());
        
        uranus.setOnMouseClicked(e -> pt7.play());
        
        neptune.setOnMouseClicked(e -> pt8.play());
                
        //Sound Loading
                
        sounds = new SoundBank(new String[]{"mercury","venus","earth","mars","jupiter","saturn","uranus","neptune"});
                
        //Bottom Grid
        
        GridPane grid = new GridPane();
        grid.setHgap(6);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));

        //Mercury
        
        ImageView mercuryView = new ImageView(mercuryImg);
            mercuryView.setFitWidth(38);
            mercuryView.setFitHeight(38);
            mercuryView.setPreserveRatio(true);

        Button mercuryBtn = new Button("Mercury", mercuryView);
            mercuryBtn.setContentDisplay(ContentDisplay.TOP);
            mercuryBtn.setPrefWidth(86);
            mercuryBtn.setOnAction(e -> {
                mercury.setVisible(true);
                mercuryPlaced = true;
                sounds.play("mercury");
            });
        
        //Venus
        
        ImageView venusView = new ImageView(venusImg);
            venusView.setFitWidth(38);
            venusView.setFitHeight(38);
            venusView.setPreserveRatio(true);
            
        Button venusBtn = new Button("Venus",venusView);
            venusBtn.setContentDisplay(ContentDisplay.TOP);
            venusBtn.setPrefWidth(86);
            venusBtn.setOnAction(e->{
                venus.setVisible(true);
                venusPlaced = true;
                sounds.play("venus");
            });

        //Earth
            
        ImageView earthView = new ImageView(earthImg);
            earthView.setFitWidth(38);
            earthView.setFitHeight(38);
            earthView.setPreserveRatio(true);
           
        Button earthBtn = new Button("Earth",earthView);
            earthBtn.setContentDisplay(ContentDisplay.TOP);
            earthBtn.setPrefWidth(86);
            earthBtn.setOnAction(e->{
                moonSystem.setVisible(true);
                earth.setVisible(true);
                earthPlaced = true;
                sounds.play("earth");
            });
        
        //Mars
            
        ImageView marsView = new ImageView(marsImg);
            marsView.setFitWidth(38);
            marsView.setFitHeight(38);
            marsView.setPreserveRatio(true);
           
        Button marsBtn = new Button("Mars",marsView);
            marsBtn.setContentDisplay(ContentDisplay.TOP);
            marsBtn.setPrefWidth(86);
            marsBtn.setOnAction(e->{
                mars.setVisible(true);
                marsPlaced = true;
                sounds.play("mars");
            });
        
        //Jupiter
            
        ImageView jupiterView = new ImageView(jupiterImg);
            jupiterView.setFitWidth(38);
            jupiterView.setFitHeight(38);
            jupiterView.setPreserveRatio(true);
           
        Button jupiterBtn = new Button("Jupiter",jupiterView);
            jupiterBtn.setContentDisplay(ContentDisplay.TOP);
            jupiterBtn.setPrefWidth(86);
            jupiterBtn.setOnAction(e->{
                jupiter.setVisible(true);
                jupiterPlaced = true;
                sounds.play("jupiter");
            });
        
        //Saturn
        
        ImageView saturnView = new ImageView(saturnImg);
            saturnView.setFitWidth(38);
            saturnView.setFitHeight(38);
            saturnView.setPreserveRatio(true);
           
        Button saturnBtn = new Button("Saturn",saturnView);
            saturnBtn.setContentDisplay(ContentDisplay.TOP);
            saturnBtn.setPrefWidth(86);
            saturnBtn.setOnAction(e->{
                saturnRing.setVisible(true);
                saturn.setVisible(true);
                saturnPlaced = true;
                sounds.play("saturn");
            });
        //Uranus
        
        ImageView uranusView = new ImageView(uranusImg);
            uranusView.setFitWidth(38);
            uranusView.setFitHeight(38);
            uranusView.setPreserveRatio(true);
           
        Button uranusBtn = new Button("Uranus",uranusView);
            uranusBtn.setContentDisplay(ContentDisplay.TOP);
            uranusBtn.setPrefWidth(86);
            uranusBtn.setOnAction(e->{
                uranus.setVisible(true);
                uranusPlaced = true;
                sounds.play("uranus");
            });
            
        //Neptune
        
        ImageView neptuneView = new ImageView(neptuneImg);
            neptuneView.setFitWidth(38);
            neptuneView.setFitHeight(38);
            neptuneView.setPreserveRatio(true);
           
        Button neptuneBtn = new Button("Neptune",neptuneView);
            neptuneBtn.setContentDisplay(ContentDisplay.TOP);
            neptuneBtn.setPrefWidth(86);
            neptuneBtn.setOnAction(e->{
                neptune.setVisible(true);
                neptunePlaced = true;
                sounds.play("neptune");
            });
        
        grid.add(mercuryBtn, 0, 0);
        grid.add(venusBtn,   1, 0);
        grid.add(earthBtn,   2, 0);
        grid.add(marsBtn,    3, 0);
        grid.add(jupiterBtn, 4, 0);
        grid.add(saturnBtn,  5, 0);
        grid.add(uranusBtn,  6, 0);
        grid.add(neptuneBtn, 7, 0);
        
        //CONTEXT MENU
        
        MenuItem startAll = new MenuItem("Start all animations");
            startAll.setOnAction(e -> {
                if (mercuryPlaced) pt1.play();
                if (venusPlaced) pt2.play();
                if (earthPlaced) {
                    pt3.play();
                    moonPt.play();
                }
                if (marsPlaced) pt4.play();
                if (jupiterPlaced) pt5.play();
                if (saturnPlaced) pt6.play();
                if (uranusPlaced) pt7.play();
                if (neptunePlaced) pt8.play();
            });
        
        MenuItem stopAll = new MenuItem("Stop all animations");
            stopAll.setOnAction(e -> {
                pt1.pause();
                pt2.pause();
                pt3.pause();
                pt4.pause();
                pt5.pause();
                pt6.pause();
                pt7.pause();
                pt8.pause();
                moonPt.pause();
            });
        
        Menu controlAnimation = new Menu("Control Animation");
            controlAnimation.getItems().add(startAll);
            controlAnimation.getItems().add(stopAll);
        
        ContextMenu contextMenu = new ContextMenu(controlAnimation);
        
        myPane.setOnContextMenuRequested(e ->
                contextMenu.show(myPane, e.getScreenX(), e.getScreenY()));
        
        
        //Planet List
        
        ListView<String> planetList = new ListView<>();
        planetList.getItems().addAll("Mercury","Venus","Earth","Mars",
                "Jupiter","Saturn","Uranus","Neptune");
        planetList.setPrefWidth(150);
        
        //Planet List Behavior
        
        planetList.setOnMouseClicked(e -> {
            String picked = planetList.getSelectionModel().getSelectedItem();
            if (picked == null) {
                return;
            }
            
            switch (picked) {
                
                case "Mercury":
                    if (mercuryPlaced) {
                        mercury.setFill(new ImagePattern(mercuryImg));
                    } else {
                        showNotPlacedAlert("Mercury", mercuryImg);
                    }   
                    break;
                    
                case "Venus":
                    if (venusPlaced) {
                        venus.setFill(new ImagePattern(venusImg));
                    } else {
                        showNotPlacedAlert("Venus", venusImg);
                    }   
                    break;
                    
                case "Earth":
                    if (earthPlaced) {
                        earth.setFill(new ImagePattern(earthImg));
                    } else {
                        showNotPlacedAlert("Earth", earthImg);
                    }   
                    break;
                    
                case "Mars":
                    if (marsPlaced) {
                        mars.setFill(new ImagePattern(marsImg));
                    } else {
                        showNotPlacedAlert("Mars", marsImg);
                    }   
                    break;
                    
                case "Jupiter":
                    if (jupiterPlaced) {
                        jupiter.setFill(new ImagePattern(jupiterImg));
                    } else {
                        showNotPlacedAlert("Jupiter", jupiterImg);
                    }   
                    break;
                    
                case "Saturn":
                    if (saturnPlaced) {
                        saturn.setFill(new ImagePattern(saturnImg));
                    } else {
                        showNotPlacedAlert("Saturn", saturnImg);
                    }   
                    break;
                    
                case "Uranus":
                    if (uranusPlaced) {
                        uranus.setFill(new ImagePattern(uranusImg));
                    } else {
                        showNotPlacedAlert("Uranus", uranusImg);
                    }   
                    break;
                    
                case "Neptune":
                    if (neptunePlaced) {
                        neptune.setFill(new ImagePattern(neptuneImg));
                    } else {
                        showNotPlacedAlert("Neptune", neptuneImg);
                    }   
                    break;
                    
                default:
                    break;
            }
            
            Platform.runLater(() -> planetList.getSelectionModel().clearSelection());
        });
        
        //Planet List Behavior end
        
        Label listLabel = new Label("PLANETS");
            listLabel.setTextFill(Color.WHITE);
        VBox leftPanel = new VBox(6, listLabel, planetList);
            leftPanel.setPadding(new Insets(10));
        
        myPane.getChildren().addAll(path1,path2,path3,path4,path5,path6,path7,path8,
                sun,mercury,venus,moonSystem,earth,mars,jupiter,saturnRing,saturn,uranus,neptune);

        myPane.setMinSize(800,450);
        myPane.setMaxSize(800,450);

        StackPane spaceHolder = new StackPane(myPane);
        spaceHolder.setStyle("-fx-background-color: #0B0E1A;");

        BorderPane root = new BorderPane();
        
        root.setStyle("-fx-background-color: #0B0E1A;");
        root.setCenter(spaceHolder);
        root.setBottom(grid);
        root.setLeft(leftPanel);
        
        
            Button detailsBtn = new Button("Planet Details");
                detailsBtn.setOnAction(e -> new DetailsDialog().showAndWait());
            HBox topBar = new HBox(detailsBtn);
                topBar.setPadding(new Insets(10));
        
        root.setTop(topBar);

        Scene myScene = new Scene(root,990,620);
        primaryStage.setTitle("SolarSystem");
        primaryStage.setScene(myScene);
        primaryStage.show();
    }
    
    private void showNotPlacedAlert(String name, Image img) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Planet Missing");
        alert.setHeaderText(name + " is not on its orbit yet");
        alert.setContentText("Click the " + name + " image button in the bottom grid to place it first.");

        ImageView icon = new ImageView(img);
        icon.setFitWidth(64);
        icon.setFitHeight(64);
        icon.setPreserveRatio(true);
        alert.setGraphic(icon);

        alert.showAndWait();
    }
    
    @Override
    public void stop() {
        if (sounds != null) {
            sounds.disposeAll();
        }
        Platform.exit();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}