package com.ikhokha.techcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) throws Exception, InterruptedException {

		Map<String, Integer> totalResults = new HashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		List<Callable<Map<String, Integer>>> tasks;
		tasks = new ArrayList<>();
		for (File commentFile : commentFiles) {
			tasks.add(new CommentAnalyzer(commentFile));
		}

		// create threads for each task
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Map<String, Integer>>> results = executor.invokeAll(tasks);
		executor.shutdown();

		System.out.println("RESULTS\n=======");
		for (Future<Map<String, Integer>> future : results) {
			addReportResults(future.get(), totalResults);;
		}
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			target.put(entry.getKey(), entry.getValue());
		}
		
	}

}
