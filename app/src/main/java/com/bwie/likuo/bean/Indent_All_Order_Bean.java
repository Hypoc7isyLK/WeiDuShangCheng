package com.bwie.likuo.bean;

import java.util.List;

/**
 * date:2019/1/11
 * author:李阔(淡意衬优柔)
 * function:
 */
public class Indent_All_Order_Bean {

    private String expressCompName;
    private String expressSn;
    private String orderId;
    private int orderStatus;
    private int payAmount;
    private int payMethod;
    private int userId;
    private List<DetailListBean> detailList;

    public String getExpressCompName() {
        return expressCompName;
    }

    public void setExpressCompName(String expressCompName) {
        this.expressCompName = expressCompName;
    }

    public String getExpressSn() {
        return expressSn;
    }

    public void setExpressSn(String expressSn) {
        this.expressSn = expressSn;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<DetailListBean> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DetailListBean> detailList) {
        this.detailList = detailList;
    }

    public static class DetailListBean {
        /**
         * commentStatus : 1
         * commodityCount : 2
         * commodityId : 29
         * commodityName : 秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲
         * commodityPic : http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/ddx/5/5.jpg
         * commodityPrice : 278
         * orderDetailId : 75
         */

        private int commentStatus;
        private int commodityCount;
        private int commodityId;
        private String commodityName;
        private String commodityPic;
        private String commodityPrice;
        private int orderDetailId;

        public int getCommentStatus() {
            return commentStatus;
        }

        public void setCommentStatus(int commentStatus) {
            this.commentStatus = commentStatus;
        }

        public int getCommodityCount() {
            return commodityCount;
        }

        public void setCommodityCount(int commodityCount) {
            this.commodityCount = commodityCount;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getCommodityPic() {
            return commodityPic;
        }

        public void setCommodityPic(String commodityPic) {
            this.commodityPic = commodityPic;
        }

        public String getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(String commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public int getOrderDetailId() {
            return orderDetailId;
        }

        public void setOrderDetailId(int orderDetailId) {
            this.orderDetailId = orderDetailId;
        }
    }
}
