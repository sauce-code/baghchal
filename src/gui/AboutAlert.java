package gui;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

/**
 * A about info alert box.
 * 
 * @author Torben Kr&uuml;ger
 *
 */
public class AboutAlert extends Alert {

	/**
	 * Creates anew About Alert.
	 * 
	 * @param icon
	 *            the icon, which shall be used
	 */
	public AboutAlert(Image icon) {
		super(AlertType.INFORMATION);
		ImageView image = new ImageView(icon);
		image.setFitWidth(100.0);
		image.setFitHeight(100.0);
		setGraphic(image);
		initStyle(StageStyle.UTILITY);
		setTitle("About");
		setHeaderText(MetaInfo.TITLE + " " + MetaInfo.VERSION);
		setContentText("Written by " + MetaInfo.AUTHOR + '\n' + "Logo by " + MetaInfo.LOGO_ARTIST
				+ '\n' + "Email: " + MetaInfo.EMAIL + '\n' + "Repository: " + MetaInfo.REPOSITORY
				+ '\n' + "Arrow Icons by Freepik from www.flaticon.com is licensed by CC 3.0");
	}

}
