<template>
    <div>
        <div style="width: 100%;margin:0 0 20px 0;" align="center">
            <a-button type="primary" v-on:click="openSosOrderEdit()">新增</a-button>
            <a-input  style="width: 35%;" v-model="queryParam[PK]"></a-input>
            <a-button type="default" v-on:click="getList()">查询</a-button>
        </div>
        <a-table :rowKey="PK" :columns="tableRow.filter(columns=>!columns.tableHidden)" :dataSource="tableData.records" :scroll="{ x: 1500 }" bordered
                 :pagination="{pageSize:page.pageSize,total:tableData.total,showSizeChanger:true,showQuickJumper:true}"
                 @change="pageChange">
            <div slot="action" slot-scope="rowData">
                <a v-on:click="openSosOrderEdit(rowData)">修改</a>
                <a v-on:click="deleteSosOrder(rowData[PK])"> 删除</a>
            </div>
        </a-table>
        <a-modal :visible="editDialogVisible" :maskClosable="false" :title='editTitle' okText='提交'
                 @ok="submitForm"
                 cancelText="取消" @cancel="closeSosOrderEdit">
            <a-form layout='horizontal' :form="editSosOrderForm">
                <div v-bind:key="prop.dataIndex" v-for="prop in tableRow">
                    <a-form-item :label="prop.title" :hidden="prop.editHidden" :label-col="{ span: 6 }" :wrapper-col="{ span: 15 }">
                        <a-input v-decorator="[prop.dataIndex,prop.options]"/>
                    </a-form-item>
                </div>
            </a-form>
        </a-modal>
    </div>
</template>
<script>
    import axios from 'axios';
    import _ from 'lodash-es';

    export default {
        beforeCreate() {
            this.editSosOrderForm = this.$form.createForm(this);
        },
        props: [],
        data() {
            return {
                editTitle:"编辑",
                isInsert: false,
                editDialogVisible: false,
                queryParam: {},
                PK: "orderId",                                                                 
                tableRow: [
                        {title: "订单ID",tableHidden:false,editHidden:false,dataIndex:"orderId",scopedSlots: {customRender: "orderId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单短号",tableHidden:false,editHidden:false,dataIndex:"orderShortId",scopedSlots: {customRender: "orderShortId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "第3方平台订单ID",tableHidden:false,editHidden:false,dataIndex:"thirdOrderId",scopedSlots: {customRender: "thirdOrderId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "第3方平台订单短号",tableHidden:false,editHidden:false,dataIndex:"thirdShortId",scopedSlots: {customRender: "thirdShortId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "平台订单号",tableHidden:false,editHidden:false,dataIndex:"platformOrderId",scopedSlots: {customRender: "platformOrderId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "红冲单号",tableHidden:false,editHidden:false,dataIndex:"redflushOrderId",scopedSlots: {customRender: "redflushOrderId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "所属门店ID",tableHidden:false,editHidden:false,dataIndex:"shopId",scopedSlots: {customRender: "shopId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "优惠后金额/客户实际支付金额",tableHidden:false,editHidden:false,dataIndex:"totalMoney",scopedSlots: {customRender: "totalMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "商家实际收到金额",tableHidden:false,editHidden:false,dataIndex:"needPayMoney",scopedSlots: {customRender: "needPayMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "未支付金额",tableHidden:false,editHidden:false,dataIndex:"nopayMoney",scopedSlots: {customRender: "nopayMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "退款金额",tableHidden:false,editHidden:false,dataIndex:"refundMoney",scopedSlots: {customRender: "refundMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "实际收到总金额",tableHidden:false,editHidden:false,dataIndex:"originalMoney",scopedSlots: {customRender: "originalMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "产品总金额",tableHidden:false,editHidden:false,dataIndex:"productTotalMoney",scopedSlots: {customRender: "productTotalMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "优惠调整",tableHidden:false,editHidden:false,dataIndex:"freeAdjust",scopedSlots: {customRender: "freeAdjust"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "优惠金额",tableHidden:false,editHidden:false,dataIndex:"freeMoney",scopedSlots: {customRender: "freeMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "第3方平台承担活动金额",tableHidden:false,editHidden:false,dataIndex:"thirdMoney",scopedSlots: {customRender: "thirdMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "优惠说明",tableHidden:false,editHidden:false,dataIndex:"freeNote",scopedSlots: {customRender: "freeNote"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "配送费",tableHidden:false,editHidden:false,dataIndex:"shippingFee",scopedSlots: {customRender: "shippingFee"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "餐盒费",tableHidden:false,editHidden:false,dataIndex:"boxFee",scopedSlots: {customRender: "boxFee"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "餐盒总数量",tableHidden:false,editHidden:false,dataIndex:"boxNum",scopedSlots: {customRender: "boxNum"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "其他费用",tableHidden:false,editHidden:false,dataIndex:"otherFee",scopedSlots: {customRender: "otherFee"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "发票金额",tableHidden:false,editHidden:false,dataIndex:"invoiceMoney",scopedSlots: {customRender: "invoiceMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "计入业绩营业额的金额",tableHidden:false,editHidden:false,dataIndex:"goalMoney",scopedSlots: {customRender: "goalMoney"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "特价产品总额",tableHidden:false,editHidden:false,dataIndex:"specialProductTotal",scopedSlots: {customRender: "specialProductTotal"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "满减产品总额",tableHidden:false,editHidden:false,dataIndex:"inFreeTotal",scopedSlots: {customRender: "inFreeTotal"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "是否红冲单",tableHidden:false,editHidden:false,dataIndex:"isRedflushOrder",scopedSlots: {customRender: "isRedflushOrder"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "是否预订单",tableHidden:false,editHidden:false,dataIndex:"isPreorder",scopedSlots: {customRender: "isPreorder"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单状态",tableHidden:false,editHidden:false,dataIndex:"orderState",scopedSlots: {customRender: "orderState"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单支付状态",tableHidden:false,editHidden:false,dataIndex:"payState",scopedSlots: {customRender: "payState"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单退款状态",tableHidden:false,editHidden:false,dataIndex:"refundState",scopedSlots: {customRender: "refundState"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "支付方式",tableHidden:false,editHidden:false,dataIndex:"payWay",scopedSlots: {customRender: "payWay"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单来源",tableHidden:false,editHidden:false,dataIndex:"orderFrom",scopedSlots: {customRender: "orderFrom"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "配送状态",tableHidden:false,editHidden:false,dataIndex:"shippingState",scopedSlots: {customRender: "shippingState"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "下单用户ID",tableHidden:false,editHidden:false,dataIndex:"orderOperator",scopedSlots: {customRender: "orderOperator"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "结算用户ID",tableHidden:false,editHidden:false,dataIndex:"settlementOperator",scopedSlots: {customRender: "settlementOperator"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "创建人设备IP",tableHidden:false,editHidden:false,dataIndex:"createIp",scopedSlots: {customRender: "createIp"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "操作人设备IP",tableHidden:false,editHidden:false,dataIndex:"operatorIp",scopedSlots: {customRender: "operatorIp"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "OPENID",tableHidden:false,editHidden:false,dataIndex:"userId",scopedSlots: {customRender: "userId"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "客户姓名",tableHidden:false,editHidden:false,dataIndex:"userName",scopedSlots: {customRender: "userName"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "配送地址",tableHidden:false,editHidden:false,dataIndex:"receiverAddress",scopedSlots: {customRender: "receiverAddress"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "配送经度",tableHidden:false,editHidden:false,dataIndex:"receiverLongitude",scopedSlots: {customRender: "receiverLongitude"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "配送维度",tableHidden:false,editHidden:false,dataIndex:"receiverLatitude",scopedSlots: {customRender: "receiverLatitude"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "联系电话",tableHidden:false,editHidden:false,dataIndex:"receiverPhone",scopedSlots: {customRender: "receiverPhone"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "所属桌台",tableHidden:false,editHidden:false,dataIndex:"tableCode",scopedSlots: {customRender: "tableCode"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "用餐人数",tableHidden:false,editHidden:false,dataIndex:"dinerPerson",scopedSlots: {customRender: "dinerPerson"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单附加要求",tableHidden:false,editHidden:false,dataIndex:"remark",scopedSlots: {customRender: "remark"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单所属日期",tableHidden:false,editHidden:false,dataIndex:"createDate",scopedSlots: {customRender: "createDate"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单创建时间",tableHidden:false,editHidden:false,dataIndex:"createTime",scopedSlots: {customRender: "createTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "客户预定时间/要求就餐时间",tableHidden:false,editHidden:false,dataIndex:"diningTime",scopedSlots: {customRender: "diningTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "结算时间",tableHidden:false,editHidden:false,dataIndex:"settlementTime",scopedSlots: {customRender: "settlementTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "制作完成时间",tableHidden:false,editHidden:false,dataIndex:"makeCompleteTime",scopedSlots: {customRender: "makeCompleteTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单分配时间",tableHidden:false,editHidden:false,dataIndex:"allotTime",scopedSlots: {customRender: "allotTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "配送完成时间",tableHidden:false,editHidden:false,dataIndex:"arrivalTime",scopedSlots: {customRender: "arrivalTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单完成时间",tableHidden:false,editHidden:false,dataIndex:"orderCompleteTime",scopedSlots: {customRender: "orderCompleteTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "操作时间",tableHidden:false,editHidden:false,dataIndex:"operatTime",scopedSlots: {customRender: "operatTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "催单次数",tableHidden:false,editHidden:false,dataIndex:"urgeCount",scopedSlots: {customRender: "urgeCount"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "使用标识",tableHidden:false,editHidden:false,dataIndex:"inUse",scopedSlots: {customRender: "inUse"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单标签",tableHidden:false,editHidden:false,dataIndex:"orderTag",scopedSlots: {customRender: "orderTag"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "申请作废前的状态",tableHidden:false,editHidden:false,dataIndex:"ext1",scopedSlots: {customRender: "ext1"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "dms骑手手机号码",tableHidden:false,editHidden:false,dataIndex:"ext2",scopedSlots: {customRender: "ext2"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "订单作废原因",tableHidden:false,editHidden:false,dataIndex:"ext3",scopedSlots: {customRender: "ext3"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "扩展字段4",tableHidden:false,editHidden:false,dataIndex:"ext4",scopedSlots: {customRender: "ext4"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "营业额时间",tableHidden:false,editHidden:false,dataIndex:"turnoverTime",scopedSlots: {customRender: "turnoverTime"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "用户Id",tableHidden:false,editHidden:false,dataIndex:"cno",scopedSlots: {customRender: "cno"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "ext5",tableHidden:false,editHidden:false,dataIndex:"ext5",scopedSlots: {customRender: "ext5"}, options:{validateTrigger: 'blur',rules: []}},
                        {title: "ext6",tableHidden:false,editHidden:false,dataIndex:"ext6",scopedSlots: {customRender: "ext6"}, options:{validateTrigger: 'blur',rules: []}},
                    {title: 'Action',key: 'operation',fixed: 'right',width: 150, editHidden:true, scopedSlots: {customRender: 'action'}}
                ],
                tableData: {total:0},
                page:{current:1,pageSize:10},
                host:"http://localhost:9092",
                api: {
                    getList: "/datahub/apiSosOrder/getSosOrder",
                    delete: "/datahub/apiSosOrder/deleteSosOrder",
                    update: "/datahub/apiSosOrder/updateSosOrder",
                    insert: "/datahub/apiSosOrder/insertSosOrder",
                }
            }

        },
        mounted() {
            this.getList();
        },
        methods: {
            pageChange(page) {
                this.page = page;
                this.getList();
            },
            getList(){
                let param = {};
                for (var key in this.queryParam) {
                    if (_.isEmpty(this.queryParam[key])) {
                        this.queryParam = {};
                        break;
                    }
                    param[key.replace(/([A-Z])/g, "_$1").toUpperCase()] = this.queryParam[key];
                }

                axios.post(this.host + this.api.getList + "?current=" + this.page.current + "&size=" + this.page.pageSize, param).then(result => {
                    this.tableData = result.data.result;
                });
            },
            insertSosOrder(param){
                axios.post(this.host + this.api.insert,param).then(result => {
                    if(result.data.errCode=="0"){
                        this.$notification['success']({
                            message: "新增成功"
                        });
                        this.closeSosOrderEdit();
                        this.getList();
                    }else{
                        this.$notification['error']({
                            message: result.data.meg
                        });
                    }
                });
            },
            updateSosOrder(param){
                axios.post(this.host + this.api.update,param).then(result => {
                    if (result.data.result) {
                        this.$notification['success']({
                            message: "修改成功"
                        });
                        this.closeSosOrderEdit();
                        this.getList();
                        return;
                    }
                    this.$notification['error']({
                        message: result.data.meg
                    });
                });
            },
            deleteSosOrder(param){
                let dom = this;
                this.$confirm({
                    title: '确定要删除该条目吗?',
                    content: '此操作不可恢复，请确认执行!',
                    onOk() {
                        axios.get(dom.host + dom.api.delete + "?id=" + param).then(result => {
                            dom.$notification['success']({
                                message: "删除成功"
                            });
                            dom.getList();
                        })
                    },
                    onCancel() {
                    },
                });
            },
            openSosOrderEdit(initParam) {
                this.editDialogVisible = true;
                if (!initParam) {
                    this.isInsert = true;
                    this.editTitle='新增';
                } else {
                    this.isInsert = false;
                    this.editTitle='编辑';
                }
                setTimeout(() => this.editSosOrderForm.setFieldsValue(initParam), 0);
            },
            closeSosOrderEdit() {
                this.editDialogVisible = false;
                this.editSosOrderForm.resetFields();
            },
            submitForm() {
                this.editSosOrderForm.validateFields(async (err, values) => {
                    if (!err) {
                        console.log(this.isInsert)
                        if (this.isInsert) {
                            this.insertSosOrder(values);
                        } else {
                            this.updateSosOrder(values);
                        }
                    }
                });
            }
        },
    }
</script>
<style scoped>
    .ruleTemplate {
        background: #fff;
        margin: auto;
        margin-bottom: 50px;
        width: 100%;
        height:100%;
        padding: 10px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    }
</style>