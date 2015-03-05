package digitalreasoning;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigitalReasoning {

    private static ArrayList<String> stop_words = new ArrayList();
    private static final ArrayList<String> multi_thread_files = new ArrayList();

    public DigitalReasoning() {
        this.loadStopWords();
    }

    private void loadStopWords() {
        stop_words = new CustomFileReader().getFileAsArrayListOfString("./stopwords.txt");
    }

    private ArrayList<String> tokenizeSentences(String inputDir) {
        ArrayList<String> sentences = new ArrayList();
        ArrayList<String> input = new CustomFileReader().getFileAsArrayListOfString(inputDir);
        char lastRecordedValue;
        StringBuilder sb;

        for (String s : input) {
            sb = new StringBuilder();
            lastRecordedValue = 0;
            for (char c : s.toCharArray()) {
                if (c == '.' || c == '!' || c == '?') {
                    if (lastRecordedValue != 0) {
                        sb.append(lastRecordedValue);
                        lastRecordedValue = c;
                    } else {
                        lastRecordedValue = c;
                    }
                } else {
                    if (lastRecordedValue != 0) {
                        sb.append(lastRecordedValue);
                        lastRecordedValue = 0;
                        if (!sb.toString().endsWith("...")
                                && !sb.toString().endsWith("Inc.")
                                && !sb.toString().endsWith("0.")
                                && !sb.toString().endsWith("1.")
                                && !sb.toString().endsWith("2.")
                                && !sb.toString().endsWith("3.")
                                && !sb.toString().endsWith("4.")
                                && !sb.toString().endsWith("5.")
                                && !sb.toString().endsWith("6.")
                                && !sb.toString().endsWith("7.")
                                && !sb.toString().endsWith("8.")
                                && !sb.toString().endsWith("9.")
                                && c != '"'
                                && c != '\'') {
                            sentences.add(sb.toString());
                            sb.setLength(0);
                        }
                        sb.append(c);
                    } else {
                        sb.append(c);
                    }
                }
            }
            if (lastRecordedValue != 0) {
                sb.append(lastRecordedValue);
            }
            if (!sb.toString().trim().isEmpty()) {
                sentences.add(sb.toString());
            }
        }

        return sentences;
    }

    private ArrayList<String> namedEntities(String input) {
        String[] first_split = input.split("[,;]");
        ArrayList<String> namedEntities = new ArrayList();
        
        for (String token : first_split) {
            String trimmed_input = token.replaceAll("[^a-zA-z\\-– ]", "").replaceAll("( )+", " ");
            String[] split = trimmed_input.split(" ");
            boolean added = false;
            StringBuilder sb = new StringBuilder();

            for (String tok : split) {
                if (!stop_words.contains(tok.toLowerCase())
                        && !tok.isEmpty()
                        && tok.charAt(0) == Character.toUpperCase(tok.charAt(0))) {
                    if (!added) {
                        if (sb.length() > 0) {
                            if (!sb.toString().trim().isEmpty()) {
                                namedEntities.add(sb.toString().trim());
                            }
                            sb.setLength(0);
                            added = false;
                        }
                        sb.append(tok);
                        added = true;
                    } else {
                        sb.append(" ").append(tok);
                    }
                } else {
                    if (added) {
                        if (!sb.toString().trim().isEmpty()) {
                            namedEntities.add(sb.toString().trim());
                        }
                        sb.setLength(0);
                        added = false;
                    }
                }
            }
            if (!sb.toString().trim().isEmpty()) {
                namedEntities.add(sb.toString().trim());
            }
        }

        return namedEntities;
    }

    private void processSentenceOutput(ArrayList<String> sentences, String fileName, String dest) {
        File dest_dir = new File(dest);
        File parent = dest_dir.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        CustomFileWriter write = new CustomFileWriter(dest);
        write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        write.println("\t<File file_name = \"" + fileName + "\">");
        write.println("\t\t<Sentences>");
        for (String sentence : sentences) {
            write.println("\t\t\t<Sentence>" + sentence + "</Sentence>");
        }
        write.println("\t\t</Sentences>");
        write.println("\t</File>");
        write.closeFile();
    }

    private void processSentenceOutputWithProperNounExtraction(ArrayList<String> sentences, String fileName, String dest) {
        File dest_dir = new File(dest);
        File parent = dest_dir.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        CustomFileWriter write = new CustomFileWriter(dest);
        write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        write.println("\t<File file_name = \"" + fileName + "\">");
        write.println("\t\t<Sentences>");
        int count = 0;
        for (String sentence : sentences) {
            count++;
            write.println("\t\t\t<Sentence_" + count + ">");
            write.println("\t\t\t\t<Sentence>" + sentence + "</Sentence>");
            ArrayList<String> toks = namedEntities(sentence);
            write.println("\t\t\t\t<ProperNouns>");
            toks = new ArrayList(new HashSet(toks));
            Collections.sort(toks);
            for (String tok : toks) {
                write.println("\t\t\t\t\t<ProperNoun>" + tok + "</ProperNoun>");
            }
            write.println("\t\t\t\t</ProperNouns>");
            write.println("\t\t\t</Sentence_" + count + ">");
        }
        write.println("\t\t</Sentences>");
        write.println("\t</File>");
        write.closeFile();
    }

    public void processSentence(String inputDir, String output_path) {
        if (inputDir == null || output_path == null) {
            return;
        }

        File file = new File(inputDir);
        ArrayList<String> sentences = tokenizeSentences(inputDir);

        processSentenceOutput(sentences, file.getName(), output_path + "/" + file.getName() + "_sentence_output.xml");
    }

    public void processSentenceAndExtractProperNouns(String inputDir, String output_path) {
        if (inputDir == null || output_path == null) {
            return;
        }
        File file = new File(inputDir);
        ArrayList<String> sentences = tokenizeSentences(inputDir);

        processSentenceOutputWithProperNounExtraction(sentences, file.getName(), output_path + "/" + file.getName() + "_sentence_with_nouns_output.xml");
    }

    protected void unZipFolder(String input, String output) {
        Unzipper unzip = new Unzipper();
        try {
            unzip.unzip(input, output);
        } catch (IOException ex) {
            Logger.getLogger(DigitalReasoning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected ArrayList<String> getMultiThreadFiles(String dir) {
        multi_thread_files.clear();
        this.searchDirForFiles(dir);

        return multi_thread_files;
    }

    private void searchDirForFiles(String dir) {
        File file = new File(dir);
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if (child.isDirectory()) {
                    searchDirForFiles(child.getAbsolutePath());
                } else {
                    if (!child.getName().startsWith(".") && !child.getName().contains("~")) {
                        multi_thread_files.add(child.getAbsolutePath());
                    }
                }
            }
        }
    }
}
