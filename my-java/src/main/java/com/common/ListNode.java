package com.common;

import java.math.BigDecimal;
import java.util.Objects;

public class ListNode {
  public int val;
  public ListNode next;
  public ListNode() {}
  public ListNode(int val) { this.val = val; }
  public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

  public static void main(String[] args) {
    
    String s = "2";
    as(s);
//    System.out.println(s);


    BigDecimal bigDecimal = new BigDecimal("0.00000000");
    if(Objects.equals(bigDecimal,BigDecimal.ZERO)){
      System.out.println(111);
    }

    String sql = "select \n" +
            "t3.tt_pm_part_reconcile_id source_bill_main_id,\n" +
            "t3.id source_bill_id,\n" +
            "t2.reconcile_no no,\n" +
            "t1.tt_pay_bill_id,\n" +
            "t1.id,\n" +
            "IFNULL(t3.reabte_tax_amount,0) + IFNULL(t3.rebate_price_without_tax,0) - ifnull(t4.price,0) price\n" +
            "from \n" +
            "(\n" +
            "\tselect t2.id,t2.tt_pay_bill_id,t1.dealer_code,t2.srcbill_no\n" +
            "\tfrom tt_pay_bill t1 \n" +
            "\tleft join tt_pay_bill_item t2 on t2.dr = 0 and t1.id = t2.tt_pay_bill_id\n" +
            "\twhere t1.id = #{id}\n" +
            ") t1 \n" +
            "left join tt_pm_part_reconcile t2 on t1.dealer_code = t2.dealer_code and t1.srcbill_no = t2.reconcile_no\n" +
            "left join tt_pm_part_rebate_item t3 on t2.dr = 0 and t2.id = t3.tt_pm_part_reconcile_id\n" +
            "left join (select source_bill_id,sum(IFNULL(advance_rec,0)) + sum(IFNULL(receive_amount,0)) price from tt_pay_bill_ycdtl where dr = 0 and org_id = #{org_id} group by source_bill_id) t4 on t3.id = t4.source_bill_id\n";

//    System.out.println(sql);
  }
  
  private static void as(String s){
    s = "1";
    
  }
  
  
  public static ListNode getListNode(String str){
    
    if(Objects.isNull(str)){
      return null;
    }

    String[] split = str.split(",");

    for (String s : split) {
      
      
      
    }

    return null;
  }
  
  // 3 2 1
  public ListNode get(int[] nums,int index,ListNode l1){
    
    if(index > nums.length){
      get(nums,index++,new ListNode(nums[index],l1));
    }
    
    return l1;    
  }
  
  
}
