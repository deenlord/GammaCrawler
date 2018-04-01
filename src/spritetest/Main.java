package spritetest;

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
		Group root = new Group();
		// Create the array
		int[][] ar = setupArray();
		// import images to use as tiles
		Image wall = new Image("https://www.shareicon.net/data/256x256/2016/07/29/803375_brick_512x512.png");
		Image floor = new Image("https://cdn4.iconfinder.com/data/icons/design-and-development-bold-line-1/48/40-128.png");
		// Control var for size of tile
		double tileSize = 64.0;
		double x;
		double y;
		Canvas cv = new Canvas(600, 600);
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
		int[][] array= { 
				{1,1,1,1,1,1,1},
				{1,0,0,0,0,0,1},
				{1,1,1,1,1,1,1}
				
		};
		
		// #wolfiewaffle replace my array with yours.
		return array;
	}
	
	
}
