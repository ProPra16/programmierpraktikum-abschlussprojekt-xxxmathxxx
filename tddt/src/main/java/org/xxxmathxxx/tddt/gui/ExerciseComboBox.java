package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.data.Exercise;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ExerciseComboBox extends ComboBox<Exercise> {

	public ExerciseComboBox(ObservableList<Exercise> list) {
		super(list);
		setPromptText("Select an exercise");

		setCellFactory(new Callback<ListView<Exercise>, ListCell<Exercise>>() {
			@Override
			public ListCell<Exercise> call(ListView<Exercise> p) {
				ListCell<Exercise> cell = new ListCell<Exercise>() {
					@Override
					protected void updateItem(Exercise e, boolean empty) {
						super.updateItem(e, empty);
						if (empty) {
							setText("");
						} else {
							setText(e.name);
						}
					}
				};
				return cell;
			}
		});

		setButtonCell(new ListCell<Exercise>() {
			@Override
			protected void updateItem(Exercise e, boolean yesNo) {
				super.updateItem(e, yesNo);
				if (yesNo) {
					setText("");
				} 
				else 
				{
					setText(e.name);
				}

			}
		});

	}

}
