package cz.cuni.mff.miyazakk.toolkit;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Arrays {
    private static final int THRESHOLD = 1000;

    public static void paraMergeSort(int[] arr){
        if (arr == null || arr.length <= 1){
            return;
        }
        
        ForkJoinPool pool = ForkJoinPool.commonPool();

        pool.invoke(new MergeSort(arr, 0, arr.length - 1));
    }

    private static class MergeSort extends RecursiveAction{
        private final int[] arr;
        private final int left;
        private final int right;

        public MergeSort(int[] arr, int left, int right){
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute(){
            if (right - left < THRESHOLD){
                java.util.Arrays.sort(arr, left, right +1);
                return;
            }

            int mid = left + (right - left) /2;

            MergeSort leftSort = new MergeSort(arr, left, mid);
            MergeSort rightSort = new MergeSort(arr, mid + 1, right);

            invokeAll(leftSort, rightSort);

            merge(arr, left, mid, right);
        }

        private void merge(int[] arr, int left, int mid, int right){
            int m = mid - left + 1;
            int n = right - mid;

            int[] leftArr = new int[m];
            int[] rightArr = new int[n];

            System.arraycopy(arr, left, leftArr, 0, m);
            System.arraycopy(arr, mid + 1, rightArr, 0, n);

            int i = 0; 
            int j = 0;
            int k = left;
            while (i < m && j < n){
                if (leftArr[i] <= rightArr[j]){
                    arr[k] = leftArr[i];
                    i++;
                } else {
                    arr[k] = rightArr[j];
                    j++;
                }
                k++;
            }

            while (i < m) {
                arr[k] = leftArr[i];
                i++;
                k++;
            }
            while (j < n) {
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }

    }
}

