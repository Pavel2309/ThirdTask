package com.stakhiyevich.infohandling.parser.impl;

import com.stakhiyevich.infohandling.entity.Symbol;
import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.parser.TextParser;

public class LetterParser implements TextParser {

    @Override
    public TextComposite parseText(String text) {
        char[] symbols = text.toCharArray();
        TextComposite symbolComposite = new TextComposite(TextElementType.LETTER);

        for (char character : symbols) {
            TextElementType type = TextElementType.LETTER;
            if (!Character.isLetter(character)) {
                type = TextElementType.SYMBOL;
            }
            TextComponent symbol = new Symbol(character, type);
            symbolComposite.add(symbol);
        }
        return symbolComposite;
    }
}
