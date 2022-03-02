package com.stakhiyevich.infohandling.parser.impl;

import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.parser.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {

    private static final String SENTENCE_PATTERN = "[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)";
    private final WordParser wordParser = new WordParser();

    @Override
    public TextComposite parseText(String text) {

        TextComposite sentenceComposite = new TextComposite(TextElementType.SENTENCE);
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        Matcher matcher = pattern.matcher(text);

        List<String> sentences = new ArrayList<>();
        while (matcher.find()) {
            sentences.add(matcher.group());
        }

        for (String sentence : sentences) {
            TextComponent wordComponents = wordParser.parseText(sentence);
            sentenceComposite.add(wordComponents);
        }

        return sentenceComposite;
    }
}
