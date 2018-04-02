package spritetest;

import com.gammacrawler.generator.Board;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler<ActionEvent>{
	ImageView iv;
	ImageView iv2;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("GammaCrawler!");
		Group root = new Group();
		// Create the array
		int[][] ar = setupArray();
		// import images to use as tiles
		Image wall = new Image("file:src/spritetest/wall.png");
		Image floor = new Image("file:src/spritetest/floor.png");
		// Control var for size of tile
		double tileSize = 32.0;
		double x;
		double y;
		Canvas cv = new Canvas(960,720);
		GraphicsContext gc = cv.getGraphicsContext2D();
		
	    // iterate through the array and convert Image to appropriate ImageView
	    // and add it, as a tile, to the TilePane
	    for (int i=0; i < (ar.length); i++ ) {
		    for (int j=0; j<ar[i].length; j++) {
		        // for each j
		    	y = (i + 1) * tileSize;
	        	x = (j + 1) * tileSize;
	        	
		        if (ar[i][j] == 1) {
		        	gc.drawImage(wall, x, y, tileSize, tileSize);		        	
		        }
		        else  {
		        	gc.drawImage(floor, x, y, tileSize, tileSize);
		        }
		    }
		}
	    
	    //set the scene, stage, and launch app
	    root.getChildren().add(cv);
	    Scene sc = new Scene(root);
	    stage.setScene(sc);
	    stage.show();
	    
	}
	
	
	public int[][] setupArray() {
		
		Board board = new Board(51, 51);
		board.addMaze();
		
		int[][] array= board.getArray();
		
		// #wolfiewaffle replace my array with yours.
		return array;
	}
	
	
}
