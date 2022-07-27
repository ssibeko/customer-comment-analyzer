package com.ikhokha.techcheck;

import java.util.HashMap;
import java.util.Map;

public class ShorterThan15 implements CommentAnalyzerStrategy {
        @Override
        public int analyzeLine(String line)
        {
            int lineResult = 0;
            if (line.length() < 15)
            {
                lineResult++;
            }
            return lineResult;
        }
}
