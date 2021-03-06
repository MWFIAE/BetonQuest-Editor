/**
 * BetonQuest Editor - advanced quest creating tool for BetonQuest
 * Copyright (C) 2016  Jakub "Co0sh" Sapalski
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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.betoncraft.betonquest.editor.model.Conversation;
import pl.betoncraft.betonquest.editor.model.Event;
import pl.betoncraft.betonquest.editor.model.GlobalLocation;
import pl.betoncraft.betonquest.editor.model.GlobalVariable;
import pl.betoncraft.betonquest.editor.model.MainPageLine;
import pl.betoncraft.betonquest.editor.model.NpcBinding;
import pl.betoncraft.betonquest.editor.model.QuestCanceler;
import pl.betoncraft.betonquest.editor.model.StaticEvent;

/**
 * Controls "Main" tab.
 *
 * @author Jakub Sapalski
 */
public class MainController {
	
	private static MainController instance;
	
	@FXML private TableView<NpcBinding> npcTable;
	@FXML private TableColumn<NpcBinding, String> npcName;
	@FXML private TableColumn<NpcBinding, Conversation> conversation;
	@FXML private TableView<GlobalVariable> globVarTable;
	@FXML private TableColumn<GlobalVariable, String> varName;
	@FXML private TableColumn<GlobalVariable, String> varValue;
	@FXML private TableView<StaticEvent> staticEventsTable;
	@FXML private TableColumn<StaticEvent, String> time;
	@FXML private TableColumn<StaticEvent, Event> event;
	@FXML private ListView<GlobalLocation> globLocList;
	@FXML private ListView<QuestCanceler> cancelList;
	@FXML private ListView<MainPageLine> mainPageList;
	
	public MainController() {
		instance = this;
	}

	public static void setNpcBindings(ObservableList<NpcBinding> bindings) {
		instance.npcTable.setItems(null);
		instance.npcTable.setItems(bindings);
		instance.npcName.setCellValueFactory(cell -> cell.getValue().getId());
		instance.conversation.setCellValueFactory(cell -> cell.getValue().getConversation());
	}
	
	public static void setGlobVariables(ObservableList<GlobalVariable> globalVariables) {
		instance.globVarTable.setItems(null);
		instance.globVarTable.setItems(globalVariables);
		instance.varName.setCellValueFactory(cell -> cell.getValue().getId());
		instance.varValue.setCellValueFactory(cell -> cell.getValue().getInstruction());
	}
	
	public static void setStaticEvents(ObservableList<StaticEvent> staticEvents) {
		instance.staticEventsTable.setItems(null);
		instance.staticEventsTable.setItems(staticEvents);
		instance.time.setCellValueFactory(cell -> cell.getValue().getId());
		instance.event.setCellValueFactory(cell -> cell.getValue().getEvent());
	}
	
	public static void setGlobalLocations(ObservableList<GlobalLocation> globalLocations) {
		instance.globLocList.setItems(null);
		instance.globLocList.setItems(globalLocations);
	}
	
	public static void setQuestCancelers(ObservableList<QuestCanceler> questCancelers) {
		instance.cancelList.setItems(null);
		instance.cancelList.setItems(questCancelers);
	}
	
	public static void setMainPageLines(ObservableList<MainPageLine> mainPageLines) {
		instance.mainPageList.setItems(null);
		instance.mainPageList.setItems(mainPageLines);
	}
	
}
