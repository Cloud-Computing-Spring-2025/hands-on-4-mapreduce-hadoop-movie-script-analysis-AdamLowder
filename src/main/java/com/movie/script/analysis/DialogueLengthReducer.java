package com.movie.script.analysis;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class DialogueLengthReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int totalLength = 0;
        //sum the dialogue lengths for the character
        for (IntWritable val : values) {
            totalLength += val.get();
        }
        //emit character and their total dialogue length
        context.write(key, new IntWritable(totalLength));
    }
}
