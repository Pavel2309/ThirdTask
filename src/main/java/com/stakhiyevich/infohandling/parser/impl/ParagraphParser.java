package com.stakhiyevich.infohandling.parser.impl;

import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.parser.TextParser;

public class ParagraphParser implements TextParser {

    private static final String PARAGRAPH_PATTERN = "[\\n\\t]+";
    private final SentenceParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parseText(String text) {
        TextComposite paragraphComposite = new TextComposite(TextElementType.PARAGRAPH);
        String[] paragraphs = text.strip().split(PARAGRAPH_PATTERN);

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parseText(paragraph);
            paragraphComposite.add(paragraphComponent);
        }
        return paragraphComposite;
    }
}
