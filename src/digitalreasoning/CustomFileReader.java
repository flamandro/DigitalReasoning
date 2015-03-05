/*
 * Written and Created and Owned by John M. Lien
 */
package digitalreasoning;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John M. Lien
 * Property of by John M. Lien
 */
public class CustomFileReader {

    private DataInputStream in;
    private BufferedReader br;
    private String strLine;
    private String checked;
    private String current;
    private String last_file_opened;
    private double curCount;

    public void reOpenLastUsedFile() {
        if (this.last_file_opened != null) {
            this.openFile(last_file_opened);
        } else {
            System.err.println("No file previously opened : reOpenLastUsedFile()");
            System.exit(1);
        }

    }

    public void openFile(String dir) {
        if (!isClosed()) {
            closeFile();
        }
        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(dir);
            // Get the object of DataInputStream
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
            last_file_opened = dir;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomFileReader.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not open file: openFile");
            System.exit(1);
        }
    }

    public boolean hasNextLine() {
        if (in == null || br == null) {
            System.out.flush();
            System.err.println("No File Currently Open: hasNextLine()");
            System.exit(1);
            return false;
        }
        try {
            if (checked != null) {
                return true;
            } else {
                if ((strLine = br.readLine()) != null) {
                    checked = strLine;
                    return true;
                } else {
                    closeFile();
                    return false;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void checkCurToGetRatio() {
        Scanner in;
        if (curCount > 1000000) {
            //throw interupt due to possible user error
            System.out.flush();
            System.err.println("#########################################################################");
            System.err.println("######                      Throwing Interrupt                     ######");
            System.err.println("######        getCurrentLine Method Calls Count Exceed 1000000     ######");
            System.err.println("#########################################################################");
            System.err.println("Are you using getCurrentLine by mistake?");
            System.out.print("Continue running program (y/n)? ");
            in = new Scanner(System.in);
            try {
                if (in.hasNext("y")) {
                    curCount = 0;
                } else {
                    System.out.flush();
                    System.err.println("Ending program early...");
                    closeFile();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Ending program early...");
                closeFile();
                System.exit(0);
            }
        }
    }

    public String getCurrentLine() {
        if (in == null || br == null) {
            System.out.flush();
            System.err.println("No File Currently Open: getCurrentLine()");
            System.exit(1);
            return null;
        }
        if (checked != null) {
            curCount++;
            this.checkCurToGetRatio();
            return checked;
        } else {
            curCount++;
            this.checkCurToGetRatio();
            return current;
        }
    }

    public String getNextLine() {
        String uncheck;
        if (in == null || br == null) {
            System.out.flush();
            System.err.println("No File Currently Open: getNextLine()");
            System.exit(1);
            return null;
        }
        try {
            if (checked != null) {
                uncheck = checked;
                checked = null;
                current = strLine;
                return uncheck;
            } else {
                if ((strLine = br.readLine()) != null) {
                    current = strLine;
                    return strLine;
                } else {
                    closeFile();
                    return null;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void closeFile() {
        try {
            if (in != null) {
                in.close();
                in = null;
            }
            if (br != null) {
                br.close();
                br = null;
            }
            checked = null;
            current = null;
        } catch (IOException ex) {
            Logger.getLogger(CustomFileReader.class.getName()).log(Level.SEVERE, null, ex);
            System.err.flush();
            System.err.println("No File Currently Open: closeFile()");
            System.exit(1);
        }
    }

    public boolean isClosed() {
        return in == null && br == null;
    }

    public void printContentsToStandardOutput(String dir) {
        this.openFile(dir);
        while (this.hasNextLine()) {
            System.out.println(this.getNextLine());
        }
    }

    public ArrayList<String> getFileAsArrayListOfString(String dir) {
        ArrayList<String> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(this.getNextLine());
        }
        return content;
    }

    public ArrayList<String> getFileAsArrayListOfStringToLowerCase(String dir) {
        ArrayList<String> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(this.getNextLine().toLowerCase());
        }
        return content;
    }

    public ArrayList<String> getFileAsArrayListOfStringToUpperCase(String dir) {
        ArrayList<String> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(this.getNextLine().toUpperCase());
        }
        return content;
    }

    public ArrayList<String> getFileAsArrayListOfStringTrimmed(String dir) {
        ArrayList<String> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(this.getNextLine().trim());
        }
        return content;
    }

    public ArrayList<String> getFileAsArrayListOfStringToLowerCaseTrimmed(String dir) {
        ArrayList<String> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(this.getNextLine().toLowerCase().trim());
        }
        return content;
    }

    public ArrayList<String> getFileAsArrayListOfStringToUpperCaseTrimmed(String dir) {
        ArrayList<String> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(this.getNextLine().toUpperCase().trim());
        }
        return content;
    }

    public ArrayList<String[]> getFileAsDelimitedStringArray(String dir, String delimiters) {
        ArrayList<String[]> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(this.getNextLine().split(delimiters));
        }
        return content;
    }

    public ArrayList<ArrayList<String>> getFileAsDelimitedStringArrayList(String dir, String delimiters) {
        ArrayList<ArrayList<String>> content = new ArrayList();
        this.openFile(dir);
        while (this.hasNextLine()) {
            content.add(new ArrayList(Arrays.asList(this.getNextLine().split(delimiters))));
        }
        return content;
    }

    public String getFileAsString(String dir) {
        StringBuilder sb = new StringBuilder();
        this.openFile(dir);
        while (this.hasNextLine()) {
            sb.append(this.getNextLine());
            sb.append("\n");
        }
        return sb.toString();
    }

    public CustomFileReader() {
        this.in = null;
        this.br = null;
        this.checked = null;
        this.current = null;
        this.last_file_opened = null;
        this.strLine = null;
        this.curCount = 0;
    }

    public CustomFileReader(String dir) {
        this.in = null;
        this.br = null;
        this.checked = null;
        this.current = null;
        this.last_file_opened = null;
        this.strLine = null;
        this.curCount = 0;
        this.openFile(dir);
    }
}
