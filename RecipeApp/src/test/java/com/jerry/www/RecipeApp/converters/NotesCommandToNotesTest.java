package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.NotesCommand;
import com.jerry.www.RecipeApp.model.Notes;
import com.jerry.www.RecipeApp.model.Recipe;

class NotesCommandToNotesTest {
	public static final Long LONG_VALUE = 1L;
	public static final String RECIPE_NOTES = "Notes";

	NotesCommandToNotes converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new NotesCommandToNotes();
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new NotesCommand()));
	}

	@Test
	public void converterTest() throws Exception {
		// given
		NotesCommand command = new NotesCommand();
		command.setId(LONG_VALUE);
		command.setRecipeNotes(RECIPE_NOTES);

		// when
		Notes notes = converter.convert(command);

		// then
		assertNotNull(notes);
		assertEquals(LONG_VALUE, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());

	}

}
