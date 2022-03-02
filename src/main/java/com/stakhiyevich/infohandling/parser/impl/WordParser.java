package com.stakhiyevich.infohandling.parser.impl;

import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.parser.TextParser;

public class WordParser implements TextParser {

    private final LetterParser letterParser = new LetterParser();

    @Override
    public TextComposite parseText(String text) {
        TextComposite wordComposite = new TextComposite(TextElementType.WORD);
        TextComponent textComponent = letterParser.parseText(text);
        wordComposite.add(textComponent);
        return wordComposite;
    }
}
