package com.movie.script.analysis;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class CharacterWordMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private Text characterWord = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        //split input line into character and dialogue
        String line = value.toString();
        int colonIndex = line.indexOf(":");
        if (colonIndex != -1) {
            String characterName = line.substring(0, colonIndex).trim();
            String dialogue = line.substring(colonIndex + 1).trim();
            //tokenize dialogue into words
            StringTokenizer tokenizer = new StringTokenizer(dialogue);
            while (tokenizer.hasMoreTokens()) {
                String wordStr = tokenizer.nextToken().toLowerCase();
                characterWord.set(characterName + ": " + wordStr);
                //set word as the key (we will count all occurrences of the word across all characters)
                word.set(wordStr);
                //emit word with count 1
                context.write(word, one);
            }
        }
    }
}
