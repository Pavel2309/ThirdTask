package com.stakhiyevich.infohandling.service.impl;

import com.stakhiyevich.infohandling.entity.TextComposite;
import com.stakhiyevich.infohandling.exception.ComponentException;
import com.stakhiyevich.infohandling.parser.TextParser;
import com.stakhiyevich.infohandling.parser.impl.ParagraphParser;
import com.stakhiyevich.infohandling.service.TextService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class TextServiceImplTest {

    private TextComposite composite;
    private TextParser parser;
    private TextService textService;

    @BeforeMethod
    public void setUp() {
        parser = new ParagraphParser();
        textService = new TextServiceImpl();
        String text = "    It thelongestwordishereonetwothree has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n" +
                "    It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n" +
                "    It is a established fact that a reader will be of a page when looking at its layout...\n" +
                "    Bye thelongestwordishereonetwothree бандерлоги.";
        composite = parser.parseText(text);
    }

    @Test
    public void testSortParagraphsBySentenceNumber() {

        try {
            textService.sortParagraphsBySentenceNumber(composite);
        } catch (ComponentException e) {
            fail("failed to sort composite");
        }

        String expected = "It is a established fact that a reader will be of a page when looking at its layout... \n" +
                "Bye бандерлоги. \n" +
                "It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n" +
                "It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n";
        String actual = composite.convertToString();

        assertEquals(actual, expected);
    }

    @Test
    public void testFindSentencesWithLongestWord() {
        int actual = textService.findSentencesWithLongestWord(composite).size();
        int expected = 2;
        assertEquals(actual, expected);
    }

    @Test
    public void testRemoveSentencesWithWordsLessThan() {

        try {
            textService.removeSentencesWithWordsLessThan(composite, 3);
        } catch (ComponentException e) {
            fail("failed to sort composite");
        }

        String expected = "It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n" +
                "It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n" +
                "It is a established fact that a reader will be of a page when looking at its layout... \n\n";

        String actual = composite.convertToString();

        assertEquals(actual, expected);

    }

    @Test
    public void testCountDuplicateWords() {
        int actual = 0;
        int expected = 25;
        try {
            actual = textService.countDuplicateWords(composite);
        } catch (ComponentException e) {
            fail("failed to sort composite");
        }

        assertEquals(actual, expected);
    }

    @Test
    public void testCountVowels() {
        int actual = 0;
        int expected = 241;
        try {
            actual = textService.countVowels(composite);
        } catch (ComponentException e) {
            fail("failed to sort composite");
        }
        assertEquals(actual, expected);
    }

    @Test
    public void testCountConsonants() {
        int actual = 0;
        int expected = 358;
        try {
            actual = textService.countConsonants(composite);
        } catch (ComponentException e) {
            fail("failed to sort composite");
        }
        assertEquals(actual, expected);
    }
}