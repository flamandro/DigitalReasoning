/*
 * Written and Created and Owned by John M. Lien
 */
package digitalreasoning;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author John M. Lien
 * Property of by John M. Lien
 */
public class CustomFileWriter {

    private BufferedWriter writer;

    public void appendFile(String dir) {
        if (writer != null) {
            this.closeFile();
        }
        try {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir, true), "utf-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createFile(String dir) {
        if (writer != null) {
            this.closeFile();
        }
        try {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dir), "utf-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println();
        }
    }

    public void println() {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(String)");
                System.exit(1);
            }
        }
    }

    public void println(String string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(string);
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(String)");
                System.exit(1);
            }
        }
    }

    public void println(int string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(int)");
                System.exit(1);
            }
        }
    }

    public void println(double string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(double)");
                System.exit(1);
            }
        }
    }

    public void println(char string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(char)");
                System.exit(1);
            }
        }
    }

    public void println(boolean string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(boolean)");
                System.exit(1);
            }
        }
    }

    public void println(char[] string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(char[])");
                System.exit(1);
            }
        }
    }

    public void println(long string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(long)");
                System.exit(1);
            }
        }
    }

    public void println(float string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(float)");
                System.exit(1);
            }
        }
    }

    public void println(Object string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
                writer.newLine();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(Object)");
                System.exit(1);
            }
        }
    }

    public void print(Object string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(Object)");
                System.exit(1);
            }
        }
    }

    public void print(float string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: print(float)");
                System.exit(1);
            }
        }
    }

    public void print(long string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: print(long)");
                System.exit(1);
            }
        }
    }

    public void print(char[] string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: print(char[])");
                System.exit(1);
            }
        }
    }

    public void print(boolean string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: print(boolean)");
                System.exit(1);
            }
        }
    }

    public void print(char string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: print(char)");
                System.exit(1);
            }
        }
    }

    public void print(double string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: print(double)");
                System.exit(1);
            }
        }
    }

    public void print(String string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(string);
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: print(String)");
                System.exit(1);
            }
        }
    }

    public void print(int string) {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.write(String.valueOf(string));
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not print to file: println(int)");
                System.exit(1);
            }
        }
    }

    public void flush() {
        if (writer == null) {
            System.out.flush();
            System.err.println("No File Currently Open");
            System.exit(1);
        } else {
            try {
                writer.flush();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not flush file: flush()");
                System.exit(1);
            }
        }
    }

    public void copy_file(String file, String new_file) {
        FileInputStream fstream = null;
        DataInputStream in = null;
        BufferedReader br = null;
        String strLine;
        try {
            fstream = new FileInputStream(file);
            // Get the object of DataInputStream
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
            CustomFileWriter write = new CustomFileWriter(new_file);
            try {
                while ((strLine = br.readLine()) != null) {
                    write.println(strLine);
                }
            } catch (IOException ex) {
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                br.close();
                in.close();
                write.closeFile();
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not close buffers: copy_file");
                System.exit(1);
            }
        } catch (FileNotFoundException ex) {
            System.out.flush();
            Logger.getLogger(CustomFileReader.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Could not open file: copy_file");
            System.exit(1);
        }
    }

    public boolean isOpen() {
        return (writer == null) ? false : true;
    }

    public void closeFile() {
        if (writer != null) {
            try {
                writer.close();
                writer = null;
            } catch (IOException ex) {
                System.out.flush();
                Logger.getLogger(CustomFileWriter.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Could not close file: copy_file");
                System.exit(1);
            }
        }
    }

    public CustomFileWriter() {
        writer = null;
    }

    public CustomFileWriter(String dir) {
        writer = null;
        createFile(dir);
    }

    public CustomFileWriter(String dir, boolean append) {
        writer = null;
        if (append) {
            appendFile(dir);
        } else {
            createFile(dir);
        }
    }
}
