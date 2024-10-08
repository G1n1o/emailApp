package com.barosanu.view;

public enum FontSize {
    SMALL,
    MEDIUM,
    BIG;

    public static String getCssPath(FontSize fontSize) {
        return switch (fontSize) {
            case SMALL-> "/view/css/fontSmall.css";
            case MEDIUM -> "/view/css/fontMedium.css";
            case BIG -> "/view/css/fontBig.css";
        };
    }
}
