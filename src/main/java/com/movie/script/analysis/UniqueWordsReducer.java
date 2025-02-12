package com.movie.script.analysis;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;

public class UniqueWordsReducer extends Reducer<Text, Text, Text, Text> {

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //collect unique words for the character
        StringBuilder uniqueWords = new StringBuilder();
        for (Text val : values) {
            if (uniqueWords.length() > 0) {
                uniqueWords.append(", ");
            }
            uniqueWords.append(val.toString());
        }
        //output character and their unique words
        context.write(key, new Text("[" + uniqueWords.toString() + "]"));
    }
}
