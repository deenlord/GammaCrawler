package spritetest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
		// Create the array
		int[][] ar = setupArray();
		// import images to use as tiles
		Image wall = new Image("https://www.shareicon.net/data/256x256/2016/07/29/803375_brick_512x512.png");
		Image floor = new Image("https://cdn4.iconfinder.com/data/icons/design-and-development-bold-line-1/48/40-128.png");
		// Control var for size of tile
		Double tileSize = 64.0;
		Double gapSize = 0.0;
		
		// Create a tilePane
		TilePane tile = new TilePane();
	    tile.setHgap(gapSize);
	    tile.setVgap(gapSize);
	    // Set the number of columns #important
	    tile.setPrefColumns(7); // need to get the number of columns in the array our map is based on.
	    
	    // iterate through the array and convert Image to appropriate ImageView
	    // and add it, as a tile, to the TilePane
	    for (int i=0; i < (ar.length); i++ ) {
		    for (int j=0; j<ar[i].length; j++) {
		        // for each j
		        if (ar[i][j] == 1) {
		        	iv = new ImageView(wall);
		        	iv.setFitWidth(tileSize);
		        	iv.setPreserveRatio(true);
		        	
		        	// add it as a Tile
		        	tile.getChildren().add(iv);
		        	
		        }
		        else  {
		        	iv2 = new ImageView(floor);
		        	iv2.setFitWidth(tileSize);
		        	iv2.setPreserveRatio(true);
		        	
		        	tile.getChildren().add(iv2);
		        }
		    }
		}
	    
	    //set the scene, stage, and launch app
	    Scene sc = new Scene(tile);
	    stage.setScene(sc);
	    stage.show();
	    
	}
	
	
	public int[][] setupArray() {
		int[][] array= { 
				{1,1,1,1,1,1,1},
				{1,0,0,0,0,0,1},
				{1,1,1,1,1,1,1}
				
		};
		return array;
	}
	
	
}
