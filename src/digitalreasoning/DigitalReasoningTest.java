/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package digitalreasoning;

/**
 *
 * @author administrator
 */
public class DigitalReasoningTest {
    public static void main(String...args){
        DigitalReasoning dg = new DigitalReasoning();
        DigitalReasoningParallel dgp = new DigitalReasoningParallel();
        
        String input_1_2 = "/home/administrator/Downloads/programming_test/NLP_test/nlp_data.txt";
        String output_1_2_3 = "/home/administrator/Downloads/programming_test/NLP_test/output";
        
     
        String input_3 = "/home/administrator/Downloads/programming_test/NLP_test/nlp_data.zip";
        String output_3 = "/home/administrator/Downloads/programming_test/NLP_test/nlp_data";
        
        //Test 1
//        dg.processSentence(input_1_2, output_1_2_3);
        
        //Test 2
//        dg.processSentenceAndExtractProperNouns(input_1_2, output_1_2_3);
        
        //Test3
        dgp.parallelProcessZipFolder(input_3, output_3, output_1_2_3);
    }
}
