package com.jerry.www.RecipeApp.converters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jerry.www.RecipeApp.commands.NotesCommand;
import com.jerry.www.RecipeApp.model.Notes;
import com.jerry.www.RecipeApp.model.Recipe;

class NotesToNotesCommandTest {
	public static final Long LONG_VALUE = 1L;
	public static final String RECIPE_NOTES = "Notes";

	NotesToNotesCommand converter;

	@BeforeEach
	public void setUp() throws Exception {
		converter = new NotesToNotesCommand();
	}

	@Test
	public void nullParameterTest() throws Exception {
		assertNull(converter.convert(null));
	}

	@Test
	public void emptyObjectTest() throws Exception {
		assertNotNull(converter.convert(new Notes()));
	}

	@Test
	public void converterTest() throws Exception {
		// given
		Notes notes = new Notes();
		notes.setId(LONG_VALUE);
		notes.setRecipeNotes(RECIPE_NOTES);

		// when
		NotesCommand command = converter.convert(notes);

		// then
		assertNotNull(command);
		assertEquals(LONG_VALUE, command.getId());
		assertEquals(RECIPE_NOTES, command.getRecipeNotes());

	}

}
