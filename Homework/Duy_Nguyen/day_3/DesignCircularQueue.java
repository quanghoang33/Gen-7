package org.global.dev.day_3;

public class DesignCircularQueue {
	
	static class MyCircularQueue {
		
		final int[] arr;
		int front = 0, rear = -1, len = 0;
		
		public MyCircularQueue(int k) {
			arr = new int[k];
		}
		
		// 1 2 3 4 5
		public boolean enQueue(int value) {
			if (!isFull()) {
				rear = (rear + 1) % arr.length;
				len++;
				arr[rear] = value;
				return true;
			}
			return false;
		}
		
		public boolean deQueue() {
			if (!isEmpty()) {
				front = (front + 1) % arr.length;
				len--;
				return true;
			}
			
			return false;
		}
		
		public int Front() {
			return isEmpty() ? -1 : arr[front];
		}
		
		public int Rear() {
			return isEmpty() ? -1 : arr[rear];
		}
		
		public boolean isEmpty() {
			return len == 0;
		}
		
		public boolean isFull() {
			return len == arr.length;
		}
	}
	
	
}
