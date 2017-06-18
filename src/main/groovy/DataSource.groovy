package com.eagle.allen

import com.beust.jcommander.internal.Lists
import com.beust.jcommander.internal.Maps
import groovy.sql.Sql
import org.testng.collections.Sets

/**
 * Created by guobingwei on 17/4/19.
 */


def dataSource = Sql.newInstance('jdbc:mysql://10.4.232.238:3306/marketing', 'zcm', '123', 'com.mysql.cj.jdbc.Driver')
println dataSource.connection.catalog;


List userCouponList = dataSource.rows('select * from promotion_user_coupon where discount_amount <= 0 limit 5393')
println "list - " + userCouponList.size();

List<Integer> couponIdList = userCouponList.grep{it.discount_amount <= 0}.collect{it.coupon_id}
Map<Integer, Object> couponInfoMap  = getCouponInfo(couponIdList, dataSource)
if (couponInfoMap == null) {
    println '没有要查询的卡券信息，任务结束。。。'
    return
}

userCouponList.each {
    println "usercouponId " + it.id
    Object coupon = couponInfoMap.get(Long.valueOf(it.coupon_id))
    if (coupon != null) {
        int discountAmount = coupon.discount_amount
        dataSource.executeUpdate("update promotion_user_coupon set discount_amount = ${discountAmount} where id = ${it.id}")
        println "update user_conpon ok"
    }
}

// 查询卡券信息
Map<Integer, Object> getCouponInfo(List<Integer> couponIdList, def dataSource) {
    if (couponIdList.size() <= 0) {
        println '卡券ID为空' + couponIdList
        return null
    }
    couponIdList = Lists.newArrayList(Sets.newHashSet(couponIdList))

    String querySql = "select * from promotion_coupon where id in ( ${couponIdList.join(',')} )"
    println querySql
    List couponList = dataSource.rows(querySql)

    Map<Integer, Object> couponInfoMap = Maps.newHashMap()
    couponList.each {
        couponInfoMap.put(it.id, it)
    }
    return couponInfoMap
}
