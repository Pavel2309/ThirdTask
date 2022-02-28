package com.stakhiyevich.infohandling.entity;

public enum TextElementType {

    PARAGRAPH("\n"),
    SENTENCE(""),
    LEXICAL_UNIT("\s"),
    WORD(""),
    LETTER(""),
    SYMBOL("");

    private final String separator;

    TextElementType(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }
}
