package com.stakhiyevich.infohandling.parser.impl;

import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.parser.TextParser;

public class LexicalUnitParser implements TextParser {

    private final TextParser wordParser = new WordParser();

    @Override
    public TextComposite parseText(String text) {
        TextComposite lexicalUnitComposite = new TextComposite(TextElementType.LEXICAL_UNIT);

        return null;
    }
}
