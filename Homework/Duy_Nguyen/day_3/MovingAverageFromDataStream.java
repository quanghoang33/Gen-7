package org.global.dev.day_3;

import java.util.ArrayDeque;
import java.util.Queue;

public class MovingAverageFromDataStream {
	
	static class MovingAverage {
		
		Queue<Integer> queue;
		long total;
		int size;
		
		public MovingAverage(int size) {
			this.size = size;
			queue = new ArrayDeque<>();
			total = 0L;
		}
		
		public double next(int val) {
			if (queue.size() < this.size) {
				queue.add(val);
				total += val;
			} else {
				int oldest = queue.poll();
				total -= oldest;
				total += val;
				queue.add(val);
			}
			return (double) total / queue.size();
		}
	}
	
}
