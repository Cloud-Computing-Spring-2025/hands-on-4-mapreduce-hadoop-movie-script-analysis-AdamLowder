package com.movie.script.analysis;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class CharacterWordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int totalCount = 0;
        //sum counts for each word
        for (IntWritable val : values) {
            totalCount += val.get();
        }
        //output word and its total count
        context.write(key, new IntWritable(totalCount));
    }
}
