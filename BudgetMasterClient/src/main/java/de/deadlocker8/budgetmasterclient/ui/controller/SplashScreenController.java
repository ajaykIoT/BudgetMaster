package de.deadlocker8.budgetmasterclient.ui.controller;

import java.io.IOException;

import de.deadlocker8.budgetmaster.logic.Settings;
import de.deadlocker8.budgetmaster.logic.utils.Colors;
import de.deadlocker8.budgetmaster.logic.utils.FileHelper;
import de.deadlocker8.budgetmaster.logic.utils.Helpers;
import de.deadlocker8.budgetmaster.logic.utils.Strings;
import de.deadlocker8.budgetmasterclient.ui.ShutdownHandler;
import de.deadlocker8.budgetmasterclient.ui.Styleable;
import de.deadlocker8.budgetmasterclient.ui.customAlert.CustomAlertController;
import fontAwesome.FontIcon;
import fontAwesome.FontIconType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logger.Logger;
import tools.AlertGenerator;
import tools.ConvertTo;
import tools.HashUtils;
import tools.Localization;

public class SplashScreenController extends BaseController implements Styleable
{
	@FXML private ImageView imageViewLogo;
	@FXML private Label labelVersion;
	@FXML private PasswordField textFieldPassword;
	@FXML private Button buttonLogin;	

	private Stage parentStage;
	private Image icon;
	private Settings settings;
	private boolean isFirstStart;
	private boolean isStartingAfterUpdate;
	private ShutdownHandler shutdownHandler;
	
	public SplashScreenController(Stage parentStage, Image icon, boolean isStartingAfterUpdate, ShutdownHandler shutdownHandler)
	{
		this.parentStage = parentStage;
		this.icon = icon;
		this.isStartingAfterUpdate = isStartingAfterUpdate;
		this.shutdownHandler = shutdownHandler;
		load("/de/deadlocker8/budgetmaster/ui/fxml/SplashScreen.fxml", Localization.getBundle());
		getStage().show();
	}
	
	@Override
	public void initStage(Stage stage)
	{	
		stage.initOwner(parentStage);
		stage.setWidth(450);
		stage.setHeight(250);
		stage.setResizable(false);			
		stage.getIcons().add(icon);
		stage.setTitle(Localization.getString(Strings.APP_NAME));
	}

	@Override
	public void init()
	{
		imageViewLogo.setImage(icon);
		
		labelVersion.setText("v" + Localization.getString(Strings.VERSION_NAME));
	
		applyStyle();
		
		textFieldPassword.setOnKeyReleased((event)->{			
			if(event.getCode() == KeyCode.ENTER)
			{
				event.consume();
				login();
			}
		});
		
		if(isStartingAfterUpdate)
		{
			Platform.runLater(() -> {
				AlertGenerator.showAlert(AlertType.INFORMATION, 
										Localization.getString(Strings.INFO_TITLE_START_AFTER_UPDATE), 
										Localization.getString(Strings.INFO_HEADER_TEXT_START_AFTER_UPDATE, Localization.getString(Strings.VERSION_NAME)),
										Localization.getString(Strings.INFO_TEXT_START_AFTER_UPDATE),
										icon, 
										getStage(),
										null, 
										false);
			});
		}
		
		settings = FileHelper.loadSettings();		
		
		if(settings == null)
		{	
			settings = new Settings();
			//first start of budgetmaster
			Platform.runLater(() -> {
				AlertGenerator.showAlert(AlertType.INFORMATION, 
										Localization.getString(Strings.INFO_TITLE_WELCOME), 
										Localization.getString(Strings.INFO_HEADER_TEXT_WELCOME),
										Localization.getString(Strings.INFO_TEXT_WELCOME_FIRST_START),
										icon, 
										getStage(), 
										null, 
										false);
			});
			isFirstStart = true;
		}
		else
		{
			if(settings.getClientSecret() == null)
			{
				//compatibility (settings exists but from older version without clientSecret)
				Platform.runLater(() -> {
					AlertGenerator.showAlert(AlertType.INFORMATION,
											Localization.getString(Strings.INFO_TITLE_WELCOME), 
											Localization.getString(Strings.INFO_HEADER_TEXT_WELCOME),
											Localization.getString(Strings.INFO_TEXT_WELCOME_COMPATIBILITY),
											icon,
											getStage(),
											null,
											false);
				});
				isFirstStart = true;
			}
			else
			{
				isFirstStart = false;
			}
		}
	}
	
	public void login()
	{
		String password = textFieldPassword.getText().trim();
		if(password == null || password.isEmpty())
		{
			new CustomAlertController(getStage(), this, AlertType.WARNING, Localization.getString(Strings.TITLE_WARNING), Localization.getString(Strings.WARNING_EMPTY_PASSWORD));
			return;		
		}		
	
		if(isFirstStart)
		{
			//save to settings
			settings.setClientSecret(HashUtils.hash(password, Helpers.SALT));
			try
			{
				FileHelper.saveSettings(settings);
				
				getStage().close();
				openBudgetMaster();	
			}
			catch(IOException e)
			{
				Logger.error(e);
				new CustomAlertController(getStage(), this, AlertType.WARNING, Localization.getString(Strings.TITLE_ERROR), Localization.getString(Strings.ERROR_PASSWORD_SAVE));
				return;				
			}
		}
		else
		{			
			//check password
			if(!HashUtils.hash(password, Helpers.SALT).equals(settings.getClientSecret()))
			{
				new CustomAlertController(getStage(), this, AlertType.WARNING, Localization.getString(Strings.TITLE_WARNING), Localization.getString(Strings.WARNING_WRONG_PASSWORD));
				return;
			}
			
			getStage().close();
			openBudgetMaster();	
		}
	}
	
	private void openBudgetMaster()
	{
		new Controller(settings, shutdownHandler);
	}
	
	public Image getIcon()
	{
		return icon;
	}

	@Override
	public void applyStyle()
	{
		buttonLogin.setGraphic(new FontIcon(FontIconType.SIGN_IN, 18, Color.WHITE));
		buttonLogin.setStyle("-fx-background-color: " + ConvertTo.toRGBHexWithoutOpacity(Colors.BACKGROUND_BUTTON_BLUE) + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16;");
		buttonLogin.setPadding(new Insets(3, 7, 3, 7));		
	}
}