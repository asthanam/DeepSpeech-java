package io.github.Oleiva.DeepSpeech;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;

public class ExecuteShellComand {
    static String separator = " ";


    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();


        String ss = "/home/ivasoft/Downloads/deepspeech/ffmpeg" + "/file/2/";
        splitFile();
        splitFile2();

        File folder = new File("/home/ivasoft/Downloads/deepspeech/ffmpeg/file/2/");
        File[] listOfFiles = folder.listFiles();

        Arrays.sort(listOfFiles);



        for (int i = 0; i < listOfFiles.length; i++) {
            map.put(listOfFiles[i].getName(),recognizeFile(ss + listOfFiles[i].getName()));
            linkedHashSet.add(recognizeFile(ss + listOfFiles[i].getName()));
            System.out.println(":: "+ss + listOfFiles[i].getName());
            }



//        map.forEach((k,v)->{
//            System.out.println("Item : " + k + " Count : " + v);
//        });

        linkedHashSet.forEach((k)->{
            System.out.println("Item : " + k);
        });




    }




    public static void splitFile() {
        ExecuteShellComand obj = new ExecuteShellComand();

        String ddmpeg_file = "/home/ivasoft/Downloads/deepspeech/ffmpeg/ffmpeg ";
        String dd = "/home/ivasoft/Downloads/deepspeech/ffmpeg/10.mp3";
        String filename = "/file/";
        String outpath = "/home/ivasoft/Downloads/deepspeech/ffmpeg/out/";

        String command = ddmpeg_file+" -i "+dd+" -acodec pcm_s16le -ac 1 -ar 16000 "+"/home/ivasoft/Downloads/deepspeech/ffmpeg"+filename+ "out.wav";


        String output = obj.executeCommand(command);
    }



    public static void splitFile2() {
        ExecuteShellComand obj = new ExecuteShellComand();

        String ddmpeg_file = "/home/ivasoft/Downloads/deepspeech/ffmpeg/ffmpeg ";
        String dd = "/home/ivasoft/Downloads/deepspeech/ffmpeg/file/out.wav";
        String filename = "/file/";
        String outpath = "/home/ivasoft/Downloads/deepspeech/ffmpeg/out/";


        String command = ddmpeg_file + separator + "-i " + dd + separator + " -f segment -segment_time 10  -c copy /home/ivasoft/Downloads/deepspeech/ffmpeg" + filename+"/2/" + "%03d.wav -acodec pcm_s16le -ac 1 -ar 16000";

        System.out.println("command : " + command);
        String output = obj.executeCommand(command);

        System.out.println(output);

    }



    public static String recognizeFile(String file_path) {
        Map<String,String> map = new HashMap<>();

        ExecuteShellComand obj = new ExecuteShellComand();

        String pb_path = "/home/ivasoft/Downloads/deepspeech/models/output_graph.pb";

        String alpha = "/home/ivasoft/Downloads/deepspeech/models/alphabet.txt";
        String model = "/home/ivasoft/Downloads/deepspeech/models/lm.binary";

        String command = "deepspeech " + pb_path + separator + file_path + separator + alpha + separator + model + separator + "models/trie";
        System.out.println("command : " + command);
        String output = obj.executeCommand(command);

        System.out.println(output);
        map.put(file_path,output);

        return output;
    }


    private String executeCommand(String command) {
        System.out.println("command "+command);

        StringBuffer output = new StringBuffer();
        System.out.println("output" + output);

        Process p;
        try {
              p = Runtime.getRuntime().exec(new String[]{"bash","-c",command});
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

}

