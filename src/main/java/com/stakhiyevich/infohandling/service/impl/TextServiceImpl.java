package com.stakhiyevich.infohandling.service.impl;

import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.entity.TextElementType;
import com.stakhiyevich.infohandling.exception.ComponentException;
import com.stakhiyevich.infohandling.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

    private static final Logger logger = LogManager.getLogger();
    private static final String PATTERN_VOWEL = "[AEIOUYaeiouy]";
    private static final String PATTERN_CONSONANT = "[[^AEIOUYaeiouy]&&A-Za-z]";

    @Override
    public void sortParagraphsBySentenceNumber(TextComposite textComposite) throws ComponentException {
        if (textComposite.getElementType() != TextElementType.PARAGRAPH) {
            logger.error("given component does not have paragraph elements");
            throw new ComponentException("given component does not have paragraph elements");
        }
        textComposite.getComponents().sort(Comparator.comparingInt(paragraph -> paragraph.getComponents().size()));
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComposite textComposite) {

        List<TextComponent> sentences = textComposite.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream()).toList();

        List<TextComponent> words = textComposite.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .flatMap(sentence -> sentence.getComponents().stream()).toList();

        long maxLength = 0L;
        for (TextComponent word : words) {
             long currentWordLength = word.getComponents().stream()
                    .filter(symbol -> symbol.getElementType() == TextElementType.LETTER)
                    .count();
            if (currentWordLength > maxLength) {
                maxLength = currentWordLength;
            }
        }

        List<TextComponent> result = new ArrayList<>();
        for (TextComponent sentence : sentences) {
            for (TextComponent word : sentence.getComponents()) {
                long currentWordLength =  word.getComponents().stream()
                        .filter(symbol -> symbol.getElementType() == TextElementType.LETTER).count();
                if (currentWordLength == maxLength) {
                    result.add(sentence);
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public void removeSentencesWithWordsLessThan(TextComposite textComposite, int numberOfWords) throws ComponentException {
        if (textComposite.getElementType() != TextElementType.PARAGRAPH) {
            logger.error("given component does not have paragraph elements");
            throw new ComponentException("given component does not have paragraph elements");
        }
        textComposite.getComponents().forEach(paragraph ->
                paragraph.getComponents().removeIf(sentence -> sentence.getComponents().size() < numberOfWords));
    }

    @Override
    public int countDuplicateWords(TextComposite textComposite) throws ComponentException {
        if (textComposite.getElementType() != TextElementType.PARAGRAPH) {
            logger.error("given component does not have paragraph elements");
            throw new ComponentException("given component does not have paragraph elements");
        }
        return  (int) textComposite.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .flatMap(sentence -> sentence.getComponents().stream())
                .map(TextComponent::convertToString).toList()
                .stream().collect(Collectors.groupingBy(k -> k, Collectors.counting()))
                .values().stream().filter(value -> value > 1).count();
    }

    @Override
    public int countVowels(TextComposite textComposite) throws ComponentException {
        return countLetters(textComposite, PATTERN_VOWEL);
    }

    @Override
    public int countConsonants(TextComposite textComposite) throws ComponentException {
        return countLetters(textComposite, PATTERN_CONSONANT);
    }

    private int countLetters(TextComposite textComposite, String pattern) {
        return textComposite.getComponents().stream()
                .flatMap(paragraph -> paragraph.getComponents().stream())
                .flatMap(sentence -> sentence.getComponents().stream())
                .flatMap(word -> word.getComponents().stream())
                .filter(letter -> letter.getElementType() == TextElementType.LETTER)
                .filter(letter -> letter.convertToString().matches(pattern))
                .toList().size();
    }

}
