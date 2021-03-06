package com.upverasmusproject.ro.recsysrestapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseSearcher {

    public static List<Video> search(VideoRepository repo, String[] searchedTitles) {
        List<Video> allvideos = repo.findAll();
        List<Video> result = new ArrayList<Video>();
        for (Video it : allvideos) {
            for (String title : searchedTitles) {
                if (it.getTitle().equals(title)) {
                    result.add(it);
                    break;
                }

            }
        }

        return result;
    }

    public static String[] processPythonQueryResult(String input){
        String[] splitInput = input.split("\n");
        int i=0;
        for(var it:splitInput ){
            int id;
            double match;
            String title;
            id = Integer.parseInt(it.substring(it.indexOf('(')+1, it.indexOf(',')).trim());
            match = Double.parseDouble(it.substring(it.indexOf(',')+1, it.indexOf(')')).trim());
            title = it.substring(it.indexOf(')')+1).trim();
            splitInput[i++] = Normalizer.normalize(title,Normalizer.Form.NFD);//transforma literele cu accente in litere normale
            //TODO either normalize all chars or use UTF-8 everywhere
        }
        return splitInput;
    }

    public static  List<VideoResult> processPythonQueryResultToList(String input){
        String[] splitInput = input.split("\n");
        ArrayList<VideoResult> results = new ArrayList<>();
        int i=0;
        for(var it:splitInput ){
            int id;
            double match;
            String title;
            id = Integer.parseInt(it.substring(it.indexOf('(')+1, it.indexOf(',')).trim());
            match = Double.parseDouble(it.substring(it.indexOf(',')+1, it.indexOf(')')).trim());
            title = it.substring(it.indexOf(')')+1).trim();

//            VideoResult videoResult = new VideoResult(id,match,Normalizer.normalize(title,Normalizer.Form.NFD));
            VideoResult videoResult = new VideoResult(id,match,title);

            results.add(videoResult);

            //TODO either normalize all chars or use UTF-8 everywhere
        }
        return results;
    }



    public static <T> String writeListToJson(List<T> input) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out, input);
        return new String(out.toByteArray(), "UTF-8");

    }

}
