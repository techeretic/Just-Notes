package com.pshetye.justnotes.util;

import com.pshetye.justnotes.R;

import android.content.Context;

public class StyleAttributes {
    public static int colorPrimary;
    public static int colorPrimaryDark;
    public static int colorAccent;
    public static int textColor;
    public static int windowNoTitle;
    public static int windowActionBar;
    public static int cardColor;
    public static int addButton;
    public static int fabAddButton;
    public static int deleteButton;
    public static int shareButton;
    public static int editButton;
    public static int saveButton;
    public static int searchButton;
    public static int paletteButton;
    public static int homeButtonNotes;
    public static int homeButtonViewNote;
    public static int backgroundcolor;
    public static int fab_color ;

    public static void setStyleAttributes(Context context, int style) {
        switch (style) {
            case R.style.AppThemeDark:
                StyleAttributes.colorPrimary          = R.color.primary_material_dark;
                StyleAttributes.colorPrimaryDark      = R.color.primary_dark_material_dark;
                StyleAttributes.colorAccent           = R.color.accent_material_dark;
                StyleAttributes.textColor             = R.color.abc_primary_text_material_dark;
                StyleAttributes.cardColor             = R.color.dark_gray;
                StyleAttributes.addButton             = R.drawable.ic_action_new_light;
                StyleAttributes.fabAddButton          = R.drawable.ic_action_new_light;
                StyleAttributes.deleteButton          = R.drawable.ic_action_discard_light;
                StyleAttributes.shareButton           = R.drawable.ic_action_share_light;
                StyleAttributes.editButton            = R.drawable.ic_action_edit_light;
                StyleAttributes.saveButton            = R.drawable.ic_action_save_light;
                StyleAttributes.searchButton          = R.drawable.ic_search_light;
                StyleAttributes.paletteButton         = R.drawable.ic_palette_light;
                StyleAttributes.homeButtonNotes       = R.drawable.ic_assignment_light;
                StyleAttributes.homeButtonViewNote    = R.drawable.ic_speaker_notes_light;
                StyleAttributes.backgroundcolor       = android.R.color.darker_gray;
                StyleAttributes.fab_color             = android.R.color.holo_orange_dark;
                break;
            case R.style.AppThemeLight:
                StyleAttributes.colorPrimary          = R.color.primary_material_light;
                StyleAttributes.colorPrimaryDark      = R.color.primary_dark_material_light;
                StyleAttributes.colorAccent           = R.color.accent_material_dark;
                StyleAttributes.textColor             = R.color.abc_primary_text_material_dark;
                StyleAttributes.cardColor             = R.color.light_gray;
                StyleAttributes.addButton             = R.drawable.ic_action_new_dark;
                StyleAttributes.fabAddButton          = R.drawable.ic_action_new_light;
                StyleAttributes.deleteButton          = R.drawable.ic_action_discard_dark;
                StyleAttributes.shareButton           = R.drawable.ic_action_share_dark;
                StyleAttributes.editButton            = R.drawable.ic_action_edit_dark;
                StyleAttributes.saveButton            = R.drawable.ic_action_save_dark;
                StyleAttributes.searchButton          = R.drawable.ic_search_dark;
                StyleAttributes.paletteButton         = R.drawable.ic_palette_dark;
                StyleAttributes.homeButtonNotes       = R.drawable.ic_assignment_dark;
                StyleAttributes.homeButtonViewNote    = R.drawable.ic_speaker_notes_dark;
                StyleAttributes.backgroundcolor       = android.R.color.primary_text_light;
                StyleAttributes.fab_color             = android.R.color.holo_red_dark;
                break;
            case R.style.AppThemeRedDark:
                StyleAttributes.colorPrimary          = R.color.primary_red;
                StyleAttributes.colorPrimaryDark      = R.color.primary_dark_red;
                StyleAttributes.colorAccent           = R.color.accent_red;
                StyleAttributes.textColor             = R.color.abc_primary_text_material_dark;
                StyleAttributes.cardColor             = R.color.dark_gray;
                StyleAttributes.addButton             = R.drawable.ic_action_new_dark;
                StyleAttributes.fabAddButton          = R.drawable.ic_action_new_light;
                StyleAttributes.deleteButton          = R.drawable.ic_action_discard_dark;
                StyleAttributes.shareButton           = R.drawable.ic_action_share_dark;
                StyleAttributes.editButton            = R.drawable.ic_action_edit_dark;
                StyleAttributes.saveButton            = R.drawable.ic_action_save_dark;
                StyleAttributes.searchButton          = R.drawable.ic_search_dark;
                StyleAttributes.paletteButton         = R.drawable.ic_palette_dark;
                StyleAttributes.homeButtonNotes       = R.drawable.ic_assignment_dark;
                StyleAttributes.homeButtonViewNote    = R.drawable.ic_speaker_notes_dark;
                StyleAttributes.backgroundcolor       = android.R.color.primary_text_light;
                StyleAttributes.fab_color             = android.R.color.holo_blue_dark;
                break;
            case R.style.AppThemeRedLight:
                StyleAttributes.colorPrimary          = R.color.primary_red;
                StyleAttributes.colorPrimaryDark      = R.color.primary_dark_red;
                StyleAttributes.colorAccent           = R.color.accent_red;
                StyleAttributes.textColor             = R.color.abc_primary_text_material_dark;
                StyleAttributes.cardColor             = R.color.light_gray;
                StyleAttributes.addButton             = R.drawable.ic_action_new_light;
                StyleAttributes.fabAddButton          = R.drawable.ic_action_new_light;
                StyleAttributes.deleteButton          = R.drawable.ic_action_discard_light;
                StyleAttributes.shareButton           = R.drawable.ic_action_share_light;
                StyleAttributes.editButton            = R.drawable.ic_action_edit_light;
                StyleAttributes.saveButton            = R.drawable.ic_action_save_light;
                StyleAttributes.searchButton          = R.drawable.ic_search_light;
                StyleAttributes.paletteButton         = R.drawable.ic_palette_light;
                StyleAttributes.homeButtonNotes       = R.drawable.ic_assignment_light;
                StyleAttributes.homeButtonViewNote    = R.drawable.ic_speaker_notes_light;
                StyleAttributes.backgroundcolor       = android.R.color.primary_text_light;
                StyleAttributes.fab_color             = android.R.color.holo_blue_dark;
                break;
            case R.style.AppThemeBlueDark:
                StyleAttributes.colorPrimary          = R.color.primary_blue;
                StyleAttributes.colorPrimaryDark      = R.color.primary_dark_blue;
                StyleAttributes.colorAccent           = R.color.accent_blue;
                StyleAttributes.textColor             = R.color.abc_primary_text_material_dark;
                StyleAttributes.cardColor             = R.color.dark_gray;
                StyleAttributes.addButton             = R.drawable.ic_action_new_dark;
                StyleAttributes.fabAddButton          = R.drawable.ic_action_new_light;
                StyleAttributes.deleteButton          = R.drawable.ic_action_discard_dark;
                StyleAttributes.shareButton           = R.drawable.ic_action_share_dark;
                StyleAttributes.editButton            = R.drawable.ic_action_edit_dark;
                StyleAttributes.saveButton            = R.drawable.ic_action_save_dark;
                StyleAttributes.searchButton          = R.drawable.ic_search_dark;
                StyleAttributes.paletteButton         = R.drawable.ic_palette_dark;
                StyleAttributes.homeButtonNotes       = R.drawable.ic_assignment_dark;
                StyleAttributes.homeButtonViewNote    = R.drawable.ic_speaker_notes_dark;
                StyleAttributes.backgroundcolor       = android.R.color.primary_text_light;
                StyleAttributes.fab_color             = android.R.color.holo_green_dark;
                break;
            case R.style.AppThemeBlueLight:
                StyleAttributes.colorPrimary          = R.color.primary_blue;
                StyleAttributes.colorPrimaryDark      = R.color.primary_dark_blue;
                StyleAttributes.colorAccent           = R.color.accent_blue;
                StyleAttributes.textColor             = R.color.abc_primary_text_material_dark;
                StyleAttributes.cardColor             = R.color.light_gray;
                StyleAttributes.addButton             = R.drawable.ic_action_new_light;
                StyleAttributes.fabAddButton          = R.drawable.ic_action_new_light;
                StyleAttributes.deleteButton          = R.drawable.ic_action_discard_light;
                StyleAttributes.shareButton           = R.drawable.ic_action_share_light;
                StyleAttributes.editButton            = R.drawable.ic_action_edit_light;
                StyleAttributes.saveButton            = R.drawable.ic_action_save_light;
                StyleAttributes.searchButton          = R.drawable.ic_search_light;
                StyleAttributes.paletteButton         = R.drawable.ic_palette_light;
                StyleAttributes.homeButtonNotes       = R.drawable.ic_assignment_light;
                StyleAttributes.homeButtonViewNote    = R.drawable.ic_speaker_notes_light;
                StyleAttributes.backgroundcolor       = android.R.color.primary_text_light;
                StyleAttributes.fab_color             = android.R.color.holo_green_dark;
                break;
        }
    }
}
