package com.ikhokha.techcheck;

public class SpamAnalyzer implements CommentAnalyzerStrategy{
    @Override
    public int analyzeLine(String line)
    {

        int lineResults = 0;
        if (line.contains("http"))
        {
             lineResults++;
        }
        return lineResults;

    }
}
