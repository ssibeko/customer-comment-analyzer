package com.ikhokha.techcheck;

import java.util.HashMap;
import java.util.Map;

public class ShakerCommentAnalyzer implements CommentAnalyzerStrategy {
    @Override
    public int analyzeLine(String line)
    {
        String [] words = line.split("[, ?.@]+");
        int lineResults = 0;

        for(String word: words)
        {
            if(word != null && word.equalsIgnoreCase("shaker"))
            {
                lineResults++;

            }
        }
        return lineResults;
    }

}
