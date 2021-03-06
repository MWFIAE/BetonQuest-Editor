/**
 * BetonQuest Editor - advanced quest creating tool for BetonQuest
 * Copyright (C) 2015  Jakub "Co0sh" Sapalski
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.betonquest.editor.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.zip.ZipFile;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import pl.betoncraft.betonquest.editor.BetonQuestEditor;
import pl.betoncraft.betonquest.editor.model.QuestPackage;

/**
 * Controls main menu.
 *
 * @author Jakub Sapalski
 */
public class MainMenuController {

	@FXML private void newPackage() {
		// TODO create new package
	}

	@FXML private void load() {
		BetonQuestEditor instance = BetonQuestEditor.getInstance();
		FileChooser fc = new FileChooser();
		fc.setTitle("Select package...");
		ExtensionFilter filter = new ExtensionFilter("ZIP Files", "*.zip");
		fc.getExtensionFilters().add(filter);
		fc.setSelectedExtensionFilter(filter);
		File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");
		if (desktop != null) fc.setInitialDirectory(desktop);
		File selectedFile = fc.showOpenDialog(instance.getPrimaryStage());
		if (selectedFile != null) {
			try {
				QuestPackage pack = QuestPackage.loadFromZip(new ZipFile(selectedFile));
				instance.getPackages().put(pack.getName().get(), pack);
				instance.display(pack);
			} catch (Exception e) {
				BetonQuestEditor.showStackTrace(e);
			}
		}
	}
	
	@FXML private void save() {
		BetonQuestEditor instance = BetonQuestEditor.getInstance();
		FileChooser fc = new FileChooser();
		fc.setTitle("Select package...");
		ExtensionFilter filter = new ExtensionFilter("ZIP Files", "*.zip");
		fc.getExtensionFilters().add(filter);
		fc.setSelectedExtensionFilter(filter);
		File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");
		if (desktop != null) fc.setInitialDirectory(desktop);
		File selectedFile = fc.showSaveDialog(instance.getPrimaryStage());
		if (selectedFile != null) {
			try {
				QuestPackage pack = BetonQuestEditor.getInstance().getDisplayedPackage();
				if (pack == null) {
					return; // TODO show error, no package loaded
				}
				pack.saveToZip(selectedFile);
			} catch (Exception e) {
				BetonQuestEditor.showStackTrace(e);
			}
		}
	}
	
	@FXML private void export() {
		// TODO export package to the server
	}
	
	@FXML private void quit() {
		System.exit(0);
	}
	
	@FXML private void about() {
		// TODO open about window
	}
	
	@FXML private void docs() {
		try {
			Desktop.getDesktop().browse(new URI("http://betonquest.betoncraft.pl/BetonQuestDocumentation.pdf"));
		} catch (IOException | URISyntaxException e) {
			BetonQuestEditor.showStackTrace(e);
		}
	}
}
