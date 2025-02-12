package com.movie.script.analysis;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

public class UniqueWordsMapper extends Mapper<Object, Text, Text, Text> {

    private Text character = new Text();
    private Text word = new Text();

    //looked up guides for hash set
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        //split input line into character and dialogue
        String line = value.toString();
        int colonIndex = line.indexOf(":");
        if (colonIndex != -1) {
            String characterName = line.substring(0, colonIndex).trim();
            String dialogue = line.substring(colonIndex + 1).trim();
            //tokenize the dialogue into words
            HashSet<String> uniqueWords = new HashSet<>();
            StringTokenizer tokenizer = new StringTokenizer(dialogue);
            while (tokenizer.hasMoreTokens()) {
                String wordStr = tokenizer.nextToken().toLowerCase();
                uniqueWords.add(wordStr);
            }
            //emit character name as key and list of unique words as value
            character.set(characterName);
            word.set(String.join(", ", uniqueWords));
            context.write(character, word);
        }
    }
}
