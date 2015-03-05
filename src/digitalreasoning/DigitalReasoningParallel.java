/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalreasoning;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author administrator
 */
public class DigitalReasoningParallel {

    public void parallelProcessZipFolder(String inputZip, String outputExtract, final String outputPath) {
        if(inputZip == null || outputExtract == null || outputPath == null){
            return;
        }
        
        final DigitalReasoning run = new DigitalReasoning();

        run.unZipFolder(inputZip, outputExtract);
        ArrayList<String> files = run.getMultiThreadFiles(outputExtract);

        final int numberOfThreads = 4;
        ArrayList<Runnable> tasks = new ArrayList();

        for (final String file : files) {
            tasks.add(new Runnable() {
                @Override
                public void run() {
                    run.processSentenceAndExtractProperNouns(file, "./multi_task");
                }
            });
        }

        EasyThread parallel = new EasyThread(numberOfThreads);
        parallel.addRunnableTasks(tasks);
        parallel.runTasks();

        processOutput(outputPath);
    }

    private void processOutput(String outputPath) {
        File dir = new File("./multi_task");
        File[] children = dir.listFiles();
        CustomFileReader read = new CustomFileReader();
        CustomFileWriter write = new CustomFileWriter();
        String output_file = outputPath + "/multi_thread.xml";
        String result;

        File dest_dir = new File(output_file);
        File parent = dest_dir.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }

        write.createFile(output_file);
        write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        write.println("<Files>");
        if (children != null) {
            for (File child : children) {
                if (!child.isDirectory()
                        && !child.getName().startsWith(".")
                        && !child.getName().contains("~")) {
                    result = read.getFileAsString(child.getAbsolutePath());
                    result = result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n", "");
                    write.println(result);
                }
            }
        }
        write.println("</Files>");
        write.closeFile();
    }

    public static void main(String... args) {
        DigitalReasoningParallel run = new DigitalReasoningParallel();

        String inputZip = "/home/administrator/Downloads/programming_test/NLP_test/nlp_data.zip";
        String outputExtract = "/home/administrator/Downloads/programming_test/NLP_test/nlp_data";
        String outputResult = "/home/administrator/Downloads/programming_test/NLP_test/output_files";

        run.parallelProcessZipFolder(inputZip, outputExtract, outputResult);
    }
}
