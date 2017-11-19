package com.dev.boot.san;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;

import com.dev.boot.dao.ProcessDAO;
import com.dev.boot.entity.ProcessFeds;

public class Startup {

	
	@Autowired
	private static ProcessDAO dao;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		System.out.println("Starting Processing");
		for (int i = 0; i <= 10; i++) {
			/// Dynamically set the executor value
			System.out.println("I->" + i);
			
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

				System.out.println("Inside PP");
				for (ProcessFeds f : dao.getAllEmployees()) {
					try {
						Files.write(Paths.get("D:/TestDir/output.txt"), f.getA_file().getBytes(),
								StandardOpenOption.APPEND);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					System.out.println("Operation done for A_File");
				}

				/////////////////////////////////////////

				System.out.println("I'll run in a separate thread than the main thread.");
			}, executor);

		}
	}
}
