package com.bidv.mobiletests.ui;

import org.openqa.selenium.By;

import net.serenitybdd.screenplay.targets.ByTarget;
import net.serenitybdd.screenplay.targets.Target;

/**
 * @author jacob
 */
public class NewNoteScreen {
        
    public static final Target INPUT_TITLE = ByTarget
            .the("Input title")
            .locatedForAndroid(By.id("com.example.android.testing.notes:id/add_note_title"))
            .locatedForIOS((By.id("add_note_title")));

    public static final Target INPUT_DESCRIPTION = ByTarget
            .the("Input content")
            .locatedForAndroid(By.id("com.example.android.testing.notes:id/add_note_description"))
            .locatedForIOS((By.id("add_note_description")));

    public static final Target SAVE_BUTTON = ByTarget
            .the("Save button")
            .locatedForAndroid(By.id("com.example.android.testing.notes:id/fab_add_notes"))
            .locatedForIOS((By.id("fab_add_notes")));


    public static final Target DETAIL_TITLE = ByTarget
        .the("Detail title")
        .locatedForAndroid(By.id("com.example.android.testing.notes:id/note_detail_title"))
        .locatedForIOS((By.id("note_detail_title")));

    public static final Target DETAIL_DESCRIPTION = ByTarget
        .the("Detail description")
        .locatedForAndroid(By.id("com.example.android.testing.notes:id/note_detail_description"))
        .locatedForIOS((By.id("note_detail_description")));
}
