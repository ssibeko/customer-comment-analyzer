package com.ikhokha.techcheck;

public class QuestionsAnalyzer implements CommentAnalyzerStrategy{
    @Override
    public int analyzeLine(String line) {
        int lineResult = 0;
        if (line.contains("?"))
        {
            lineResult++;
        }
        return lineResult;
    }
}
