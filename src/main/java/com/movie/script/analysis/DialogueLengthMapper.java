package com.movie.script.analysis;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class DialogueLengthMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable wordCount = new IntWritable();
    private Text character = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Split the input line into character and dialogue
        String line = value.toString();
        int colonIndex = line.indexOf(":");
        if (colonIndex != -1) {
            String characterName = line.substring(0, colonIndex).trim(); // Extract character name
            String dialogue = line.substring(colonIndex + 1).trim(); // Extract dialogue
            // Calculate the length of the dialogue
            int length = dialogue.length();
            //set character dialogue length
            character.set(characterName);
            wordCount.set(length);
            //emit character and its corresponding dialogue length
            context.write(character, wordCount);
        }
    }
}
