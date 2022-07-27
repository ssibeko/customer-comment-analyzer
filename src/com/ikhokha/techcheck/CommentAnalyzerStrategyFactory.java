package com.ikhokha.techcheck;

public class CommentAnalyzerStrategyFactory {
    private CommentAnalyzerStrategy commentAnalyzerStrategy;
    public CommentAnalyzerStrategy getCommentAnalyzerStrategy (Metrics metric)
    {
        switch (metric)
        {
            case MOVER_MENTIONS:
            {
                commentAnalyzerStrategy = new MoverCommentAnalyzer();
                break;
            }
            case SHAKER_MENTIONS:
            {
                commentAnalyzerStrategy = new ShakerCommentAnalyzer();
                break;
            }
            case SHORTER_THAN_15:
            {
                commentAnalyzerStrategy = new ShorterThan15();
                break;
            }
            case SPAM:
            {
                commentAnalyzerStrategy = new SpamAnalyzer();
                break;
            }
            case QUESTIONS:
            {
                commentAnalyzerStrategy = new QuestionsAnalyzer();
                break;
            }
            default:
            {
                return null;
            }
        }
        return commentAnalyzerStrategy;
    }
}
