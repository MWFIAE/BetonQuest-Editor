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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pl.betoncraft.betonquest.editor.model.Condition;
import pl.betoncraft.betonquest.editor.model.Conversation;
import pl.betoncraft.betonquest.editor.model.ConversationOption;
import pl.betoncraft.betonquest.editor.model.Event;
import pl.betoncraft.betonquest.editor.model.NpcOption;
import pl.betoncraft.betonquest.editor.model.PlayerOption;

/**
 * Controls the "Conversation" tab.
 *
 * @author Jakub Sapalski
 */
public class ConversationController {
	
	private static ConversationController instance;
	
	@FXML private ComboBox<Conversation> conversation;
	
	@FXML private TextField npc;
	@FXML private CheckBox stop;
	@FXML private ChoiceBox<NpcOption> startingOptionsChoice;
	@FXML private ComboBox<NpcOption> startingOptionsCombo;
	@FXML private ChoiceBox<Event> finalEventsChoice;
	@FXML private ComboBox<Event> finalEventsCombo;
	
	@FXML private ListView<NpcOption> npcList;
	@FXML private TextField npcField;
	@FXML private ListView<PlayerOption> playerList;
	@FXML private TextField playerField;
	
	@FXML private TextField option;
	@FXML private ChoiceBox<Event> eventChoice;
	@FXML private ComboBox<Event> eventCombo;
	@FXML private ChoiceBox<Condition> conditionChoice;
	@FXML private ComboBox<Condition> conditionCombo;
	
	@FXML private ListView<ConversationOption> pointsToList;
	@FXML private ComboBox<ConversationOption> pointsToCombo;
	@FXML private ListView<ConversationOption> pointedByList;
	
	private Conversation currentConversation;
	private ConversationOption currentOption;
	private boolean firstSelection = true;
	
	public ConversationController() {
		instance = this;
	}
	
	public static void setConversations(ObservableList<Conversation> conversations) {
		instance.conversation.setItems(conversations);
		instance.displayConversation(conversations.get(0));
	}
	
	public synchronized void displayConversation(Conversation conversation) {
		if (firstSelection) {
			firstSelection = false;
			this.conversation.getSelectionModel().select(conversation);
			return;
		}
		if (currentConversation != null) {
			npc.textProperty().unbindBidirectional(currentConversation.getNPC().get(
					currentConversation.getPack().getDefLang()));
			stop.selectedProperty().unbindBidirectional(currentConversation.getStop());
		}
		currentConversation = conversation;
		String lang = conversation.getPack().getDefLang();
		npc.textProperty().bindBidirectional(conversation.getNPC().get(lang));
		stop.selectedProperty().bindBidirectional(conversation.getStop());
		startingOptionsChoice.setItems(conversation.getStartingOptions());
		if (currentConversation.getStartingOptions().size() > 0) {
			startingOptionsChoice.getSelectionModel().select(0);
		}
		// TODO list of choosable starting options
		finalEventsChoice.setItems(conversation.getFinalEvents());
		if (currentConversation.getFinalEvents().size() > 0) {
			finalEventsChoice.getSelectionModel().select(0);
		}
		// TODO list of choosable final events
		npcList.setItems(conversation.getNpcOptions());
		playerList.setItems(conversation.getPlayerOptions());
		if(conversation.getNpcOptions().size()>0) //Bugfix empty Conversation
			displayOption(conversation.getNpcOptions().get(0));
	}
	
	public void displayOption(ConversationOption option) {
		String lang = currentConversation.getPack().getDefLang();
		if (currentOption != null) {
			this.option.textProperty().unbindBidirectional(currentOption.getText().get(lang));
		}
		currentOption = option;
		this.option.textProperty().bindBidirectional(option.getText().get(lang));
		eventChoice.setItems(option.getEvents());
		if (option.getEvents().size() > 0) {
			eventChoice.getSelectionModel().select(0);
		}
		conditionChoice.setItems(option.getConditions());
		if (option.getConditions().size() > 0) {
			conditionChoice.getSelectionModel().select(0);
		}
		pointsToList.setItems(option.getPointers());

		ObservableList<ConversationOption> list =FXCollections.observableArrayList();
		if(option instanceof NpcOption){

			for(PlayerOption po: currentConversation.getPlayerOptions())
			{
				if(po.getPointers().contains(option))
					list.add(po);
			}
		}else
		{
			for(NpcOption no: currentConversation.getNpcOptions())
			{
				if(no.getPointers().contains(option))
					list.add(no);
			}
		}
		pointedByList.setItems(list);
	}
	
	@FXML private void selectConversation() {
		Conversation selected = conversation.getSelectionModel().getSelectedItem();
		if (selected == null) {
			return;
		}
		displayConversation(selected);
	}
	
	@FXML private void clickNpcOption() {
		playerList.getSelectionModel().clearSelection();
		playerField.clear();
		NpcOption option = npcList.getSelectionModel().getSelectedItem();
		if (option == null) {
			return;
		}
		npcField.setText(option.getId().get());
		displayOption(option);
	}
	
	@FXML private void clickPlayerOption() {
		npcList.getSelectionModel().clearSelection();
		npcField.clear();
		PlayerOption option = playerList.getSelectionModel().getSelectedItem();
		if (option == null) {
			return;
		}
		playerField.setText(option.getId().get());
		displayOption(option);
	}
	
	public static void refresh() {
		// TODO refresh everything
	}

}
