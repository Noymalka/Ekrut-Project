package ekrut.client.gui;

import java.io.IOException;

import ekrut.entity.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemInCartController extends HBox {

	@FXML
	private HBox itemInCartHBox;

	@FXML
	private Button deleteItemBtn;

	@FXML
	private Label itemNameLbl;

	@FXML
	private Label itemPriceLbl;

	@FXML
	private Label noDigitError;
	
	@FXML
	private Button minusBtn;

	@FXML
	private Button plusBtn;

	@FXML
	private TextField quantityTxt;

	@FXML
	private Button updateBtn;
	

	private final static String DOTS_AFTER_ITEM_NAME = "....................................."
													 + ".....................................";
	
	// order manager
	private Integer cartQuantity = 0;

	public ItemInCartController(Item item, ItemController itemController) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemInCart.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
//		itemNameLbl.setText(item.getItemName() + DOTS_AFTER_ITEM_NAME);
//		itemPriceLbl.setText(Float.toString(item.getItemPrice()));	
//		quantityTxt.setText(Integer.toString(itemController.getCartQuantity()));
	}


	@FXML
	void deleteItem(ActionEvent event) {
		((VBox) getParent()).getChildren().remove(this);
	}

	@FXML
	void minusItem(ActionEvent event) {
		Integer textQuantity = null;
		try {
			textQuantity = Integer.parseInt(quantityTxt.getText());
			if (textQuantity > 0) {
				quantityTxt.setText(Integer.toString(textQuantity - 1));
				textQuantity--;
				if (cartQuantity == textQuantity)
					setQuantityTxtStyle("#FFFFFF");
				else
					setQuantityTxtStyle("#FFB4AB");
			}
		} catch (NumberFormatException e) {
		} finally {
			if (textQuantity == null)
				noDigitError.setVisible(true);
			else
				noDigitError.setVisible(false);
		}
	}

	@FXML
	void plusItem(ActionEvent event) {
		Integer textQuantity = null;
		try {
			textQuantity = Integer.parseInt(quantityTxt.getText());
			quantityTxt.setText(Integer.toString(textQuantity + 1));
			textQuantity++;
			if (cartQuantity == textQuantity)
				setQuantityTxtStyle("#FFFFFF");
			else
				setQuantityTxtStyle("#FFB4AB");
		} catch (NumberFormatException e) {
			noDigitError.setVisible(true);
		}finally {
			if (textQuantity == null)
				noDigitError.setVisible(true);
			else
				noDigitError.setVisible(false);
		}
	}

	private void setQuantityTxtStyle(String color) {
		quantityTxt.setStyle("-fx-border-color: #000000; -fx-background-radius: 20; " +
							"-fx-border-radius: 20; -fx-background-color: " + color + ";");
	}
	
	@FXML
	void updateItemQuantity(ActionEvent event) {
		Integer textQuantity = null;
		try {
			textQuantity = Integer.parseInt(quantityTxt.getText());
			cartQuantity = textQuantity;
			setQuantityTxtStyle("#FFFFFF");
		} catch (NumberFormatException e) { 
		} finally {
			if (textQuantity == null) 
				noDigitError.setVisible(true);
			else
				noDigitError.setVisible(false);
		}
	}
	

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void cancelOrder(ActionEvent event) {
    	
    }

}
