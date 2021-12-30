package com.zjn.queue;

import java.util.Comparator;

/**
 * 二叉堆实现优先级队列
 */
public class PriorityQ<E> {


    private Object[] queue;

    private Comparator<? super E> comparator;

    private int size;

    public PriorityQ(Comparator comparator) {
        this.comparator = comparator;
    }


    /**
     * 添加元素
     * @param e e
     */
    public boolean offer(E e) {
        if (e == null) {
            return false;
        }
        int i = size;
        // 扩容，这里省略
//        if (i >= queue.length) {
//            grow(i + 1);
//        }

        size ++;
        if (i == 0) {
            queue[0] = e;
        } else {
            siftUp(i, e);
        }

        return true;
    }

    /**
     * 移除堆顶元素
     */
    public E poll() {
        if (size == 0) {
            return null;
        }
        int i = -- size;
        E o = (E) queue[0];
        E last = (E) queue[i];
        queue[i] = null;
        if (i > 0) {
            siftDown(0, last);
        }
        return o;
    }

    /**
     * 添加元素后将元素上移    大顶堆和小顶堆均适用
     * @param k 位置
     * @param e 新增元素
     */
    private void siftUp(int k, E e) {

        while (k > 0) {
            // 由于数组中的index不是从1开始的，所以这里计算父节点需要 -1
            int parentIndex = (k - 1) >> 1;
            Object parent = queue[parentIndex];
            if (comparator.compare(e, (E) parent) >= 0) {
                break;
            }
            queue[k] = parent;
            k = parentIndex;
        }
        queue[k] = e;

    }

    /**
     * 删除堆顶元素后元素下移
     * @param k index
     * @param e e
     */
    private void siftDown(int k, E e) {
        int i = size >> 1;
        while (k > 0 && k < i) {
            // 数组从0开始，需要位移后 +1
            int childIndex = k << 1 + 1;
            E child = (E) queue[childIndex];
            int rightIndex = childIndex + 1;
            // 找到左右子节点中最大或者最小的
            if (rightIndex < size && comparator.compare(child, (E) queue[rightIndex]) >= 0) {
                child = (E) queue[rightIndex];
                childIndex = rightIndex;
            }
            if (comparator.compare(child, e) >= 0) {
                break;
            }
            queue[k] = child;
            k = childIndex;
        }
        queue[k] = e;
    }






}
