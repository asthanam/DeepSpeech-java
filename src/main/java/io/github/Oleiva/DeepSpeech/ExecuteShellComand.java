package io.github.Oleiva.DeepSpeech;



import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteShellComand {

    public static void main(String[] args) {

        ExecuteShellComand obj = new ExecuteShellComand();

//        String command = "/home/ivasoft/Downloads/deepspeech/ffmpeg/ffmpeg -i /home/ivasoft/Downloads/deepspeech/ffmpeg/10.mp3 -f segment -segment_time 3 -c copy /home/ivasoft/Downloads/deepspeech/ffmpeg/out%03d.wav -acodec pcm_s16le -ac 1 -ar 16000 >file.txt";




        String  command ="deepspeech /home/ivasoft/Downloads/deepspeech/models/output_graph.pb /home/ivasoft/Downloads/deepspeech/out.wav /home/ivasoft/Downloads/deepspeech/models/alphabet.txt /home/ivasoft/Downloads/deepspeech/models/lm.binary models/trie ";
        String output = obj.executeCommand(command);

        System.out.println(output);

    }

    private String executeCommand(String command) {

        StringBuffer output = new StringBuffer();
        System.out.println("output"+output);

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

}

