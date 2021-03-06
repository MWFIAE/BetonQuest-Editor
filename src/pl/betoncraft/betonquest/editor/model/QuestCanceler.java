/**
 * 
 */
package pl.betoncraft.betonquest.editor.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.betoncraft.betonquest.editor.data.ID;
import pl.betoncraft.betonquest.editor.data.TranslatableText;

/**
 * Represents a quest canceler.
 *
 * @author Jakub Sapalski
 */
public class QuestCanceler implements ID {
	
	private StringProperty id;
	private TranslatableText name = new TranslatableText();
	private ObservableList<Condition> conditions = FXCollections.observableArrayList();
	private ObservableList<Event> events = FXCollections.observableArrayList();
	private ObservableList<Objective> objectives = FXCollections.observableArrayList();
	private ObservableList<String> tags = FXCollections.observableArrayList();
	private ObservableList<String> points = FXCollections.observableArrayList();
	private ObservableList<JournalEntry> journal = FXCollections.observableArrayList();
	private StringProperty location = new SimpleStringProperty();
	
	public QuestCanceler(String id) {
		this.id = new SimpleStringProperty(id);
	}

	@Override
	public StringProperty getId() {
		return id;
	}

	public String getLocation() {
		return location.get();
	}

	public void setLocation(String location) {
		this.location.set(location);
	}

	public TranslatableText getName() {
		return name;
	}

	public ObservableList<Condition> getConditions() {
		return conditions;
	}

	public ObservableList<Event> getEvents() {
		return events;
	}

	public ObservableList<Objective> getObjectives() {
		return objectives;
	}

	public ObservableList<String> getTags() {
		return tags;
	}

	public ObservableList<String> getPoints() {
		return points;
	}

	public ObservableList<JournalEntry> getJournal() {
		return journal;
	}
	
	@Override
	public String toString() {
		return id.get();
	}

}
