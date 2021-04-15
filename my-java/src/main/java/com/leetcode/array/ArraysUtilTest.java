package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.sxm.common.util.PrintUtil.printList;

/**
 * java arrays使用
 * @author zxl
 * @date 2020/10/27 16:15
 */
public class ArraysUtilTest {

    public static void main(String[] args) {
        returnArraysSort();
        returnArraysBinarySerch();
        returnArrayToString();
        returnArrayFill();
        returnArraysEquals();
        returnArraysAsList();
        returnArrayListCopyOf();
        重写比较规则();
        asList具体使用();
    }

    /**
     * Arrays.copyOf() :数组的拷贝
     */
    public static void returnArrayListCopyOf() {
        int[] numGroup = {25,12,68,78,33,55};
        int[] numGroup02=null;
        numGroup02=Arrays.copyOf(numGroup, 9);
        System.out.println(Arrays.toString(numGroup02));
    }

    /**
     * Arrays.asList() 查看数组中的特定值
     */
    public static void returnArraysAsList() {
        int[] numGroup = {25,12,68,78,33,55};
        Boolean judge=Arrays.asList(numGroup).contains(numGroup);
        System.out.println("查看数组中的特定值:"+judge);
    }

    /**
     * Arrays.asList() 判断两个数组是否相等
     */
    public static void returnArraysEquals() {
        int[] numGroup = {25,12,68,78,33,55};
        int[] numGroup02 = {35,12,68,78,33,55};
        Boolean judge=Arrays.equals(numGroup, numGroup02);
        System.out.println("判断两个数组是否相等:"+judge);
    }

    /**
     * Arrays.fill() 数组的填充
     */
    public static void returnArrayFill() {
        int[] numGroup = {25,12,68,78,33,55};
        Arrays.fill(numGroup, 13);
        System.out.println("数组的填充:"+Arrays.toString(numGroup));
    }

    /**
     * Arrays.toString() 打印数组内容
     */
    public static void returnArrayToString() {
        int[] numGroup = {25,12,68,78,33,55};
        System.out.println("输出数组内容为:"+ Arrays.toString(numGroup));
    }

    /**
     * Arrays.binarySerch() 二分查找，找到则定位元素下标
     */
    public static void returnArraysBinarySerch() {
        int[] numGroup = {12,25,33,55,68,78};
        int index=Arrays.binarySearch(numGroup, 12);
        System.out.println("该元素下标为:"+index);
    }

    /**
     * Arrays.sort():对数组进行排序(由小到大)
     */
    public static void returnArraysSort() {
        int[] numGroup = {25,12,68,78,33,55};
        Arrays.sort(numGroup);
        for (int i : numGroup) {
            System.out.print(i+"\t");
        }
        System.out.println();
    }

    public static void 重写比较规则(){
        int[][] intervals = {{1,4},{0,4},{5,3},{0,3}};
        //重写比较规则，若第一列不同，则根据第一列升序；如果第一列相同，则根据第二列升序。
        Arrays.sort(intervals, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for(int i = 0;i<intervals.length;i++){
            for(int j = 0;j<intervals[i].length;j++){
                System.out.print(intervals[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void asList具体使用(){

        System.out.println("asList具体使用");
        /*
        Arrays.asList 把数组转换为集合，不能使用集合相关方法，会抛出UnsupportedOperationException.
        该方法体现的是适配器模式，只是转换接口，后台数据仍是数组。
         */
        String[] str = {"you","know"};
        List strList = Arrays.asList(str);
        str[0] = "me";//strList.get(0)也会改变
        System.out.println(strList);
//        strList.add("what");//报错：ArrayIndexOutOfBoundsException
        System.out.println(strList);

        /*
        传递的数组必须是对象数组，而不是基本类型。
         */
        int[] myArray = {1, 2, 3};
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException

        //转换为list，可以使用list方法
        //1.最简单方法
        List list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        list.add("e");
        System.out.println(list);


        //2. java8 方法
        Integer [] myArray1 = { 1, 2, 3 };
        List myList1 = Arrays.stream(myArray1).collect(Collectors.toList());
        myList1.add(4);
        printList(myList1);
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        int [] myArray2 = { 1, 2, 3 };
        List myList2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        myList2.add(5);
        printList(myList2);


    }



}
