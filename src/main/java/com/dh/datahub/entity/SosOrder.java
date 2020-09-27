package com.dh.datahub.entity;

import com.google.gson.Gson;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * SosOrdersnapshot Entity
 *
 * @author: Nevernow
 * @Date: 2020-09-08
 */
public class SosOrder extends Model<SosOrder> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单ID
     */
    @TableId
    protected String orderId;
    /**
     * 订单短号
     */
    protected String orderShortId;
    /**
     * 第3方平台订单ID
     */
    protected String thirdOrderId;
    /**
     * 第3方平台订单短号
     */
    protected Integer thirdShortId;
    /**
     * 平台订单号
     */
    protected String platformOrderId;
    /**
     * 红冲单号
     */
    protected String redflushOrderId;
    /**
     * 所属门店ID
     */
    protected String shopId;
    /**
     * 优惠后金额/客户实际支付金额
     */
    protected BigDecimal totalMoney;
    /**
     * 商家实际收到金额
     */
    protected BigDecimal needPayMoney;
    /**
     * 未支付金额
     */
    protected BigDecimal nopayMoney;
    /**
     * 退款金额
     */
    protected BigDecimal refundMoney;
    /**
     * 实际收到总金额
     */
    protected BigDecimal originalMoney;
    /**
     * 产品总金额
     */
    protected BigDecimal productTotalMoney;
    /**
     * 优惠调整
     */
    protected BigDecimal freeAdjust;
    /**
     * 优惠金额
     */
    protected BigDecimal freeMoney;
    /**
     * 第3方平台承担活动金额
     */
    protected BigDecimal thirdMoney;
    /**
     * 优惠说明
     */
    protected String freeNote;
    /**
     * 配送费
     */
    protected BigDecimal shippingFee;
    /**
     * 餐盒费
     */
    protected BigDecimal boxFee;
    /**
     * 餐盒总数量
     */
    protected BigDecimal boxNum;
    /**
     * 其他费用
     */
    protected BigDecimal otherFee;
    /**
     * 发票金额
     */
    protected BigDecimal invoiceMoney;
    /**
     * 计入业绩营业额的金额
     */
    protected BigDecimal goalMoney;
    /**
     * 特价产品总额
     */
    protected BigDecimal specialProductTotal;
    /**
     * 满减产品总额
     */
    protected BigDecimal inFreeTotal;
    /**
     * 是否红冲单
     */
    protected String isRedflushOrder;
    /**
     * 是否预订单
     */
    protected String isPreorder;
    /**
     * 订单状态
     */
    protected String orderState;
    /**
     * 订单支付状态
     */
    protected String payState;
    /**
     * 订单退款状态
     */
    protected String refundState;
    /**
     * 支付方式
     */
    protected String payWay;
    /**
     * 订单来源
     */
    protected String orderFrom;
    /**
     * 配送状态
     */
    protected String shippingState;
    /**
     * 下单用户ID
     */
    protected String orderOperator;
    /**
     * 结算用户ID
     */
    protected String settlementOperator;
    /**
     * 创建人设备IP
     */
    protected String createIp;
    /**
     * 操作人设备IP
     */
    protected String operatorIp;
    /**
     * OPENID
     */
    protected String userId;
    /**
     * 客户姓名
     */
    protected String userName;
    /**
     * 配送地址
     */
    protected String receiverAddress;
    /**
     * 配送经度
     */
    protected String receiverLongitude;
    /**
     * 配送维度
     */
    protected String receiverLatitude;
    /**
     * 联系电话
     */
    protected String receiverPhone;
    /**
     * 所属桌台
     */
    protected String tableCode;
    /**
     * 用餐人数
     */
    protected Integer dinerPerson;
    /**
     * 订单附加要求
     */
    protected String remark;
    /**
     * 订单所属日期
     */
    protected String createDate;
    /**
     * 订单创建时间
     */
    protected Date createTime;
    /**
     * 客户预定时间/要求就餐时间
     */
    protected Date diningTime;
    /**
     * 结算时间
     */
    protected Date settlementTime;
    /**
     * 制作完成时间
     */
    protected Date makeCompleteTime;
    /**
     * 订单分配时间
     */
    protected Date allotTime;
    /**
     * 配送完成时间
     */
    protected Date arrivalTime;
    /**
     * 订单完成时间
     */
    protected Date orderCompleteTime;
    /**
     * 操作时间
     */
    protected Date operatTime;
    /**
     * 催单次数
     */
    protected Integer urgeCount;
    /**
     * 使用标识
     */
    protected String inUse;
    /**
     * 订单标签
     */
    protected String orderTag;
    /**
     * 申请作废前的状态
     */
    protected String ext1;
    /**
     * dms骑手手机号码
     */
    protected String ext2;
    /**
     * 订单作废原因
     */
    protected String ext3;
    /**
     * 扩展字段4
     */
    protected String ext4;
    /**
     * 营业额时间
     */
    protected Date turnoverTime;
    /**
     * 用户Id
     */
    protected Integer cno;
    protected String ext5;
    protected String ext6;


    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderShortId() {
        return this.orderShortId;
    }

    public void setOrderShortId(String orderShortId) {
        this.orderShortId = orderShortId;
    }

    public String getThirdOrderId() {
        return this.thirdOrderId;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId;
    }

    public Integer getThirdShortId() {
        return this.thirdShortId;
    }

    public void setThirdShortId(Integer thirdShortId) {
        this.thirdShortId = thirdShortId;
    }

    public String getPlatformOrderId() {
        return this.platformOrderId;
    }

    public void setPlatformOrderId(String platformOrderId) {
        this.platformOrderId = platformOrderId;
    }

    public String getRedflushOrderId() {
        return this.redflushOrderId;
    }

    public void setRedflushOrderId(String redflushOrderId) {
        this.redflushOrderId = redflushOrderId;
    }

    public String getShopId() {
        return this.shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getNeedPayMoney() {
        return this.needPayMoney;
    }

    public void setNeedPayMoney(BigDecimal needPayMoney) {
        this.needPayMoney = needPayMoney;
    }

    public BigDecimal getNopayMoney() {
        return this.nopayMoney;
    }

    public void setNopayMoney(BigDecimal nopayMoney) {
        this.nopayMoney = nopayMoney;
    }

    public BigDecimal getRefundMoney() {
        return this.refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public BigDecimal getOriginalMoney() {
        return this.originalMoney;
    }

    public void setOriginalMoney(BigDecimal originalMoney) {
        this.originalMoney = originalMoney;
    }

    public BigDecimal getProductTotalMoney() {
        return this.productTotalMoney;
    }

    public void setProductTotalMoney(BigDecimal productTotalMoney) {
        this.productTotalMoney = productTotalMoney;
    }

    public BigDecimal getFreeAdjust() {
        return this.freeAdjust;
    }

    public void setFreeAdjust(BigDecimal freeAdjust) {
        this.freeAdjust = freeAdjust;
    }

    public BigDecimal getFreeMoney() {
        return this.freeMoney;
    }

    public void setFreeMoney(BigDecimal freeMoney) {
        this.freeMoney = freeMoney;
    }

    public BigDecimal getThirdMoney() {
        return this.thirdMoney;
    }

    public void setThirdMoney(BigDecimal thirdMoney) {
        this.thirdMoney = thirdMoney;
    }

    public String getFreeNote() {
        return this.freeNote;
    }

    public void setFreeNote(String freeNote) {
        this.freeNote = freeNote;
    }

    public BigDecimal getShippingFee() {
        return this.shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getBoxFee() {
        return this.boxFee;
    }

    public void setBoxFee(BigDecimal boxFee) {
        this.boxFee = boxFee;
    }

    public BigDecimal getBoxNum() {
        return this.boxNum;
    }

    public void setBoxNum(BigDecimal boxNum) {
        this.boxNum = boxNum;
    }

    public BigDecimal getOtherFee() {
        return this.otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getInvoiceMoney() {
        return this.invoiceMoney;
    }

    public void setInvoiceMoney(BigDecimal invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public BigDecimal getGoalMoney() {
        return this.goalMoney;
    }

    public void setGoalMoney(BigDecimal goalMoney) {
        this.goalMoney = goalMoney;
    }

    public BigDecimal getSpecialProductTotal() {
        return this.specialProductTotal;
    }

    public void setSpecialProductTotal(BigDecimal specialProductTotal) {
        this.specialProductTotal = specialProductTotal;
    }

    public BigDecimal getInFreeTotal() {
        return this.inFreeTotal;
    }

    public void setInFreeTotal(BigDecimal inFreeTotal) {
        this.inFreeTotal = inFreeTotal;
    }

    public String getIsRedflushOrder() {
        return this.isRedflushOrder;
    }

    public void setIsRedflushOrder(String isRedflushOrder) {
        this.isRedflushOrder = isRedflushOrder;
    }

    public String getIsPreorder() {
        return this.isPreorder;
    }

    public void setIsPreorder(String isPreorder) {
        this.isPreorder = isPreorder;
    }

    public String getOrderState() {
        return this.orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getPayState() {
        return this.payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getRefundState() {
        return this.refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    public String getPayWay() {
        return this.payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getOrderFrom() {
        return this.orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getShippingState() {
        return this.shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getOrderOperator() {
        return this.orderOperator;
    }

    public void setOrderOperator(String orderOperator) {
        this.orderOperator = orderOperator;
    }

    public String getSettlementOperator() {
        return this.settlementOperator;
    }

    public void setSettlementOperator(String settlementOperator) {
        this.settlementOperator = settlementOperator;
    }

    public String getCreateIp() {
        return this.createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getOperatorIp() {
        return this.operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReceiverAddress() {
        return this.receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverLongitude() {
        return this.receiverLongitude;
    }

    public void setReceiverLongitude(String receiverLongitude) {
        this.receiverLongitude = receiverLongitude;
    }

    public String getReceiverLatitude() {
        return this.receiverLatitude;
    }

    public void setReceiverLatitude(String receiverLatitude) {
        this.receiverLatitude = receiverLatitude;
    }

    public String getReceiverPhone() {
        return this.receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getTableCode() {
        return this.tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public Integer getDinerPerson() {
        return this.dinerPerson;
    }

    public void setDinerPerson(Integer dinerPerson) {
        this.dinerPerson = dinerPerson;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDiningTime() {
        return this.diningTime;
    }

    public void setDiningTime(Date diningTime) {
        this.diningTime = diningTime;
    }

    public Date getSettlementTime() {
        return this.settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }

    public Date getMakeCompleteTime() {
        return this.makeCompleteTime;
    }

    public void setMakeCompleteTime(Date makeCompleteTime) {
        this.makeCompleteTime = makeCompleteTime;
    }

    public Date getAllotTime() {
        return this.allotTime;
    }

    public void setAllotTime(Date allotTime) {
        this.allotTime = allotTime;
    }

    public Date getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getOrderCompleteTime() {
        return this.orderCompleteTime;
    }

    public void setOrderCompleteTime(Date orderCompleteTime) {
        this.orderCompleteTime = orderCompleteTime;
    }

    public Date getOperatTime() {
        return this.operatTime;
    }

    public void setOperatTime(Date operatTime) {
        this.operatTime = operatTime;
    }

    public Integer getUrgeCount() {
        return this.urgeCount;
    }

    public void setUrgeCount(Integer urgeCount) {
        this.urgeCount = urgeCount;
    }

    public String getInUse() {
        return this.inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    public String getOrderTag() {
        return this.orderTag;
    }

    public void setOrderTag(String orderTag) {
        this.orderTag = orderTag;
    }

    public String getExt1() {
        return this.ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return this.ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return this.ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return this.ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public Date getTurnoverTime() {
        return this.turnoverTime;
    }

    public void setTurnoverTime(Date turnoverTime) {
        this.turnoverTime = turnoverTime;
    }

    public Integer getCno() {
        return this.cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getExt5() {
        return this.ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    public String getExt6() {
        return this.ext6;
    }

    public void setExt6(String ext6) {
        this.ext6 = ext6;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }



}
