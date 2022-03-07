package com.stakhiyevich.infohandling.service;

import com.stakhiyevich.infohandling.entity.TextComponent;
import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.exception.ComponentException;

import java.util.List;

public interface TextService {

    void sortParagraphsBySentenceNumber(TextComposite textComposite) throws ComponentException;

    List<TextComponent> findSentencesWithLongestWord(TextComposite textComposite);

    void removeSentencesWithWordsLessThan(TextComposite textComposite, int numberOfWords) throws ComponentException;

    int countDuplicateWords(TextComposite textComposite) throws ComponentException;

    int countVowels(TextComposite textComposite) throws ComponentException;

    int countConsonants(TextComposite textComposite) throws ComponentException;


}
