package com.stakhiyevich.infohandling.parser.impl;

import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.parser.TextParser;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.Optional;
import java.util.regex.Pattern;


public class WordParser implements TextParser {

    private static final String WORD_SEPARATOR = "\\s+";
    private static final String CALCULATION_PATTERN = "([0-9]+[\\+\\-\\*\\/]{1}[0-9]+)+([\\+\\-\\*\\/]{1}[0-9]+)*";
    private final SymbolParser symbolParser = new SymbolParser();

    @Override
    public TextComposite parseText(String text) {
        TextComposite wordComposite = new TextComposite(TextElementType.WORD);
        String[] words = text.strip().split(WORD_SEPARATOR);
        Pattern pattern = Pattern.compile(CALCULATION_PATTERN);
        for (int i = 0; i < words.length; i++) {
            if (pattern.matcher(words[i]).find()) {
                words[i] = calculate(words[i]).orElse(words[i]);
            }
        }
        for (String word : words) {
            TextComponent symbolComponent = symbolParser.parseText(word);
            wordComposite.add(symbolComponent);
        }
        return wordComposite;
    }

    private Optional<String> calculate(String input) {
        Expression expression = new ExpressionBuilder(input).build();
        String result = String.valueOf(expression.evaluate());
        return Optional.of(result);
    }
}
