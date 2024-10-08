package com.barosanu.view;

public enum ColorTheme {
    LIGHT, DEFAULT, DARK;

    public static String getCssPath(ColorTheme colorTheme) {
        return switch (colorTheme) {
            case LIGHT -> "/view/css/themeLight.css";
            case DEFAULT -> "/view/css/themeDefault.css";
            case DARK -> "/view/css/themeDark.css";
        };
    }
}

