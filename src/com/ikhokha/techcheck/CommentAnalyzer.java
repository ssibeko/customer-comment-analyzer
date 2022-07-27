package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

public class CommentAnalyzer implements Callable<Map<String, Integer>> {
	
	private File file;
	private final CommentAnalyzerStrategyFactory commentAnalyzerStrategyfactory = new CommentAnalyzerStrategyFactory();
	
	public CommentAnalyzer(File file) {
		this.file = file;
		System.out.println("Create Task to get content from " + file);

	}
	@Override
	public Map<String, Integer> call() throws Exception{
		
		Map<String, Integer> resultsMap = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null)
			{

				List<Metrics> metricsList = Arrays.asList(Metrics.values());
				for(Metrics metric:metricsList)
				{
					CommentAnalyzerStrategy  commentAnalyzerStrategy =  commentAnalyzerStrategyfactory.getCommentAnalyzerStrategy(metric);
					if(commentAnalyzerStrategy!= null)
					{
						if(commentAnalyzerStrategy.analyzeLine(line) > 0)
							incOccurrence(resultsMap, String.valueOf(metric));

					}

				}

			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		
		return resultsMap;
		
	}

	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, String key) {

		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}

}
