package cz.cuni.mff.miyazakk.toolkit;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Arrays {
    private static final int THRESHOLD = 1000;

    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();
        return pool.invoke(new MaxTask(arr, 0, arr.length - 1));
    }

    private static class MaxTask extends RecursiveTask<Integer> {
        private final int[] arr;
        private final int left;
        private final int right;

        public MaxTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected Integer compute() {
            if (right - left < THRESHOLD) {
                int max = Integer.MIN_VALUE;
                for (int i = left; i <= right; i++) {
                    if (arr[i] > max) {
                        max = arr[i];
                    }
                }
                return max;
            }
            int mid = left + (right - left) / 2;

            MaxTask leftTask = new MaxTask(arr, left, mid);
            MaxTask rightTask = new MaxTask(arr, mid + 1, right);

            leftTask.fork();
            
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();

            return Math.max(leftResult, rightResult);
        }
    }
}

