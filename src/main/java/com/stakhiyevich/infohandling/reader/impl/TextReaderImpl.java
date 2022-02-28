package com.stakhiyevich.infohandling.reader.impl;

import com.stakhiyevich.infohandling.exception.ReaderException;
import com.stakhiyevich.infohandling.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String readText(String textPath) throws ReaderException {

        logger.info("trying to read the \"{}\" file", textPath);

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(textPath);
        if (resource == null) {
            logger.error("file {} does not exist", textPath);
            throw new ReaderException("file " + textPath + " does not exist");
        }

        String resultText = "";
        try {
            resultText = Files.readString(Paths.get(resource.toURI()));
        } catch (IOException e) {
            logger.error("can't read {}", textPath, e);
            throw new ReaderException("can't read " + textPath, e);
        } catch (URISyntaxException e) {
            logger.error("can't read {}", textPath, e);
        }

        if (resultText.isEmpty()) {
            logger.error("the file is empty");
            throw new ReaderException("the file is empty");
        }

        return resultText;
    }
}
