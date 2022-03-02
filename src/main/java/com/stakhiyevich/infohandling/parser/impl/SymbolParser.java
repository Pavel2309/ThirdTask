package com.stakhiyevich.infohandling.parser.impl;

import com.stakhiyevich.infohandling.entity.Symbol;
import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.parser.TextParser;


public class SymbolParser implements TextParser {

    private static final String SYMBOL_SEPARATOR = "";
    private static final String NUMBER_PATTERN = "\\d";
    private static final String PUNCTUATION_PATTERN = "[\\p{Punct}\\s]";

    @Override
    public TextComposite parseText(String text) {
        String[] symbols = text.strip().split(SYMBOL_SEPARATOR);
        TextComposite symbolComposite = new TextComposite(TextElementType.SYMBOL);

        for (String symbol : symbols) {
            if (!symbol.isBlank()) {
                TextElementType type;

                if (symbol.matches(NUMBER_PATTERN)) {
                    type = TextElementType.NUMBER;
                } else if (symbol.matches(PUNCTUATION_PATTERN)) {
                    type = TextElementType.PUNCTUATION;
                } else {
                    type = TextElementType.LETTER;
                }
                TextComponent component = new Symbol(symbol.charAt(0), type);
                symbolComposite.add(component);
            }
        }
        return symbolComposite;
    }
}
